package com.williamhill.pds;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IntegrationTest {

	private ApplicationContext springContext;
	
	@Before
	public void setup() {
		springContext = new ClassPathXmlApplicationContext("spring/context.xml");
	}
	
	@Test
	public void testExtractorReads() throws Exception {
		
		System.out.println("Sleeping for 2 secs");
		Thread.sleep(2*1000);
		System.out.println("Woken up");
	}	
}