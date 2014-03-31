package com.ggl.stockmarket.game.model;

import java.awt.Color;
import java.text.NumberFormat;
import java.util.List;

public class Stock {
	
	protected static final NumberFormat CURRENCY_FORMAT = 
		NumberFormat.getCurrencyInstance();
	
	protected Color backgroundColor;
	
	protected String name;
	
	protected int dividend;
	
	protected String direction;
	
	protected int minimumPrice;
	
	protected List<Integer> prices;
	
	public Stock() {
		
	}
	
	public Stock(String name, int dividend, String direction,
			Color backgroundColor) {
		this.name = name;
		this.dividend = dividend;
		this.direction = direction;
		this.backgroundColor = backgroundColor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDividend() {
		return dividend;
	}

	public void setDividend(int dividend) {
		this.dividend = dividend;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public void setPrices(List<Integer> prices) {
		this.prices = prices;
	}
	
	public int getPrice(int pointer) {
		return prices.get(pointer);
	}
	
	public int getPriceSize() {
		return prices.size();
	}
	
	public String getFormattedPrice(int pointer) {
		CURRENCY_FORMAT.setMaximumFractionDigits(0);
		return CURRENCY_FORMAT.format(getPrice(pointer));
	}

	public int getMinimumPrice() {
		return minimumPrice;
	}

	public void setMinimumPrice(int minimumPrice) {
		this.minimumPrice = minimumPrice;
	}

}
