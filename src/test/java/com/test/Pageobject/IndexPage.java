package com.test.Pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IndexPage {
	// create object of WebDriver

	WebDriver ldriver;

	public IndexPage(WebDriver rdriver) {

		ldriver = rdriver;

		PageFactory.initElements(rdriver, this);
	}

//identify webelements
	@FindBy(linkText = "Sign in")
	WebElement signin;
	
	@FindBy(xpath="(//a[text()='T-shirts'])[2]")
	WebElement tshirtMenu;
//identify action on webelement

	public void ClickOnSignIn() {

		signin.click();

	}
	public String getPageTitle()
	{
		System.out.println(ldriver.getTitle());
		return(ldriver.getTitle());
	}
	
	public void clickOnTShirtMenu()
	{
		tshirtMenu.click();
	}
}
