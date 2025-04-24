package POM;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import utils.ConfigReader;
import utils.ElementUtils;

public class login 
{
	WebDriver driver;
	private ElementUtils elementUtils;
//	public Properties configProp;
	
	public login(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver,this);
		elementUtils = new ElementUtils(driver);
	}
	By txt_Email=By.xpath("//input[@id='username']");
	By txt_Password= By.xpath("//input[@id='password']");
	By txt_loginBtn= By.xpath("//input[@id='Login']");
	
	public void AdminLogin() throws IOException, Throwable
	{
//		configProp =new Properties();
		Properties configProp = new ConfigReader().intializeProperties();
		FileInputStream configProfile=new FileInputStream("config.properties");
		configProp.load(configProfile);
		
//		Thread.sleep(2000);
		WebElement element=driver.findElement(txt_Email);
		element.sendKeys(configProp.getProperty("EmaiCredentials"));
		
		driver.findElement(txt_Password).sendKeys(configProp.getProperty("Password"));
	    driver.findElement(txt_loginBtn).click();
	    	    
	}
	
}
