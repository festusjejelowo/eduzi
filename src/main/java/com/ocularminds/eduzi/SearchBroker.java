package com.ocularminds.eduzi;
/**
 * SearchBroker.java
 * Purpose: Class shared by the Property crawler API
 * to retrieve property key-value pairs from any URI.
 * Supported URI includes http://,file:/// classpath:resources/.
 *
 * These resources are also validated to meet expected property files
 * which MUST be any of .properties or .json after which a DefaultProperty class
 * is returned.
 *
 * code snipet:<br>
 * <pre>
 * String[] uri = {"classpath:resources/jdbc.properties",
 *                 "file:///temp/system.properties"};
 * List<SearchObjectCache> data = broker.search(url,attributes);
 *
 * @author Jejelowo Festus
 * @version 1.0 18/11/2015
 */
 import java.util.concurrent.Callable;
 import java.util.concurrent.ExecutionException;
 import java.util.concurrent.ExecutorService;
 import java.util.concurrent.Executors;
 import java.util.concurrent.Future;
 import java.util.concurrent.TimeUnit;
 import java.util.logging.Level;
 import java.util.logging.Logger;
 import java.util.List;
 import java.util.Date;
 import java.util.Properties;
 import java.util.Arrays;
 import java.util.ArrayList;
 import com.jaunt.*;

 import org.quartz.JobDetail;
 import org.quartz.Scheduler;
 import org.quartz.SchedulerFactory;
 import org.quartz.Trigger;
 import org.quartz.JobKey;
 import static org.quartz.TriggerBuilder.*;
 import static org.quartz.JobBuilder.*;
 import static org.quartz.TriggerBuilder.*;
 import static org.quartz.SimpleScheduleBuilder.*;
 import org.quartz.impl.StdSchedulerFactory;

 import com.ocularminds.eduzi.svr.SportSearchJob;
 import com.ocularminds.eduzi.svr.TrafficSearchJob;
 import com.ocularminds.eduzi.svr.EventSearchJob;
 import com.ocularminds.eduzi.svr.DataAnalysisJob;

public class SearchBroker{

	public static final int URI_MALFORMED = 0;
	public static final int URI_NOT_EXIST = 1;

	Properties properties;
	Scheduler sched = null;
	private static Logger logger = Logger.getLogger("SearchBroker");

	private static SearchBroker broker;

	public static SearchBroker newInstance(){

		if(broker == null){
			broker = new SearchBroker();
		}

		return broker;
	}

	public static void displayManual(){

		logger.info("usage: SearchBroker \"website uri deliminated by spaces\n"+
					"http://www.nairaland.com/recent http://www.nairaland.com/crime");
	}

    /**
    * display fine error by error type
    */
	public static void displayError(int error){

		String display = "";
		StringBuffer sb = new StringBuffer();
		sb.append("Invalid URI! URI must be prefixed with http");

		switch(error){

			case SearchBroker.URI_MALFORMED:
			display = sb.toString();
			break;

			default:
			display = "Property file not found!";
		}

		//logger.info(display);
	}

	/**
	 * This is the only method exposed as the API call
	 * loads multiple configuration files from specified URI
	 * an invokes a background {@link SearchAgent} callable task
	 * which in turn does the action property collection from
	 * various streams.
	 * This is the method that is exposed as API call.
	 *
	 * @params resourses String containg space separated uri where data will be extracted from.
	 * @params attribute String containg space separated keywords to look for.
	 * @return List class containing the loaded SearchObjectCache.
	*/

	public static List<SearchObjectCache> search(String resources,String attributes){
		//"crawling data sources";
        ExecutorService service = Executors.newFixedThreadPool(10);
        List<SearchObjectCache> records = new ArrayList<SearchObjectCache>();
		java.util.List<String> sources = Arrays.asList(resources.split(" "));
		List<Future<SearchObjectCache>> tasks = new ArrayList<Future<SearchObjectCache>>();
		try{

			for(String source:sources){

			   Future future = service.submit(new SearchAgent(source,attributes));
			   tasks.add(future);
			}

			for(Future<SearchObjectCache> task:tasks){

				List<SearchObjectCache> data = (List<SearchObjectCache>)task.get();
				records.addAll(data);

			}

			service.shutdown();
			service.awaitTermination(5,TimeUnit.SECONDS);

		}catch(Exception ex) {

			logger.info("error loading resources ");
			ex.printStackTrace();
			logger.log(Level.SEVERE, null, ex);

		}finally{
			if(!service.isTerminated()){
				logger.info("Cancel non-finish tasks");
			}
			service.shutdownNow();
		}

		logger.info("Task is completed, let's check result");
		logger.info("Document search completed\n");
		return records;
	}

	private void checkConfiguration(){

		properties = new Properties();
		System.out.println("[eduzi]: Initializing search broker jobs configuration :");
		System.out.println("[eduzi]:Done initializing jobs configuration.");
	}

	public void shutdown() throws Exception {
		if(sched != null) sched.shutdown();
	}

	public void scheduleJobs() throws Exception {

		System.out.println("------- Initializing ----------------------");
		checkConfiguration();

		// First we must get a reference to a scheduler
		SchedulerFactory sf = new StdSchedulerFactory();
		sched = sf.getScheduler();
		sched.deleteJob(JobKey.jobKey("event-search-job",  "search.broker.jobs"));
		sched.deleteJob(JobKey.jobKey("traffic-search-job","search.broker.jobs"));
		sched.deleteJob(JobKey.jobKey("sport-search-job",  "search.broker.jobs"));
		sched.deleteJob(JobKey.jobKey("data-analysis-job", "search.broker.jobs"));

		System.out.println("------- Initialization Complete -----------");
		System.out.println("------- Scheduling Jobs -------------------");

		//Job #1 is scheduled to run every 3 minutes
		JobDetail evtJob = newJob(EventSearchJob.class).withIdentity("event-search-job", "search.broker.jobs").build();
		Trigger ET = newTrigger().withIdentity("event-trigger", "search.broker.jobs")
		                  .startNow().withSchedule(simpleSchedule().withIntervalInSeconds(60*03).repeatForever())
                          .build();

         JobDetail tfcJob = newJob(TrafficSearchJob.class).withIdentity("traffic-search-job", "search.broker.jobs").build();
		Trigger TT = newTrigger().withIdentity("traffic-trigger", "search.broker.jobs")
						  .startNow().withSchedule(simpleSchedule().withIntervalInSeconds(60*03).repeatForever())
				          .build();

        JobDetail danJob = newJob(DataAnalysisJob.class).withIdentity("data-analysis-job", "search.broker.jobs").build();
		Trigger DT = newTrigger().withIdentity("analysis-trigger", "search.broker.jobs")
						  .startNow().withSchedule(simpleSchedule().withIntervalInSeconds(60*15).repeatForever())
				          .build();

	    JobDetail spoJob = newJob(SportSearchJob.class).withIdentity("sport-search-job", "search.broker.jobs").build();
		Trigger ST = newTrigger().withIdentity("sport-trigger", "search.broker.jobs")
						  .startNow().withSchedule(simpleSchedule().withIntervalInSeconds(60*10).repeatForever())
				          .build();

		sched.scheduleJob(tfcJob, TT);
		sched.scheduleJob(evtJob, ET);
		sched.scheduleJob(spoJob, ST);
		sched.scheduleJob(danJob, DT);

		sched.start();
		System.out.println("[eduzi] all jobs started.");
	}

	public static void main(String[] args){

		SearchBroker broker = SearchBroker.newInstance();
		try{

		    broker.scheduleJobs();

		}catch(Exception e){

			System.out.println("error starting jobs..");
			e.printStackTrace();
		}
	}
}