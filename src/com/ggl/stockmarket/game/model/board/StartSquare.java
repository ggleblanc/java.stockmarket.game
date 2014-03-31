package com.ggl.stockmarket.game.model.board;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import com.ggl.stockmarket.game.model.GameStatus;
import com.ggl.stockmarket.game.model.Player;
import com.ggl.stockmarket.game.view.StockMarketFont;

public class StartSquare extends AbstractSquare {
	
	protected static final int FEE = 100;
	
	protected boolean start;
	
	public StartSquare() {
		this.direction = 0;
		this.marketAmount = 0;
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	@Override
	public void execute(GameStatus gameStatus) {
		if (!isStart()) {
			Player player = gameStatus.getPlayer(gameStatus.getPlayerTurn());
			player.subtractCash(FEE);
		}

	}
	
	@Override
	public BufferedImage drawImage() {
		BufferedImage bufferedImage = super.drawImage();
		Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
		drawStartText(g);
		drawFeeText(g);
		g.dispose();
		return bufferedImage;
	}
	
	protected void drawStartText(Graphics2D g) {
		Font directionFont = StockMarketFont.getBoldFont(28);
		FontRenderContext frc = g.getFontRenderContext();
		StringBuilder sb = new StringBuilder();
		sb.append("START");
		
		g.setColor(Color.red);
		TextLayout layout = new TextLayout(sb.toString(), directionFont, frc);
		Rectangle2D bounds = layout.getBounds();
		float fx = (float) (bounds.getX()) + (float) (IMAGE_WIDTH - bounds.getWidth()) * 0.5F; 
		float fy = (float) bounds.getHeight() + 20.0F;
		layout.draw(g, fx, fy);
	}
	
	protected void drawFeeText(Graphics2D g) {
		Font directionFont = StockMarketFont.getBoldFont(18);
		FontRenderContext frc = g.getFontRenderContext();
		StringBuilder sb = new StringBuilder();
		sb.append("Pay $");
		sb.append(FEE);
		sb.append(" Fee");
		
		setTextColor(g);
		TextLayout layout = new TextLayout(sb.toString(), directionFont, frc);
		Rectangle2D bounds = layout.getBounds();
		float fx = (float) (bounds.getX()) + (float) (IMAGE_WIDTH - bounds.getWidth()) * 0.5F; 
		float fy = (float) bounds.getHeight() + 60.0F;
		layout.draw(g, fx, fy);
	}
}
