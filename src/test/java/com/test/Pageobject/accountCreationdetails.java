package com.test.Pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class accountCreationdetails {
	// create object of WebDriver
	WebDriver ldriver;

	public accountCreationdetails(WebDriver rdriver) {

		ldriver = rdriver;

		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(name="id_gender")
	WebElement titleMr;
	
	@FindBy(id="customer_firstname")
	WebElement custfirstName;
	
	@FindBy(id="customer_lastname")
	WebElement custlastName;
	
	@FindBy(id="email")
	WebElement emailadd;
	
	@FindBy(id="passwd")
	WebElement password;
	
	@FindBy(id="submitAccount")
	WebElement register;
	
	public void selectTitleMr()
	{
		titleMr.click();
	}
	public void enterCustomerFirstName(String fname)
	{
		custfirstName.sendKeys(fname);
	}

	public void enterCustomerLastName(String lname)
	{
		custlastName.sendKeys(lname);
	}
	public void enteremailadd(String emailad) {
		emailadd.clear();
		emailadd.sendKeys(emailad);
		
	}
	public void enterpassword(String pass) {
		password.sendKeys(pass);
	}
	
	public void clickOnRegister()
	{
		register.click();
	}
	
	

}