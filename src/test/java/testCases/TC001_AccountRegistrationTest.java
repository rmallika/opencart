package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration()
	{
		logger.info("*** Starting TC001_AccountRegistrationTest ***");
		
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicken on myAccount Link...");
		
		
		hp.clickRegister();
		logger.info("Clicked on Register Link");
		
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
	
		logger.info("Providing Customer Details....");
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		regpage.setEmail(randomString()+"@gmail.com");
		regpage.setTelePhone(randomNumber());
		
		String pwd = randomAlphanumeric();
		regpage.setPassword(pwd);
		regpage.setConfirmPassword(pwd);
		
		regpage.chkPolicy();
		regpage.btnContinue();
		
		logger.info("Validating expected message...");
		String confmsg=regpage.getConfirmationMsg();
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
		
			logger.error("Test failed..");
			logger.debug("Debug logs...");
			Assert.assertTrue(false);
		}
		
		//Assert.assertEquals(confmsg, "Your Account Has Been Created!!!");
		}
		catch(Exception e)
		{
			
			Assert.fail();
		}
		
	}
	
	
}
