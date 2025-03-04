package testRunner;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import QA.DriverFactory.DriverFactory;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions (
		
		features = {
	            		
//				".//Featurefiles//",
//				".//Featurefiles/Login.feature",
				".//Featurefiles/RegisterUser.feature"
						
		         },
	    glue={"stepdefinitions","hooks"},
	    publish = true,
		dryRun = false,
//		monochrome = true,
				
				plugin = {"pretty",
						"html:reports/WFM_HTML-Reports.html",
						"json:reports/WFM_JSON_Reports.json",
						"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",	
						"rerun:target/rerun.txt", //Mandatory to capture failures
				        
				        },
               tags = "@smoke" // Run only scenarios with @smoke tag
		)
public class TestNGRunner extends AbstractTestNGCucumberTests
{
	 @BeforeClass
	    @Parameters("browser")
	    public void setUp(String browser) throws Throwable 
	{
//		    System.out.println("Initializing browser: " + browser + " | Thread ID: " + Thread.currentThread().getId());
//	        DriverFactory.initializeBrowser(browser);  // Initialize driver for each thread
	    }

	    @Override
	    @DataProvider(parallel = true)  // Enables parallel execution
	    public Object[][] scenarios() {
	        return super.scenarios();
	    }
}
