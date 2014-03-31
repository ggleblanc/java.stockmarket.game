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

public class CenterSquare {
	
	public static final int IMAGE_WIDTH = 800;
	public static final int IMAGE_HEIGHT = 800;
	
	public static final Insets INSETS = new Insets(4, 4, 4, 4);
	
	protected Rectangle rollDiceLocation;
	protected Rectangle sellStockLocation;
	protected Rectangle squareLocation;
	
	public Rectangle getRollDiceLocation() {
		Rectangle r = new Rectangle(rollDiceLocation);
		r.x += squareLocation.x;
		r.y += squareLocation.y;
		return r;
	}
	
	public void setRollDiceLocation(Rectangle rollDiceLocation) {
		this.rollDiceLocation = rollDiceLocation;
	}
	
	public Rectangle getSellStockLocation() {
		Rectangle r = new Rectangle(sellStockLocation);
		r.x += squareLocation.x;
		r.y += squareLocation.y;
		return r;
	}

	public void setSellStockLocation(Rectangle sellStockLocation) {
		this.sellStockLocation = sellStockLocation;
	}
	

	public void setSquareLocation(Rectangle squareLocation) {
		this.squareLocation = squareLocation;
	}

	public BufferedImage drawImage() {
		BufferedImage bufferedImage = new BufferedImage(IMAGE_WIDTH,
				IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
		drawSquare(g, IMAGE_WIDTH, IMAGE_HEIGHT, INSETS);
		drawText(g, IMAGE_WIDTH, IMAGE_HEIGHT);
		g.dispose();
		
		return bufferedImage;
	}

	protected void drawSquare(Graphics2D g, int width, int height, Insets insets) {
		g.setColor(Color.blue);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.white);
		g.fillRect(insets.left, insets.top, width - insets.right - insets.left,
				height - insets.bottom - insets.top);
	}
	
	protected void drawText(Graphics2D g, int width, int height) {
		Font titleFont = StockMarketFont.getSpecialBoldFont(108);
		FontRenderContext frc = g.getFontRenderContext();
		TextLayout layout = new TextLayout("STOCK", titleFont, frc);
		Rectangle2D bounds = layout.getBounds();
		float fx = (float) (bounds.getX())
				+ (float) (width - bounds.getWidth()) * 0.5F;
		float fy = (float) (bounds.getHeight() + 90);
		g.setColor(Color.black);
		layout.draw(g, fx, fy);
		
		layout = new TextLayout("MARKET", titleFont, frc);
		bounds = layout.getBounds();
		fx = (float) (bounds.getX()) 
				+ (float) (width - bounds.getWidth()) * 0.5F;
		fy += (float) (bounds.getHeight() + 20);
		g.setColor(Color.black);
		layout.draw(g, fx, fy);

		layout = new TextLayout("GAME", titleFont, frc);
		bounds = layout.getBounds();
		fx = (float) (bounds.getX()) 
				+ (float) (width - bounds.getWidth()) * 0.5F;
		fy += (float) (bounds.getHeight() + 20);
		g.setColor(Color.black);
		layout.draw(g, fx, fy);
		
		Font optionFont = StockMarketFont.getBoldFont(72);
		frc = g.getFontRenderContext();
		layout = new TextLayout("Sell Stock", optionFont, frc);
		bounds = layout.getBounds();
		fx = (float) (bounds.getX())
				+ (float) (width - bounds.getWidth()) * 0.5F;
		fy += (float) (bounds.getHeight() + 100);
		g.setColor(Color.black);
		layout.draw(g, fx, fy);
		setSellStockLocation(new Rectangle((int) fx,
				(int) (fy - bounds.getHeight()), (int) bounds.getWidth(),
				(int) bounds.getHeight()));
		
		layout = new TextLayout("Roll Dice", optionFont, frc);
		bounds = layout.getBounds();
		fx = (float) (bounds.getX())
				+ (float) (width - bounds.getWidth()) * 0.5F;
		fy += (float) (bounds.getHeight() + 100);
		g.setColor(Color.black);
		layout.draw(g, fx, fy);
		setRollDiceLocation(new Rectangle((int) fx,
				(int) (fy - bounds.getHeight()), (int) bounds.getWidth(),
				(int) bounds.getHeight()));
	}

}
