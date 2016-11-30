CS362-F16 Project Team

Erin Sullens
Jason Ghiraldini
Keith Adkins
Markus Woltjer

Description of how to run our test suite:

  1. Go to the directory /Apache-URLValidator/BuggyURLValidator
  
  2. Running our test suite which includes unit tests for coverage, mock tests, and random tests, 
     enter the following script at the command prompt.  This will run all test files listed in the
     script.
     
     $ run-unit-tests

  3. Running Pit mutation tests, enter the following script at the command prompt.  This will run all
     test files in src/test that are named *_Green.java.  This is needed since Pit mutation 
     testing requires a green test suite with no faults or errors.
     
     $ run-mutation-tests   

  4. Deploy an html report to your ~/public_html folder:
  
    $ deploy-pit-report 
     
     
    