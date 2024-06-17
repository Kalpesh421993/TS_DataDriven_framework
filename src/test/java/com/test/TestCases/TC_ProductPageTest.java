package com.test.TestCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.test.Pageobject.IndexPage;
import com.test.Pageobject.OrderAddressPage;
import com.test.Pageobject.OrderConfirmationPage;
import com.test.Pageobject.OrderPaymentPage;
import com.test.Pageobject.OrderShippingPage;
import com.test.Pageobject.OrderSummaryPage;
import com.test.Pageobject.ProductPage;
import com.test.Pageobject.SearchResultPage;
import com.test.Pageobject.accountCreationdetails;
import com.test.Pageobject.myAccout;
import com.test.Pageobject.regesteredUserAccount;


	public class TC_ProductPageTest  extends BaseClass  {

//		private String emailAdd;
//		private String pwd;


		@Test
		public void VerifySearchProduct() throws IOException
		{
			String searchKey = "T-shirts";
			log.info("\n***************TestCase Search Product started*****************"); 

			//Sign in 
			IndexPage indexPg = new IndexPage(driver);
			indexPg.ClickOnSignIn();


			//Enter account details- email and password
			myAccout pg = new myAccout(driver);
			pg.enterEmailAddress(emailAddress);

			log.info("User Email and Password entered.");
			pg.enterPassword(password);

			pg.clickSignIn();
			log.info("Sign In link clicked");

			//Enter searchkey in search box
			regesteredUserAccount productPg = new regesteredUserAccount(driver);
			productPg.EnterDataInSearchBox(searchKey);
			productPg.ClickOnSearchButton();

			// Get Name of Searched Product
			SearchResultPage resultPg = new SearchResultPage(driver);

			String SearchResultProductname=resultPg.getSearchResultProductName();


			//Verify that correct Product is displaying after search

			if(SearchResultProductname.contains(searchKey))
			{
				log.info("Search Product testcase - Passed"); 
				Assert.assertTrue(true);

				productPg.clickOnSignOut();

			}
			else
			{
				log.info("Search Product testcase - Failed");
				captureScreenShot(driver,"VerifySearchProduct");
				Assert.assertTrue(false);

			} 

			log.info("***************TestCase Search Product ends*****************"); 

		}


		@Test
		public void VerifyBuyProduct() throws IOException
		{

			log.info("\n***************TestCase Buy Product started*****************"); 

			/*	driver.get(url);
			logger.info("Url opened");*/

			//Sign in 
			IndexPage indexPg = new IndexPage(driver);
			indexPg.ClickOnSignIn();
			
			
//			JavascriptExecutor js=(JavascriptExecutor) driver;
//			js.executeScript("window.scrollBy(0,500)","");
			
			//Enter account details- email and password
			myAccout pg = new myAccout(driver);
			pg.enterEmailAddress(emailAddress);

			log.info("User Email and Password entered.");
			pg.enterPassword(password);

			pg.clickSignIn();
			log.info("Sign In link clicked");

			regesteredUserAccount prodCatPg = new regesteredUserAccount(driver);
			prodCatPg.EnterDataInSearchBox("blouse");
			log.info("Blouse entered in search box");
			
			
			prodCatPg.ClickOnSearchButton();
			log.info("clicked on search button");

//			js.executeScript("window.scrollBy(0,300)","");
			
			ProductPage prg=new ProductPage(driver);
			prg.mouseOverOnTshirtProduct();
			
			
			
			SearchResultPage searchResultPg = new SearchResultPage(driver);
			searchResultPg.ClickOnMoreLink();
			log.info("Clicked on more button");

			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//

			ProductPage prodPg = new ProductPage(driver);
			
			prodPg.setSize("L");
			log.info("size M entered");
				
			prodPg.setQuantity("2");
			log.info("quantity 2 entereed");

			

			prodPg.clickOnAddToCart();
			log.info("Clicked on add to cart");

			prodPg.clickOnProceedToCheckOut();
			log.info("Clicked on proceed to checkout on product page");


			OrderSummaryPage orderSumPg = new OrderSummaryPage(driver);
			orderSumPg.cickOnProceedToCheckout(); 
			log.info("Clicked on proceed to checkout on order summary page");

			OrderAddressPage orderAddPg = new OrderAddressPage(driver);
			orderAddPg.cickOnProceedToCheckout();
			log.info("Clicked on proceed to checkout on order address page");

			OrderShippingPage orderShippingPg = new OrderShippingPage(driver);
			orderShippingPg.selectTermsOfServices();
			log.info("selected term of service check box");

			orderShippingPg.cickOnProceedToCheckout();
			log.info("Clicked on proceed to checkout on order shipping page");

			OrderPaymentPage orderPaymentPg = new OrderPaymentPage(driver);
			log.info(orderPaymentPg.getPageTitle());

			orderPaymentPg.clickOnPayByCheque();
			log.info("Clicked on pay by cheque");

			OrderConfirmationPage orderConfirmPg = new OrderConfirmationPage(driver);
			orderConfirmPg.cickOnConfirmOrder();

			log.info("Clicked on confirmed order");

			String sucessMsg = orderConfirmPg.getOrderSucessMessage();

			//	Assert.assertEquals(sucessMsg, "Your order on My Store is complete.");

			if(sucessMsg.equals("Your order on My Shop is complete."))
			{
				log.info("VerifyBuyProduct - Passed"); 
				Assert.assertTrue(true);

				orderConfirmPg.clickOnSignOut();

			}
			else
			{
				log.info("VerifyBuyProduct - Failed");
				captureScreenShot(driver,"VerifyBuyProduct");
				Assert.assertTrue(false);

			} 

			log.info("***************TestCase BuyProduct ends*****************"); 

		}


		@Test(enabled = false)
		public void verifyAddToWishlistWithoutLogin() throws IOException
		{
			log.info("\n***************TestCase verifyAddToWishlistWithoutLogin started*****************"); 

			IndexPage indexPage = new IndexPage(driver);
			indexPage.clickOnTShirtMenu();

			ProductPage prodPage = new ProductPage(driver);
			prodPage.mouseOverOnTshirtProduct();

			prodPage.clickOnAddToWishList();

			String actualAlertMsg = prodPage.getTextOfAlertForWishList();
			String expectedAlertMsg = "You must be logged in to manage your wishlist.";

			if(actualAlertMsg.equals(expectedAlertMsg))
			{
				log.info("verifyAddToWishlistWithoutLogin - passed"); 
				Assert.assertTrue(true);
			}
			else
			{
				log.info("verifyAddToWishlistWithoutLogin - failed"); 
				captureScreenShot(driver,"verifyAddToWishlistWithoutLogin");
				Assert.assertTrue(false);
			}

		}
	}

