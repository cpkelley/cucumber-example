package com.ddc.test;




import java.util.List;
import org.apache.log4j.Logger;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.ddc.test.TestDataHolder.Platform;
import com.ddc.selenium.cms.CmsPage;
import com.ddc.selenium.cms.listingspage.FacetBrowser;
import com.ddc.selenium.cms.listingspage.HeliosFacetBrowser;
import com.ddc.selenium.cms.listingspage.HeliosListings;
import com.ddc.selenium.cms.listingspage.Listings;
import com.ddc.selenium.cms.listingspage.V9FacetBrowser;
import com.ddc.selenium.cms.listingspage.V9Listings;
import com.ddc.selenium.cms.listingspage.v8.V8Listings;



public class InventoryListingsFlow {
	
	static Listings vlp ;
	static FacetBrowser facetbrowser ;
	public Logger logger ;

	private Platform platform;
	

	public InventoryListingsFlow() {
		super();
		vlp = new V9Listings() ;
		facetbrowser = new V9FacetBrowser() ;
		this.platform = Platform.V9;
		logger = Logger.getLogger(this.getClass().getSimpleName());
	}
	
	public InventoryListingsFlow(Platform platform) {
		super();
		this.platform = platform;
		setListingsPage(platform);
		setFacetBrowser(platform) ;
	
	}
	
	private static void setListingsPage(Platform platform) {
		switch (platform) {
		 	case V8: {  vlp = new V8Listings(); break;}
		 	case NPV: { vlp = new V8Listings(); break;}
		 	case V9: { vlp = new V9Listings(); break; }
		 	case HELIOS1: {vlp = new HeliosListings(); break; }
		 }
		 
	}
	
	private static void setFacetBrowser(Platform platform) {
		switch (platform) {
		 	case V8: {  break;}
		 	case NPV: { break;}
		 	case V9: { facetbrowser = new V9FacetBrowser(); break; }
		 	case HELIOS1: {facetbrowser = new HeliosFacetBrowser(); break;} 
		 }
	}
	
	
	public void goToPage(String accountId, String inventoryType) {
		new CmsPage().openPage(accountId, "/"+inventoryType+"-inventory/index.htm");
		
	}

	/**
	 * Verifies the exact list of Facet Categories listed in the facet browse column
	 * Excludes the "Search" Form. 
	 * 
	 * @param expectedHeaders
	 */
	public void verifyFacetCategories(List<String> expectedHeaders) {
		List<String> actualHeaders = facetbrowser.getFacetHeaders();
		
		assertThat(actualHeaders,containsInAnyOrder(expectedHeaders.toArray()));
	
	}
	
	public void verifyListingPageType(String inventoryType) {
		
		String pageHeader = vlp.getListingPageType();
		
		
		if (inventoryType.toLowerCase().equals("used")){
			String actual = pageHeader.toLowerCase();
			assertThat(actual,anyOf(containsString("used"), containsString("pre-owned")));
				
		} else {
			assertThat(pageHeader.toLowerCase(),containsString(inventoryType));		
		}
		
		assertThat(vlp.hasListings(), is(true));
		
		
	}
	
}