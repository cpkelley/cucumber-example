package com.ddc.test;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.ddc.test.TestDataHolder.Platform;
import com.ddc.selenium.ScreenCapture;
import com.ddc.selenium.SearchData;
import com.ddc.selenium.seleniumutils.SeleniumManager;
import com.ddc.selenium.seleniumutils.TheSeleniumManager;
import com.ddc.test.TestDataHolder;
import com.ddc.test.InventoryListingsFlow;

import cucumber.api.java.*;
import cucumber.api.java.en.*;

import cucumber.api.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;



public class BaseSteps {

	private int result;
		
	public class Entry {

		public String category;

	}


	private InventoryListingsFlow flow;
	private TestDataHolder testdataholder;
	private SeleniumManager sm;

	public BaseSteps(TestDataHolder testdataholder) throws Throwable {
		
		testdataholder.setPlatform(Platform.V9);
		TheSeleniumManager.setSharedManager(true);
		sm = TheSeleniumManager.getSeleniumManager();
		testdataholder.setSearchData(new SearchData());
		this.testdataholder = testdataholder;

	}	
	
	private void startSM() throws Throwable {
		try {
			sm.start();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		start();
		flow = new InventoryListingsFlow();
		
	}

	@Given("^(\\d+) and (\\d+)$")
	public void and(int arg1, int arg2) throws Throwable {
	    result = 0;
	    result = arg1 + arg2;
	}

	@Then("^equals (\\d+)$")
	public void equals(int arg1) throws Throwable {
	    assertThat(arg1, is(equalTo(result)));
	    
	}			

	
	 @When("^I go to the ([A-z]+) listings for account \"([^\"]*)\"$")
	 public void I_go_to_the_listings_for_account(String inventoryType, String account) throws Throwable {
		 startSM();
		 
		 testdataholder.setPlatformBasedOnAccount(account);
		 flow = new InventoryListingsFlow();
		 testdataholder.setAccount(account);
		 
		 flow.goToPage(account, inventoryType);
		 if (!inventoryType.equals("all")) {
			 flow.verifyListingPageType(inventoryType);
		 }		 
	 }
	 
	 
		@Then ("^the facet browse categories should include$") 
		public void theFacetBrowseCategoriesShouldInclude(List<Entry> categories){
			List<String> expectedFacetHeaders  = new ArrayList<String>();
			for (Entry entry : categories)
				expectedFacetHeaders.add(entry.category);
				
			flow.verifyFacetCategories(expectedFacetHeaders);
		}

		
		
		private void start() {
			sm.getDriver().manage().deleteAllCookies();
		}
		
		
		
		public void stopServer(Scenario result) throws Exception {
			
		      byte[] b = null;
		      FileInputStream stream = null;
			
			if (result.isFailed()) {
				
				if (testdataholder.getAccount() != null) {
					result.write("Account under test:\n " + testdataholder.getAccount());
				}
				if (testdataholder.getUser() != null) {
					result.write("User under test (if 'dotted' in, change to your id):\n " + testdataholder.getUser().getUserName());
				}
				//result.write("Page location of failure:\n" +sm.getDriver().getCurrentUrl());
				result.write("Browser: \n" +sm.getBrowser());
				            
				File file = new ScreenCapture().takeScreenCapture(sm.getDriver());
				            
				try {
					stream = FileUtils.openInputStream(file);
				            b = IOUtils.toByteArray(stream);
				                result.embed(b, "image/png") ;
				            } finally {
				                IOUtils.closeQuietly(stream);
				            }
				
			}

		}
}
