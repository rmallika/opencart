package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.net.URL;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@BeforeClass(groups = {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws IOException
	{
		//Loading config.properties file
		
		FileReader fr = new FileReader("./src//test//resources//config.properties");
		p = new Properties();
		p.load(fr);
		
	logger=LogManager.getLogger(this.getClass()); 
		
	if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
	{
		DesiredCapabilities cap = new DesiredCapabilities();
		//os
		if(os.equalsIgnoreCase("windows"))
		{
			cap.setPlatform(Platform.WIN10);
		}
		else if (os.equalsIgnoreCase("mac"))
		{
			cap.setPlatform(Platform.MAC);
			
		}else
		{
			System.out.println("No matching os");
			return;
		}
		
		//Browser
		
		switch(br.toLowerCase())
		{
		case "chrome" : cap.setBrowserName("chrome"); break;
		case "edge" : cap.setBrowserName("MicrosoftEdge"); break;
		default :System.out.println("No matching browser"); return;
		}
		
		driver=new RemoteWebDriver(new URL("http://192.168.1.3:4444/wd/hub"),cap);
	}
	if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
	{
		switch(br.toLowerCase())
		{
		case "chrome": driver=new ChromeDriver(); break;
		case "edge" : driver=new EdgeDriver(); break;
		default : System.out.println("Invalid browser..");return;
		}	
	}
	
	
	
	
	
	
	driver=new ChromeDriver();
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
	driver.get(p.getProperty("appURL"));
	driver.manage().window().maximize();
	
	}
	
	@AfterClass(groups = {"Sanity","Regression","Master"})
	public void tearDown()
	{
		driver.quit();
	}
	
	public String randomString()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(5);
		
		return generatedstring;
	}

	public String randomNumber()
	{
		String generatednum = RandomStringUtils.randomNumeric(10);
		return generatednum;
	}
	
	public String randomAlphanumeric()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(3);
		String generatednum = RandomStringUtils.randomNumeric(3);
		return (generatedstring+"@"+generatednum);
	}
	public String captureScreen(String tname) throws IOException
	{
		String timeStamp =new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takescreenshot = (TakesScreenshot) driver;
		File sourceFile = takescreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+"\\Screenshots\\" + tname + "_" + timeStamp;
		File targetFile =new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;
	}

}
