package fr.mrfern.spongeplugintest.config;

import org.junit.Test;

import fr.mrfern.pumpmysponge.config.Config;

import static org.junit.Assert.*;

public class ConfigTest{
	protected Config cfg = Config.getInstance();

	
	public void Testsetup() {
				
	}

	public void TestisActive() {
		
	}

	@Test
	public void TestisSetup() {
		cfg.setIsSetup(true);
		assertEquals(true,cfg.isSetup());
		
		cfg.setIsSetup(false);
		assertEquals(false,cfg.isSetup());
	}
	
	

	@Test
	public void TestsetIsSetup() {
		cfg.setIsSetup(true);
		assertEquals(true,cfg.isSetup());
		
		cfg.setIsSetup(false);
		assertEquals(false,cfg.isSetup());
	}	
	
}
