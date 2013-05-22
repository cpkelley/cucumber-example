package com.ddc.test;




import cucumber.api.java.en.*;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;



public class BaseSteps {

	private int result;
		
	public class Entry {

		public String category;

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
		
}
