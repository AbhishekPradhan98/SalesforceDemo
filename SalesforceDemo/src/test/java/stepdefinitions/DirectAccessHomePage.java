package stepdefinitions;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import POM.LoginPage;
import POM.login;
import QA.DriverFactory.DriverFactory;
import io.cucumber.java.en.Given;

public class DirectAccessHomePage 
{
	WebDriver driver;
	public LoginPage loginPage;
	private login lp;
	
	
	


	@Given("user is on Home Page")
	public void user_is_on_home_page() throws Throwable, Throwable 
	{
		driver = DriverFactory.getDriver();	
	     lp=new login(driver);
	     lp.AdminLogin();
	}



}
