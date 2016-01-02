package com.ocularminds.eduzi.svr;

 import org.quartz.Job;
 import org.quartz.JobExecutionContext;
 import org.quartz.JobExecutionException;

 import java.util.List;

 import com.ocularminds.eduzi.SearchBroker;
 import com.ocularminds.eduzi.SearchObjectLoader;
 import com.ocularminds.eduzi.SearchObjectCache;

public class DataAnalysisJob implements Job{

	final String attributes = "Robbery,Hijack,Flood,Disaster,Rape,Crush,Suspect,Fraud,"+
					"Illegal,Forge,crime,kill,Attack,Traffic,Slow,Blocked,"+
					"Moving,Accident,Murder,Hold up,Downpour,Fire";

   public void execute(JobExecutionContext context) throws JobExecutionException{



   }
}