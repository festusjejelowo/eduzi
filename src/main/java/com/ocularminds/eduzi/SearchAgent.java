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
		  userAgent.sendHEAD(source);
		  String date = userAgent.response.getHeader("last-modified");
		  if(date == null) date = userAgent.response.getHeader("date");
		  userAgent.visit(source);

		  SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss Z",Locale.US);

		  for(String text: Arrays.asList(attributes.split(","))){

			 //find every element who's tagname is div,span,p,li,a,h3,b,i.
			Elements elements = userAgent.doc.findEvery("<li|div|span|p|abbr|a|h3|b|i|>"+text+"|"+text.toLowerCase());
			for(Element ol : elements){

				String d = Long.toString(System.currentTimeMillis());
				int id = Integer.parseInt(d.substring(d.length()-6,d.length()));
			  //removes white spaces .replaceAll("\\s+", " ").
			  if(ol.innerText().trim().length() > 7) {

				  if(ol.innerText().trim().replaceAll("\\s+", " ").contains("Traffic Alert")||
				  ol.innerText().trim().replaceAll("\\s+", " ").contains("trafficalert")||
				  ol.innerText().trim().replaceAll("\\s+", " ").contains("trafficwatch")||
				  ol.innerText().trim().replaceAll("\\s+", " ").contains("Traffic Cam")||
				  ol.innerText().trim().replaceAll("\\s+", " ").contains("Traffic Update")){
					  //skip
				  }else{

					  Date dd = (date != null)?sdf.parse(date):new Date();
					  System.out.println(text+","+source+","+ol.innerText().trim().replaceAll("\\s+", " ").replaceAll(",", " ")+","+date);
					  data.add(new SearchObjectCache(id,source,text,ol.innerText().trim().replaceAll("\\s+", " ").replaceAll(",", " "),dd));
				  }
			  }
			 }
		  }

		}catch(Exception e){ //if an HTTP/connection error occurs, handle JauntException.
			logger.log(Level.SEVERE, "Error crawling datasource", e);
		}
	}
}
