package com.ggl.stockmarket.game.model.board;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.ggl.stockmarket.game.model.GameStatus;
import com.ggl.stockmarket.game.view.StockMarketFont;

public abstract class AbstractSquare {
	
	public static final int IMAGE_WIDTH = 160;
	public static final int IMAGE_HEIGHT = 192;
	
	public static final Insets INSETS = new Insets(4, 2, 4, 2);
	
	/** 
	 * The direction to move on the next turn. 
	 * +1 means clockwise, -1 means counter-clockwise,
	 * and zero means that an odd dice roll is clockwise,
	 * while an even dice roll is counter-clockwise. 
	 */
	protected int direction;
	
	/**
	 * The direction and distance to move the market.
	 * A positive integer means the market moves down.
	 * A negative integer means the market moves up.
	 */
	protected int marketAmount;
	
	protected Color backgroundColor;
	
	protected Rectangle boardLocation;

	public int getDirection(int roll) {
		if (direction == 0) {
			if ((roll / 2 * 2) == roll) {
				return -1;
			} else {
				return +1;
			}
		} else {
			return direction;
		}
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getDirection() {
		return direction;
	}
	
	public int getMarketAmount() {
		return marketAmount;
	}

	public void setMarketAmount(int marketAmount) {
		this.marketAmount = marketAmount;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	
	public Rectangle getBoardLocation() {
		return boardLocation;
	}

	public void setBoardLocation(Rectangle boardLocation) {
		this.boardLocation = boardLocation;
	}

	public List<String> splitStockName(String name) {
		List<String> list = new ArrayList<String>();
		int pos = name.lastIndexOf(' ');
		if (pos < 0) {
			list.add(name);
		} else {
			list.add(name.substring(0, pos));
			list.add(name.substring(pos + 1));
		}
		return list;
	}
	
	protected BufferedImage drawImage(int width, int height, Insets insets) {
		BufferedImage bufferedImage = new BufferedImage(width,
				height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
		drawSquare(g, width, height, insets);
		drawMovementText(g, width, height);
		drawMovementArrows(g, width, height);

		return bufferedImage;
	}

	public BufferedImage drawImage() {
		BufferedImage bufferedImage = new BufferedImage(IMAGE_WIDTH,
				IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
		drawSquare(g, IMAGE_WIDTH, IMAGE_HEIGHT, INSETS);
		drawMovementText(g, IMAGE_WIDTH, IMAGE_HEIGHT);
		drawMovementArrows(g, IMAGE_WIDTH, IMAGE_HEIGHT);
		g.dispose();
		
		return bufferedImage;
	}

	protected void drawSquare(Graphics2D g, int width, int height, Insets insets) {
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);

		if (backgroundColor == null) {
			g.setColor(Color.white);
		} else {
			g.setColor(backgroundColor);
		}
		g.fillRect(insets.left, insets.top, width - insets.right - insets.left,
				height - insets.bottom - insets.top);
	}

	protected void drawMovementText(Graphics2D g, int width, int height) {
		Font directionFont = StockMarketFont.getBoldFont(16);
		FontRenderContext frc = g.getFontRenderContext();
		StringBuilder sb = new StringBuilder();
		if (marketAmount < 0) {
			sb.append("Down ");
			sb.append(Math.abs(marketAmount));
		} else if (marketAmount > 0) {
			sb.append("Up ");
			sb.append(marketAmount);
		} else {
			sb.append("Odd    Even");
		}

		setTextColor(g);
		TextLayout layout = new TextLayout(sb.toString(), directionFont, frc);
		Rectangle2D bounds = layout.getBounds();
		float fx = (float) (bounds.getX())
				+ (float) (width - bounds.getWidth()) * 0.5F;
		float fy = (float) height - 10.0F;
		layout.draw(g, fx, fy);
	}
	
	public void setTextColor(Graphics2D g) {
		g.setColor(Color.black);
		if ((backgroundColor != null) && (backgroundColor.equals(Color.blue))) {
			g.setColor(Color.white);
		}
	}
	
	protected void drawMovementArrows(Graphics2D g, int width, int height) {
		if (direction == 0) {
			int w = (width - 30) / 2;
			int x = 10;
			int y = height - 40;
			drawArrow(g, x, y, +1, w);
			x = width - w - 10;
			drawArrow(g, x, y, -1, w);
		} else {
			int w = (width - 40);
			int x = (width - w) / 2;
			int y = height - 40;
			drawArrow(g, x, y, direction, w);
		}
	}
	
	protected void drawArrow(Graphics2D g, int x, int y, int direction,
			int length) {
		// arrow thickness, arrow point height and width
		int t = 4;
		int h = 14;
		int w = 20;

		Polygon p = new Polygon();
		if (direction > 0) {
			g.fillRect(x + h, y, length - h, t);
			p.addPoint(x + h, y - ((w - t) / 2));
			p.addPoint(x + h, y + ((w + t) / 2));
			p.addPoint(x, y + (t / 2));
		} else {
			g.fillRect(x, y, length - h, t);
			p.addPoint(x + length - h, y - ((w - t) / 2));
			p.addPoint(x + length - h, y + ((w + t) / 2));
			p.addPoint(x + length, y + (t / 2));
		}
		g.fillPolygon(p);
	}
	
	public abstract void execute(GameStatus gameStatus);
	
}
