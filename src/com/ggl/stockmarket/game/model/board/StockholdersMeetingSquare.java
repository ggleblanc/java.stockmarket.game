package com.ggl.stockmarket.game.model.board;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import com.ggl.stockmarket.game.view.StockMarketFont;

public class StockholdersMeetingSquare {
	
	public static final int IMAGE_WIDTH = AbstractSquare.IMAGE_WIDTH;
	public static final int IMAGE_HEIGHT = 64;
	
	protected String displayText;
	
	protected int multiplier;
	
	protected Rectangle boardLocation;
	
	public StockholdersMeetingSquare(String displayText, int multiplier) {
		this.displayText = displayText;
		this.multiplier = multiplier;
	}

	public String getDisplayText() {
		return displayText;
	}

	public int getMultiplier() {
		return multiplier;
	}
	
	public Rectangle getBoardLocation() {
		return boardLocation;
	}

	public void setBoardLocation(Rectangle boardLocation) {
		this.boardLocation = boardLocation;
	}

	public BufferedImage drawImage(Insets insets) {
		BufferedImage bufferedImage = new BufferedImage(IMAGE_WIDTH,
				IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
		drawSquare(g, IMAGE_WIDTH, IMAGE_HEIGHT, insets);
		drawDisplayText(g, getDisplayText());
		g.dispose();
		return bufferedImage;
	}
	
	protected void drawSquare(Graphics2D g, int width, int height, Insets insets) {
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.white);
		g.fillRect(insets.left, insets.top, width - insets.right - insets.left,
				height - insets.bottom - insets.top);
	}
	
	protected void drawDisplayText(Graphics2D g, String displayText) {
		Font directionFont = StockMarketFont.getBoldFont(32);
		FontRenderContext frc = g.getFontRenderContext();
		StringBuilder sb = new StringBuilder();
		sb.append(displayText);

		g.setColor(Color.black);
		TextLayout layout = new TextLayout(sb.toString(), directionFont, frc);
		Rectangle2D bounds = layout.getBounds();
		float fx = (float) (IMAGE_WIDTH - bounds.getWidth()) * 0.5F;
		float fy = (float) ((IMAGE_HEIGHT - bounds.getHeight()) * 0.5F)
				+ (float) bounds.getHeight();
		layout.draw(g, fx, fy);
	}

}
