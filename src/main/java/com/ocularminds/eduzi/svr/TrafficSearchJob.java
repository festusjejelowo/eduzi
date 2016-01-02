package com.ocularminds.eduzi.svr;

 import org.quartz.Job;
 import org.quartz.JobExecutionContext;
 import org.quartz.JobExecutionException;

 import java.util.List;

 import com.ocularminds.eduzi.SearchBroker;
 import com.ocularminds.eduzi.SearchObjectLoader;
 import com.ocularminds.eduzi.SearchObjectCache;

public class TrafficSearchJob implements Job{

	// tweeter@gidi traffik
	final String DATASOURCE = "http://www.facebook.com "+
	"http://www.tsaboin.com "+
	"http://www.beattraffik.com/ "+
	"http://www.nairaland.com/recent ";//accidents, flood and armed robberies on the site.

	final String attributes = "Hijack,Flood,Accident,Slow,Blocked,Moving,Accident,Hold up,Downpour";

   public void execute(JobExecutionContext context) throws JobExecutionException{

        List<SearchObjectCache> data = SearchBroker.search(DATASOURCE,attributes);
	   	if(data.size() > 0){
	   		SearchObjectLoader.upload(data);
	   }
   }
}