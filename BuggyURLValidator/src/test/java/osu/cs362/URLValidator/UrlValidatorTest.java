//Test for URL-Validator
package osu.cs362.URLValidator;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Random;
import java.util.List;
import java.util.Arrays;

public class UrlValidatorTest {
  /******************************************************************
  * Returns random number that will be the length of the String
  *******************************************************************/
  private int getRandomLength(){
    final int MAX_RAND = 25;
    final int MIN_RAND = 1;
    Random r = new Random();
    return r.nextInt((MAX_RAND - MIN_RAND) + 1) + MIN_RAND;
  }
  /******************************************************************
  * Returns number 0, 1, or 2
  *******************************************************************/
  private int getScheme(){
    final int MAX_RAND = 2;
    final int MIN_RAND = 0;
    Random r = new Random();
    return r.nextInt((MAX_RAND - MIN_RAND) + 1) + MIN_RAND;
  }
  /******************************************************************
  * Returns number between [1-19]
  *******************************************************************/
  private int getGenericIdx(){
    final int MAX_RAND = 19;
    final int MIN_RAND = 0;
    Random r = new Random();
    return r.nextInt((MAX_RAND - MIN_RAND) + 1) + MIN_RAND;
  }
  /******************************************************************
  * Return random string of random length
  *******************************************************************/
  private String getBadScheme(){
    Random r = new Random();
    int strLength = getScheme()+5;
    char data = ' ';
    String str = "";
    for (int i = 0; i < strLength; i++) {
      data = (char)(r.nextInt(25)+97);
      str += data;
    }
    return str;
  }
  /******************************************************************
  * Return random string of random length (max = 255 characters)
  *******************************************************************/
  private String getString(){
    Random r = new Random();
    int strLength = getRandomLength();
    char data = ' ';
    String str = "";
    for (int i = 0; i < strLength; i++) {
      data = (char)(r.nextInt(25)+97);
      str = data + str;
    }
    return str;
  }
  /******************************************************************
  * Return random a valid URL string (i.e. http://sldkfjsdl.com)
  *******************************************************************/
  private String getValidURL(){
    Random r = new Random();
    String[] schemes = {"http", "https", "ftp"};
    int strLength = getRandomLength();
    char data = ' ';
    String str = "";
    str += schemes[getScheme()];
    str += "://";
    for (int i = 0; i < strLength; i++) {
      data = (char)(r.nextInt(25)+97);
      str += data;
    }
    List GENERIC_TLD_LIST = Arrays.asList(GENERIC_TLDS);
    String postStr = GENERIC_TLD_LIST.get(getGenericIdx()).toString();
    str += ".";
    str += postStr;
    return str;
  }
  /******************************************************************
  * Return random a valid URL string (i.e. http://sldkfjsdl.com)
  *******************************************************************/
  private String getValidURL_s1(int scheme){
    Random r = new Random();
    String[] schemes = {"http", "https", "ftp"};
    int strLength = getRandomLength();
    char data = ' ';
    String str = "";
    str += schemes[scheme];
    str += "://";
    for (int i = 0; i < strLength; i++) {
      data = (char)(r.nextInt(25)+97);
      str += data;
    }
    List GENERIC_TLD_LIST = Arrays.asList(GENERIC_TLDS);
    String postStr = GENERIC_TLD_LIST.get(getGenericIdx()).toString();
    str += ".";
    str += postStr;
    return str;
  }

  /******************************************************************
  * Return random a NON valid URL string (i.e. dgt://sldkfjsdl.gfg)
  *******************************************************************/
  private String getNonValidURL(){
    Random r = new Random();
    int strLength = getRandomLength();
    char data = ' ';
    String str = "";
    str += getBadScheme();
    str += "://";
    for (int i = 0; i < strLength; i++) {
      data = (char)(r.nextInt(25)+97);
      str += data;
    }
    str += ".";
    str += getBadScheme();
    return str;
  }

  /******************************************************************
  * Tests
  *----------------------
  * Various Constructors
  * ---------------------
  * UrlValidator() - default properties
  * UrlValidator(String[] schemes) - pass in schemes
  * UrlValidator(long options) - ALLOW_2_SLASHES + NO_FRAGMENTS (both)
  * UrlValidator(String[] schemes, long options)
  * UrlValidator(RegexValidator authorityValidator, long options)
  * UrlValidator(String[] schemes, RegexValidator authorityValidator, long options)
  * ----------------
  * Methods to Test?
  * ----------------
  * isValid(String); - public
  * isValidScheme(String); - protected
  * isValidAuthority(String); - protected
  * isValidPath(String); - protected
  *******************************************************************/

  /******************************************************************
  * Default Constructor, default options - all should PASS
  *******************************************************************/
  @Test
  public void URLtest1() {
    int testRun = 0;
    int numTests = 100000;

    for(int i = 0; i < numTests; i++) {
      //String myStr = getString();
      //RegexValidator myReg = new RegexValidator(myStr,true);
      UrlValidator myURL = new UrlValidator();
      String url_ = getValidURL();
      boolean b = myURL.isValid(url_);

      if(!b){
        System.out.println("Failure in URL Test #1 - URL: ");
        System.out.println(url_);
      }
      assertTrue(b);
      testRun++;
    }
    System.out.print("# Tests run in URL Test 1: ");
    System.out.println(testRun);

  }
  /******************************************************************
  * Default Constructor, default options - all should FAIL
  *******************************************************************/
  @Test
  public void URLtest2() {
    int testRun = 0;
    int numTests = 100000;

    for(int i = 0; i < numTests; i++) {
      //String myStr = getString();
      //RegexValidator myReg = new RegexValidator(myStr,true);
      UrlValidator myURL = new UrlValidator();
      String url_ = getNonValidURL();
      boolean b = myURL.isValid(url_);

      if(b){
        System.out.println("Failure in URL Test #2 - URL: ");
        System.out.println(url_);
      }
      assertFalse(b);
      testRun++;
    }
    System.out.print("# Tests run in URL Test 2: ");
    System.out.println(testRun);

  }
  /******************************************************************
  * Only allow 1 scheme at a time (declared in getValidURL_s1())
  *******************************************************************/
  @Test
  public void URLtest3() {
    int testRun = 0;
    int numTests = 100000;
    String[] schemes  = new String[1];
    for(int schemeNumber = 0; schemeNumber < 3; schemeNumber++){
      if(schemeNumber == 0) schemes[0] = "http";
      if(schemeNumber == 1) schemes[0] = "https";
      if(schemeNumber == 2) schemes[0] = "ftp";
      for(int i = 0; i < numTests; i++) {
        //String myStr = getString();
        //RegexValidator myReg = new RegexValidator(myStr,true);
        UrlValidator myURL = new UrlValidator(schemes);
        String url_ = getValidURL_s1(schemeNumber);
        boolean b = myURL.isValid(url_);

        if(!b){
          System.out.println("Failure in URL Test #3 - URL: ");
          System.out.println(url_);
        }
        assertTrue(b);
        testRun++;
      }
    }
    System.out.print("# Tests run in URL Test 3: ");
    System.out.println(testRun);

  }
  /******************************************************************
  * Options:
  *        + ALLOW_2_SLASHES
  *          ALLOW_ALL_SCHEMES
  *          NO_FRAGMENTS
  *          ALLOW_LOCAL_URLS
  *******************************************************************/
  @Test
  public void URLtest4() {
    int testRun = 0;
    int numTests = 100000;

    for(int i = 0; i < numTests; i++) {
      //String myStr = getString();
      //RegexValidator myReg = new RegexValidator(myStr,true);
      UrlValidator myURL = new UrlValidator(UrlValidator.ALLOW_2_SLASHES);
      String url_ = getValidURL();
      boolean b = myURL.isValid(url_);

      if(!b){
        System.out.println("Failure in URL Test #4 - URL: ");
        System.out.println(url_);
      }
      assertTrue(b);
      testRun++;
    }
    System.out.print("# Tests run in URL Test 4: ");
    System.out.println(testRun);

  }
  /******************************************************************
  * Options:
  *          ALLOW_2_SLASHES
  *        + ALLOW_ALL_SCHEMES
  *          NO_FRAGMENTS
  *          ALLOW_LOCAL_URLS
  *******************************************************************/
  @Test
  public void URLtest5() {
    int testRun = 0;
    int numTests = 100000;

    for(int i = 0; i < numTests; i++) {
      //String myStr = getString();
      //RegexValidator myReg = new RegexValidator(myStr,true);
      UrlValidator myURL = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES);
      String url_ = getValidURL();
      boolean b = myURL.isValid(url_);

      if(!b){
        System.out.println("Failure in URL Test #5 - URL: ");
        System.out.println(url_);
      }
      assertTrue(b);
      testRun++;
    }
    System.out.print("# Tests run in URL Test 5: ");
    System.out.println(testRun);

  }
  /******************************************************************
  * Options:
  *          ALLOW_2_SLASHES
  *          ALLOW_ALL_SCHEMES
  *        + NO_FRAGMENTS
  *          ALLOW_LOCAL_URLS
  *******************************************************************/
  @Test
  public void URLtest6() {
    int testRun = 0;
    int numTests = 100000;

    for(int i = 0; i < numTests; i++) {
      //String myStr = getString();
      //RegexValidator myReg = new RegexValidator(myStr,true);
      UrlValidator myURL = new UrlValidator(UrlValidator.NO_FRAGMENTS);
      String url_ = getValidURL();
      boolean b = myURL.isValid(url_);

      if(!b){
        System.out.println("Failure in URL Test #6 - URL: ");
        System.out.println(url_);
      }
      assertTrue(b);
      testRun++;
    }
    System.out.print("# Tests run in URL Test 6: ");
    System.out.println(testRun);

  }
  /******************************************************************
  * Options:
  *          ALLOW_2_SLASHES
  *          ALLOW_ALL_SCHEMES
  *          NO_FRAGMENTS
  *        + ALLOW_LOCAL_URLS
  *******************************************************************/
  @Test
  public void URLtest7() {
    int testRun = 0;
    int numTests = 100000;

    for(int i = 0; i < numTests; i++) {
      //String myStr = getString();
      //RegexValidator myReg = new RegexValidator(myStr,true);
      UrlValidator myURL = new UrlValidator(UrlValidator.ALLOW_LOCAL_URLS);
      String url_ = getValidURL();
      boolean b = myURL.isValid(url_);

      if(!b){
        System.out.println("Failure in URL Test #7 - URL: ");
        System.out.println(url_);
      }
      assertTrue(b);
      testRun++;
    }
    System.out.print("# Tests run in URL Test 7: ");
    System.out.println(testRun);

  }
  /******************************************************************
  * Test Scheme + Options Constructor
  *******************************************************************/
  @Test
  public void URLtest8() {
    int testRun = 0;
    int numTests = 100000;
    String[] schemes  = new String[1];
    long options = 0L;
    for(int schemeNumber = 0; schemeNumber < 3; schemeNumber++){
      if(schemeNumber == 0) schemes[0] = "http"; options = 2L; // ALLOW_2_SLASHES
      if(schemeNumber == 1) schemes[0] = "https"; options = 4L; // NO_FRAGMENTS
      if(schemeNumber == 2) schemes[0] = "ftp"; options = 8L; // ALLOW_LOCAL_URLS
      for(int i = 0; i < numTests; i++) {
        //String myStr = getString();
        //RegexValidator myReg = new RegexValidator(myStr,true);
        UrlValidator myURL = new UrlValidator(schemes, options);
        String url_ = getValidURL_s1(schemeNumber);
        boolean b = myURL.isValid(url_);

        if(!b){
          System.out.println("Failure in URL Test #8 - URL: ");
          System.out.println(url_);
        }
        assertTrue(b);
        testRun++;
      }
    }
    System.out.print("# Tests run in URL Test 8: ");
    System.out.println(testRun);

  }
  /******************************************************************
  * Test Regex + options constructor
  *******************************************************************/
  @Test
  public void URLtest9() {
    int testRun = 0;
    int numTests = 100000;
    String[] schemes  = new String[1];
    long options = 0L;
    for(int schemeNumber = 0; schemeNumber < 3; schemeNumber++){
      if(schemeNumber == 0) schemes[0] = "http"; options = 2L; // ALLOW_2_SLASHES
      if(schemeNumber == 1) schemes[0] = "https"; options = 4L; // NO_FRAGMENTS
      if(schemeNumber == 2) schemes[0] = "ftp"; options = 8L; // ALLOW_LOCAL_URLS
      for(int i = 0; i < numTests; i++) {
        String myStr = getString();
        RegexValidator myReg = new RegexValidator(myStr,true);
        UrlValidator myURL = new UrlValidator(myReg, options);
        String url_ = getValidURL_s1(schemeNumber);
        boolean b = myURL.isValid(url_);

        if(!b){
          System.out.println("Failure in URL Test #9 - URL: ");
          System.out.println(url_);
        }
        assertTrue(b);
        testRun++;
      }
    }
    System.out.print("# Tests run in URL Test 9: ");
    System.out.println(testRun);

  }
  /******************************************************************
  * Test Schemes, Regex, + options constructor
  *******************************************************************/
  @Test
  public void URLtest10() {
    int testRun = 0;
    int numTests = 100000;
    String[] schemes  = new String[1];
    long options = 0L;
    for(int schemeNumber = 0; schemeNumber < 3; schemeNumber++){
      if(schemeNumber == 0) schemes[0] = "http"; options = 2L; // ALLOW_2_SLASHES
      if(schemeNumber == 1) schemes[0] = "https"; options = 4L; // NO_FRAGMENTS
      if(schemeNumber == 2) schemes[0] = "ftp"; options = 8L; // ALLOW_LOCAL_URLS
      for(int i = 0; i < numTests; i++) {
        String myStr = getString();
        RegexValidator myReg = new RegexValidator(myStr,true);
        UrlValidator myURL = new UrlValidator(schemes, myReg, options);
        String url_ = getValidURL_s1(schemeNumber);
        boolean b = myURL.isValid(url_);

        if(!b){
          System.out.println("Failure in URL Test #10 - URL: ");
          System.out.println(url_);
        }
        assertTrue(b);
        testRun++;
      }
    }
    System.out.print("# Tests run in URL Test 10: ");
    System.out.println(testRun);

  }

  /******************************************************************
  *
  *******************************************************************/
  private static final String[] GENERIC_TLDS = new String[] {
    "aero",               // air transport industry
    "asia",               // Pan-Asia/Asia Pacific
    "biz",                // businesses
    "cat",                // Catalan linguistic/cultural community
    "com",                // commercial enterprises
    "coop",               // cooperative associations
    "info",               // informational sites
    "jobs",               // Human Resource managers
    "mobi",               // mobile products and services
    "museum",             // museums, surprisingly enough
    "name",               // individuals' sites
    "net",                // internet support infrastructure/business
    "org",                // noncommercial organizations
    "pro",                // credentialed professionals and entities
    "tel",                // contact data for businesses and individuals
    "travel",             // entities in the travel industry
    "gov",                // United States Government
    "edu",                // accredited postsecondary US education entities
    "mil",                // United States Military
    "int"                 // organizations established by international treaty
  };
}
