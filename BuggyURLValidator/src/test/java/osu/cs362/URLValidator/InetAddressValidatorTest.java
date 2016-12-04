//Test for InetAddress
package osu.cs362.URLValidator;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Random;

public class InetAddressValidatorTest {


  /******************************************************************
  *
  *******************************************************************/
  private static int ipGoodSegment(){
    final int MAX_RAND = 255;
    final int MIN_RAND = 0;
    Random r = new Random();
    return r.nextInt((MAX_RAND - MIN_RAND) + 1) + MIN_RAND;
  }
  /******************************************************************
  *
  *******************************************************************/
  private static int ipBadSegment(){
    final int MAX_RAND = 999;
    final int MIN_RAND = 255;
    Random r = new Random();
    return r.nextInt((MAX_RAND - MIN_RAND) + 1) + MIN_RAND;
  }

/******************************************************************
*
*******************************************************************/
  @Test
  public void validIpAddr1() {
    int numLoops = 1000000;
    String dot = ".";
    int segment1 = 0;
    String ipSeg = "";
    String ip = "";
    int testRun = 0;

    InetAddressValidator myInetAddr = new InetAddressValidator();

    // Number of times to test random ip address
    for(int j = 0; j < numLoops; j++){

      // Get randome ip address
      for(int i = 0; i < 4; i++){
        segment1 = ipGoodSegment();
        ipSeg = Integer.toString(segment1);
        ip += segment1;
        if(i < 3)
        ip += dot;
      }
      //System.out.print(ip);
      //System.out.print(" : ");
      //System.out.println(myInetAddr.isValid(ip));
      if(!myInetAddr.isValid(ip)){
        System.out.print("ERROR - IP ADDR NOT CONSIDERED VALID: ");
        System.out.println(ip);
      }
      assertTrue(myInetAddr.isValid(ip));
      // Reinitialize ip string for next random variable
      testRun++;
      ipSeg = "";
      ip = "";
    }
    System.out.print("# Tests Run in InetAddr Test 1: ");
    System.out.println(testRun);
  }

  /******************************************************************
  *
  *******************************************************************/
    @Test
    public void validIpAddr2() {
      int numLoops = 1000000;
      String dot = ".";
      int segment1 = 0;
      String ipSeg = "";
      String ip = "";
      int testRun = 0;

      InetAddressValidator myInetAddr = new InetAddressValidator();

      // Number of times to test random ip address
      for(int j = 0; j < numLoops; j++){

        // Get randome ip address
        for(int i = 0; i < 4; i++){
          segment1 = ipBadSegment();
          ipSeg = Integer.toString(segment1);
          ip += segment1;
          if(i < 3)
          ip += dot;
        }
        //System.out.print(ip);
        //System.out.print(" : ");
        //System.out.println(myInetAddr.isValid(ip));
        if(myInetAddr.isValid(ip)){
          System.out.print("ERROR - IP ADDR CONSIDERED VALID: ");
          System.out.println(ip);
        }
        assertFalse(myInetAddr.isValid(ip));
        // Reinitialize ip string for next random variable
        testRun++;
        ipSeg = "";
        ip = "";
      }
      System.out.print("# Tests Run in InetAddr Test 2: ");
      System.out.println(testRun);
    }

}
