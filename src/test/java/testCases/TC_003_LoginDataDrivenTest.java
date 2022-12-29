package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_LoginDataDrivenTest extends BaseClass{

	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class,groups= {"datadriven"})
	public void test_LoginDDT(String email, String pwd, String exp)
	{
		logger.info("***** String TC_003_LoginDataDrivenTest ****");
	
		try
		{
		//HomePage -->MyAccount-->Login
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//LoginPage-->email & password then click
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		//Check MyAccount label is present
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetpage=macc.isMyAccountPageExists();
		
	
		if(exp.equals("Valid"))
		{
			if(targetpage ==true)
			{
				macc.clickLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.fail();
			}
		}
		
		
		if(exp.equals("Invalid"))
		{
			if(targetpage ==true)
			{
				macc.clickLogout();
				Assert.fail();
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
				
		logger.info("**** Finished TC_003_LoginDataDrivenTest  ***** ");
	}
	
}
