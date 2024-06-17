package com.test.TestCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.test.Pageobject.IndexPage;
import com.test.Pageobject.accountCreationdetails;
import com.test.Pageobject.myAccout;
import com.test.Pageobject.regesteredUserAccount;

public class TC_MyAccountPageTest extends BaseClass {
	@Test(enabled = false)
	public void verifyRegistrationAndlogin() {
		
		//open url
		driver.get(url);
		log.info("url opened");
		
		IndexPage pg= new IndexPage(driver);
		pg.ClickOnSignIn();
		
		myAccout pa=new myAccout(driver);
		pa.enterCreateEmailAddress("ketan123@gmail.com");
		pa.clickSubmitCreate();
		log.info("clicked on create an account button");
		
		accountCreationdetails accCreationPg = new accountCreationdetails(driver);
		accCreationPg.selectTitleMr();
		accCreationPg.enterCustomerFirstName("ketan");
		accCreationPg.enterCustomerLastName("patil");
		accCreationPg.enteremailadd("ketan123@gmail.com");
		accCreationPg.enterpassword("Angel@123");
		log.info("entered user details on account creation page.");
		accCreationPg.clickOnRegister();
		log.info("clicked on Register button");
		
		regesteredUserAccount regUser = new regesteredUserAccount(driver);
		String userName = regUser.getUserName();

		Assert.assertEquals("Ketan patil", userName);

		log.info("***************TestCase Verify Registration and Login ends*****************"); 
	}
	
	
	@Test
	public void VerifyLogin() throws IOException 
	{
//		driver.get(url);
//		log.info("url opened");
		log.info("***************TestCase Verify Login starts*****************"); 

		IndexPage pg = new IndexPage(driver);

		pg.ClickOnSignIn();
		log.info("Clicked on sign in link");

		myAccout myAcpg = new myAccout(driver);

		myAcpg.enterEmailAddress(emailAddress);
		log.info("Entered email address");

		myAcpg.enterPassword(password);
		log.info("Entered password");

		myAcpg.clickSignIn();
		log.info("Clicked on sign in link..");


		regesteredUserAccount regUser = new regesteredUserAccount(driver);
		String userName = regUser.getUserName();


		if(userName.equals("Ketan patil"))
		{
			log.info("VerifyLogin - Passed");
			regUser.clickOnSignOut();
			Assert.assertTrue(true);
		}
		else
		{
			log.info("VerifyLogin - Failed");
			captureScreenShot(driver,"VerifyLogin");
			Assert.assertTrue(false);
		}

		log.info("***************TestCase Verify Login ends*****************"); 


	}
		
	@Test
	public void VerifySignOut() throws IOException 
	{

		log.info("***************TestCase Verify Sign out starts*****************"); 

		IndexPage pg = new IndexPage(driver);

		pg.ClickOnSignIn();
		log.info("Clicked on sign in link");

		myAccout myAcpg = new myAccout(driver);

		myAcpg.enterEmailAddress(emailAddress);
		log.info("Entered email address");

		myAcpg.enterPassword(password);
		log.info("Entered password");

		myAcpg.clickSignIn();
		log.info("Clicked on sign in link..");


		regesteredUserAccount regUser = new regesteredUserAccount(driver);
		regUser.clickOnSignOut();
		
//		System.out.println();	
		if(pg.getPageTitle().equals("Login - My Shop"))
		{
			log.info("VerifySignOut - Passed");
			Assert.assertTrue(true);
		}

		else
		{
			log.info("VerifySignOut - Failed");
			captureScreenShot(driver,"VerifySignOut");
			Assert.assertTrue(false);
		}

	
		log.info("***************TestCase Verify Sign out ends*****************"); 

	}
	
}
