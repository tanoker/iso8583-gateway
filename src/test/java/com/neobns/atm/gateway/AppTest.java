package com.neobns.atm.gateway;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
    	//Verify PIN test data
    	//0100420040010000180031314299200551444597122117063460113036888888844C0B72ADC0871D2001010100
    	//Financial transaction data
    	//0200F23A4591A880900400000000040000001950418710010815000041020510000000300001221171528215349144224122612266011021000020000000010000000087803878375041871001081500004=0001111111111111100000000000008787002608 006980990160490012230000670
        assertTrue( true );
    }
}
