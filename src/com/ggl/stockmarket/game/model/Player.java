package com.ggl.stockmarket.game.model;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class Player {
	
	protected static final NumberFormat CURRENCY_FORMAT = 
		NumberFormat.getCurrencyInstance();
	
	protected static final int INVALID_BOARD_POSITION = -1;

	protected String name;
	
	protected boolean employed;
	
	protected PlayerState playerState;

	protected Occupation occupation;
	
	protected int boardPosition;
	protected int stockholdersMeetingPosition;
	protected int cash;
	
	protected List<PlayerStock> playerStocks;
	
	public Player() {
		this.employed = true;
		this.occupation = null;
		this.boardPosition = INVALID_BOARD_POSITION;
		this.stockholdersMeetingPosition = INVALID_BOARD_POSITION;
		this.cash = 0;
		this.playerStocks = new ArrayList<PlayerStock>();
		this.playerState = new PlayerState();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Occupation getOccupation() {
		return occupation;
	}

	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}
	
	public boolean isEmployed() {
		return employed;
	}

	public void setEmployed(boolean employed) {
		this.employed = employed;
	}

	public PlayerState getPlayerState() {
		return playerState;
	}

	public void setPlayerState(PlayerState playerState) {
		this.playerState = playerState;
	}

	public int getBoardPosition() {
		return boardPosition;
	}

	public void setBoardPosition(int boardPosition) {
		this.boardPosition = boardPosition;
	}
	
	public void resetBoardPosition() {
		this.boardPosition = INVALID_BOARD_POSITION;
	}
	
	public boolean isValidBoardPosition() {
		return boardPosition != INVALID_BOARD_POSITION;
	}

	public int getStockholdersMeetingPosition() {
		return stockholdersMeetingPosition;
	}

	public void setStockholdersMeetingPosition(int stockholdersMeetingPosition) {
		this.stockholdersMeetingPosition = stockholdersMeetingPosition;
	}

	public void resetStockholdersMeetingPosition() {
		this.stockholdersMeetingPosition = INVALID_BOARD_POSITION;
	}
	
	public boolean isValidStockholdersMeetingPosition() {
		return stockholdersMeetingPosition != INVALID_BOARD_POSITION;
	}
	
	public int getCash() {
		return cash;
	}
	
	public String getFormattedCash() {
		CURRENCY_FORMAT.setMaximumFractionDigits(0);
		return CURRENCY_FORMAT.format(cash);
	}

	public void setCash(int cash) {
		this.cash = cash;
	}
	
	public void addCash(int cash) {
		this.cash += cash;
	}
	
	public int subtractCash(int cash) {
		return this.cash -= cash;
	}

	public List<PlayerStock> getPlayerStocks() {
		return playerStocks;
	}
	
	public PlayerStock getPlayerStock(String name) {
		for (PlayerStock playerStock : playerStocks) {
			if (playerStock.getName().equals(name)) {
				return playerStock;
			}
		}
		return null;
	}
	
	public void setStocks(List<PlayerStock> playerStocks) {
		this.playerStocks = playerStocks;
	}
	
	public void sellAllStock(String name, int price) {
		for (PlayerStock playerStock : playerStocks) {
			if (playerStock.getName().equals(name)) {
				addCash(playerStock.getAmount() * price);
				playerStocks.remove(playerStock);
				break;
			}
		}
	}
	
	public int countStocks() {
		int count = 0;
		for (PlayerStock playerStock : playerStocks) {
			count += playerStock.getAmount();
		}
		return count;
	}
	
	public int getStockValue(GameStatus gameStatus, PlayerStock playerStock) {
		Stock stock = gameStatus.getStock(playerStock.getName());
		int price = stock.getPrice(gameStatus.getStockPointer());
		return price * playerStock.getAmount();
	}
	
	public int getNetWorth(GameStatus gameStatus) {
		int value = getCash();
		for (PlayerStock playerStock : playerStocks) {
			value += getStockValue(gameStatus, playerStock);
		}
		return value;
	}
	
	public String getFormattedNetWorth(GameStatus gameStatus) {
		CURRENCY_FORMAT.setMaximumFractionDigits(0);
		return CURRENCY_FORMAT.format(getNetWorth(gameStatus));
	}

}
