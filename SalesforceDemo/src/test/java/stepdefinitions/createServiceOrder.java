package stepdefinitions;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import POM.AccountPage;
import POM.LoginPage;
import QA.DriverFactory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonUtils;

public class createServiceOrder {
	
	WebDriver driver;
	public LoginPage loginPage;
	private AccountPage accountPage;
	private CommonUtils commonUtils;
	
	@Given("User navigates to login page")
	public void user_navigates_to_login_page() {
		
		driver = DriverFactory.getDriver();	
	}
	
	
	@When("^User enters valid email address (.+) into email field$")
	public void User_enters_valid_email_address_into_email_field(String emailText) throws Throwable 
	{
		loginPage=new LoginPage(driver);
		loginPage.enterEmailAddress(emailText);

		
		
	}
	
	@And("^User enters valid password (.+) into password field$")
	public void user_enters_valid_password_into_password_field(String passwordText) 
	{
	    
		loginPage.enterPassword(passwordText);
		
	}

	@And("User clicks on Login button")
	public void user_clicks_on_login_button() {
	    
		accountPage = loginPage.clickOnLoginButton();
				
	}

	@Then("User should get successfully logged in and Create Service Request link is display")
	public void user_should_get_successfully_logged_in_and_create_service_request_link_is_display() 
	{
		Assert.assertTrue(accountPage.displayModuleName());
	}



	@When("user click Create Service Request link")
	public void user_click_create_service_request_link() 
	{
	    driver.findElement(By.xpath("//lightning-layout-item[5]//slot[1]//lightning-icon[1]")).click();
		
	}
	@Then("Client Information page is display")
	public void client_information_page_is_display() 
	{
		 WebElement titleElement = driver.findElement(By.xpath("(//h2[normalize-space()='CLIENT INFORMATION'])[1]"));
	        String actualTitle = titleElement.getText();
	       
	        if(titleElement.isDisplayed())
	        {
	        	System.out.println("Client Information Page is Display : Test Pass");
	        }else 
	        {
	        	System.out.println("Client Information Page is not  Display : Test Fail");

			}

	       
	}



	@When("the user enters the following details:")
	public void the_user_enters_the_following_details(io.cucumber.datatable.DataTable dataTable) throws Throwable 
	{  
		// Convert DataTable to List of Maps
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> row : data) 
        {
            String chooseAccount = row.get("Choose an Account");
            String branch = row.get("Branch");
            String branchPerson = row.get("Branch Person");
            String loanType = row.get("Loan Type");
            String referenceNumber = row.get("Reference Number");
            String mobileNumber = row.get("Branch Person Mobile Number");
            String loanAmount = row.get("Loan Amount (INR)");	
        	
            //to select Account
        	driver.findElement(By.xpath("//input[@name='AccountId']")).sendKeys(chooseAccount);
        	Thread.sleep(2000);
        	WebElement ulelement=driver.findElement(By.xpath("//ul[@role='menu']"));
            
        	List<WebElement>fromlist=ulelement.findElements(By.tagName("li"));
        	System.out.println("Total Number of Account" + fromlist.size());
        	
        	for(int i=0;i<fromlist.size();i++)
        	{
        		if(fromlist.get(i).getText().contains("DMI HOUSING FINANCE PRIVATE LIMITED"))
        		{
        			fromlist.get(i).click();
        			break;
        		}
        		
        		
        	}
        	
        	
            // Entering Branch
           WebElement Branch= driver.findElement(By.xpath("(//input[@class='slds-input'])[3]"));
           Branch.click();
           Thread.sleep(1000);
           driver.findElement(By.xpath("//div[@class='dropdown-item']")).click();     
          
          
//            // Entering Branch Person name
           WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Wait for the Branch Person dropdown to be clickable
        WebElement BranchPersonName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[starts-with(@id, 'combobox-button')]")));
        System.out.println("User selects Branch Person Name: " + branchPerson);
        // Click the dropdown
        BranchPersonName.click();
        Thread.sleep(1000);
        // Select the first option using ARROW_DOWN and ENTER
        BranchPersonName.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(2000);
        BranchPersonName.sendKeys(Keys.ENTER);
           
   
//            // Selecting Loan Type
           driver.findElement(By.xpath("(//input[@class='slds-input'])[6]")).click();
           JavascriptExecutor js = (JavascriptExecutor) driver;
           WebElement option = driver.findElement(By.xpath("//div[@data-value='Home Loan Purchase']"));
           js.executeScript("arguments[0].click();", option);
//            
//            // Entering Reference Number
            WebElement ReferenceNumber = driver.findElement(By.xpath("(//input[@class='slds-input'])[4]"));
            ReferenceNumber.sendKeys(referenceNumber);
            
//            // Entering Loan Amount
            WebElement LoanAmount = driver.findElement(By.xpath("(//input[@class='slds-input'])[7]"));
            LoanAmount.sendKeys(loanAmount);
        
	}
	}
 @When("user clicks on Save&Next")
 public void user_clicks_on_save_next() 
 {
	 // Clicking Save & Next
  WebElement saveNextButton = driver.findElement(By.xpath("//button[normalize-space()='Save & Next']"));
  saveNextButton.click();
 }



 @Then("success message is dislay and user navigate to Service Details page.")
 public void success_message_is_dislay_and_user_navigate_to_service_details_page() throws Throwable 
 {
    WebElement successMessage= driver.findElement(By.xpath("//span[@class='toastMessage forceActionsText']"));
    
    if(successMessage.isDisplayed())
    {
    	System.out.println("Success Message is:" + successMessage.getText());
    }else 
    {
    	System.out.println("Success Message is not display :" + "Test Fail");

	}

	 Thread.sleep(2000);
 }


 @When("user Select Sevices and Technical Report Type")
 public void user_select_sevices_and_technical_report_type() throws Throwable 
 {
	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	// Wait for "Select Services" input and click
     WebElement selectServices = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[starts-with(@id, 'input')])[1]")));
     selectServices.click();

     // Wait for the checkbox and click
     WebElement checkBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='slds-checkbox_faux'])[2]")));
     checkBox.click();

     System.out.println("Successfully clicked Select Services and selected the checkbox.");
     selectServices.click();
     
   
     
     
  // Wait for "Select Tech Report" input and click
     WebElement selectTechReport = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[starts-with(@id, 'input')])[2]")));
     selectTechReport.click();
     Thread.sleep(1000);
     // Wait for the checkbox and click
     WebElement checkBox1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='slds-checkbox_faux'])[2]")));
     checkBox1.click();
     selectTechReport.click();
 }


 @When("User is on the Add Applicant\\/Co-Applicant Details section")
 public void user_is_on_the_add_applicant_co_applicant_details_section() 
 {
       System.out.println("User move to Add Applicant // Co-Applicant Details section");

 }



 @When("User enters Name as {string}, selects Customer Type and Applicant Type from dropdowns")
 public void user_enters_name_as_selects_customer_type_and_applicant_type_from_dropdowns(String name) 
 {
     driver.findElement(By.xpath("(//input[@class='slds-input'])[3]")).sendKeys(name);
     
     //user select customer type dropdown
     WebElement ele=driver.findElement(By.xpath("(//button[starts-with(@id, 'combobox-button')])[1]"));
     ele.click();
     ele.sendKeys(Keys.ARROW_DOWN.ENTER);
     
     //user select Application type dropdown
     WebElement ApplicationType=driver.findElement(By.xpath("(//button[starts-with(@id, 'combobox-button')])[2]"));
     ApplicationType.click();
     ApplicationType.sendKeys(Keys.ARROW_DOWN.ENTER);
 }



 @When("User enters Mobile Number and Enter Additional Information and Add Address")
 public void user_enters_mobile_number_and_enter_additional_information_and_add_address() 
 {
	// Generate a random 10-digit mobile number
			 commonUtils=new CommonUtils();
		     driver.findElement(By.xpath("(//input[@class='slds-input'])[4]")).sendKeys(CommonUtils.generateRandomMobileNumber());
		     
		   //enter Address 
		     driver.findElement(By.xpath("//button[normalize-space()='Additional Information']")).click();  
		     
		     //enter details
		     driver.findElement(By.xpath("(//input[starts-with(@id, 'input')])[5]")).sendKeys("CHKPG1234R");  //enter pan
		     driver.findElement(By.xpath("(//input[starts-with(@id, 'input')])[7]")).sendKeys("909064737272");  //enter Aadhar 
		    WebElement status= driver.findElement(By.xpath("(//button[@type='button'])[15]"));  //enter marital status 
		    status.click();
		    status.sendKeys(Keys.ARROW_DOWN.ENTER);
		    
		    
		     driver.findElement(By.xpath("(//input[starts-with(@id, 'input')])[9]")).sendKeys("Genus");  //enter Electricity Provider
		     driver.findElement(By.xpath("(//input[starts-with(@id, 'input')])[11]")).sendKeys("Abhi");  //enter Spouse Name
		    
		     
		     driver.findElement(By.xpath("(//input[starts-with(@id, 'input')])[8]")).sendKeys("13-Mar-1998");  //enter Date of Birth
		     
		     driver.findElement(By.xpath("(//input[starts-with(@id, 'input')])[10]")).sendKeys("123454321");  //enter Electricity Counsumer Number
		     
		    WebElement gender= driver.findElement(By.xpath("(//button[@type='button'])[16]"));
		    gender.click();
		    
		    gender.sendKeys(Keys.ARROW_DOWN.ENTER);  //enter Gender as male
		    
		    
		    //click save button
		    driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
 }



 @When("User click Add Address button and fill Required details :")
 public void user_click_add_address_button_and_fill_required_details(io.cucumber.datatable.DataTable dataTable) throws Throwable 
 {
	 
	 driver.findElement(By.xpath("(//button[contains(text(),'Add Address')])[1]")).click();   //click Add Address link

	    //Address detail page 
	// Convert DataTable to List of Maps
     List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

     for (Map<String, String> row : data) 
     {
         String Addre = row.get("Address");
         String pin = row.get("Pincode");
         
         
       driver.findElement(By.xpath("(//input[@class='slds-input'])[5]")).sendKeys(Addre);
       WebElement pincode=driver.findElement(By.xpath("//input[starts-with(@id, 'combobox-input-')]"));
  	   pincode.sendKeys(pin);
        Thread.sleep(2000); 
        pincode.sendKeys(Keys.ARROW_DOWN.ENTER);
        Thread.sleep(2000);
//        Thread.sleep(500); // Small wait to ensure dropdown item is selected
//        pincode.sendKeys(Keys.ENTER);
  	    

        driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();   //click save button
     }
      
	 
 }


 @When("User navigates to the Add Collateral Details section and verify Collateral Id it should be disabled and selects Collateral Type from dropdown")
 public void user_navigates_to_the_add_collateral_details_section_and_verify_collateral_id_it_should_be_disabled_and_selects_collateral_type_from_dropdown() throws Throwable 
 {
	 WebElement field = driver.findElement(By.xpath("(//input[@class='slds-input'])[5]"));

	 if (!field.isEnabled()) 
	 {
	     System.out.println("Field is disabled.");
	 } else 
	 {
	     System.out.println("Field is enabled.");
	 }
	 
	 //
	 //in Add Collateral Details select dropdown
     WebElement button = driver.findElement(By.xpath("(//button[starts-with(@id, 'combobox-button-')])[3]"));
     JavascriptExecutor js = (JavascriptExecutor) driver;
     js.executeScript("arguments[0].click();", button);
     
     button.sendKeys(Keys.ARROW_DOWN.ENTER);
     
   
	 
 }


 @When("User clicks the {string} button and fills in the required details:")
 public void user_clicks_the_button_and_fills_in_the_required_details(String string, io.cucumber.datatable.DataTable dataTable) throws Throwable 
 {
	
	 
	 
	 //click Add Adress link
     driver.findElement(By.xpath("(//button[contains(text(),'Add Address')])[1]")).click();
     
  // Convert DataTable to List of Maps
     List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

     for (Map<String, String> row : data) 
     {
         String SecAddress = row.get("Address");
         String pincode = row.get("Pincode");
         
         
         driver.findElement(By.xpath("(//input[@class='slds-input'])[6]")).sendKeys(SecAddress);
    	 WebElement pincode1=driver.findElement(By.xpath("//input[starts-with(@id, 'combobox-input-')]"));
    	 pincode1.sendKeys(pincode);
         Thread.sleep(2000);
         pincode1.sendKeys(Keys.ARROW_DOWN.ENTER);
         Thread.sleep(2000);
        
         driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();   //click save button
         driver.findElement(By.xpath("//button[normalize-space()='Save & Next']")).click();  //click save and next button
     }
     
 }
    @When("user clicks on Submit Button")
    public void user_clicks_on_submit_button() 
    {
    	// Wait until the Submit button is clickable
//    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//    	WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'saveBtn') and text()='Submit']")));
//
//    	// Click the Submit button
//    	submitButton.click();
    	
    	// Wait for the loader (spinner) to disappear
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//lightning-spinner")));

    	// Wait until the Submit button is clickable
    	WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'saveBtn') and text()='Submit']")));

    	// Click the Submit button
    	submitButton.click();


    }









 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 @When("User enters Mobile Number and Enter Additional Information")
 public void user_enters_mobile_number_and_enter_additional_information() throws Throwable 
 {
	// Generate a random 10-digit mobile number
//		 commonUtils=new CommonUtils();
//	     driver.findElement(By.xpath("(//input[@class='slds-input'])[4]")).sendKeys(CommonUtils.generateRandomMobileNumber());
//	     
//	     //enter Adress
//	     driver.findElement(By.xpath("//button[normalize-space()='Additional Information']")).click();
//	     
//	     //enter details
//	     driver.findElement(By.xpath("(//input[starts-with(@id, 'input')])[5]")).sendKeys("CHKPG1234R");  //enter pan
//	     driver.findElement(By.xpath("(//input[starts-with(@id, 'input')])[7]")).sendKeys("909064737272");  //enter Aadhar 
//	    WebElement status= driver.findElement(By.xpath("(//button[@type='button'])[15]"));  //enter marital status 
//	    status.click();
//	    status.sendKeys(Keys.ARROW_DOWN.ENTER);
//	    
//	    
//	     driver.findElement(By.xpath("(//input[starts-with(@id, 'input')])[9]")).sendKeys("Genus");  //enter Electricity Provider
//	     driver.findElement(By.xpath("(//input[starts-with(@id, 'input')])[11]")).sendKeys("Abhi");  //enter Spouse Name
//	    
//	     
//	     driver.findElement(By.xpath("(//input[starts-with(@id, 'input')])[8]")).sendKeys("13-Mar-1998");  //enter Date of Birth
//	     
//	     driver.findElement(By.xpath("(//input[starts-with(@id, 'input')])[10]")).sendKeys("123454321");  //enter Electricity Counsumer Number
//	     
//	    WebElement gender= driver.findElement(By.xpath("(//button[@type='button'])[16]"));
//	    gender.click();
//	    
//	    gender.sendKeys(Keys.ARROW_DOWN.ENTER);  //enter Gender as male
//	    
//	    
//	    //click save button
//	    driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
	    
	    
	    //------------------------------------Add Address
	    
//	    driver.findElement(By.xpath("(//button[contains(text(),'Add Address')])[1]")).click();   //click Add Address link
//
//	    //Address detail page 
//	    driver.findElement(By.xpath("(//input[@class='slds-input'])[5]")).sendKeys("Mansarovar");
//	    
//	     WebElement pincode=driver.findElement(By.xpath("//input[starts-with(@id, 'combobox-input-')]"));
//	     pincode.sendKeys("302020");
//         Thread.sleep(2000); 
//         pincode.sendKeys(Keys.ARROW_DOWN);
//         Thread.sleep(500); // Small wait to ensure dropdown item is selected
//         pincode.sendKeys(Keys.ENTER);
//	    
//	    //--------
//
//         driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();   //click save button
//         
        
         //in Add Collateral Details select dropdown
         WebElement button = driver.findElement(By.xpath("(//button[starts-with(@id, 'combobox-button-')])[3]"));
         JavascriptExecutor js = (JavascriptExecutor) driver;
         js.executeScript("arguments[0].click();", button);
         
         button.sendKeys(Keys.ARROW_DOWN.ENTER);
         
         //click Add Adress link
         driver.findElement(By.xpath("(//button[contains(text(),'Add Address')])[1]")).click();
         
         driver.findElement(By.xpath("(//input[@class='slds-input'])[6]")).sendKeys("Mansarovar");
 	    
         
 	      WebElement pincode1=driver.findElement(By.xpath("//input[starts-with(@id, 'combobox-input-')]"));
 	      pincode1.sendKeys("302020");
          Thread.sleep(2000);
          pincode1.sendKeys(Keys.ARROW_DOWN.ENTER);
      
          
          
          
          driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();   //click save button

          driver.findElement(By.xpath("//button[normalize-space()='Save & Next']")).click();  //click save and next button
	     
          
          
          //service details page is display here user can upload file and click submit button

//        driver.findElement(By.xpath("(//button[@class='slds-button slds-button_neutral'])[2]")).click();  
          
          // Wait for the button to be visible and clickable
          WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
          WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'slds-button_neutral') and contains(@class, 'saveBtn')]")));   


          
          


 }
 
 
 
 
 @When("User navigates to the Add Collateral Details section")
 public void user_navigates_to_the_add_collateral_details_section() 
 {
     System.out.println("user Navigate to Add Collateral Details section");
 }

 @When("User verify Collateral Id it should be disabled and selects Collateral Type from dropdown")
 public void user_verify_collateral_id_it_should_be_disabled_and_selects_collateral_type_from_dropdown() throws Throwable 
 {
	 WebElement field = driver.findElement(By.xpath("(//input[@class='slds-input'])[5]"));

	 if (!field.isEnabled()) 
	 {
	     System.out.println("Field is disabled.");
	 } else 
	 {
	     System.out.println("Field is enabled.");
	 }

//user select Collateral Type from dropdown
	 

     
 }








 @When("User provides City as {string} and Address as {string}")
 public void user_provides_city_as_and_address_as(String string, String string2) 
 {
    
 }



 
 
 




 
}

	
//	
//	@When("User dont enter email address into email field")
//	public void user_dont_enter_email_address_into_email_field() throws Throwable 
//	{
//		loginPage=new LoginPage(driver);
//		loginPage.enterEmailAddress("");
//		Thread.sleep(7000);
//	    
//	}

	


