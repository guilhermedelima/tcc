package br.unb.social.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PropertiesLoaderTest {

	private PropertiesLoader databaseProperties;
	
	@Before
	public void setup(){
		databaseProperties = PropertiesLoader.getInstance();
	}
	
	@Test
	public void testValuesNotNullAndEmpty() {
		
		Assert.assertNotNull(databaseProperties.getDriver());
		Assert.assertNotNull(databaseProperties.getUrl());
		Assert.assertNotNull(databaseProperties.getUser());
		Assert.assertNotNull(databaseProperties.getPassword());
		
		Assert.assertFalse(databaseProperties.getDriver().isEmpty());
		Assert.assertFalse(databaseProperties.getUrl().isEmpty());
		Assert.assertFalse(databaseProperties.getUser().isEmpty());
		Assert.assertFalse(databaseProperties.getPassword().isEmpty());
	
		System.out.println(databaseProperties.getDriver());
		System.out.println(databaseProperties.getUrl());
		System.out.println(databaseProperties.getUser());
		System.out.println(databaseProperties.getPassword());
		
	}

}
