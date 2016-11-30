/*****************************************************************************
* Filename:     UrlValidatorMockGreenTest.java
* Author:       Keith Adkins 
* Email:        adkinske@oregonstate.edu
* Class:        CS362_400 Fall 2016
* Assignment:   Group Project
* Due Date:     12/5/2016
* Description:  Tests for the UrlValidator class.  Includes mocking of the 
* RegexValidator class, which is the only external class passed into the UrlValidator
* constructor.
*
******************************************************************************/

package osu.cs362.URLValidator;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class UrlValidatorMockTest_Green  {
    
    /*************************************************************************************************/
    /*  Testing for URLValidator.isValid() method. The constructor regex authority validator is mocked. */
    /*************************************************************************************************/
   
    // UrlValidator default scheme and default options. 
    // Constructor regex authority validator isValid mocked to true.
    @Test
    public void mockTestIsValid1() {   
        RegexValidator rv = mock(RegexValidator.class);
        UrlValidator uv = new UrlValidator(rv,0L);  // default scheme and no options
        // Mock the constructor regex authority validator to true.
        when(rv.isValid(anyString())).thenReturn(true);                        
        // Urls forced true by mock
        assertTrue(uv.isValid("http://abc"));
        assertTrue(uv.isValid("https://www.abc.xyz"));
        // Valid urls
        assertTrue(uv.isValid("http://www.abc.com"));       
        assertTrue(uv.isValid("https://www.abc.org"));               
        assertTrue(uv.isValid("ftp://abc.net"));            
        assertTrue(uv.isValid("http://www.abc.com/path#")); 
        // Invalid urls
        assertFalse(uv.isValid("http://www.abc.com//"));
        assertFalse(uv.isValid("xyz://www.abc.com")); 
	}
    
    // UrlValidator scheme: "http" and "https".  Options are default.  
    // Constructor regex authority validator isValid mocked to true.    
    @Test
    public void mockTestIsValid2() {   
        RegexValidator rv = mock(RegexValidator.class);
        String[] schemes = {"http", "https"};                
        UrlValidator uv = new UrlValidator(schemes,rv,0L);  
        // Mock the optional regex authority validator to true.
        when(rv.isValid(anyString())).thenReturn(true);  
        // Urls forced true by mock
        assertTrue(uv.isValid("http://abc"));
        assertTrue(uv.isValid("https://www.abc.xyz"));
        // Valid urls
        assertTrue(uv.isValid("http://www.abc.com"));       
        assertTrue(uv.isValid("https://www.abc.org"));                           
        assertTrue(uv.isValid("http://www.abc.com/path#"));     
        // Invalid urls
        assertFalse(uv.isValid("ftp://abc.net")); 
        assertFalse(uv.isValid("http://www.abc.com//")); 
        assertFalse(uv.isValid("xyz://www.abc.com"));         
	}

    // UrlValidator constructed with default scheme and option of ALLOW_2_SLASHES. 
    // Constructor regex authority validator isValid mocked to true.
    @Test
    public void mockTestIsValid3() {   
        RegexValidator rv = mock(RegexValidator.class);
        UrlValidator uv = new UrlValidator(rv,UrlValidator.ALLOW_2_SLASHES); 
        // Mock the optional regex authority validator to true.
        when(rv.isValid(anyString())).thenReturn(true);                        
        // Urls forced true by mock
        assertTrue(uv.isValid("http://abc"));
        assertTrue(uv.isValid("https://www.abc.xyz"));
        // Valid urls
        assertTrue(uv.isValid("http://www.abc.com"));       
        assertTrue(uv.isValid("https://www.abc.org"));  
        assertTrue(uv.isValid("ftp://abc.net"));         
        assertTrue(uv.isValid("http://www.abc.com/path#")); 
        assertTrue(uv.isValid("http://www.abc.com//"));
        // Invalid urls        
        assertFalse(uv.isValid("xyz://www.abc.com"));          
	}    

    // UrlValidator constructed with default scheme and option of NO_FRAGMENTS. 
    // Constructor regex authority validator isValid mocked to true.    
    @Test
    public void mockTestIsValid4() {   
        RegexValidator rv = mock(RegexValidator.class);
        UrlValidator uv = new UrlValidator(rv,UrlValidator.NO_FRAGMENTS); 
        // Mock the optional regex authority validator to true.
        when(rv.isValid(anyString())).thenReturn(true);                        
        // Urls forced true by mock
        assertTrue(uv.isValid("http://abc"));
        assertTrue(uv.isValid("https://www.abc.xyz"));
        // Valid urls
        assertTrue(uv.isValid("http://www.abc.com"));       
        assertTrue(uv.isValid("https://www.abc.org"));  
        assertTrue(uv.isValid("ftp://abc.net"));                 
        // Invalid urls
        assertFalse(uv.isValid("http://www.abc.com//")); 
        assertFalse(uv.isValid("http://www.abc.com/path#"));
        assertFalse(uv.isValid("xyz://www.abc.com"));         
	}    
    
    // UrlValidator constructed with default scheme and option of ALLOW_ALL_SCHEMES.  
    // Constructor regex authority validator isValid mocked to true.
    @Test
    public void mockTestIsValid5() {   
        RegexValidator rv = mock(RegexValidator.class);
        UrlValidator uv = new UrlValidator(rv,UrlValidator.ALLOW_ALL_SCHEMES); 
        // Mock the optional regex authority validator to true.
        when(rv.isValid(anyString())).thenReturn(true);                        
        // Urls forced true by mock
        assertTrue(uv.isValid("http://abc"));
        assertTrue(uv.isValid("https://www.abc.xyz"));
        // Valid urls
        assertTrue(uv.isValid("http://www.abc.com"));       
        assertTrue(uv.isValid("https://www.abc.org"));  
        assertTrue(uv.isValid("ftp://abc.net")); 
        assertTrue(uv.isValid("xyz://www.abc.com"));
        assertTrue(uv.isValid("http://www.abc.com/path#"));        
        // Invalid urls
        assertFalse(uv.isValid("http://www.abc.com//")); 
	}    
  
    // UrlValidator constructed with default scheme and default options. 
    // Constructor regex authority validator isValid mocked to false.
    // When authorityValidator.isValid() = false, the authority is further validated.
    @Test
    public void mockTestIsValid6() {   
        RegexValidator rv = mock(RegexValidator.class);
        UrlValidator uv = new UrlValidator(rv,0L); 
        // Mock the optional regex authority validator to false.
        when(rv.isValid(anyString())).thenReturn(false); 
        // clearly invalid urls
        assertFalse(uv.isValid("abc"));
        assertFalse(uv.isValid("abc.com"));
        assertFalse(uv.isValid("www.abc.com"));
        assertFalse(uv.isValid(""));
        assertFalse(uv.isValid(null));
        // different schemes 
        assertTrue(uv.isValid("http://www.abc.com"));       
        assertTrue(uv.isValid("https://www.abc.com"));               
        assertTrue(uv.isValid("ftp://www.abc.com"));  
        assertFalse(uv.isValid("xyz://www.abc.com"));
        // different authorities
        assertTrue(uv.isValid("http://abc.com"));
        assertTrue(uv.isValid("http://abc.org"));
        assertTrue(uv.isValid("http://abc.net"));  
        assertTrue(uv.isValid("http://www.abc.com:0"));
        assertTrue(uv.isValid("http://www.abc.com:999"));
        assertTrue(uv.isValid("http://255.255.255.255"));
        assertTrue(uv.isValid("http://0.0.0.0"));
        assertTrue(uv.isValid("http://255.255.255.255:0"));
        assertTrue(uv.isValid("http://0.0.0.0:999"));
        // urls ending with '/'
        assertTrue(uv.isValid("http://www.abc.com/"));
        assertTrue(uv.isValid("http://www.abc.com:0/"));
        assertTrue(uv.isValid("http://www.abc.com:999/"));  
        // urls ending with '//'
        assertFalse(uv.isValid("http://www.abc.com//"));
        assertFalse(uv.isValid("http://www.abc.com:0//"));
        assertFalse(uv.isValid("http://www.abc.com:999//"));         
        // path added
        assertTrue(uv.isValid("http://www.abc.com/path"));
        // fragment added
        assertTrue(uv.isValid("http://www.abc.com/#"));
        assertTrue(uv.isValid("http://www.abc.com/path#"));
    }
    
    // UrlValidator constructed with a scheme that includes "http" and "https".  Options are default.  
    // Constructor regex authority validator isValid mocked to false.
    // When authorityValidator.isValid() = false, the authority is further validated.
    @Test
    public void mockTestIsValid7() {   
        RegexValidator rv = mock(RegexValidator.class);
        String[] schemes = {"http", "https"};        
        UrlValidator uv = new UrlValidator(schemes,rv,0L);  // default scheme and no options
        // Mock the optional regex authority validator to false.
        when(rv.isValid(anyString())).thenReturn(false);                        
        // different schemes 
        assertTrue(uv.isValid("http://www.abc.com"));       
        assertTrue(uv.isValid("https://www.abc.com"));               
        assertFalse(uv.isValid("ftp://www.abc.com"));  
        assertFalse(uv.isValid("xyz://www.abc.com"));
	}
    
    // UrlValidator constructed with default scheme and option of ALLOW_2_SLASHES.  
    // Constructor regex authority validator isValid mocked to false.
    // When authorityValidator.isValid() = false, the authority is further validated.    
    @Test
    public void mockTestIsValid8() {   
        RegexValidator rv = mock(RegexValidator.class);
        UrlValidator uv = new UrlValidator(rv,UrlValidator.ALLOW_2_SLASHES); 
        // Mock the optional regex authority validator to false.
        when(rv.isValid(anyString())).thenReturn(false);                         
        // urls ending with '//'
        assertTrue(uv.isValid("http://www.abc.com//"));
        assertTrue(uv.isValid("http://www.abc.com:0//"));
        assertTrue(uv.isValid("http://www.abc.com:999//"));              
	} 

    // UrlValidator constructed with default scheme and option of NO_FRAGMENTS. 
    // Constructor regex authority validator isValid mocked to false.
    // When authorityValidator.isValid() = false, the authority is further validated.    
    @Test
    public void mockTestIsValid9() {   
        RegexValidator rv = mock(RegexValidator.class);
        UrlValidator uv = new UrlValidator(rv,UrlValidator.NO_FRAGMENTS); 
        // Mock the optional regex authority validator to false.
        when(rv.isValid(anyString())).thenReturn(false);                        
        // fragment added
        assertFalse(uv.isValid("http://www.abc.com/#"));
        assertFalse(uv.isValid("http://www.abc.com/path#"));
	}   

    // UrlValidator constructed with default scheme and option of ALLOW_ALL_SCHEMES. 
    // Constructor regex authority validator isValid mocked to false.
    // When authorityValidator.isValid() = false, the authority is further validated.    
    @Test
    public void mockTestIsValid10() {   
        RegexValidator rv = mock(RegexValidator.class);
        UrlValidator uv = new UrlValidator(rv,UrlValidator.ALLOW_ALL_SCHEMES); 
        // Mock the optional regex authority validator to false.
        when(rv.isValid(anyString())).thenReturn(false);                        
        // different schemes 
        assertTrue(uv.isValid("http://www.abc.com"));       
        assertTrue(uv.isValid("https://www.abc.com"));               
        assertTrue(uv.isValid("ftp://www.abc.com"));  
        assertTrue(uv.isValid("xyz://www.abc.com"));
        assertFalse(uv.isValid("www.abc.com"));
	}  
    
    /* Failing tests.  Commented out for use with PIT mitant testing which requires a green test. */
    /*
    
    // Query String test.
    // UrlValidator constructed with default scheme and default options. 
    // Constructor regex authority validator isValid mocked to false.
    // When authorityValidator.isValid() = false, the authority is further validated.
    @Test
    public void mockTestIsValid11() {   
        RegexValidator rv = mock(RegexValidator.class);
        UrlValidator uv = new UrlValidator(rv,0L); 
        // Mock the optional regex authority validator to false.
        when(rv.isValid(anyString())).thenReturn(false); 
        // query string added
        assertTrue(uv.isValid("http://www.abc.com/path?abc=123&def=345"));
    }    
    
    // Port number with values from 1000 to 65535
    // UrlValidator constructed with default scheme and default options.
    // Constructor regex authority validator isValid mocked to false.
    // When authorityValidator.isValid() = false, the authority is further validated.    
    @Test
    public void mockTestIsValid12() {   
        RegexValidator rv = mock(RegexValidator.class);
        UrlValidator uv = new UrlValidator(rv,0L); 
        // Mock the optional regex authority validator to false.
        when(rv.isValid(anyString())).thenReturn(false);                        
        assertTrue(uv.isValid("http://www.abc.com:1000"));
        assertTrue(uv.isValid("http://www.abc.com:65535"));
        assertFalse(uv.isValid("http://www.abc.com:65536"));
	}   
    
    // IP address with integer value > 255.
    // UrlValidator constructed with default scheme and default options.
    // Constructor regex authority validator isValid mocked to false.
    // When authorityValidator.isValid() = false, the authority is further validated.    
    @Test
    public void mockTestIsValid13() {   
        RegexValidator rv = mock(RegexValidator.class);
        UrlValidator uv = new UrlValidator(rv,0L); 
        // Mock the optional regex authority validator to false.
        when(rv.isValid(anyString())).thenReturn(false);                        
        // invalid ip address
        assertFalse(uv.isValid("http://256.256.256.256"));
	} 
    */    
}
