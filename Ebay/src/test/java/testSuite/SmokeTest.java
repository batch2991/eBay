package testSuite;

import org.testng.annotations.Test;

import Pages.Home;

public class SmokeTest 
{

	@Test
	public void smoketesting()
	{
		Home h=new Home();
		h.openUrl();
		h.validateTitle("Electronics, Cars, Fashion");
	}
}
