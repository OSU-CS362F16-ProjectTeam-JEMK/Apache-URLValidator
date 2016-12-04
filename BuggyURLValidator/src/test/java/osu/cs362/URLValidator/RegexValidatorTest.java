//Tester for RegexValidator
package osu.cs362.URLValidator;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Random;
import java.util.List;
import java.util.Arrays;

public class RegexValidatorTest {

  /******************************************************************
  * Returns random number that will be the length of the String
  *******************************************************************/
  private int getRandomLength(){
    final int MAX_RAND = 255;
    final int MIN_RAND = 1;
    Random r = new Random();
    return r.nextInt((MAX_RAND - MIN_RAND) + 1) + MIN_RAND;
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
  * Return String[] of random strings of random length (max 255 characters)
  *******************************************************************/
  private String[] getStringArr(int numStr){
    String[] strArr = new String[numStr];
    String str = "";
    for(int i = 0; i < numStr; i++){
      Random r = new Random();
      int strLength = getRandomLength();
      char data = ' ';
      for (int j = 0; j < strLength; j++) {
        data = (char)(r.nextInt(25)+97);
        str = data + str;
      }
      strArr[i] = str;
    }
    return strArr;
  }

  /******************************************************************
  * Returns Regex String
  *******************************************************************/
  private String getRegString(String[] inStr, int num){
    String reg = "RegexValidator{";
    String c = ",";
    String end = "}";
    String ret = "";
    ret += reg;
    for(int i = 0; i < num; i++){
      ret += inStr[i];
      if(i < num-1)
      ret+=c;
    }
    ret += end;
    return ret;
  }


  /******************************************************************
  *
  *
  *******************************************************************/
  @Test
  public void regexTest1() {
    int numTest = 100;
    int testRun = 0;
    for(int i = 0; i < numTest; i++){
      String myStr = getString();
      boolean b = false;
      if ( (i & 1) == 0 ) { b = true; }
      RegexValidator myReg = new RegexValidator(myStr,b);
      boolean resultValid = myReg.isValid(myStr);
      if(!resultValid){
        System.out.print("Failure in Regex Test 1A: ");
        System.out.println(resultValid);
        assertTrue(resultValid);
      } else {
        assertTrue(resultValid);
      }
      testRun++;
    }
    System.out.print("# Tests Run in RegexValidator Test 1: ");
    System.out.println(testRun);
  }

  /******************************************************************
  *
  *
  *******************************************************************/
  @Test
  public void regexTest2() {
    int numTest = 100;
    int testRun = 0;

    for(int j = 0; j < numTest; j++){
      String[] myStrMatch = new String[numTest];
      myStrMatch = getStringArr(numTest);
      boolean b = false;
      if ( (j & 1) == 0 ) { b = true; }
      RegexValidator myReg = new RegexValidator(myStrMatch,b);
      // Test each string in array to match string[]
      String[] resultMatch = myReg.match(myStrMatch[j]);
      if(resultMatch.length < 0) {
        boolean r = false;
        System.out.print("Failure in Regex Test 1B: ");
        System.out.println(resultMatch);
        assertTrue(r);
      } else {
        boolean r = true;
        assertTrue(r);
      }
      testRun++;
    }
    System.out.print("# Tests Run in RegexValidator Test 2: ");
    System.out.println(testRun);

  }
  /******************************************************************
  *
  *
  *******************************************************************/
  @Test
  public void regexTest3() {
    int numTest = 100;
    int testRun = 0;

    for(int k = 0; k < numTest; k++){
      String[] myStrMatch = new String[numTest];
      myStrMatch = getStringArr(numTest);
      //String[] myStrMatch = {"test", "me", "one"};
      boolean b = false;
      if ( (k & 1) == 0 ) { b = true; }
      RegexValidator myReg = new RegexValidator(myStrMatch,b);
      // Test each string in array to validate string[]
      String resultValidate = myReg.validate(myStrMatch[k]); // Returns empty string
      if(resultValidate  == null || resultValidate.isEmpty()){
        boolean r = false;
        System.out.print("Failure in Regex Test 1C - Validated String is null or empty: ");
        System.out.println(resultValidate);
        assertTrue(r);
      } else {
        boolean r = true;
        assertTrue(r);
      }
      testRun++;
    }
    System.out.print("# Tests Run in RegexValidator Test 3: ");
    System.out.println(testRun);
  }
  /******************************************************************
  *
  *
  *******************************************************************/
  @Test
  public void regexTest4() {
    int numTest = 100;
    int testRun = 0;
    for(int k = 0; k < numTest; k++){
      String[] myStrMatch = new String[3];
      myStrMatch = getStringArr(3);
      boolean b = false;
      if ( (k & 1) == 0 ) { b = true; }
      RegexValidator myReg = new RegexValidator(myStrMatch,b);
      // Test each string in array to validate string[]
      String resultString = myReg.toString();
      // Create string to compare with result from "toString()"
      String compareStr = getRegString(myStrMatch, 3);
      assertEquals("Failure in Regex Test 1D", resultString, compareStr);
      testRun++;
    }
    System.out.print("# Tests Run in RegexValidator Test 4: ");
    System.out.println(testRun);
  }
}
