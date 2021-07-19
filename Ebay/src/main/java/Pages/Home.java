package Pages;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;

import base.Base;

public class Home extends Base
{

	By searchinput=By.xpath("//input[@placeholder='Search for anything']");
	By searchbtn=By.xpath("//input[@value='Search']");
	
	public void openUrl()
	{
		driver.get(prop.getProperty("url"));
	}
	public void validateTitle(String exptitle)
	{
		String acttitle=driver.getTitle();
		if(acttitle.contains(exptitle))
		{
			testlog=report.createTest("Validating Title");
			testlog.log(Status.PASS, "Title is correct  :"+exptitle);
			takescreenshot("home.png");
			
		}
		else
		{
			testlog=report.createTest("Validating Title");
			testlog.log(Status.FAIL, "Title NOT correct  :"+exptitle);
			takescreenshot("home.png");
		}
		
	}
	public void search()
	{
		driver.findElement(searchinput).sendKeys("outdoor toys");
		driver.findElement(searchbtn).click();
		
	}
}
