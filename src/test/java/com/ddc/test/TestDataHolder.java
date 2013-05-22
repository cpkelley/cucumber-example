package com.ddc.test;

import java.util.ArrayList;
import java.util.List;

import com.ddc.selenium.LeadData;
import com.ddc.selenium.SearchData;
import com.ddc.selenium.User;
import com.ddc.selenium.VehicleAttributes;
import com.ddc.selenium.cc.crm.Appointment;
import com.ddc.selenium.cc.crm.Email;
import com.ddc.selenium.cc.crm.Opportunity;
import com.ddc.selenium.cc.crm.SaleManagerDash.SalesManagerStats;

/**
 * @author ddcchrisk
 * Store any test data that will be shared within a scenario in this test data holder. 
 * This singleton should be reset on a per Scenario basis so that there is no leaking of test data.  
 */
public class TestDataHolder {
	private List<VehicleAttributes> vehicles ;
	private List<LeadData> leads;
	private Appointment appointment;
	private String username;
	public User user;
	private SalesManagerStats intitialSmdStats;
	private String account;
	private Platform platform;
	private LeadData lead;
	private String alertMessage;
	private SearchData searchData;
	
	public enum Platform { V8, V9, NPV, HELIOS1 } ;
	
	
	public TestDataHolder() {
		setPlatform(Platform.V9);
		vehicles = new ArrayList<VehicleAttributes>();
		leads = new ArrayList<LeadData>();
	}
	

	public List<VehicleAttributes> getVehicles() {
		return this.vehicles;
	}
	
	public List<LeadData> getLeads() {
		if (leads == null) {
			leads = new ArrayList<LeadData>();
		}
		return this.leads;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
		
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setUserName(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setInitialSmdStats(SalesManagerStats stats) {
		this.intitialSmdStats = stats;
		
	}
	
	public SalesManagerStats getInitialSmdStats() {
		return intitialSmdStats;
	}


	public String getAccount() {
		return account;
	}
	
	public void setAccount(String account) {
		this.account = account;
	}


	public Platform getPlatform() {
		return platform;
	}


	public void setPlatform(Platform platform) {
		this.platform = platform;
	}
	
	public void setPlatformBasedOnAccount(String account) {
		if (account.equals("ddctest0007") || account.contains("cllc")) {
			setPlatform(Platform.V8);
		} else if (account.equals("bavarianbmw")) {
			setPlatform(Platform.HELIOS1);
		} else setPlatform(Platform.V9);
		
	}


	public LeadData getLead() {
		return lead;
	}


	public void setLead(LeadData lead) {
		this.lead = lead;
	}


	public void setAlertMessage(String alertMessage) {
		this.alertMessage = alertMessage;
		
	}
	
	public String getAlertMessage() {
		return alertMessage;
	}
	
	


	public LeadData getLead(Integer id) {
		LeadData result = null;
		for (LeadData lead : leads) {
			if (lead.getId().equals(id)) {
				result =  lead;
				break;
			}
		}
		
		return result;
	}


	public VehicleAttributes getVehicle() {
		return this.getVehicles().get(0);
	}
	
	public void setVehicle(VehicleAttributes vehicle) {
		this.getVehicles().add(0, vehicle);
	}


	public SearchData getSearchData() {
		return searchData;
	}


	public void setSearchData(SearchData searchData) {
		this.searchData = searchData;
	}
	
	
}
