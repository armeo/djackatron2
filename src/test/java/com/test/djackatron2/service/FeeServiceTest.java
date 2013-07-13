package com.test.djackatron2.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.djackatron2.service.FeeService;

@RunWith(value = Parameterized.class)
public class FeeServiceTest {

	private double fixedRate;
	private double transferAmount;
	private double expected;
	
	public FeeServiceTest(double fixedRate, double transferAmount, double expceted){
		this.fixedRate = fixedRate;
		this.transferAmount = transferAmount;
		this.expected = expceted;
	}
	
	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] { { 5, 1, 5 }, { 5, 10, 5 }, { 5, 100, 5 } };
		return Arrays.asList(data);
	}

	@Test
	public void testShouldBeCalculateFeeByTransferAmount() {
		FeeService feeService = new FeeService(this.fixedRate);
		double feeRate = feeService.calculateFee(this.transferAmount);
		assertThat(feeRate, equalTo(expected));
	}
}
