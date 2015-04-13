package br.unb.social.model;

import junit.framework.Assert;

import org.junit.Test;

public class PoliticianTypeTest {

	@Test
	public void testGetAecio() {
		Assert.assertEquals(PoliticianType.AECIO, PoliticianType.getPoliticianByID("aecio"));
	}
	
	@Test
	public void testGetDilma() {
		Assert.assertEquals(PoliticianType.DILMA, PoliticianType.getPoliticianByID("dilma"));
	}
	
	@Test
	public void testGetNull() {
		Assert.assertNull(PoliticianType.getPoliticianByID(null));
	}
	
	@Test
	public void testGetInvalid() {
		Assert.assertNull(PoliticianType.getPoliticianByID("invalid"));
	}

}
