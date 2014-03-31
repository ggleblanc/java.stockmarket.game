package com.ggl.stockmarket.game.model.factory;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.ggl.stockmarket.game.model.Stock;

public class StockFactory {
	
	protected static StockFactory instance;
	
	protected static List<Stock> stocks;
	
	protected static final int stockPrices[][] = 
		{{ 18,  18,  19,  19,  20,  20,  21,  21,  22,  22,  23,  23,
		   24,  24,  25,  25,  26,  26,  27,  27,  28,  28,  29,  29,
		   30,  30,  30,  31,  31,  32,  32,  33,  33,  34,  34,  35,
		   35,  36,  36,  37,  37,  38,  38,  39,  39,  40,  40,  41,
		   41,  42,  42}, 
		 { 15,  16,  17,  18,  19,  20,  21,  22,  23,  24,  25,  26, 
		   27,  28,  29,  30,  31,  32,  33,  34,  35,  37,  39,  41,
		   43,  45,  47,  49,  51,  53,  55,  56,  57,  58,  59,  60,
		   61,  62,  63,  64,  65,  66,  67,  68,  69,  70,  71,  72,
		   73,  74,  75},
		 { 10,  12,  14,  16,  18,  20,  22,  24,  26,  28,  30,  32,
		   34,  36,  38,  40,  42,  44,  46,  48,  50,  52,  54,  56,
		   58,  60,  62,  64,  66,  68,  70,  72,  74,  76,  78,  80,
		   82,  84,  86,  88,  90,  92,  94,  96,  98, 100, 102, 104,
		  106, 108, 110},
		 { 30,  34,  38,  42,  46,  50,  54,  58,  62,  66,  70,  74,
		   78,  82,  86,  90,  94,  98, 102, 106, 110, 114, 118, 122,
		  126, 130, 134, 138, 142, 146, 150, 154, 158, 162, 166, 170,
		  174, 178, 182, 186, 190, 194, 198, 202, 206, 210, 214, 218,
		  222, 226, 230}};
	
	private StockFactory() {
		stocks = new ArrayList<Stock>();
		generateStocks();
	}
	
	public static List<Stock> getStocks() {
		if (instance == null) instance = new StockFactory();
		return stocks;
	}
	
	protected void generateStocks() {
		stocks.add(generateStock("International Shoe", 1, "F", Color.magenta));
		stocks.add(generateStock("General Mills", 1, "R", Color.blue));
		stocks.add(generateStock("Maytag", 2, "F", Color.cyan));
		stocks.add(generateStock("J. I. Case", 2, "R", Color.orange));
		stocks.add(generateStock("Western Publishing", 3, "F", Color.green));
		stocks.add(generateStock("American Motors", 3, "R", Color.yellow));
		stocks.add(generateStock("Woolworth", 4, "F", Color.pink));
		stocks.add(generateStock("Alcoa", 4, "R", Color.red));
	}
	
	protected Stock generateStock(String name, int dividend, String direction,
			Color backgroundColor) {
		Stock stock = new Stock(name, dividend, direction, backgroundColor);
		stock.setMinimumPrice(stockPrices[dividend - 1][0]);
		stock.setPrices(generateStockPrices(dividend, direction));
		return stock;
	}
	
	protected List<Integer> generateStockPrices(int dividend, String direction) {
		List<Integer> list = new ArrayList<Integer>();
		if (direction.equals("F")) {
			for (int i = 0; i < stockPrices[dividend - 1].length; i++) {
				list.add(stockPrices[dividend - 1][i]);
			}
		} else {
			for (int i = stockPrices[dividend - 1].length - 1; i >= 0; i--) {
				list.add(stockPrices[dividend - 1][i]);
			}
		}
		return list;
	}

}
