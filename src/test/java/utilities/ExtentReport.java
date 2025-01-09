package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReport implements ITestListener {
	public  ExtentSparkReporter sparkReporter;
	public  ExtentReports extent;
	public  ExtentTest test;
	
	
	public void onStart(ITestContext context) {
		
		String repName;
		
//		SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH:mm:ss");
//		Date dt=new Date();
//		String currentdatetimestamp=df.format(dt);
		
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		repName="Test-Report-" + timestamp + ".html";

		
		sparkReporter = new ExtentSparkReporter(".\\Reports\\" + repName);
		
		sparkReporter.config().setDocumentTitle("OpenCart Automation Report");
		sparkReporter.config().setReportName(" OpenCart Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Application", "OpenCart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Submodule", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		
		String os= context.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser= context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups",includedGroups.toString());
		}
		
			  }
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, "Test case successfully executed:" + result.getName());
	  }
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "Test case fail is:" + result.getName());
		test.log(Status.FAIL, "Test case fail cause is:" + result.getThrowable().getMessage());
	
		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	
	}
	
	
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+"got Skipped");
		test.log(Status.FAIL,  result.getThrowable().getMessage());
	  }
	public void onFinish(ITestContext context, String repName) {
		extent.flush();
		
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\Reports\\"+repName;
		File ExtentReport = new File(pathOfExtentReport);
		
	
		try {
			Desktop.getDesktop().browse(ExtentReport.toURI());
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	
		/* try { URL url = new URL
		 *(System.getProperty("user.dir")+"\\Reports\\"+repName);
		 * 
		 * 
		 * // ImageHtmlEmail email =new ImageHtmlEmail();
		 * email.setDataSourceResolver(new DataSourceUrlResolver(url));
		 * email.setHostName("smtp.googlemail.com"); email.setSmtpPort(465);
		 * email.setAuthenticator(new
		 * DefaultAuthenticator("mallika741995@gmail.com","MukeshMa@143"));
		 * email.setSSLOnConnect(true); email.setFrom("mallika741995@gmail.com");
		 * email.setSubject("TEST RESULTS"); email.setMsg("Please find report");
		 * email.addTo("mallika741995@gmail.com"); email.attach(url,"extent report"
		 * ,"Please check report"); email.send();
		 * 
		 * 
		 * 
		 * } catch(IOException e) { e.printStackTrace(); }
		 */
	
	}
	}

