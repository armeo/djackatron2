package com.djackatron2.service.impl;

import com.djackatron2.service.FeeService;

public class VariableFeeService implements FeeService{

	@Override
	public double calculateFee(double transferAmount) {
		double feeAmount = 0d;
		if(transferAmount <= 1000d){
			feeAmount = 0d;
		}else if(transferAmount > 1000d && transferAmount < 1000000d){
			feeAmount = transferAmount * 0.01;
		}else if(transferAmount == 1000000d){
			feeAmount = 10000d;
		}else{
			feeAmount = 20000d;
		}
		
		return feeAmount;
	}

}
