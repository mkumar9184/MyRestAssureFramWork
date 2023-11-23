package api.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener
{
	

	public ExtentSparkReporter sparkreport;
	public ExtentReports extent;
	public ExtentTest test;
	String repName;
	@Override
	public void onStart(ITestContext result) {
		// TODO Auto-generated method stub
		String timesStamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
		repName= "Test-report"+timesStamp+".html";
		
		System.out.println("repName---"+repName);
		
		sparkreport =new  ExtentSparkReporter(".\\reports\\"+repName);
		sparkreport.config().setDocumentTitle("MY RestAssureApiAutomation Testing Report");
		sparkreport.config().setReportName("PetApi Automatin");
		sparkreport.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkreport);
		extent.setSystemInfo("Application", "PetStorUserAPI");
		extent.setSystemInfo("Operatin System", System.getProperty("os.name"));	
		extent.setSystemInfo("User Name", System.getProperty("user.name"));		
		extent.setSystemInfo("Environemt","QA");
		extent.setSystemInfo("User","Manoj");
		
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		
		test= extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS,"Test Passed");	
	}
	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		test= extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.FAIL,"Test Failed");

		test.log(Status.FAIL,result.getThrowable().getMessage());
		
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		test= extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.SKIP,"Test Failed");
		test.log(Status.SKIP,result.getThrowable().getMessage());
		
	}
	
	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}

}
