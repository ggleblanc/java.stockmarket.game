package com.ggl.stockmarket.game.model.factory;

import com.ggl.stockmarket.game.model.board.StockholdersMeetingSquare;

public class StockholdersMeetingFactory {
	
	protected static StockholdersMeetingFactory instance;
	
	protected static StockholdersMeetingSquare[][] stockholdersMeeting;
	
	private StockholdersMeetingFactory() {
		stockholdersMeeting = new StockholdersMeetingSquare[4][];
		stockholdersMeeting[0] = generateStockholdersMeeting1();
		stockholdersMeeting[1] = generateStockholdersMeeting2();
		stockholdersMeeting[2] = generateStockholdersMeeting3();
		stockholdersMeeting[3] = generateStockholdersMeeting4();
	}
	
	public static StockholdersMeetingSquare[][] getStockholdersMeeting() {
		if (instance == null) instance = new StockholdersMeetingFactory();
		return stockholdersMeeting;
	}
	
	protected StockholdersMeetingSquare[] generateStockholdersMeeting1() {
		StockholdersMeetingSquare[] square = new StockholdersMeetingSquare [9];
		square[0] = new StockholdersMeetingSquare("1 For 1", 2);
		square[1] = new StockholdersMeetingSquare("3 For 1", 4);
		square[2] = new StockholdersMeetingSquare("2 For 1", 3);
		square[3] = new StockholdersMeetingSquare("3 For 1", 4);
		square[4] = new StockholdersMeetingSquare("2 For 1", 3);
		square[5] = new StockholdersMeetingSquare("3 For 1", 4);
		square[6] = new StockholdersMeetingSquare("2 For 1", 3);
		square[7] = new StockholdersMeetingSquare("3 For 1", 4);
		square[8] = new StockholdersMeetingSquare("1 For 1", 2);
		return square;
	}
	
	protected StockholdersMeetingSquare[] generateStockholdersMeeting2() {
		StockholdersMeetingSquare[] square = new StockholdersMeetingSquare [9];
		square[0] = new StockholdersMeetingSquare("1 For 1", 2);
		square[1] = new StockholdersMeetingSquare("2 For 1", 3);
		square[2] = new StockholdersMeetingSquare("3 For 1", 4);
		square[3] = new StockholdersMeetingSquare("2 For 1", 3);
		square[4] = new StockholdersMeetingSquare("3 For 1", 4);
		square[5] = new StockholdersMeetingSquare("2 For 1", 3);
		square[6] = new StockholdersMeetingSquare("3 For 1", 4);
		square[7] = new StockholdersMeetingSquare("2 For 1", 3);
		square[8] = new StockholdersMeetingSquare("1 For 1", 2);
		return square;
	}
	
	protected StockholdersMeetingSquare[] generateStockholdersMeeting3() {
		StockholdersMeetingSquare[] square = new StockholdersMeetingSquare [9];
		square[0] = new StockholdersMeetingSquare("1 For 1", 2);
		square[1] = new StockholdersMeetingSquare("2 For 1", 3);
		square[2] = new StockholdersMeetingSquare("3 For 1", 4);
		square[3] = new StockholdersMeetingSquare("2 For 1", 3);
		square[4] = new StockholdersMeetingSquare("1 For 1", 2);
		square[5] = new StockholdersMeetingSquare("2 For 1", 3);
		square[6] = new StockholdersMeetingSquare("3 For 1", 4);
		square[7] = new StockholdersMeetingSquare("2 For 1", 3);
		square[8] = new StockholdersMeetingSquare("1 For 1", 2);
		return square;
	}
	
	protected StockholdersMeetingSquare[] generateStockholdersMeeting4() {
		StockholdersMeetingSquare[] square = new StockholdersMeetingSquare [9];
		square[0] = new StockholdersMeetingSquare("1 For 1", 2);
		square[1] = new StockholdersMeetingSquare("2 For 1", 3);
		square[2] = new StockholdersMeetingSquare("1 For 1", 2);
		square[3] = new StockholdersMeetingSquare("2 For 1", 3);
		square[4] = new StockholdersMeetingSquare("3 For 1", 4);
		square[5] = new StockholdersMeetingSquare("2 For 1", 3);
		square[6] = new StockholdersMeetingSquare("1 For 1", 2);
		square[7] = new StockholdersMeetingSquare("2 For 1", 3);
		square[8] = new StockholdersMeetingSquare("1 For 1", 2);
		return square;
	}

}
