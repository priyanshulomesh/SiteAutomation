package MyTestCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import MyUtilities.ReadConfig;

public class BaseClass {
	
	public String baseUrl;
    public String email;
    public String password;
    public static WebDriver driver;
    ReadConfig readConfig;
	
	@BeforeMethod
	public void setup() {
		readConfig=new ReadConfig();
		
		baseUrl=readConfig.getApplicationURL();
		email=readConfig.getUserName();
		password=readConfig.getPassword();
		System.setProperty("webdriver.chrome.driver","C:\\Users\\ACER\\eclipse-workspace\\SiteAutomation\\Drivers\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}
