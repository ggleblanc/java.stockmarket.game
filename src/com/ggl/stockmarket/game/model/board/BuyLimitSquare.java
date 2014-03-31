package com.ggl.stockmarket.game.model.board;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.List;

import com.ggl.stockmarket.game.model.GameStatus;
import com.ggl.stockmarket.game.model.Player;
import com.ggl.stockmarket.game.model.PlayerStock;
import com.ggl.stockmarket.game.view.StockMarketFont;

public class BuyLimitSquare extends AbstractSquare {
	
	protected static final int LIMIT = 1;
	
	public static final int IMAGE_WIDTH = AbstractSquare.IMAGE_WIDTH;
	public static final int IMAGE_HEIGHT = AbstractSquare.IMAGE_HEIGHT + 64;
	
	protected String name;
	
	protected int dividend;
	
	protected int stockholdersMeetingLevel;
	
	protected int boardEntryPosition;
	protected int boardEntryDirection;

	public BuyLimitSquare(GameStatus gameStatus, int position) {
		this.name = gameStatus.getStock(position).getName();
		this.dividend = gameStatus.getStock(position).getDividend();
		this.backgroundColor = gameStatus.getStock(position).getBackgroundColor();
		this.stockholdersMeetingLevel = gameStatus.getStock(position)
				.getDividend() - 1;
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

	public int getBoardEntryPosition() {
		return boardEntryPosition;
	}

	public void setBoardEntryPosition(int boardEntryPosition) {
		this.boardEntryPosition = boardEntryPosition;
	}

	public int getBoardEntryDirection() {
		return boardEntryDirection;
	}

	public void setBoardEntryDirection(int boardEntryDirection) {
		this.boardEntryDirection = boardEntryDirection;
	}

	public int getStockholdersMeetingLevel() {
		return stockholdersMeetingLevel;
	}

	@Override
	public void execute(GameStatus gameStatus) {
		Player player = gameStatus.getPlayer(gameStatus.getPlayerTurn());
		PlayerStock playerStock = player.getPlayerStock(name);
		if (playerStock != null) {
			player.addCash(dividend * playerStock.getAmount());
		}
		gameStatus.addStockPointer(marketAmount);
		//TODO Write a stock purchase dialog 
		//TODO Write a stockholders meeting dialog
	}
	
	@Override
	public BufferedImage drawImage() {
		BufferedImage bufferedImage = super.drawImage(IMAGE_WIDTH,
				IMAGE_HEIGHT, AbstractSquare.INSETS);
		Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
		drawMeetingText(g);
		drawDividendText(g, dividend);
		List<String> parts = splitStockName(name);
		float disp = 118.0F;
		for (String part : parts) {
			drawStockText(g, part, disp);
			disp += 22.0F;
		}
		disp += 10.0F;
		drawLimitText(g, "Purchase Limit", disp);
		disp += 14.0F;
		drawLimitText(g, "One Share", disp);
		g.dispose();
		return bufferedImage;
	}
	
	protected void drawMeetingText(Graphics2D g) {
		Polygon p = new Polygon();
		p.addPoint(0, 64);
		p.addPoint(0, 32);
		p.addPoint(IMAGE_WIDTH / 2, 0);
		p.addPoint(IMAGE_WIDTH, 32);
		p.addPoint(IMAGE_WIDTH, 64);
		g.setColor(Color.black);
		g.fillPolygon(p);
		
		Font directionFont = StockMarketFont.getBoldFont(12);
		FontRenderContext frc = g.getFontRenderContext();
		StringBuilder sb = new StringBuilder();
		sb.append("Stockholders Meeting");
		
		g.setColor(Color.white);
		TextLayout layout = new TextLayout(sb.toString(), directionFont, frc);
		Rectangle2D bounds = layout.getBounds();
		float fx = (float) (bounds.getX()) + (float) (IMAGE_WIDTH - bounds.getWidth()) * 0.5F; 
		float fy = (float) bounds.getHeight() + 42.0F;
		layout.draw(g, fx, fy);
	}
	
	protected void drawDividendText(Graphics2D g, int dividend) {
		Font directionFont = StockMarketFont.getBoldFont(16);
		FontRenderContext frc = g.getFontRenderContext();
		StringBuilder sb = new StringBuilder();
		sb.append("$");
		sb.append(dividend);
		sb.append(" Dividend");
		
		setTextColor(g);
		TextLayout layout = new TextLayout(sb.toString(), directionFont, frc);
		Rectangle2D bounds = layout.getBounds();
		float fx = (float) (bounds.getX()) + (float) (IMAGE_WIDTH - bounds.getWidth()) * 0.5F; 
		float fy = (float) bounds.getHeight() + 84.0F;
		layout.draw(g, fx, fy);
	}
	
	protected void drawStockText(Graphics2D g, String name, float disp) {
		Font directionFont = StockMarketFont.getBoldFont(18);
		FontRenderContext frc = g.getFontRenderContext();
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		
		setTextColor(g);
		TextLayout layout = new TextLayout(sb.toString(), directionFont, frc);
		Rectangle2D bounds = layout.getBounds();
		float fx = (float) (bounds.getX()) + (float) (IMAGE_WIDTH - bounds.getWidth()) * 0.5F; 
		float fy = (float) bounds.getHeight() + disp;
		layout.draw(g, fx, fy);
	}
	
	protected void drawLimitText(Graphics2D g, String name, float disp) {
		Font directionFont = StockMarketFont.getBoldFont(12);
		FontRenderContext frc = g.getFontRenderContext();
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		
		setTextColor(g);
		TextLayout layout = new TextLayout(sb.toString(), directionFont, frc);
		Rectangle2D bounds = layout.getBounds();
		float fx = (float) (bounds.getX()) + (float) (IMAGE_WIDTH - bounds.getWidth()) * 0.5F; 
		float fy = (float) bounds.getHeight() + disp;
		layout.draw(g, fx, fy);
	}

}
