package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.CommonUtils;
import utils.ElementUtils;

public class AccountPage {
	
	WebDriver driver;
	private ElementUtils elementUtils;
	
	public AccountPage(WebDriver driver) 
	{
		
		this.driver = driver;
		PageFactory.initElements(driver,this);
		elementUtils = new ElementUtils(driver);
		
	}
	
	@FindBy(xpath ="//span[normalize-space()='Create Service Request']")
	private WebElement ModuleName;
	
	public boolean displayModuleName() 
	{
		
		return elementUtils.displayStatusOfElement(ModuleName,CommonUtils.EXPLICIT_WAIT_BASIC_TIME);
		
	}

}
