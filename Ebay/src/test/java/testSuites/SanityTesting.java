package testSuites;

import org.testng.annotations.Test;

import pages.Home;
import pages.Results;

public class SanityTesting 
{

	@Test
	public void sanityTest()
	{
		Home h=new Home();		
		h.openUrl();		
		h.searchProduct();
		h.validateTitle("outdoor toys");
		
	}

}
