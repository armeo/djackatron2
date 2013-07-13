package com.djackatron2.service.impl;

import com.djackatron2.service.FeeService;

public class FixedFeeService implements FeeService{
	
	private double fixedFeeAmount;
	
	public FixedFeeService(double fixedFeeAmount){
		this.fixedFeeAmount = fixedFeeAmount;
	}

	@Override
	public double calculateFee(double transferAmount) {
		return this.fixedFeeAmount;
	}

}
