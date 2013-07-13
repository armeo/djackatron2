package com.test.djackatron2.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.djackatron2.service.impl.VariableFeeService;

public class VariableFeeServiceTest {
	
	@Test
	public void testShouldBeZeroFeeLessThanThousandTransferAmount(){
		double transferAmount = 999d;
		VariableFeeService feeService = new VariableFeeService();
		double fee = feeService.calculateFee(transferAmount);
		assertThat(fee, equalTo(0d));
	}
	
	@Test
	public void testShouldBeOnePercentFeeMoreThanThousandTransferAmount(){
		double transferAmount = 1001d;
		VariableFeeService feeService = new VariableFeeService();
		double fee = feeService.calculateFee(transferAmount);
		assertThat(fee, equalTo(10.01d));
	}
	
	@Test
	public void testShouldBeTenThousandFeeIfTransferAmountMillion(){
		double transferAmount = 1000000d;
		VariableFeeService feeService = new VariableFeeService();
		double fee = feeService.calculateFee(transferAmount);
		assertThat(fee, equalTo(10000d));
	}
	
	@Test
	public void testShouldBeTwentyThousandFeeIfTransferAmountMoreThanOneMillion(){
		double transferAmount = 1000001d;
		VariableFeeService feeService = new VariableFeeService();
		double fee = feeService.calculateFee(transferAmount);
		assertThat(fee, equalTo(20000d));
	}

}
