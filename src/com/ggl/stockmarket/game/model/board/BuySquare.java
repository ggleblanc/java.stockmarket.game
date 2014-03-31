package com.ggl.stockmarket.game.model.board;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.List;

import com.ggl.stockmarket.game.model.GameStatus;
import com.ggl.stockmarket.game.model.Player;
import com.ggl.stockmarket.game.model.PlayerStock;
import com.ggl.stockmarket.game.view.StockMarketFont;

public class BuySquare extends AbstractSquare {
	
	protected String name;
	
	protected int dividend;
	
	public BuySquare(GameStatus gameStatus, int position) {
		this.name = gameStatus.getStock(position).getName();
		this.dividend = gameStatus.getStock(position).getDividend();
		this.backgroundColor = gameStatus.getStock(position).getBackgroundColor();
	}

	public int getDividend() {
		return dividend;
	}

	public String getName() {
		return name;
	}

	@Override
	public void execute(GameStatus gameStatus) {
		Player player = gameStatus.getPlayer(gameStatus.getPlayerTurn());
		PlayerStock playerStock = player.getPlayerStock(name);
		if (playerStock != null) {
			player.addCash(dividend * playerStock.getAmount());
		}
		gameStatus.addStockPointer(marketAmount);
		//TODO Write stock purchase dialog
		
	}
	
	@Override
	public BufferedImage drawImage() {
		BufferedImage bufferedImage = super.drawImage();
		Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
		drawDividendText(g, dividend);
		List<String> parts = splitStockName(name);
		float disp = 65.0F;
		for (String part : parts) {
			drawStockText(g, part, disp);
			disp += 22.0F;
		}
		g.dispose();
		return bufferedImage;
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
		float fy = (float) bounds.getHeight() + 20.0F;
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

}
