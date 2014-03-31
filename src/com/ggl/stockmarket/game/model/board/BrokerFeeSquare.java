package com.ggl.stockmarket.game.model.board;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import com.ggl.stockmarket.game.model.GameStatus;
import com.ggl.stockmarket.game.model.Player;
import com.ggl.stockmarket.game.view.StockMarketFont;

public class BrokerFeeSquare extends AbstractSquare {
	
	protected static final int FEE = 10;
	
	public static final int IMAGE_WIDTH = AbstractSquare.IMAGE_HEIGHT;
	
	protected static final Insets INSETS = new Insets(2, 4, 4, 2);
	
	public BrokerFeeSquare() {
		this.direction = -1;
	}

	@Override
	public void execute(GameStatus gameStatus) {
		Player player = gameStatus.getPlayer(gameStatus.getPlayerTurn());
		int count = player.countStocks();
		player.subtractCash(count * FEE);
		gameStatus.addStockPointer(marketAmount);
	}
	
	@Override
	public BufferedImage drawImage() {
		BufferedImage bufferedImage = super.drawImage(IMAGE_WIDTH,
				IMAGE_HEIGHT, INSETS);
		Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
		drawPayText(g);
		drawBrokerText(g);
		drawFeeText(g);
		g.dispose();
		return bufferedImage;
	}
	
	protected void drawPayText(Graphics2D g) {
		Font directionFont = StockMarketFont.getBoldFont(28);
		FontRenderContext frc = g.getFontRenderContext();
		StringBuilder sb = new StringBuilder();
		sb.append("PAY");
		
		setTextColor(g);
		TextLayout layout = new TextLayout(sb.toString(), directionFont, frc);
		Rectangle2D bounds = layout.getBounds();
		float fx = (float) (bounds.getX()) + (float) (IMAGE_WIDTH - bounds.getWidth()) * 0.5F; 
		float fy = (float) bounds.getHeight() + 20.0F;
		layout.draw(g, fx, fy);
	}
	
	protected void drawBrokerText(Graphics2D g) {
		Font directionFont = StockMarketFont.getBoldFont(18);
		FontRenderContext frc = g.getFontRenderContext();
		StringBuilder sb = new StringBuilder();
		sb.append("Broker Fee");
		
		setTextColor(g);
		TextLayout layout = new TextLayout(sb.toString(), directionFont, frc);
		Rectangle2D bounds = layout.getBounds();
		float fx = (float) (bounds.getX()) + (float) (IMAGE_WIDTH - bounds.getWidth()) * 0.5F; 
		float fy = (float) bounds.getHeight() + 50.0F;
		layout.draw(g, fx, fy);
	}
	
	protected void drawFeeText(Graphics2D g) {
		Font directionFont = StockMarketFont.getBoldFont(16);
		FontRenderContext frc = g.getFontRenderContext();
		StringBuilder sb = new StringBuilder();
		sb.append("$");
		sb.append(FEE);
		sb.append(" per share");
		
		setTextColor(g);
		TextLayout layout = new TextLayout(sb.toString(), directionFont, frc);
		Rectangle2D bounds = layout.getBounds();
		float fx = (float) (bounds.getX()) + (float) (IMAGE_WIDTH - bounds.getWidth()) * 0.5F; 
		float fy = (float) bounds.getHeight() + 90.0F;
		layout.draw(g, fx, fy);
	}

}
