package com.ddc.test;

import cucumber.api.junit.*;

import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@Cucumber.Options(glue = {"com.ddc.test"}, 
					tags = {"~@skip","@test"},
					features = {"classpath:features"}, 
					format = {"html:target/cucumber" , "json:target/cucumber/cucumber.json" ,"junit:target/surefire-reports/TEST-cucumber.xml"})
public class RunCukesTest {
}