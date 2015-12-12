package com.ocularminds.eduzi;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A simple SearchObjectLoader loader which reads a file from disk and sends entries to the webservice
 *
 * @author Jejelowo Festus
 */
public class SearchObjectLoader {

    public static void upload(List<SearchObjectCache> data){

		Client client = ClientBuilder.newClient();
		WebTarget query = client.target("http://localhost:8080/query");
        WebTarget loader = client.target("http://localhost:8080/loader");

        System.out.println("sending "+data.size()+" to server");
		Response response = loader.path("push").request()
			.accept(MediaType.APPLICATION_JSON)
			.post(Entity.entity(data,MediaType.APPLICATION_JSON),Response.class);

		String output = response.readEntity(String.class);
		System.out.println("Server response .... \n");
		System.out.println(response);

     }
  }