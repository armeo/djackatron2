package com.djackatron2.service;

import org.joda.time.LocalTime;

public class TimeService {

	LocalTime openTime;
	LocalTime closeTime ;
	
	public TimeService(LocalTime openTime, LocalTime closeTime) {
		this.openTime = openTime;
		this.closeTime = closeTime;
	}

	public boolean isAvailable(LocalTime time) {
		return !(time.isBefore(openTime) || time.isAfter(closeTime));
	}

}
