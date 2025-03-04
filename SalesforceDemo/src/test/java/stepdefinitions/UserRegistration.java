package stepdefinitions;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import QA.DriverFactory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserRegistration 
{
	WebDriver driver;



		@Given("the user launches the browser and navigates to https:\\/\\/automationexercise.com\"")
		public void the_user_launches_the_browser_and_navigates_to_https_automationexercise_com() 
		{
			driver = DriverFactory.getDriver();	
        
		}


	@Then("the home page should be visible successfully")
	public void the_home_page_should_be_visible_successfully() 
	{
      WebElement homepage=driver.findElement(By.xpath("//a[normalize-space()='Home']"));
      if(homepage.isDisplayed())
      {
    	  System.out.println("Home is display : Test Pass");
      }

	}
	@When("the user clicks on Signup\\/Login button")
	public void the_user_clicks_on_signup_login_button() 
	{
		WebElement button=driver.findElement(By.xpath("//a[normalize-space()='Signup / Login']"));
		 if (button.getText().contains("Signup / Login")) 
		 {
			 driver.findElement(By.xpath("//a[normalize-space()='Signup / Login']")).click();
		 }else 
		 {
			System.out.println("Signup / Login button is not display : Test Fail");
		 }
		 

	}
	@Then("New User Signup! should be visible")
	public void new_user_signup_should_be_visible() 
	{
		 WebElement homePageElement = driver.findElement(By.xpath("//button[normalize-space()='Signup']"));
	        Assert.assertTrue(homePageElement.isDisplayed());

	}
	@When("the user enters name {string} and email {string}")
	public void the_user_enters_name_and_email(String Name, String Email) 
	{
       driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys(Name);
       driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys(Email);


	}
	@When("clicks on the Signup button")
	public void clicks_on_the_signup_button() 
	{
	    driver.findElement(By.xpath("//button[normalize-space()='Signup']")).click();
	}
	@Then("ENTER ACCOUNT INFORMATION should be visible")
	public void enter_account_information_should_be_visible() 
	{
        WebElement ele= driver.findElement(By.xpath("//b[normalize-space()='Enter Account Information']"));
        if(ele.isDisplayed())
        {
        	System.out.println("ENTER ACCOUNT INFORMATION Tittle is display : Test Pass");
        }else 
        {
        	System.out.println("ENTER ACCOUNT INFORMATION Tittle is not display : Test Fail");
		}

	}
	@When("the user fills account details:")
	public void the_user_fills_account_details(io.cucumber.datatable.DataTable dataTable) throws Throwable 
	{
		 List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
	        for (Map<String, String> row : data) {
	            String title = row.get("Title");
	            String password = row.get("Password");
	            String dob = row.get("Date of Birth");
	            
	            
	            if (title.equalsIgnoreCase("Mr.")) 
	            {
	                driver.findElement(By.xpath("//input[@type='radio']")).click();
	            } else 
	            {
	                driver.findElement(By.xpath("(//input[@type='radio'])[2]")).click();
	            }
	            
	           // to scroll slowly 
	            JavascriptExecutor js = (JavascriptExecutor) driver;

	         // Slow scroll loop (like real mouse scroll)
	            for (int i = 0; i < 10; i++) 
	            {  // Scroll in small steps
	                js.executeScript("window.scrollBy(0, 20);"); // Scroll 100 pixels at a time
	                Thread.sleep(300); // Pause 300ms for smooth effect
	            }
	            
	            
	         // Filling form fields	         
	            driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);

	            // Date of Birth
	            String[] dobParts = dob.split("/");
	            driver.findElement(By.xpath("//select[@id='days']")).sendKeys(dobParts[0]);
	            driver.findElement(By.xpath("//select[@id='months']")).sendKeys(dobParts[1]);
	            
	            driver.findElement(By.xpath("//select[@id='years']")).sendKeys(dobParts[2]);
	        }      
	        
	}
	@When("selects {string}")
	public void selects(String checkbox) 
	{
		 if (checkbox.equalsIgnoreCase("Sign up for our newsletter!")) 
		 {
	            driver.findElement(By.xpath("//input[@id='newsletter']")).click();
	        } else if (checkbox.equalsIgnoreCase("Receive special offers from our partners!")) 
	        {
	            driver.findElement(By.xpath("//input[@id='optin']")).click();
	        }

	}
	@When("fills personal details:")
	public void fills_personal_details(io.cucumber.datatable.DataTable dataTable) 
	{
       List<Map<String, String>>data=dataTable.asMaps(String.class,String.class);
       for (Map<String, String> row : data) 
       {
           String firstName = row.get("First Name");
           String lastname=row.get("Last Name");
           
           
           
           driver.findElement(By.xpath("//input[@id='first_name']")).sendKeys(firstName);
           driver.findElement(By.xpath("//input[@id='last_name']")).sendKeys(lastname);

       }

	 
	}
	@When("clicks on Create Account button")
	public void clicks_on_create_account_button() {


	}
	@Then("ACCOUNT CREATED! should be visible")
	public void account_created_should_be_visible() {


	}
	@When("the user clicks on Continue button")
	public void the_user_clicks_on_continue_button() {


	}
	@Then("Logged in as {string} should be visible")
	public void logged_in_as_should_be_visible(String string) {


	}
	@When("the user clicks on Delete Account button")
	public void the_user_clicks_on_delete_account_button() 
	{


	}
	@Then("{string} should be visible")
	public void should_be_visible(String string) 
	{


	}
	@Then("the user clicks Continue button")
	public void the_user_clicks_continue_button() 
	{


	}




}
