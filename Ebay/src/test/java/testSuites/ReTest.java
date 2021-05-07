package testSuites;

import org.testng.annotations.Test;


import pages.Home;
import pages.Results;

public class ReTest 
{

	@Test
	public void retesting()
	{
		Home h=new Home();	
		h.openUrl();
		h.validateTitle("Electronics, Cars, Fashion, Collectibles & More | eBay");
		
		h.validatecategories();
		
		h.searchProduct();
		
		Results r=new Results();
		r.displayTotalResults();
		try{r.validatePortableLinks();}catch(Exception e) {}	
		
	}
}
