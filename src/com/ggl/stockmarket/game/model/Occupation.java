package com.ggl.stockmarket.game.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.text.NumberFormat;
import java.util.List;

import com.ggl.stockmarket.game.model.board.AbstractSquare;
import com.ggl.stockmarket.game.view.StockMarketFont;

public class Occupation {
	
	protected static final NumberFormat CURRENCY_FORMAT = 
		NumberFormat.getCurrencyInstance();
	
	public static final int IMAGE_WIDTH = AbstractSquare.IMAGE_WIDTH
			+ AbstractSquare.IMAGE_WIDTH;
	public static final int IMAGE_HEIGHT = AbstractSquare.IMAGE_WIDTH
			+ AbstractSquare.IMAGE_WIDTH;
	
	public static final Insets INSETS = new Insets(0, 0, 0, 0);
	
	protected int amount;
	
	protected List<Integer> rolls;
	
	protected Rectangle occupationLocation;
	
	protected String name;
	
	public Occupation() {
		
	}

	public Occupation(String name, int amount) {
		this.name = name;
		this.amount = amount;
	}

	public int getAmount() {
		return amount;
	}
	
	public String getFormattedAmount() {
		CURRENCY_FORMAT.setMaximumFractionDigits(0);
		return CURRENCY_FORMAT.format(amount);
	}

	public List<Integer> getRolls() {
		return rolls;
	}

	public String getName() {
		return name;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setRolls(List<Integer> rolls) {
		this.rolls = rolls;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isValidRoll(int roll) {
		for (Integer t_roll : rolls) {
			if (roll == t_roll) return true; 
		}
		return false;
	}
	
	public Rectangle getOccupationLocation() {
		return occupationLocation;
	}

	public void setOccupationLocation(Rectangle occupationLocation) {
		this.occupationLocation = occupationLocation;
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
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Occupation [amount=");
		builder.append(amount);
		builder.append(", rolls=");
		builder.append(rolls);
		builder.append(", occupationLocation=");
		builder.append(occupationLocation);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}

	protected void drawSquare(Graphics2D g, int width, int height, Insets insets) {
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.white);
		g.fillRect(insets.left, insets.top, width - insets.right - insets.left,
				height - insets.bottom - insets.top);
	}
	
	protected void drawText(Graphics2D g, int width, int height) {
		Font occupationFont = StockMarketFont.getBoldFont(32);
		FontRenderContext frc = g.getFontRenderContext();
		TextLayout layout = new TextLayout(getName(), occupationFont, frc);
		Rectangle2D bounds = layout.getBounds();
		float fx = (float) (bounds.getX())
				+ (float) (width - bounds.getWidth()) * 0.5F;
		float fy = (float) (bounds.getHeight() + (IMAGE_HEIGHT - 93) / 2);
		g.setColor(Color.black);
		layout.draw(g, fx, fy);
		
		Font textFont = StockMarketFont.getBoldFont(24);
		frc = g.getFontRenderContext();
		StringBuilder sb = new StringBuilder();
		sb.append(getRolls().get(0));
		sb.append(" or ");
		sb.append(getRolls().get(1));
		sb.append(" pays");
		layout = new TextLayout(sb.toString(), textFont, frc);
		bounds = layout.getBounds();
		fx = (float) (bounds.getX())
				+ (float) (width - bounds.getWidth()) * 0.5F;
		fy += (float) bounds.getHeight() + 10.0F;
		g.setColor(Color.black);
		layout.draw(g, fx, fy);
		
		sb = new StringBuilder();
		sb.append(getFormattedAmount());
		sb.append(" salary");
		layout = new TextLayout(sb.toString(), textFont, frc);
		bounds = layout.getBounds();
		fx = (float) (bounds.getX())
				+ (float) (width - bounds.getWidth()) * 0.5F;
		fy += (float) bounds.getHeight() + 10.0F;
		g.setColor(Color.black);
		layout.draw(g, fx, fy);
	}
}
