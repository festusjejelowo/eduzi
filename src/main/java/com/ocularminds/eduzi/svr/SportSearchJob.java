package com.ocularminds.eduzi.svr;

 import org.quartz.Job;
 import org.quartz.JobExecutionContext;
 import org.quartz.JobExecutionException;

 import java.util.List;

 import com.ocularminds.eduzi.SearchBroker;
 import com.ocularminds.eduzi.SearchObjectLoader;
 import com.ocularminds.eduzi.SearchObjectCache;

public class SportSearchJob implements Job{

	// tweeter@gidi traffik
	final String DATASOURCE = "http://www.vanguardngr.com/"+new java.text.SimpleDateFormat("yyy/MM/dd").format(new java.util.Date())+"/ "+
	"http://www.completesportsnigeria.com/ "+
	"http://www.newsnow.co.uk/h/World+News/Africa/Nigeria/Sport "+
	"http://www.latestnigeriannews.com/latest-news/sports/ "+
	"http://www.allnigeriasoccer.com/ "+
	"http://www.fourfourtwo.com/nigeria ";

	final String attributes = "Match,game,win,lost,away,home,goalles draw,triumph,defeat";

   public void execute(JobExecutionContext context) throws JobExecutionException{

        List<SearchObjectCache> data = SearchBroker.search(DATASOURCE,attributes);
		if(data.size() > 0){
			SearchObjectLoader.upload(data);
	   }
   }
}