package MyPageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver ldriver;
	public LoginPage(WebDriver rdriver) 
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver,this);
	}
	
	@FindBy(id="loginBtn")
    WebElement loginBtn;
    @FindBy(id="loginEmail")
    WebElement loginEmail;
    @FindBy(id="loginPassword")
    WebElement loginPassword;
    @FindBy(id="loginSubmit")
    WebElement signinBtn;
    @FindBy(id="signup-btn")
    WebElement signupBtn;
    @FindBy(id="register-btn")
    WebElement registerBtn;
    @FindBy(id="registerEmail")
    WebElement regEmail;
    @FindBy(id="password")
    WebElement regPass;
    @FindBy(id="cnfrmpassword")
    WebElement regCnfrmPass;
    @FindBy(id="usr")
    WebElement regName;
    
    public void clickLogin() {
        loginBtn.click();
    }
    public void clicksignUp() {
        signupBtn.click();
    }
    public void clickRegister() {
        registerBtn.click();
    }
    public void setEmail(String email) {
        loginEmail.sendKeys(email);
    }
    public void setRegisterEmail(String email) {
        regEmail.sendKeys(email);
    }
    public void setRegisterName(String name) {
        regName.sendKeys(name);
    }
    public void setPassword(String password) {
        loginPassword.sendKeys(password);
    }
    public void setRegisterPassword(String password) {
        regPass.sendKeys(password);
        regCnfrmPass.sendKeys(password);
    }
    public void clickSignin() {
        signinBtn.click();
    }
    
}
