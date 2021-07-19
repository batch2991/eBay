package base;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Base 
{
   public static WebDriver driver;
   public static Properties prop;
   
   public static ExtentHtmlReporter exthtml;
   public static ExtentReports report;
   public static ExtentTest testlog;
   
   @BeforeSuite
   public void setUp()
   {
	   prop=new Properties();
	   
	   try {prop.load(new FileInputStream("src/main/java/config/config.properties"));} 
	   catch (Exception e) {e.printStackTrace();}
	   
	   if(prop.getProperty("browserName").matches("firefox"))
	   {
		   System.setProperty("webdriver.gecko.driver","D:\\sel_prac\\myebaylocalrep\\Ebay\\geckodriver.exe");
		   driver=new FirefoxDriver();		   
	   }
	   if(prop.getProperty("browserName").matches("chrome"))
	   {
		   System.setProperty("webdriver.chrome.driver", "D:\\sel_prac\\myebaylocalrep\\Ebay\\chromedriver.exe");
		   driver=new ChromeDriver();		   
	   }
	   driver.manage().window().maximize();
	   driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	   
	    exthtml = new ExtentHtmlReporter(prop.getProperty("reportlocation"));
		report = new ExtentReports();
	 	report.attachReporter(exthtml);
	 	report.setSystemInfo("Host Name", "TestSystem");  //name of thesystem
	 	report.setSystemInfo("Environment", "Test Env");
	 	report.setSystemInfo("User Name", "venkatg");
	 	   
	 	exthtml.config().setDocumentTitle("EBay");
	 	exthtml.config().setReportName("Ebay Functional Testing");
	 	exthtml.config().setTestViewChartLocation(ChartLocation.TOP);
	 	exthtml.config().setTheme(Theme.STANDARD);
   }
   public void takescreenshot(String imagename)
   {
	   try {
	    File f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(f,new File(prop.getProperty("images")+imagename));
		testlog.addScreenCaptureFromPath(prop.getProperty("images")+imagename);
	   }catch(Exception e) {}
   }
   
   @AfterSuite
   public void tearDown()
   {
	   report.flush(); //save the report
	   driver.close();
   }
}
