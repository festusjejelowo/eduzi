package com.ocularminds.eduzi;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;

import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

import java.net.URI;
import java.net.URISyntaxException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import com.ocularminds.eduzi.vao.Feed;
import com.ocularminds.eduzi.util.FeedCache;
import com.ocularminds.eduzi.util.DateUtil;
import com.ocularminds.eduzi.util.FileUtil;
import com.ocularminds.eduzi.util.ImageUtil;
import com.ocularminds.eduzi.util.PasswordUtil;
import com.ocularminds.eduzi.vao.Place;
import com.ocularminds.eduzi.vao.User;
import com.ocularminds.eduzi.vao.Comment;
import com.ocularminds.eduzi.vao.Message;
import com.ocularminds.eduzi.vao.Signal;
import com.ocularminds.eduzi.vao.VideoMessage;
import com.ocularminds.eduzi.dao.Authorizer;
import com.ocularminds.eduzi.dao.PostWriter;
import com.ocularminds.eduzi.dao.DbFactory;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.namespace.Namespace;

public class GeoProcessor{

   public List<String> process(String latitude,String longitude,String distance,String type,String name){

    String s = SearchPlace.search(latitude,longitude,distance,type,name);
    List<String> records = new ArrayList<String>();
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

		records.add(String.format("%s - %s %s",places.get(x).getName(),d,w));
	}

	return records;
  }

}