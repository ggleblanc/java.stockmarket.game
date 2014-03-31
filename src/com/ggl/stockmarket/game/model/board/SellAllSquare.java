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
import com.ggl.stockmarket.game.view.StockMarketFont;

public class SellAllSquare extends AbstractSquare {

	protected String name;

	protected int minimumPrice;

	public SellAllSquare(GameStatus gameStatus, int position) {
		this.name = gameStatus.getStock(position).getName();
		this.minimumPrice = gameStatus.getStock(position).getMinimumPrice();
		this.backgroundColor = gameStatus.getStock(position).getBackgroundColor();
	}

	public String getName() {
		return name;
	}

	public int getMinimumPrice() {
		return minimumPrice;
	}
	
	@Override
	public void execute(GameStatus gameStatus) {
		Player player = gameStatus.getPlayer(gameStatus.getPlayerTurn());
		player.sellAllStock(name, minimumPrice);
		gameStatus.addStockPointer(marketAmount);
	}
	
	@Override
	public BufferedImage drawImage() {
		BufferedImage bufferedImage = super.drawImage();
		Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
		drawSellAllText(g);
		List<String> parts = splitStockName(name);
		float disp = 55.0F;
		for (String part : parts) {
			drawStockText(g, part, disp);
			disp += 22.0F;
		}
		disp += 10.0F;
		drawFeeText(g, minimumPrice, disp);
		g.dispose();
		return bufferedImage;
	}
	
	protected void drawSellAllText(Graphics2D g) {
		Font directionFont = StockMarketFont.getBoldFont(28);
		FontRenderContext frc = g.getFontRenderContext();
		StringBuilder sb = new StringBuilder();
		sb.append("Sell All");
		
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
	
	protected void drawFeeText(Graphics2D g, int fee, float disp) {
		Font directionFont = StockMarketFont.getBoldFont(12);
		FontRenderContext frc = g.getFontRenderContext();
		StringBuilder sb = new StringBuilder();
		sb.append("at $");
		sb.append(fee);
		sb.append(" per share");
		
		setTextColor(g);
		TextLayout layout = new TextLayout(sb.toString(), directionFont, frc);
		Rectangle2D bounds = layout.getBounds();
		float fx = (float) (bounds.getX()) + (float) (IMAGE_WIDTH - bounds.getWidth()) * 0.5F; 
		float fy = (float) bounds.getHeight() + disp;
		layout.draw(g, fx, fy);
	}

}
