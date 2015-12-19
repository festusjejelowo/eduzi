package com.ocularminds.eduzi;

import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.Locale;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

import java.net.URI;
import java.net.URISyntaxException;

import static spark.Spark.*;
import spark.template.freemarker.FreeMarkerEngine;
import spark.ModelAndView;
import spark.Spark;
import spark.Request;
import spark.template.freemarker.FreeMarkerEngine;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import static j2html.TagCreator.*;

import com.ocularminds.eduzi.vao.Feed;
import com.ocularminds.eduzi.util.FeedCache;
import com.ocularminds.eduzi.vao.Place;

import com.heroku.sdk.jdbc.DatabaseUrl;
import java.util.concurrent.TimeUnit;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

public class WebService {

	FeedCache cache;
	Gson gson;

	public WebService(){

		cache = FeedCache.instance();
		gson = new Gson();
	}

  public static void main(String[] args) {

       WebService ws = new WebService();
       ws.listen();
  }

  public void broadcast(Object o){

	  String destUri = "ws://localhost:"+System.getenv("PORT")+"/chat/";
	  WebSocketClient client = new WebSocketClient();
	  WebSocketHandler socket = new WebSocketHandler(o);
	  try {

		  client.start();
		  URI echoUri = new URI(destUri);
		  ClientUpgradeRequest request = new ClientUpgradeRequest();
		  client.connect(socket, echoUri, request);
		  System.out.printf("Connecting to : %s%n", echoUri);
		  socket.awaitClose(5, TimeUnit.SECONDS);

	  } catch (Throwable t) {
		  t.printStackTrace();
	  } finally {
		  try {
			  client.stop();
		  } catch (Exception e) {
			  e.printStackTrace();
		 }
	}
  }

  private void listen(){

	 //Spark.port(9998);
	 Spark.port(Integer.valueOf(System.getenv("PORT")));
	 Spark.staticFileLocation("/public");
	 Spark.webSocket("/chat", WebSocketService.class);

	 Spark.get("/", (request, response) -> {

		 Map<String, Object> attributes = new HashMap<>();
		 attributes.put("message", "Hello World!");
		 return new ModelAndView(attributes, "index.ftl");
        }, new FreeMarkerEngine());

     Spark.get("/chat", (request, response) -> {
		 Map<String, Object> attributes = new HashMap<>();
		 attributes.put("message", "Hello World!");
		 return new ModelAndView(attributes, "chat.ftl");
     }, new FreeMarkerEngine());

	Spark.get("/ping", (request, response) -> {return new Fault("00","Ok ready.");},new JsonFront());

	Spark.before("/protected/*", (request, response) -> {halt(401, "Go Away!"); });

    Spark.get("/api/locate/:longitude/:latitude/:range", (request, response) -> {

		   String longitude = request.params(":longitude");
		   String latitude = request.params(":latitude");
		   String distance = request.params(":range");

		   List<String> all = new ArrayList();
		   all.add("Festus, 200");
		   all.add("Tolu all");
		   response.status(200);
		   response.type("application/json");
		  return all;

	  },new JsonFront());

	  /*https://maps.gstatic.com/mapfiles/openhand_8_8.cur
	  https://f.vimeocdn.com/p/2.10.5/css/player.css
	  https://f.vimeocdn.com/p/2.10.5/js/player.js
	  */

	 Spark.get("/api/feeds", (request, response) -> {

		List<Feed> feeds = cache.findAll();//new ArrayList<SearchObjectCache>();
		if(feeds.size() == 0){

			java.util.Date dd = new java.util.Date();
			String ds = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm",Locale.US).format(dd);
			feeds.add(new Feed(new Long(1),"Any eduzi.movement",dd,"Plan your movement. ","TODO","i","","#"));
		}
		   response.status(200);
		   response.type("application/json");
		  return feeds;

	  },new JsonFront());

	  Spark.post("/api/location", (request, response) -> {

	  		Fault fault = new Fault("00","Success");
	  		Map<String,String> m = gson.fromJson(request.body(),Map.class);
	  		String userid = m.get("userid");
	  		String longitude = m.get("lon");
	  		String latitude = m.get("lat");

	  		System.out.println("updating user "+userid+" location longitude:"+longitude+",latitude:"+latitude);

	  		String name = null;//"Oshodi Lagos Apapa Ikorodu Oworonsoki Ojota Ikeja Agege Ogba Somolu OjuElegba Berger Ojodu ";//"Oshodi";
		    String type = null;//"neighborhood|political|routes|point_of_interest";
		    String distance = "500";
		    String s = SearchPlace.search(latitude,longitude,distance,type,name);
		    List<Place> places = SearchPlace.parsePlaces(latitude,longitude,s);
		    System.out.println("total Places found - "+places.size());

            for(int x = 0; x < places.size(); x++){

				String d = "";
				String w = "";
				double r = places.get(x).getDistance();
				if(r == 0.00) continue;

				if(places.get(x).getDistance() < 1){

					d = String.format("%.2fm",r *1000);
					w = String.format("%2dmins walk", SearchPlace.nextArrival(r,SearchPlace.WALK_MODE));

				}else{

					double t = SearchPlace.nextArrival(r,SearchPlace.DRIVE_MODE);
					d = String.format("%.2fkm", r);
					if(t < 1){
						w = String.format("%.2fmins drive",t*60);
					}else{
						w = String.format("%.2fhrs drive", t);
					}

				}

				System.out.println("You are "+String.format("%.4f",r)+" "+d+" from "+places.get(x).getName()+" "+w);
			}
	  	   return fault;

	  },new JsonFront());

	  Spark.post("/loader/push",(request, response) -> {

			Type listType = new TypeToken<ArrayList<SearchObjectCache>>() {}.getType();
			List<SearchObjectCache> data = gson.fromJson(request.body(), listType);

			System.out.println("push receives data size "+data.size());
			Fault fault = new Fault("00","Success");

			if(data == null || data.size() == 0) fault = new Fault("51","No data uploaded");
			boolean isOperationSuccessful = cache.load(data);
			if(!isOperationSuccessful) fault = new Fault("10","Service not available. Try again");

			fault.setData((Object)data);
			fault.setGroup("feed");
			broadcast(fault);
			fault.setData(null);

			return fault;

	 },new JsonFront());

     Spark.get("/throwexception", (request, response) -> {
	     throw new Exception();
	 });

	 exception(Exception.class, (e, request, response) -> {
	     response.status(404);
	     response.body("Resource not found");
	 });

	  /*Spark.post("/login", (request, response) -> {
			return ConnectionSession.login(request, response);
		} , new FreeMarkerEngine(configuration));

	Spark.get("/logout", (request, response) -> {
		ConnectionSession.logout(request, response);
		response.redirect("/");
		return null;
	});
	Spark.post("/obliterate/purge", (request, response) -> {
			return DepotControl.postObliteratePurge(request, response);
		} , JsonUtil.json());

		Spark.post("/deploy/:type", (request, response) -> {
			return DeployControl.postDeploy(request, response);
		});

		Spark.get("/submit/:type", (request, response) -> {
			return DeployControl.getSubmit(request, response);
		} , new FreeMarkerEngine(configuration));

		Spark.post("/submit/:type", (request, response) -> {
			return DeployControl.postSubmit(request, response);
	});

    /*
	 get("/users/:id", (req, res) -> {

	   String id = req.params(":id");
	   User user = userService.getUser(id);
	   if (user != null) {

		 response.status(200);
	     response.type("application/json");
		 return dataToJson(all);
	   }

	   response.status(400);
	   response.type("application/json");
	   return dataToJson(new String("{error:\"No user with id "+id+" found\"}"));

	 }, json());

	 //updating user
	/* Spark.put("/users/:id", (req, res) -> userService.updateUser(
		 req.params(":id"),
		 req.queryParams("name"),
		 req.queryParams("email")
	  ), json());


	  Spark.get("/db", (req, res) -> {
		Connection connection = null;
		Map<String, Object> attributes = new HashMap<>();
		try {

		  connection = DatabaseUrl.extract().getConnection();
		  Statement stmt = connection.createStatement();
		  stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
		  stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
		  ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

		  ArrayList<String> output = new ArrayList<String>();
		  while (rs.next()) {
			output.add( "Read from DB: " + rs.getTimestamp("tick"));
		  }

		  attributes.put("results", output);
		  return new ModelAndView(attributes, "db.ftl");

		} catch (Exception e) {
			 attributes.put("message", "There was an error: " + e);
			return new ModelAndView(attributes, "error.ftl");
		} finally {
		  if (connection != null) try{connection.close();} catch(SQLException e){}
		}
	  }, new FreeMarkerEngine());*/

  }
}
