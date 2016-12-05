//Tester for Domain
package osu.cs362.URLValidator;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.io.Serializable;

public class DomainValidatorTest_Green {

  /******************************************************************
  *
  *******************************************************************/
  private int getRandomLength(){
    final int MAX_RAND = 255;
    final int MIN_RAND = 1;
    Random r = new Random();
    return r.nextInt((MAX_RAND - MIN_RAND) + 1) + MIN_RAND;
  }
  /******************************************************************
  *
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
  *
  *******************************************************************/
  private int getList(){
    final int MAX_RAND = 3;
    final int MIN_RAND = 1;
    Random r = new Random();
    return r.nextInt((MAX_RAND - MIN_RAND) + 1) + MIN_RAND;
  }
  /******************************************************************
  *
  *******************************************************************/
  private int getLocalIdx(){
    final int MAX_RAND = 1;
    final int MIN_RAND = 0;
    Random r = new Random();
    return r.nextInt((MAX_RAND - MIN_RAND) + 1) + MIN_RAND;
  }
  /******************************************************************
  *
  *******************************************************************/
  private int getGenericIdx(){
    final int MAX_RAND = 19;
    final int MIN_RAND = 0;
    Random r = new Random();
    return r.nextInt((MAX_RAND - MIN_RAND) + 1) + MIN_RAND;
  }

  /******************************************************************
  *
  *******************************************************************/
  private int getInfIdx(){
    final int MAX_RAND = 1;
    final int MIN_RAND = 0;
    Random r = new Random();
    return r.nextInt((MAX_RAND - MIN_RAND) + 1) + MIN_RAND;
  }
  /******************************************************************
  *
  *******************************************************************/
  private int getCCIdx(){
    final int MAX_RAND = 108;
    final int MIN_RAND = 0;
    Random r = new Random();
    return r.nextInt((MAX_RAND - MIN_RAND) + 1) + MIN_RAND;
  }
  /******************************************************************
  *
  *******************************************************************/
  @Test
  public void domainVal_1() {
    // With local
    DomainValidator myDN_wLocal = DomainValidator.getInstance(true);
    int numTests = 100000;
    int testsRun = 0;

    for(int i = 0; i < numTests; i++){
      String domainName = getString();
      // Uses:
      // bool isValidLocalTld(String)
      // Uses: LOCAL_TLD_LIST.contains()
      // TRUE if Contains LOCAL_TLDS
      List LOCAL_TLD_LIST = Arrays.asList(LOCAL_TLDS);
      String postStr = LOCAL_TLD_LIST.get(getLocalIdx()).toString();
      domainName += ".";
      domainName += postStr;
      testsRun++;
      boolean b = myDN_wLocal.isValidTld(domainName);
      if(!b){
        System.out.println(domainName);
        System.out.println(b);
        assertTrue(b);
      }
    }
    System.out.print("# Tests Run in Domain Validator Test 1: ");
    System.out.println(testsRun);
  }

  /******************************************************************
  *
  *******************************************************************/
  @Test
  public void domainVal_2() {
    // Local Address not allowed
    DomainValidator myDN_wOutLocal = DomainValidator.getInstance(false);
    int loopCnt = 100000; // number of tests to runs
    int testRun = 0;
    // Uses:
    // bool isValidInfrastructureTld(String)
    // bool isValidGenericTld(String)
    // bool isValidCountryCodeTld(String)
    // TRUE if contains anything from:
    //    INFRASTRUCTURE_TLDS
    //    GENERIC_TLDS +
    //    COUNTRY_CODE_TLDS

    for(int i = 0; i < loopCnt; i++) {
      String postStr = null;
      String domainName = getString();
      int s = getList();
      if(s == 1){
        List INFRASTRUCTURE_TLD_LIST = Arrays.asList(INFRASTRUCTURE_TLDS);
        postStr = INFRASTRUCTURE_TLD_LIST.get(getInfIdx()).toString();
      }else if(s == 2){
        List COUNTRY_CODE_TLD_LIST = Arrays.asList(COUNTRY_CODE_TLDS);
        postStr = COUNTRY_CODE_TLD_LIST.get(getCCIdx()).toString();
      }else {
        List GENERIC_TLD_LIST = Arrays.asList(GENERIC_TLDS);
        postStr = GENERIC_TLD_LIST.get(getGenericIdx()).toString();
      }
      domainName += ".";
      domainName += postStr;
      boolean b = myDN_wOutLocal.isValid(domainName);
      testRun++;

      // If false, print domain string
      if(!b){
        System.out.println(domainName);
        System.out.println(b);
        assertTrue(b);
      }
      domainName = "";
    }
    // Verify Number of test completed
    System.out.print("# Tests Run in Domain Validator Test 2: ");
    System.out.println(testRun);
  }
  /******************************************************************
  *
  *******************************************************************/
  private static final String[] INFRASTRUCTURE_TLDS = new String[] {
    "arpa",               // internet infrastructure
    "root"                // diagnostic marker for non-truncated root zone
  };
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
  /******************************************************************
  *
  *******************************************************************/
  private static final String[] COUNTRY_CODE_TLDS = new String[] {
    "ac",                 // Ascension Island
    "ad",                 // Andorra
    "ae",                 // United Arab Emirates
    "af",                 // Afghanistan
    "ag",                 // Antigua and Barbuda
    "ai",                 // Anguilla
    "al",                 // Albania
    "am",                 // Armenia
    "an",                 // Netherlands Antilles
    "ao",                 // Angola
    "aq",                 // Antarctica
    "ar",                 // Argentina
    "as",                 // American Samoa
    "at",                 // Austria
    "au",                 // Australia (includes Ashmore and Cartier Islands and Coral Sea Islands)
    "aw",                 // Aruba
    "ax",                 // Åland
    "az",                 // Azerbaijan
    "ba",                 // Bosnia and Herzegovina
    "bb",                 // Barbados
    "bd",                 // Bangladesh
    "be",                 // Belgium
    "bf",                 // Burkina Faso
    "bg",                 // Bulgaria
    "bh",                 // Bahrain
    "bi",                 // Burundi
    "bj",                 // Benin
    "bm",                 // Bermuda
    "bn",                 // Brunei Darussalam
    "bo",                 // Bolivia
    "br",                 // Brazil
    "bs",                 // Bahamas
    "bt",                 // Bhutan
    "bv",                 // Bouvet Island
    "bw",                 // Botswana
    "by",                 // Belarus
    "bz",                 // Belize
    "ca",                 // Canada
    "cc",                 // Cocos (Keeling) Islands
    "cd",                 // Democratic Republic of the Congo (formerly Zaire)
    "cf",                 // Central African Republic
    "cg",                 // Republic of the Congo
    "ch",                 // Switzerland
    "ci",                 // Côte d'Ivoire
    "ck",                 // Cook Islands
    "cl",                 // Chile
    "cm",                 // Cameroon
    "cn",                 // China, mainland
    "co",                 // Colombia
    "cr",                 // Costa Rica
    "cu",                 // Cuba
    "cv",                 // Cape Verde
    "cx",                 // Christmas Island
    "cy",                 // Cyprus
    "cz",                 // Czech Republic
    "de",                 // Germany
    "dj",                 // Djibouti
    "dk",                 // Denmark
    "dm",                 // Dominica
    "do",                 // Dominican Republic
    "dz",                 // Algeria
    "ec",                 // Ecuador
    "ee",                 // Estonia
    "eg",                 // Egypt
    "er",                 // Eritrea
    "es",                 // Spain
    "et",                 // Ethiopia
    "eu",                 // European Union
    "fi",                 // Finland
    "fj",                 // Fiji
    "fk",                 // Falkland Islands
    "fm",                 // Federated States of Micronesia
    "fo",                 // Faroe Islands
    "fr",                 // France
    "ga",                 // Gabon
    "gb",                 // Great Britain (United Kingdom)
    "gd",                 // Grenada
    "ge",                 // Georgia
    "gf",                 // French Guiana
    "gg",                 // Guernsey
    "gh",                 // Ghana
    "gi",                 // Gibraltar
    "gl",                 // Greenland
    "gm",                 // The Gambia
    "gn",                 // Guinea
    "gp",                 // Guadeloupe
    "gq",                 // Equatorial Guinea
    "gr",                 // Greece
    "gs",                 // South Georgia and the South Sandwich Islands
    "gt",                 // Guatemala
    "gu",                 // Guam
    "gw",                 // Guinea-Bissau
    "gy",                 // Guyana
    "hk",                 // Hong Kong
    "hm",                 // Heard Island and McDonald Islands
    "hn",                 // Honduras
    "hr",                 // Croatia (Hrvatska)
    "ht",                 // Haiti
    "hu",                 // Hungary
    "id",                 // Indonesia
    "ie",                 // Ireland (Éire)
    "il",                 // Israel
    "im",                 // Isle of Man
    "in",                 // India
    "io",                 // British Indian Ocean Territory
    "iq",                 // Iraq
    "ir",                 // Iran
    "is",                 // Iceland
    "it",                 // Italy
  };
  /******************************************************************
  *
  *******************************************************************/
  private static final String[] LOCAL_TLDS = new String[] {
    "localhost",           // RFC2606 defined
    "localdomain"          // Also widely used as localhost.localdomain
  };

}
