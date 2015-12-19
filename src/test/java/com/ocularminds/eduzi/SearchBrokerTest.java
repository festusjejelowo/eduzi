package com.ocularminds.eduzi;

import java.util.List;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple SearchBrokerTest.
 */
public class SearchBrokerTest  extends TestCase{

	final SearchBroker broker = new SearchBroker();
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public SearchBrokerTest( String testName ){
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite(){
        return new TestSuite(SearchBrokerTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp(){
        assertTrue( true );
    }

    public void testSearch(){
       // tweeter@gidi traffik
        String url ="http://www.vanguardngr.com/"+new java.text.SimpleDateFormat("yyy/MM/dd").format(new java.util.Date())+"/ "+
					//"http://www.thisdaylive.com/news/ "+
					//"http://sunnewsonline.com/new/ "+
					"https://www.facebook.com "+
					"https://twitter.com/search?q=traffic%20nigeria&src=typd "+
					"https://twitter.com/Gidi_Traffic "+
					"http://www.lindaikejisblog.com/ "+
					/*
					"http://www.channelstv.com/ "+
					"http://www.channelstv.com/category/local/ "+
					"http://www.vanguardngr.com/ "+
					"http://www.punchng.com "+
					"http://www.tsaboin.com "+
					"http://www.beattraffik.com/ "+
					"http://www.nairaland.com/crime "+
					"http://www.nairaland.com/recent */"";

		String attributes = "Robbery,Hijack,Flood,Disaster,Rape,Crush,Suspect,Fraud,"+
							"Illegal,Forge,crime,kill,Attack,Traffic,Slow,Blocked,"+
							"Moving,Accident,Murder,Hold up,Downpour,Fire";
		//List<SearchObjectCache> data = broker.search(url,attributes);
		//System.out.println("Total records fetched - "+data.size());
		//assertTrue(data.size() > 0);
	}


	public void testAddRemoveObjects() {

		// Test with timeToLiveInSeconds = 200 seconds
		// timerIntervalInSeconds = 500 seconds
		// maxItems = 10
		MemoryCache<String, String> cache = new MemoryCache<String, String>(200, 500, 10);

		cache.put("eBay", "eBay");
		cache.put("Paypal", "Paypal");
		cache.put("Google", "Google");
		cache.put("Microsoft", "Microsoft");
		cache.put("IBM", "IBM");
		cache.put("Facebook", "Facebook");

		System.out.println("6 Cache Object Added.. cache.size(): " + cache.size());
		cache.remove("IBM");
		System.out.println("One object removed.. cache.size(): " + cache.size());

		cache.put("Twitter", "Twitter");
		cache.put("SAP", "SAP");
		System.out.println("Two objects Added but reached maxItems.. cache.size(): " + cache.size());

	}

	public void testExpiredCacheObjects() throws InterruptedException {

		// Test with timeToLiveInSeconds = 5 second
		// timerIntervalInSeconds = 1 second
		// maxItems = 10
		MemoryCache<String, String> cache = new MemoryCache<String, String>(5, 1, 10);
/*
		cache.put("eBay1", "eBay");
		Thread.sleep(500);
		cache.put("Paypal1", "Paypal");
		Thread.sleep(500);
		cache.put("eBay2", "eBay");
		Thread.sleep(500);
		cache.put("Paypal2", "Paypal");
		cache.put("eBay3", "eBay");
		Thread.sleep(500);
		cache.put("Paypal4", "Paypal");
		// Adding 3 seconds sleep.. Both above objects will be removed from
		// Cache because of timeToLiveInSeconds value
		Thread.sleep(5000);*/

		System.out.println("Two objects are added but reached timeToLive. cache.size(): " + cache.size());

	}

	public void testObjectsCleanupTime() throws InterruptedException {

		int size = 500000;

		// Test with timeToLiveInSeconds = 100 seconds
		// timerIntervalInSeconds = 100 seconds
		// maxItems = 500000

		/*MemoryCache<String, String> cache = new MemoryCache<String, String>(100, 100, 500000);

		for (int i = 0; i < size; i++) {
			String value = Integer.toString(i);
			cache.put(value, value);
		}

		Thread.sleep(200);
		long start = System.currentTimeMillis();
		cache.cleanup();
		double finish = (double) (System.currentTimeMillis() - start) / 1000.0;

		System.out.println("Cleanup times for " + size + " objects are " + finish + " s");*/

    }
}
