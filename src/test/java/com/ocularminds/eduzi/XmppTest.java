package com.ocularminds.eduzi;

import java.util.List;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple SearchBrokerTest.
 */
public class XmppTest extends TestCase{

	final XmppManager xm = XmppManager.getInstance("127.0.0.1", 5222);
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public XmppTest( String testName ){
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite(){
		return new TestSuite(XmppTest.class );
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp(){
		assertTrue( true );
    }

    public void testChat()throws Exception{

        String username = "festi";
        String password = "locus1";

        /*xm.init();
        xm.performLogin(username, password);
        xm.setStatus(true, "Hello everyone");
        xm.printRoster();

        String buddyJID = "karimo";
        String buddyName = "Beck Wears";
        xm.createEntry(buddyJID, buddyName);
        String[] messages = {"I dont know what to say","These people are angry","Do what you like","I dont care","bye bye!"};
        xm.sendMessage("Hello mate", "karimo@research");

        boolean isRunning = true;
        int i = 0;
        while (isRunning) {

			xm.sendMessage(messages[i%4], "karimo@research");
			i++;
            Thread.sleep(5000);
        }

        xm.destroy();*/

    }

}