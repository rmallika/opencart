package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountRegistrationPage extends BasePage {
	
	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	
	
@FindBy(xpath="//input[@id='input-firstname']")	
WebElement txtFirstName;

@FindBy(xpath="//input[@id='input-lastname']")
WebElement txtLastName;

@FindBy(xpath="//input[@id='input-email']")
WebElement txtEmail;

@FindBy(xpath="//input[@id='input-telephone']")
WebElement txtTelePhone;

@FindBy(xpath="//input[@id='input-password']")
WebElement txtPassword;

@FindBy(xpath="//input[@id='input-confirm']")
WebElement txtConfirmPassword;

//@FindBy(xpath="//label[normalize-space()='Yes']//input[@name='newsletter']")
//WebElement chknewsLetter;

@FindBy(xpath="//input[@name='agree']")
WebElement chkPolicy;

@FindBy(xpath="//input[@value='Continue']")
WebElement btnContinue;

@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
WebElement msgConfirmation;

public void setFirstName(String fname) {
	txtFirstName.sendKeys(fname);
}

public void setLastName(String fname) {
	txtLastName.sendKeys(fname);
}

public void setEmail(String email) {
	txtEmail.sendKeys(email);
}

public void setTelePhone(String telephone) {
	txtTelePhone.sendKeys(telephone);
}

public void setPassword(String pwd) {
	txtPassword.sendKeys(pwd);
}

public void setConfirmPassword(String pwd) {
	txtConfirmPassword.sendKeys(pwd);
}

//public void setchknewsLetter() {
//	chknewsLetter.click();;
//}

public void chkPolicy() {
	chkPolicy.click();
}

public void btnContinue() {
	btnContinue.click();
}
//sol2
//btnContinue.submit();

//sol3
//Actions act=new Actions(driver);
//act.moveToElement(btnContinue).click().confirm();

//sol4
//JavaScriptExecuter js =(JavaScriptExecuter)driver;
//js.executeScript("arguments[0].click();", btnContinue)

//sol5
//btnContinue.sendKeys(keys.RETURN);

//sol6
//WebDriverWait mywait = new WebDriverWait(driver,Duration.ofSeconds(10));
//mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();

public String getConfirmationMsg() {
	try {
		return(msgConfirmation.getText());
	}catch (Exception e) {
		return(e.getMessage());
	}
}





}
