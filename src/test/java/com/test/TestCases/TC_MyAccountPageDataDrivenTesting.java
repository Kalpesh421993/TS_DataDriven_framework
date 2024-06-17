package com.test.TestCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.test.Pageobject.IndexPage;
import com.test.Pageobject.accountCreationdetails;
import com.test.Pageobject.myAccout;
import com.test.Pageobject.regesteredUserAccount;
import com.test.Utilities.ReadExcelFile;

public class TC_MyAccountPageDataDrivenTesting extends BaseClass {
	@Test
	public void verifyRegistrationAndlogin() {
		
		//open url
		driver.get(url);
		log.info("url opened");
		
		IndexPage pg= new IndexPage(driver);
		pg.ClickOnSignIn();
		
		myAccout pa=new myAccout(driver);
		pa.enterCreateEmailAddress("rahul123@gmail.com");
		pa.clickSubmitCreate();
		log.info("clicked on create an account button");
		
		accountCreationdetails accCreationPg = new accountCreationdetails(driver);
		accCreationPg.selectTitleMr();
		accCreationPg.enterCustomerFirstName("ketanath");
		accCreationPg.enterCustomerLastName("patil");
		accCreationPg.enteremailadd("ketanath123@gmail.com");
		accCreationPg.enterpassword("Angel@123");
		log.info("entered user details on account creation page.");
		accCreationPg.clickOnRegister();
		log.info("clicked on Register button");
		
		regesteredUserAccount regUser = new regesteredUserAccount(driver);
		String userName = regUser.getUserName();

		Assert.assertEquals("Ketanath Patil", userName);

		log.info("***************TestCase Verify Registration and Login ends*****************"); 
	}
	
	
	@Test(dataProvider = "LoginDataProvider")
	public void VerifyLogin(String userEmail, String userPwd, String expectedUsername) throws IOException 
	{
		log.info("***************TestCase Verify Login starts*****************"); 
		
		IndexPage pg = new IndexPage(driver);

		pg.ClickOnSignIn();
		log.info("Clicked on sign in link");

		myAccout myAcpg = new myAccout(driver);

		myAcpg.enterEmailAddress(userEmail);
		log.info("Entered email address");
		
		myAcpg.enterPassword(userPwd);
		log.info("Entered password");

		myAcpg.clickSignIn();
		log.info("Clicked on sign in link..");


		regesteredUserAccount regUser = new regesteredUserAccount(driver);
		String userName = regUser.getUserName();


		if(userName.equals(expectedUsername))
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
	
	@DataProvider(name = "LoginDataProvider")
	public String[][] LoginDataProvider()
	{
		String fileName = System.getProperty("user.dir") + "\\test-data\\TestTestdata2.xlsx";
		ReadExcelFile read = new ReadExcelFile(log);
		int ttlRows = read.getRowCount(fileName, "LoginTestData");
		int ttlColumns = read.getColCount(fileName, "LoginTestData");
		log.info(ttlRows);
		log.info(ttlColumns);
		log.info(fileName);

		String data[][]=new String[ttlRows-1][ttlColumns];

		for(int i=1;i<ttlRows;i++)//rows =1,2
		{
			for(int j=0;j<ttlColumns;j++)//col=0, 1,2
			{

				data[i-1][j]=read.getCellValue(fileName,"LoginTestData", i,j);
			}

		}
		return data;
	}
		
	
	
	
	@Test(enabled = false)
	public void VerifySignOut() throws IOException 
	{

		log.info("***************TestCase Verify Sign out starts*****************"); 

		IndexPage pg = new IndexPage(driver);

		pg.ClickOnSignIn();
		log.info("Clicked on sign in link");

		myAccout myAcpg = new myAccout(driver);

		myAcpg.enterEmailAddress("kalpesh421@gmail.com");
		log.info("Entered email address");

		myAcpg.enterPassword("Maxi@12mm");
		log.info("Entered password");

		myAcpg.clickSignIn();
		log.info("Clicked on sign in link..");


		regesteredUserAccount regUser = new regesteredUserAccount(driver);
		regUser.clickOnSignOut();

		if(pg.getPageTitle().equals("Login - My Store"))
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
