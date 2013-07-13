package com.test.djackatron2.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.djackatron2.service.impl.FixedFeeService;

@RunWith(value = Parameterized.class)
public class FixedFeeServiceTest {

	private double fixedFeeAmount;
	private double transferAmount;
	private double expected;

	public FixedFeeServiceTest(double fixedFeeAmount, double transferAmount,
			double expceted) {
		this.fixedFeeAmount = fixedFeeAmount;
		this.transferAmount = transferAmount;
		this.expected = expceted;
	}

	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] { { 5, 1, 5 }, { 5, 10, 5 },
				{ 5, 100, 5 } };
		return Arrays.asList(data);
	}

	@Test
	public void testShouldBeCalculateFeeByTransferAmount() {
		FixedFeeService feeService = new FixedFeeService(this.fixedFeeAmount);
		double feeRate = feeService.calculateFee(this.transferAmount);
		assertThat(feeRate, equalTo(expected));
	}
}
