package com.bionic.edu.entities;

public class TotalReport {

	private double sum;
	private long amount;

	public TotalReport() {

	}

	public TotalReport(Double sum, long amount) {

		if (sum == null) {
			this.sum = 0.0d;
		} else {
		this.sum = sum.doubleValue();
		}
		this.amount = amount;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

}
