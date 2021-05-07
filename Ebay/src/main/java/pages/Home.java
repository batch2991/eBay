package pages;

import java.io.File;

import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.Status;

import base.Base;
import pobjects.PHome;

public class Home extends Base
{
   ////change the code with pagefactory design
	
	public void openUrl()
	{
		driver.get(prop.getProperty("url"));
	}
	public void validateTitle(String exptitle)
	{
		String str=driver.getTitle();
		if(str.contains(exptitle))
		{
			exttest=report.createTest("Validate Title");
			exttest.log(Status.PASS, "Title is correct : "+str);
			takescreenshot("home.png");
			
		}			
		else
		{
			exttest=report.createTest("Validate Title");
			exttest.log(Status.FAIL, "Title is Invalid :"+str);
			takescreenshot("home.png");
		}			
	}
	public void validatecategories()
	{
		String expproducts[]={"Saved","Electronics","Fashion","Health&Beauty","Home&Garden","Sports","Collectibles and Art","Industrial equipment","Motors","Deals","Selling"};
		
		List<WebElement> lst=driver.findElements(By.xpath(PHome.xcategories));
		for(int i=1;i<lst.size()-1;i++)
		{
			if(lst.get(i).findElement(By.xpath("a")).getText().matches(expproducts[i-1]))
			{
				exttest=report.createTest("Validate Categories");
				exttest.log(Status.PASS, "Product category is matching  :"+expproducts[i-1]);
				takescreenshot("homecategories.png");
			}
			else
			{
				exttest=report.createTest("Validate Categories");
				exttest.log(Status.FAIL, "Product category is NOT Correct  :"+expproducts[i-1]);
				takescreenshot("homecategories.png");
			}
					
		}
	}
	public void searchProduct()
	{
		driver.findElement(By.xpath(PHome.xsearchbox)).sendKeys("Outdoor Toys");
		driver.findElement(By.xpath(PHome.xsearchbtn)).click();
		try {Thread.sleep(5000);}catch(Exception e) {}		
	}
}
