package com.ocularminds.eduzi;
/**
 * SearchAgent.java
 * Purpose: Multi-URI Unstructured data search
 *
 * This is Lagos " is a medium for you to share your real life experiences on the Lagos roads as a pedestrian,
 * a commuter that makes use of public transport,
 * a passenger in private vehicle,
 * a vehicle owner or driver - anyone behind the steering wheel of a vehicle.
*
*	We have heard numerous stories of car-jacking, gun hidden underneath sausage carton robbery,
*	catch-in-the-fly yellow bus robbery, acid threat in hold-up, motorcycle robbery, etc.
*	If you have been a victim of any incidence mentioned above or you have experienced a life threatening incidence in traffic,
*	kindly let us share in your experience as link to saving the lives of many other people and learn from it.
*
*	My Traffic Links (MTL) is an online medium that seeks to offer you safe and easy drive
*	through the traffic to your various destinations within the Lagos metropolis by providing traffic updates,
*	transport education, re-orientation and trainings that are geared towards the transformation of our driving
*	cultures so that we can have very efficient transportation system whereby every road user shows love
*	and respect to other road users with a heart for obedience of all traffic regulations.
*
*	Traffic Chief Nigeria is an online crowd-sourced traffic visualization and notification app.
*	The app gathers all the traffic tweets shared by everyday people like you and me on Twitter and does cool stuff with it!
 *
 * @author Festus B Jejelowo
 * @version 1.0 23/11/2015
 */
import java.util.Arrays;
import java.util.List;
import java.util.Date;
import java.util.Locale;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import com.jaunt.UserAgent;
import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.JauntException;
import com.jaunt.util.HandlerForBinary;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.ocularminds.eduzi.util.DateUtil;

public class SearchAgent implements Callable<List<SearchObjectCache>> {

	private String source;
	private String attributes;
	private List<SearchObjectCache> data;
	private static Logger logger = Logger.getLogger("SearchBroker");

	public SearchAgent(String source,String attributes) {

		this.source = source;
		this.attributes = attributes;
	}

	@Override
	public List<SearchObjectCache> call() {

		logger.info("Searc Agent - "+source+" running");
		data = new ArrayList<SearchObjectCache>();//initializing for 10million records
		try {

			search();
		    Thread.sleep(10);

		} catch (InterruptedException ex) {
			logger.log(Level.SEVERE, null, ex);
		}

		return data;
	}

	/** checks the validity of the URI prefix*/
	private boolean isValidPrefix(String uri){
		return (isFromFile(uri) || isFromRemote(uri) || isFromClasspath(uri));
	}

	private boolean isValidSurfix(String surfix){
		return (surfix.equals(".json") || surfix.equals(".data"));
	}

	private boolean isFromFile(String uri){
		return (uri.indexOf("file://") > -1);
	}

	private boolean isFromRemote(String uri){
		return (uri.indexOf("http://") > -1);
	}

	private boolean isFromClasspath(String uri){
		return (uri.indexOf("classpath:attributes/") > -1);
	}

	/*private void searchTweet(){

		//search term will be the tweeter handle #giditraffic
		String url = "http://search.twitter.com/search.json?q=" +encodeURIComponent(that.searchTerm());
		OAuthService service = new ServiceBuilder()
		service.provider(TwitterApi.class)
		service.apiKey("your_api_key")
		service.apiSecret("your_api_secret")
	    service.build();
		$.ajax({
		  dataType: "jsonp",
		  url: url,
		  success: function (response) {

			if (response.results.length > 0) {
			  // create an array to hold the results
			  var tweetViewModels = [];

			  // add the new items
			  $.each(response.results, function () {
				var tweet = new TweetViewModel(this);
				tweetViewModels.push(tweet);
			  });

			  // navigate to the results view model
			  application.navigateTo(new SearchResultsViewModel(tweetViewModels));
			  addSearchTermToRecentSearches();
			} else {
			  that.userMessage("There were no matches for the given search term");
			}

			that.isSearching(false);
		  }
       });
	}*/
	/**
	*Parses the tweet date to give a more readable format.
	*
	private String parseDate(String date) {

	    long diff = (new Date() - new Date(date)) / 1000;

	    if (diff < 60) {
	      return Long.toString(diff) + " seconds ago";
	    }

	    diff = diff / 60;
	    if (diff < 60) {
	      return Long.toString(diff) + " minutes ago";
	    }

	    diff = diff / 60;
	    if (diff < 10) {
	      return Long.toString(diff) + " hours ago";
	    }

	    diff = diff / 24;
	    return Long.toString(diff) + " days ago";
    }*/

	private void search(){

		try{

		  if(!isValidPrefix(source)) return;
		  logger.info("Searching "+source+"\n");

          HandlerForBinary handlerForBinary = new HandlerForBinary();
		  UserAgent userAgent = new UserAgent();

		  if(source.contains("https")){

			  System.out.println("setting http proxy for secure site "+source);
			  System.setProperty("https.proxyHost", "199.16.156.38");
              System.setProperty("https.proxyPort", "443");
		  }
		  userAgent.sendHEAD(source);
		  String date = userAgent.response.getHeader("last-modified");
		  if(date == null) date = userAgent.response.getHeader("date");
		  userAgent.visit(source);

		  String datePattern = "EEE, dd MMM yyyy hh:mm:ss z";

		  for(String text: Arrays.asList(attributes.split(","))){

			 //find every element who's tagname is div,span,p,li,a,h3,b,i.
			Elements elements = null;
			if(source.contains("beattraffik")){
			     elements = userAgent.doc.findEach("<li>");//+text+"|"+text.toLowerCase());
			 }else{
				 elements = userAgent.doc.findEvery("<li|div|span|p|abbr|a|h3|b|i|>"+text+"|"+text.toLowerCase());
			 }
			for(Element ol : elements){

				String d = Long.toString(System.currentTimeMillis());
				int id = Integer.parseInt(d.substring(d.length()-6,d.length()));
				String report = ol.innerText().trim().replaceAll("\\s+", " ").replaceAll(",", " ");

			  if(report.length() > 7) {

				  if(report.equalsIgnoreCase("Traffic Alert") ||
				      report.equalsIgnoreCase("trafficalert") ||
					  report.equalsIgnoreCase("trafficwatch") ||
					  report.equalsIgnoreCase("Traffic Cam")  ||
					  report.equalsIgnoreCase("trafficlord")  ||
					  report.equalsIgnoreCase("Accident")     ||
					  report.equalsIgnoreCase("Fire Incident")||
					  report.contains("Traffic Alert")||
					  report.equalsIgnoreCase("Traffic Update")){
					   continue;
				  }else{

					  if(source.contains("beattraffik") && (!report.toLowerCase().contains(text.toLowerCase()))){
						  continue;
					  }

					  LocalDateTime dd = (date != null)?DateUtil.parseWithTime(date,datePattern):LocalDateTime.now();
					  String ds = dd.toString();

					  if(report.contains("Official BeatTraffik Report")){

						  //22nd Dec 2015 04:22PM DD yyyy hh:mma
						  report =  report.replaceAll("  Lagos  Nigeria","");
						  ds = report.substring(report.lastIndexOf("Official BeatTraffik Report")+"Official BeatTraffik Report".length()+1,report.length());
						  ds = ds.replaceAll("nd","").replaceAll("st","")
						         .replaceAll("th","").replaceAll("rd","")
						         .replaceAll(" GMT","")
						         .replaceAll("Sun, ","")
						         .replaceAll("Mon, ","")
						         .replaceAll("Tue, ","")
						         .replaceAll("Wed, ","")
						         .replaceAll("Thu, ","")
						         .replaceAll("Fri, ","")
						         .replaceAll("Sat, ","");

						  dd = DateUtil.parseWithTime(ds,"dd MMM yyyy hh:mma");
						  report = report.substring(0,report.indexOf("Official BeatTraffik Report"));
					  }

					  System.out.println(text+","+source+","+report+","+ds);
					  data.add(new SearchObjectCache(id,source,text,report,dd));
				  }
			  }
			 }
		  }

		}catch(Exception e){ //if an HTTP/connection error occurs, handle JauntException.
			logger.log(Level.SEVERE, "Error crawling datasource", e);
		}
	}
}
