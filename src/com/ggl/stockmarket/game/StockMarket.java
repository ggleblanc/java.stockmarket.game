package com.ggl.stockmarket.game;

import javax.swing.SwingUtilities;

import com.ggl.stockmarket.game.model.GameStatus;
import com.ggl.stockmarket.game.view.StockMarketFrame;

public class StockMarket implements Runnable {
	
	@Override
	public void run() {
		GameStatus gameStatus = new GameStatus();
		new StockMarketFrame(gameStatus).run();
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new StockMarket());
	}

}
