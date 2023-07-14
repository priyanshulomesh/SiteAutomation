package MyTestCases;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import MyPageObjects.LoginPage;

public class LoginTest extends BaseClass {
    ExtentReports extent=new ExtentReports();

    ExtentSparkReporter spark=new ExtentSparkReporter("ExtentTest.html");
    WebDriverWait wait;
    
    @BeforeClass
    public void start() {
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("Report");
        extent.attachReporter(spark);
    }
    
    
    @AfterClass
    public void end() {
        extent.flush();
    }
    
    
    public void login(ExtentTest test) {
        wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

        LoginPage lp=new LoginPage(driver);
        lp.clickLogin();
        test.info("Login Clicked");
        
        
        // Find the modal element
        WebElement modal = driver.findElement(By.id("loginModal"));

        // Wait for the modal to become visible and have non-zero size
        wait.until(ExpectedConditions.visibilityOf(modal));
        wait.until(ExpectedConditions.elementToBeClickable(modal));
        wait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(modal),
                ExpectedConditions.not(ExpectedConditions.attributeToBe(modal, "style", "display: none;"))
        ));

        lp.setEmail(email);

        test.info("Email Sent");
        lp.setPassword(password);
        test.info("Password Sent");

        lp.clickSignin();
        test.info("Signin Clicked");
        
        try {
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loginModal")));
        
                // Wait for redirected page to load
                wait.until(ExpectedConditions.titleContains("NULL POINTER"));
        }
        catch(Exception e) {};
    }
    @Test
    public void testcase1() {
        
        //to test login
        ExtentTest test=extent.createTest("Login").assignAuthor("tester");
        driver.get(baseUrl);
        try {
        login(test);}
        catch(Exception e)
        {
        	test.fail("Login Unsuccessful");
            test.addScreenCaptureFromPath(capturescreenshot(driver));
            Assert.assertTrue(false);
        }
        
        try{
            driver.findElement(By.id("loginBtn"));
            //if exists then unable to login
            test.fail("Login Unsuccessful");
            test.addScreenCaptureFromPath(capturescreenshot(driver));
            Assert.assertTrue(false);
        }catch(Exception e) {
            //else throws exception and we're logged in
            test.pass("Logged In Successfully");
            Assert.assertTrue(true);
        }
        
        }
        
        @Test
        public void testcase2() {
            //to test profile page opening
            ExtentTest test=extent.createTest("Profile Page").assignAuthor("tester");
            try {
            driver.get(baseUrl);
            login(test);
            
            driver.findElement(By.id("dropdown-button-toggle")).click();
            test.info("DropDown Toggled");
            
            // Wait for the dropdown menu to be visible
            By dropdownMenuLocator = By.id("loginDropdownMenu");
            wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownMenuLocator));
            
            driver.findElement(By.id("profile-btn")).click();
            test.info("Profile Button Clicked");

//          // Wait for the contents of the dropdown to be loaded
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
            test.info("Captured Page Title"+driver.getTitle());
            if(driver.getTitle().equals("Your profile")) {
                test.pass("Profile page loaded successfully");
                Assert.assertTrue(true);
            }
            else {
                test.fail("Could not load Profile page");
                test.addScreenCaptureFromPath(capturescreenshot(driver));
                Assert.assertTrue(false);
            }
            }catch(Exception e) {
            	test.fail("Could not load Profile page");
                test.addScreenCaptureFromPath(capturescreenshot(driver));
                Assert.assertTrue(false);
            }
        }
        @Test
        public void testcase3() {
            //to test logout
            ExtentTest test=extent.createTest("Logout").assignAuthor("tester");
            try {
            driver.get(baseUrl);
            login(test);
            driver.findElement(By.id("dropdown-button-toggle")).click();
            
            test.info("DropDown Toggled");
            // Wait for the dropdown menu to be visible
            By dropdownMenuLocator = By.id("loginDropdownMenu");
            wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownMenuLocator));
            
            driver.findElement(By.id("logout-btn")).click();
            test.info("Logout Button Clicked");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
            //test.info("Captured Page Title"+driver.getTitle());
            if(driver.getTitle().equals("NULL POINTER")) {
                test.pass("Logged out successfully");
                Assert.assertTrue(true);
            }
            
            else {
                System.out.println("hi");
                test.fail("Logout unsuccessful");
                test.addScreenCaptureFromPath(capturescreenshot(driver));
                Assert.assertTrue(false);
            }
            }catch(Exception e){
                System.out.println("hi");
                test.fail("Logout unsuccessful");
                test.addScreenCaptureFromPath(capturescreenshot(driver));
                Assert.assertTrue(false);
            }
        }
            
        public static String capturescreenshot(WebDriver driver)
        {
        	File srcFile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        	File destinationFilePath=new File("src/../images/screenshot"+System.currentTimeMillis()+".png");
        	String absolutePathLocation=destinationFilePath.getAbsolutePath();
        	try {
        		FileUtils.copyFile(srcFile,destinationFilePath);
    
        	}
        	catch(IOException e)
        	{
        		e.printStackTrace()
;        	}
        	return absolutePathLocation;
        }
 
            
            
    }

