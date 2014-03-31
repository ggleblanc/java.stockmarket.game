package com.ggl.stockmarket.game.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ggl.stockmarket.game.model.board.AbstractSquare;
import com.ggl.stockmarket.game.model.board.CenterSquare;
import com.ggl.stockmarket.game.model.board.StockholdersMeetingSquare;
import com.ggl.stockmarket.game.model.factory.BoardFactory;
import com.ggl.stockmarket.game.model.factory.OccupationFactory;
import com.ggl.stockmarket.game.model.factory.StockFactory;
import com.ggl.stockmarket.game.model.factory.StockholdersMeetingFactory;
import com.ggl.stockmarket.game.view.StockMarketFont;

public class GameStatus {
	
	protected static final int WINNING_VALUE = 1000000;
	
	protected static final int OCCUPATION_VALUE = 1000;
	
	protected int die1;
	protected int die2;
	protected int playerTurn;
	protected int stockPointer;
	
	protected CenterSquare centerSquare;
	
	protected List<Occupation> occupations;
	protected List<Player> players;
	protected List<Stock> stocks;
	
	protected List<AbstractSquare> board;
	
	protected Random random;
	
	protected StockholdersMeetingSquare[][] stockholdersMeeting;
	
	public GameStatus() {
		this.occupations  = OccupationFactory.getOccupations();
		this.players      = new ArrayList<Player>();
		//TODO Replace with player dialogs in StockMarketFrame
		createPlayer("Player 1");
		createPlayer("Player 2");
		this.playerTurn   = 0;
		this.stocks       = StockFactory.getStocks();
		this.stockholdersMeeting = StockholdersMeetingFactory.getStockholdersMeeting();
		Stock stock       = stocks.get(0);
		this.stockPointer = stock.getPriceSize() / 2;
		this.board        = BoardFactory.getBoard(this);
		this.centerSquare = new CenterSquare();
		this.random       = new Random();
	}

	protected void createPlayer(String name) {
		Player player  = new Player();
		player.setName(name);
		player.setCash(0);
		addPlayer(player);
	}
	
	public CenterSquare getCenterSquare() {
		return centerSquare;
	}

	public void rollDice() {
		this.die1 = random.nextInt(6) + 1;
		this.die2 = random.nextInt(6) + 1;
	}
	
	public int getDie1() {
		return die1;
	}
	
	public int getDie2() {
		return die2;
	}
	
	public int getDiceTotal() {
		return die1 + die2;
	}
	
	public void setDie1(int die1) {
		this.die1 = die1;
	}

	public void setDie2(int die2) {
		this.die2 = die2;
	}

	public int getPlayerTurn() {
		return playerTurn;
	}
	
	public void setPlayerTurn(int playerTurn) {
		this.playerTurn = playerTurn;
	}
	
	public void nextPlayerTurn() {
		this.playerTurn++;
		if (this.playerTurn >= players.size()) {
			this.playerTurn = 0;
		}
	}
	
	public List<Occupation> getOccupations() {
		return occupations;
	}
	
	public Occupation getOccupation(String name) {
		for (Occupation occupation : occupations) {
			if (name.equals(occupation.getName())) {
				return occupation;
			}
		}
		return null;
	}
	
	public void checkOccupationPayAmount() {
		int roll = getDiceTotal();
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			if (player.isEmployed()) {
				Occupation occupation = player.getOccupation();
				if (occupation != null) {
					if (occupation.isValidRoll(roll)) {
						player.addCash(occupation.getAmount());
					}
				}
			}
		}
	}
	
	public int getStockPointer() {
		return stockPointer;
	}

	public void setStockPointer(int stockPointer) {
		this.stockPointer = stockPointer;
	}
	
	public void addStockPointer(int stockPointer) {
		this.stockPointer += stockPointer;
		int size = stocks.get(0).getPriceSize() - 1;
		if (this.stockPointer > size) {
			this.stockPointer = size + size - this.stockPointer;
		} else if (this.stockPointer < 0) {
			this.stockPointer = -this.stockPointer;
		}
	}

	public List<Player> getPlayers() {
		return players;
	}
	
	public Player getPlayer(int playerTurn) {
		return players.get(playerTurn);
	}
	
	public void addPlayer(Player player) {
		this.players.add(player);
	}
	
	public List<AbstractSquare> getBoard() {
		return board;
	}

	public StockholdersMeetingSquare[][] getStockholdersMeeting() {
		return stockholdersMeeting;
	}

	public List<Stock> getStocks() {
		return stocks;
	}
	
	public Stock getStock(String name) {
		for (Stock stock : stocks) {
			if (name.equals(stock.getName())) {
				return stock;
			}
		}
		
		return null;
	}
	
	public Stock getStock(int position) {
		return stocks.get(position);
	}
	
	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}
	
	public void movePlayer(Player player) {
		int boardPosition = player.getBoardPosition();
		AbstractSquare abstractSquare = board.get(boardPosition);
		int direction = abstractSquare.getDirection();
		if (direction == 0) {
			direction = abstractSquare.getDirection(getDiceTotal());
		}
		//TODO Walk dice roll, checking for stockholders meeting entry
		int newBoardPosition = boardPosition + getDiceTotal() * direction;
		if (newBoardPosition < 0) {
			newBoardPosition += board.size();
		} else if (newBoardPosition >= board.size()) {
			newBoardPosition -= board.size();
		}
		player.setBoardPosition(newBoardPosition);
	}
	
	public boolean isWinner(int value) {
		if (value >= WINNING_VALUE) return true;
		else return false;
	}
	
	public boolean isOccupationOver(int value) {
		if (value >= OCCUPATION_VALUE) return true;
		else return false;
	}
	
	public BufferedImage drawMarkers(BufferedImage image) {
		BufferedImage bufferedImage = new BufferedImage(image.getWidth(),
				image.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
		g.drawImage(image, 0, 0, null);
		
		for (int i = 0; i < players.size(); i++) {
			if (i != playerTurn) {
				Player player = players.get(i);
				drawMarker(g, player, i);
			}
		}
		
		Player player = players.get(playerTurn);
		drawMarker(g, player, playerTurn);
		
		g.dispose();
		
		return bufferedImage;
	}

	protected void drawMarker(Graphics2D g, Player player, int playerNumber) {
		int diameter = AbstractSquare.IMAGE_WIDTH / 2;
		if (player.isValidStockholdersMeetingPosition()) {

		} else if (player.isValidBoardPosition()) {
			Rectangle r = board.get(player.getBoardPosition())
					.getBoardLocation();
			drawShape(g, (int) r.getCenterX(), (int) r.getCenterY(), diameter,
					playerNumber);
		} else if (player.isEmployed()) {
			Occupation occupation = player.getOccupation();
			if (occupation != null) {
				Rectangle r = occupation.getOccupationLocation();
				drawShape(g, (int) r.getCenterX(), (int) r.getCenterY(),
						diameter, playerNumber);
			}
		}
	}
	
	protected void drawShape(Graphics2D g, int x, int y, int diameter,
			int playerNumber) {
		int px = x - diameter / 2;
		int py = y - diameter / 2;
		g.setColor(Color.black);
		g.fillOval(px, py, diameter, diameter);

		Font directionFont = StockMarketFont.getBoldFont(48);
		FontRenderContext frc = g.getFontRenderContext();

		g.setColor(Color.white);
		TextLayout layout = new TextLayout(Integer.toString(playerNumber + 1),
				directionFont, frc);
		Rectangle2D bounds = layout.getBounds();
		float fx = (float) (x - (bounds.getWidth() / 2) - bounds.getX());
		float fy = (float) (y + (bounds.getHeight() / 2));
		layout.draw(g, fx, fy);
	}
}
