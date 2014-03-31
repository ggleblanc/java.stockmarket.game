package com.ggl.stockmarket.game.model.factory;

import java.util.ArrayList;
import java.util.List;

import com.ggl.stockmarket.game.model.GameStatus;
import com.ggl.stockmarket.game.model.board.AbstractSquare;
import com.ggl.stockmarket.game.model.board.BrokerFeeSquare;
import com.ggl.stockmarket.game.model.board.BuyLimitSquare;
import com.ggl.stockmarket.game.model.board.BuySquare;
import com.ggl.stockmarket.game.model.board.SellAllSquare;
import com.ggl.stockmarket.game.model.board.StartSquare;

public class BoardFactory {
	
	protected static BoardFactory instance;
	
	protected static List<AbstractSquare> board;
	
	private BoardFactory(GameStatus gameStatus) {
		BoardFactory.board = new ArrayList<AbstractSquare>();
		generateBoard(gameStatus);
	}
	
	public static List<AbstractSquare> getBoard(GameStatus gameStatus) {
		if (instance == null) instance = new BoardFactory(gameStatus);
		return board;
	}
	
	protected void generateBoard(GameStatus gameStatus) {
		// Position 0
		StartSquare startSquare = new StartSquare();
		board.add(startSquare);
		
		BuySquare buySquare = new BuySquare(gameStatus, 3);
		buySquare.setDirection(+1);
		buySquare.setMarketAmount(+1);
		board.add(buySquare);
		
		buySquare = new BuySquare(gameStatus, 0);
		buySquare.setDirection(+1);
		buySquare.setMarketAmount(-2);
		board.add(buySquare);
		
		BuyLimitSquare buyLimitSquare = new BuyLimitSquare(gameStatus, 6);
		buyLimitSquare.setDirection(+1);
		buyLimitSquare.setMarketAmount(+3);
		buyLimitSquare.setBoardEntryDirection(-1);
		buyLimitSquare.setBoardEntryPosition(45);
		board.add(buyLimitSquare);
		
		buySquare = new BuySquare(gameStatus, 4);
		buySquare.setDirection(+1);
		buySquare.setMarketAmount(-4);
		board.add(buySquare);
		
		SellAllSquare sellAllSquare = new SellAllSquare(gameStatus, 7);
		sellAllSquare.setDirection(+1);
		sellAllSquare.setMarketAmount(+5);
		board.add(sellAllSquare);
		
		BrokerFeeSquare brokerFeeSquare = new BrokerFeeSquare();
		brokerFeeSquare.setMarketAmount(-20);
		board.add(brokerFeeSquare);
		
		sellAllSquare = new SellAllSquare(gameStatus, 1);
		sellAllSquare.setDirection(-1);
		sellAllSquare.setMarketAmount(-5);
		board.add(sellAllSquare);
		
		buySquare = new BuySquare(gameStatus, 3);
		buySquare.setDirection(-1);
		buySquare.setMarketAmount(+4);
		board.add(buySquare);
		
		buyLimitSquare = new BuyLimitSquare(gameStatus, 0);
		buyLimitSquare.setDirection(-1);
		buyLimitSquare.setMarketAmount(-3);
		buyLimitSquare.setBoardEntryDirection(+1);
		buyLimitSquare.setBoardEntryPosition(15);
		board.add(buyLimitSquare);
		
		buySquare = new BuySquare(gameStatus, 6);
		buySquare.setDirection(+1);
		buySquare.setMarketAmount(+2);
		board.add(buySquare);
		
		buySquare = new BuySquare(gameStatus, 4);
		buySquare.setDirection(+1);
		buySquare.setMarketAmount(-1);
		board.add(buySquare);
		
		// Position 12
		startSquare = new StartSquare();
		board.add(startSquare);
		
		buySquare = new BuySquare(gameStatus, 5);
		buySquare.setDirection(+1);
		buySquare.setMarketAmount(-1);
		board.add(buySquare);
		
		buySquare = new BuySquare(gameStatus, 7);
		buySquare.setDirection(+1);
		buySquare.setMarketAmount(+2);
		board.add(buySquare);
		
		buyLimitSquare = new BuyLimitSquare(gameStatus, 1);
		buyLimitSquare.setDirection(+1);
		buyLimitSquare.setMarketAmount(-3);
		buyLimitSquare.setBoardEntryDirection(-1);
		buyLimitSquare.setBoardEntryPosition(9);
		board.add(buyLimitSquare);
		
		buySquare = new BuySquare(gameStatus, 2);
		buySquare.setDirection(+1);
		buySquare.setMarketAmount(+4);
		board.add(buySquare);
		
		sellAllSquare = new SellAllSquare(gameStatus, 0);
		sellAllSquare.setDirection(+1);
		sellAllSquare.setMarketAmount(-5);
		board.add(sellAllSquare);
		
		brokerFeeSquare = new BrokerFeeSquare();
		brokerFeeSquare.setMarketAmount(+20);
		board.add(brokerFeeSquare);
		
		sellAllSquare = new SellAllSquare(gameStatus, 5);
		sellAllSquare.setDirection(-1);
		sellAllSquare.setMarketAmount(+5);
		board.add(sellAllSquare);
		
		buySquare = new BuySquare(gameStatus, 6);
		buySquare.setDirection(-1);
		buySquare.setMarketAmount(-4);
		board.add(buySquare);
		
		buyLimitSquare = new BuyLimitSquare(gameStatus, 4);
		buyLimitSquare.setDirection(-1);
		buyLimitSquare.setMarketAmount(+3);
		buyLimitSquare.setBoardEntryDirection(+1);
		buyLimitSquare.setBoardEntryPosition(27);
		board.add(buyLimitSquare);
		
		buySquare = new BuySquare(gameStatus, 3);
		buySquare.setDirection(+1);
		buySquare.setMarketAmount(-2);
		board.add(buySquare);
		
		buySquare = new BuySquare(gameStatus, 0);
		buySquare.setDirection(+1);
		buySquare.setMarketAmount(+1);
		board.add(buySquare);
		
		// Position 24
		startSquare = new StartSquare();
		board.add(startSquare);
		
		buySquare = new BuySquare(gameStatus, 1);
		buySquare.setDirection(+1);
		buySquare.setMarketAmount(+1);
		board.add(buySquare);
		
		buySquare = new BuySquare(gameStatus, 2);
		buySquare.setDirection(+1);
		buySquare.setMarketAmount(-2);
		board.add(buySquare);
		
		buyLimitSquare = new BuyLimitSquare(gameStatus, 5);
		buyLimitSquare.setDirection(+1);
		buyLimitSquare.setMarketAmount(+3);
		buyLimitSquare.setBoardEntryDirection(-1);
		buyLimitSquare.setBoardEntryPosition(21);
		board.add(buyLimitSquare);
		
		buySquare = new BuySquare(gameStatus, 7);
		buySquare.setDirection(+1);
		buySquare.setMarketAmount(-4);
		board.add(buySquare);
		
		sellAllSquare = new SellAllSquare(gameStatus, 4);
		sellAllSquare.setDirection(+1);
		sellAllSquare.setMarketAmount(+5);
		board.add(sellAllSquare);
		
		brokerFeeSquare = new BrokerFeeSquare();
		brokerFeeSquare.setMarketAmount(-20);
		board.add(brokerFeeSquare);
		
		sellAllSquare = new SellAllSquare(gameStatus, 3);
		sellAllSquare.setDirection(-1);
		sellAllSquare.setMarketAmount(-5);
		board.add(sellAllSquare);
		
		buySquare = new BuySquare(gameStatus, 0);
		buySquare.setDirection(-1);
		buySquare.setMarketAmount(+4);
		board.add(buySquare);
		
		buyLimitSquare = new BuyLimitSquare(gameStatus, 2);
		buyLimitSquare.setDirection(-1);
		buyLimitSquare.setMarketAmount(-3);
		buyLimitSquare.setBoardEntryDirection(+1);
		buyLimitSquare.setBoardEntryPosition(39);
		board.add(buyLimitSquare);
		
		buySquare = new BuySquare(gameStatus, 4);
		buySquare.setDirection(+1);
		buySquare.setMarketAmount(+2);
		board.add(buySquare);
		
		buySquare = new BuySquare(gameStatus, 6);
		buySquare.setDirection(+1);
		buySquare.setMarketAmount(-1);
		board.add(buySquare);
		
		// Position 36
		startSquare = new StartSquare();
		board.add(startSquare);
		
		buySquare = new BuySquare(gameStatus, 7);
		buySquare.setDirection(+1);
		buySquare.setMarketAmount(-1);
		board.add(buySquare);
		
		buySquare = new BuySquare(gameStatus, 5);
		buySquare.setDirection(+1);
		buySquare.setMarketAmount(+2);
		board.add(buySquare);
		
		buyLimitSquare = new BuyLimitSquare(gameStatus, 3);
		buyLimitSquare.setDirection(+1);
		buyLimitSquare.setMarketAmount(-3);
		buyLimitSquare.setBoardEntryDirection(-1);
		buyLimitSquare.setBoardEntryPosition(33);
		board.add(buyLimitSquare);
		
		buySquare = new BuySquare(gameStatus, 1);
		buySquare.setDirection(+1);
		buySquare.setMarketAmount(+4);
		board.add(buySquare);
		
		sellAllSquare = new SellAllSquare(gameStatus, 2);
		sellAllSquare.setDirection(+1);
		sellAllSquare.setMarketAmount(-5);
		board.add(sellAllSquare);
		
		brokerFeeSquare = new BrokerFeeSquare();
		brokerFeeSquare.setMarketAmount(+20);
		board.add(brokerFeeSquare);
		
		sellAllSquare = new SellAllSquare(gameStatus, 6);
		sellAllSquare.setDirection(-1);
		sellAllSquare.setMarketAmount(+5);
		board.add(sellAllSquare);
		
		buySquare = new BuySquare(gameStatus, 5);
		buySquare.setDirection(-1);
		buySquare.setMarketAmount(-4);
		board.add(buySquare);
		
		buyLimitSquare = new BuyLimitSquare(gameStatus, 7);
		buyLimitSquare.setDirection(-1);
		buyLimitSquare.setMarketAmount(+3);
		buyLimitSquare.setBoardEntryDirection(+1);
		buyLimitSquare.setBoardEntryPosition(3);
		board.add(buyLimitSquare);
		
		buySquare = new BuySquare(gameStatus, 1);
		buySquare.setDirection(+1);
		buySquare.setMarketAmount(-2);
		board.add(buySquare);
		
		buySquare = new BuySquare(gameStatus, 2);
		buySquare.setDirection(+1);
		buySquare.setMarketAmount(+1);
		board.add(buySquare);
	}

}
