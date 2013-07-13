package com.djackatron2.service;

public class FeeService {

	private double fixedRate;

	public FeeService(double fixedRate) {
		this.fixedRate = fixedRate;
	}

	public double calculateFee(double transferAmount) {
		return this.fixedRate;
	}

}
