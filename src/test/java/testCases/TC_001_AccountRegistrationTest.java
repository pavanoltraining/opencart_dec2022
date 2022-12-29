package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass{
	@Test(groups= {"Regression","Master"})  // step 8 groups added
	public void test_account_Registration()
	{
		logger.info("********* staring TC_001_AccountRegistrationTest ******");		
		logger.trace("capturing tracing logs.....");
		logger.debug("capturing debug logs.......");
		
		try
		{
		HomePage hp=new HomePage(driver);
		logger.info("clicking on myaccount link");	
		hp.clickMyAccount();
		
		logger.info("clicking on registration link");	
		
		hp.clickRegister();
		
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		
		logger.info("Proving customer details");
		regpage.setFirstName(randomeString().toUpperCase());
		regpage.setLastName(randomeString().toUpperCase());
		regpage.setEmail(randomeString()+"@gmail.com");// randomly generated the email
		regpage.setTelephone(randomeNumber());
		
		String password=randomAlphaNumeric();
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
		regpage.setPrivacyPolicy();
		
		logger.info("clicking on continue");
		regpage.clickContinue();
		
		String confmsg=regpage.getConfirmationMsg();
		
		logger.info("verifying customer registration");
		
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			
			logger.info("test passed..");
			Assert.assertTrue(true);
	
		}
		else
		{
			logger.warn("customer registration is not successful");
			logger.error("test failed..");
			Assert.assertTrue(false);
			
		}
		
		
		}
		catch(Exception e)
		{
			
			Assert.fail();
		}
		logger.info("********* finished TC_001_AccountRegistrationTest ******");	
	}
		
}
