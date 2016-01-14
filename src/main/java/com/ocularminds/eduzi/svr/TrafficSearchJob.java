package com.ocularminds.eduzi.svr;

 import org.quartz.Job;
 import org.quartz.JobExecutionContext;
 import org.quartz.JobExecutionException;

 import java.util.List;
 import java.util.Set;
 import java.util.HashSet;

 import com.ocularminds.eduzi.Analyser;
 import com.ocularminds.eduzi.SearchAgent;
 import com.ocularminds.eduzi.SearchBroker;
 import com.ocularminds.eduzi.SearchObjectLoader;
 import com.ocularminds.eduzi.SearchObjectCache;

public class TrafficSearchJob implements Job{

	// tweeter@gidi traffik
	Set<String> routes = new HashSet<String>();
	final String DATASOURCE = "https://www.facebook.com "+
	"https://twitter.com/Gidi_Traffic "+
	"http://www.tsaboin.com "+
	"http://www.beattraffik.com/ "+
	"http://www.nairaland.com/recent ";//accidents, flood and armed robberies on the site.

	final String attributes = "Hijack,Flood,Accident,Slow,Blocked,Moving,Accident,Hold up,Downpour";

   public void execute(JobExecutionContext context) throws JobExecutionException{

        List<SearchObjectCache> data = null;
        try{

			data = SearchBroker.search(DATASOURCE,attributes,SearchAgent.TRAFFIC_SEARCH_AGENT);
			if(data.size() > 0){
				Analyser.reduce(data);
		   }
	   }catch(Exception e){
		   e.printStackTrace();
	   }
   }
}