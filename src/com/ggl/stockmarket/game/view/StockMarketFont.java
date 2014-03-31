package com.ggl.stockmarket.game.view;

import java.awt.Font;

public class StockMarketFont {
	
	protected static final String FONT_NAME = "Verdana";
	protected static final String SPECIAL_FONT_NAME = "Comic Sans MS";
	
	public static Font getBoldFont(int pointSize) {
		return new Font(FONT_NAME, Font.BOLD, pointSize);
	}
	
	public static Font getSpecialBoldFont(int pointSize) {
		return new Font(SPECIAL_FONT_NAME, Font.BOLD, pointSize);
	}

}
