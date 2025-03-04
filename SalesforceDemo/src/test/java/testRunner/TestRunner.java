package testRunner;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions (
		
		features = {
	            		
//				".//Featurefiles//",
				".//Featurefiles/CreateServiceRequest.feature",
						
		         },
	    glue={"stepdefinitions","hooks"},
	    publish = true,
		dryRun = false,
//		monochrome = true,
	
				plugin = {"pretty",
						"html:reports/Saleforce_HTML-Reports.html",
						"json:reports/salesforce_JSON_Reports.json",
						"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",	
						"rerun:target/rerun.txt", //Mandatory to capture failures
				        
				        })
public class TestRunner
{

}
