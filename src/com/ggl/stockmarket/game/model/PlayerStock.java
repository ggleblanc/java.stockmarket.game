package com.ggl.stockmarket.game.model;

import java.text.NumberFormat;

public class PlayerStock {
	
	protected static final NumberFormat NUMBER_FORMAT = 
		NumberFormat.getInstance();
	
	protected String name;
	
	protected int amount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}
	
	public String getFormattedAmount() {
		return NUMBER_FORMAT.format(getAmount());
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public void addAmount(int amount) {
		this.amount += amount;
	}

}
