package pages;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.Status;

import base.Base;
import pobjects.PResults;

public class Results extends Base
{
////change the code with pagefactory design
	
  public void validatePortableLinks() throws Exception
  {
	  HttpURLConnection huc = null;
	  int respCode;
	  String url;
	  
	  List<WebElement> lst=driver.findElements(By.xpath(PResults.xpresults));
	  
	  exttest=report.createTest("Validating The broken link");
	  exttest.log(Status.INFO, "The below status for broken links");
	  takescreenshot("Results.png");
	  
	  for(int i=0;i<lst.size();i++)
	  {
		  url=lst.get(i).getAttribute("href");
		  if(url == null || url.isEmpty())             //check if the url of the link is empty or null then no need to check its working or not
		  {
			  exttest=report.createTest("Validate The broken link");
			  exttest.log(Status.WARNING, lst.get(i).getText()+" will not work , href is empty");				
		  }
		  else
		  {
			  huc = (HttpURLConnection)(new URL(url).openConnection());            //try to connect to the URL
			  huc.setRequestMethod("HEAD");
			  huc.connect();
			  respCode = huc.getResponseCode();							//get the response code if response code is <400 it is valid link else broken link/dead link
			  if(respCode >= 400)
			  {
				  exttest=report.createTest("Validate The broken link");
				  exttest.log(Status.INFO, lst.get(i).getText()+" links is not working");	
			  }
			  else
			  {
				  exttest=report.createTest("Validate The broken link");
				  exttest.log(Status.INFO, lst.get(i).getText()+" links is  working");				
			  }
		  }
	  }	  
    }
    public void displayTotalResults()
    {
    	String str=driver.findElement(By.xpath(PResults.xtotalres)).getText();
    	exttest=report.createTest("Total Results");
		exttest.log(Status.INFO, "Total Products results are  :"+str);    	
    }
}
