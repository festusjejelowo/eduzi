package com.ocularminds.eduzi;

import java.sql.*;
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
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

import java.net.URI;
import java.net.URISyntaxException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import static j2html.TagCreator.*;

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
import com.ocularminds.eduzi.dao.Authorizer;
import com.ocularminds.eduzi.dao.PostWriter;
import com.ocularminds.eduzi.dao.DbFactory;
import java.util.concurrent.TimeUnit;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;

public class SocketIOService {

	FeedCache cache;
	Gson gson;
	Authorizer authorizer;
	PostWriter writer;

	final File upload = new File("upload");
	private static final String USER_SESSION_ID = "USER_SESSION_ID";

	public SocketIOService(){

		cache = FeedCache.instance();
		gson = new Gson();
		authorizer = Authorizer.instance();
		writer = PostWriter.instance();
		DbFactory.instance();

	    if (!upload.exists() && !upload.mkdirs()) {
		   throw new RuntimeException("Failed to create directory " + upload.getAbsolutePath());
	    }
		//uploadConfig = new MultipartConfigElement(upload.getAbsolutePath(),1024*1024*5, 1024*1024*5*5, 1024*1024);
	}

  public static void main(String[] args){

       SocketIOService ws = new SocketIOService();
       ws.socketIO();
  }

  private void socketIO() {

	  Configuration config = new Configuration();
	  config.setHostname("127.0.0.1");
	  config.setPort(7851);
	  config.setMaxFramePayloadLength(1024 * 1024);
	  config.setMaxHttpContentLength(1024 * 1024);

	  //config.setCloseTimeout(30);
	 /* config.setAuthorizationListener(new AuthorizationListener() {
		  @Override
		  public boolean isAuthorized(HandshakeData data) {
			  String username = data.getSingleUrlParam("username");
			  String password = data.getSingleUrlParam("password");
			  if(true){// username and password correct
				 return true;
			  }else{
				 return false;
			 }
		  }
      });*/
     final SocketIOServer server = new SocketIOServer(config);

     server.addConnectListener(new ConnectListener(){

		 @Override
		 public void onConnect(SocketIOClient client){

			 String msg = "You are connected!";
             System.out.println("Client "+client+" connected.");
			 client.sendEvent("connect",msg);
			 System.out.println("Message sent to client "+client.toString());
		 }
	 });

	  server.addEventListener("connect", String.class, new DataListener<String>() {
		  @Override
		  public void onData(SocketIOClient client, String data, AckRequest ackRequest) {
            //String k = user + KeyFactory.keyToString(key);
            String msg = "You are connected!";
            System.out.println(msg);
            server.getBroadcastOperations().sendEvent("connect",msg);
		  }
	  });

	  server.addEventListener("message", JsonObject.class, new DataListener<JsonObject>() {
		  @Override
		  public void onData(SocketIOClient client, JsonObject data, AckRequest ackRequest) {
			  //String k = user + KeyFactory.keyToString(key);
			  System.out.println("received message "+data);
			  server.getBroadcastOperations().sendEvent("message",data);
		  }
	  });

	  server.addEventListener("message", String.class, new DataListener<String>() {
		  @Override
		  public void onData(SocketIOClient client, String data, AckRequest ackRequest) {
			  //String k = user + KeyFactory.keyToString(key);
			  System.out.println("received message "+data);
			  server.getBroadcastOperations().sendEvent("message",data);
		  }
	  });

	  server.addEventListener("ipaddr", String.class, new DataListener<String>() {
	  		  @Override
	  		  public void onData(SocketIOClient socketIOClient, String data, AckRequest ackRequest) throws Exception {
	  			  System.out.println("string emit received");
	  			  String str = "This is String Hander";
	  			  server.getBroadcastOperations().sendEvent("string",str);
	  		  }
	  });

      server.addEventListener("string", String.class, new DataListener<String>() {
		  @Override
		  public void onData(SocketIOClient socketIOClient, String data, AckRequest ackRequest) throws Exception {
			  System.out.println("string emit received");
			  String str = "This is String Hander";
			  server.getBroadcastOperations().sendEvent("string",str);
		  }
	  });

	  server.addEventListener("binary", byte[].class, new DataListener<byte[]>() {
		  @Override
		  public void onData(SocketIOClient socketIOClient, byte[] buffer, AckRequest ackRequest) throws Exception {
			  System.out.println("binary emit received");
			  String str = "This is Binary Hander";
			  byte[] bt = str.getBytes();
			  server.getBroadcastOperations().sendEvent("string", str);
		  }
      });
      server.start();
  }
}
