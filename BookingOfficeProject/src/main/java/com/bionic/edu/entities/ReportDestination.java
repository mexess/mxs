package com.bionic.edu.entities;

public class ReportDestination {

	private String destination;
	private double sum;
	private long amount;

	public ReportDestination(String destination, double sum, long amount) {
		this.destination = destination;
		this.sum = sum;
		this.amount = amount;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
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

	public String toString() {
		String report = "Destination: " + destination + ".\n";

		if (amount == 0) {
			report += "There were not sold any tickets.";
			return report;
		}

		if (amount == 1) {
			report += "There was sold " + amount + " ticket";
		} else {
			report += "There were sold " + amount + " tickets";
		}

		report += " with total sum: " + sum;

		return report;
	}

}
