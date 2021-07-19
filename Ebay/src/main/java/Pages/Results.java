package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;

import base.Base;

public class Results extends Base
{

	By total=By.xpath("//h1[@class='srp-controls__count-heading']/span[1]");
	By portable=By.xpath("//h3[@class='s-item__title']");
	
	public void getTotal()
	{
		String tot=driver.findElement(total).getText();
		testlog=report.createTest("Total products");
		testlog.log(Status.INFO, "Total products  :"+tot);	
	}	
	public void getListProducts()
	{
		List<WebElement> lst=driver.findElements(portable);
		
		testlog=report.createTest("List of Products");
		for(int i=0;i<lst.size();i++)
		{
			if(lst.get(i).getText().contains("Portable"))
			{
				testlog.log(Status.INFO,lst.get(i).getText());
			}
		}		
	}
	
}
