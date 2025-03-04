package POM;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.CommonUtils;
import utils.ElementUtils;

public class LoginPage {
	
	WebDriver driver;
	private ElementUtils elementUtils;
	public Properties configProp;
	
	public LoginPage(WebDriver driver) 
	{
		
		this.driver = driver;
		PageFactory.initElements(driver,this);
		elementUtils = new ElementUtils(driver);
		
	}
	
	@FindBy(xpath="//input[@id='username']")
	private WebElement emailField;
	
	//Enter Password 
	@FindBy(xpath= "//input[@id='password']")
	private WebElement passwordField;
	
	//click Login Button
	@FindBy(xpath="//input[@id='Login']")
	private WebElement ContinueButton;
	
	
	public void enterEmailAddress(String emailText) 
	{
		
		elementUtils.typeTextIntoElement(emailField, emailText,CommonUtils.EXPLICIT_WAIT_BASIC_TIME);
		
	}

	public void enterPassword(String passwordText) 
	{
		
		elementUtils.typeTextIntoElement(passwordField, passwordText,CommonUtils.EXPLICIT_WAIT_BASIC_TIME);
		
	}
	
	
	public AccountPage clickOnLoginButton()  
	{
		
		elementUtils.clickOnElement(ContinueButton,CommonUtils.EXPLICIT_WAIT_BASIC_TIME);
		return new AccountPage(driver);
		
		
	}

}
