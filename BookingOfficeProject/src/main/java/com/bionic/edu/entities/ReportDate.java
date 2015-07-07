package com.bionic.edu.entities;

import java.sql.Date;

public class ReportDate {

	private Date date;
	private double sum;
	private long amount;

	public ReportDate(Date date, double sum, long amount) {
		this.date = date;
		this.sum = sum;
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
