package com.test.djackatron2.service;

import static org.junit.Assert.*;

import org.joda.time.LocalTime;
import org.junit.Test;

import com.djackatron2.service.TimeService;

public class TimeServiceTest {

	@Test
	public void testShouldBeAvailableIfTimeEightAMToNinePM(){
		LocalTime openTime = new LocalTime(8, 0);
		LocalTime closeTime = new LocalTime(21, 0);
		LocalTime onTime = new LocalTime(9, 0);
		
		TimeService timeService = new TimeService(openTime, closeTime);
		
		assertTrue(timeService.isAvailable(onTime));
	}
	
	@Test
	public void testShouldBeAvailableIfTimeEightAM(){
		LocalTime openTime = new LocalTime(8, 0);
		LocalTime closeTime = new LocalTime(21, 0);
		LocalTime onTime = new LocalTime(8, 0);
		
		TimeService timeService = new TimeService(openTime, closeTime);
		
		assertTrue(timeService.isAvailable(onTime));
	}
	
	@Test
	public void testShouldBeAvailableIfTimeNinePM(){
		LocalTime openTime = new LocalTime(8, 0);
		LocalTime closeTime = new LocalTime(21, 0);
		LocalTime onTime = new LocalTime(21, 0);
		
		TimeService timeService = new TimeService(openTime, closeTime);
		
		assertTrue(timeService.isAvailable(onTime));
	}
	
	@Test
	public void testShouldBeUnavailableIfTimeBeforeEightAM(){
		LocalTime openTime = new LocalTime(8, 0);
		LocalTime closeTime = new LocalTime(21, 0);
		LocalTime onTime = new LocalTime(7, 0);
		
		TimeService timeService = new TimeService(openTime, closeTime);
		
		assertFalse(timeService.isAvailable(onTime));
	}
	
	@Test
	public void testShouldBeUnavailableIfTimeAfterNinePM(){
		LocalTime openTime = new LocalTime(8, 0);
		LocalTime closeTime = new LocalTime(21, 0);
		LocalTime onTime = new LocalTime(22, 0);
		
		TimeService timeService = new TimeService(openTime, closeTime);
		
		assertFalse(timeService.isAvailable(onTime));
	}
}
