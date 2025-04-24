 package QA.DriverFactory;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import utils.CommonUtils;

public class DriverFactory
{
public static WebDriver driver=null;
	
	
	public static WebDriver initializeBrowser(String browserName) throws Throwable
	{
		
		if(browserName.equals("chrome")) 
		{
//			WebDriverManager.chromedriver().setup();
			 // Create ChromeOptions instance
	        ChromeOptions options = new ChromeOptions();
//	        options.addArguments("--headless"); // Run Chrome in headless mode
	        // Disable notifications
	        options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);
			

			
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS) ;
			driver.manage().timeouts().pageLoadTimeout(100,TimeUnit.SECONDS);			

			
		}else if(browserName.equals("firefox")) 
		{
//			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			
		}
		else if(browserName.equals("edge"))
		{
//			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			
		}
		else if(browserName.equals("safari")) 
		{
			
			driver = new SafariDriver();
			
		}
		
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(CommonUtils.PAGE_LOAD_TIME));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(CommonUtils.IMPLICIT_WAIT_TIME));
		
		return driver;
		
	}
	
	public static WebDriver getDriver() {
		
		return driver;
		
	}
	
}
