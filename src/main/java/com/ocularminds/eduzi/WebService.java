package com.ocularminds.eduzi;

import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

import java.net.URI;
import java.net.URISyntaxException;

import static spark.Spark.*;
import spark.template.freemarker.FreeMarkerEngine;
import spark.ModelAndView;
import spark.Spark;
import spark.Request;
import spark.template.freemarker.FreeMarkerEngine;

import com.ocularminds.eduzi.util.FeedCache;

import com.heroku.sdk.jdbc.DatabaseUrl;
import com.google.gson.Gson;

public class WebService {

	FeedCache cache;

	public WebService(){

		cache = FeedCache.instance();
	}

  private static boolean shouldReturnHtml(Request request) {

	  String accept = request.headers("Accept");
	  return accept != null && accept.contains("text/html");
  }

  public static void main(String[] args) {

       WebService ws = new WebService();
       ws.listen();
  }

  private void listen(){

	 Gson gson = new Gson();
	 port(Integer.valueOf(System.getenv("PORT")));
	 staticFileLocation("/public");

	 get("/", (request, response) -> {
	             Map<String, Object> attributes = new HashMap<>();
	             attributes.put("message", "Hello World!");
	             return new ModelAndView(attributes, "index.ftl");
        }, new FreeMarkerEngine());

	Spark.get("/ping", (req, res) -> {return new Fault("00","Ok ready.");},gson::toJson);

	/* Spark.before("/protected/*", (request, response) -> {
	     // ... check if authenticated
	     halt(401, "Go Away!");
     });*/

	 /*
     get("/throwexception", (request, response) -> {
	     throw new NotFoundException();
	 });

	 exception(NotFoundException.class, (e, request, response) -> {
	     response.status(404);
	     response.body("Resource not found");
	 });*/

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

	try {
		  em.getTransaction().begin();
		  // Operations that modify the database should come here.
		  em.getTransaction().commit();
	  }
	  finally {
		  if (em.getTransaction().isActive())
			  em.getTransaction().rollback();
	}
	*

	  Spark.get("/", (request, response) -> {
			  Map<String, Object> attributes = new HashMap<>();
			  attributes.put("message", "Hello World!");
			  return new ModelAndView(attributes, "index.ftl");
		  }, new FreeMarkerEngine());

	  Spark.get("/ping", (request, response) -> {
				Map<String, Object> attributes = new HashMap<>();
				attributes.put("message", "Service is up and running!");
				return new ModelAndView(attributes, "index.ftl");
			}, new FreeMarkerEngine());

	Spark.get("/api/locate/:longitude/:latitude/:range", (request, response) -> {

	   String longitude = request.params(":longitude");
	   String latitude = request.params(":latitude");
	   String distance = request.params(":range");

	   List<String> all = new ArrayList();
	   all.add("Festus, 200");
	   all.add("Tolu all");
	   response.status(200);
	   response.type("application/json");
		 return dataToJson(all);
	 }
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

	  Spark.post("/load",(req, res) -> {

			request.body();
			System.out.println("push receives data size "+data.size());
			String json = "{error:\"Success\"}";
			if(data == null || data.size() == 0) json = "{error:\"No data uploaded\"}";
			boolean isOperationSuccessful = cache.load(data);
			if(!isOperationSuccessful) json = "{error:\"Service not available. Try again\"");

		},json);

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
