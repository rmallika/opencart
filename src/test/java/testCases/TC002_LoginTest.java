package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	
	@Test(groups={"sanity","Master"})
	public void verify_Login() {
		logger.info("*** Login Test Started");
		
		
		//HomePage
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//LoginPage
		LoginPage lp = new LoginPage(driver);
		
		lp.setEmail(p.getProperty("email"));
		lp.setPwd(p.getProperty("password"));
		lp.clickLogin();
		
		//MyAccount page
		
		MyAccountPage map = new MyAccountPage(driver);
		boolean targetpage=map.isMyPageAccountExist();
		
		//Assert.assertEquals(targetpage, true ,"Login failed");
		Assert.assertTrue(targetpage);
		
		logger.info("*** Login Test Finished");
		}
		
		
		
	}
	
	


