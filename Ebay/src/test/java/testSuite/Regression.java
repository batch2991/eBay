package testSuite;

import org.testng.annotations.Test;

import Pages.Home;
import Pages.Results;

public class Regression 
{

	@Test
	public void regressiontesting()
	{
		Home h=new Home();
		h.openUrl();
		h.search();
		
		Results r=new Results();
		r.getTotal();
		r.getListProducts();
	}
	
}
