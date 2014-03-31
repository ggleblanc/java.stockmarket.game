package com.ggl.stockmarket.game.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ggl.stockmarket.game.model.GameStatus;
import com.ggl.stockmarket.game.model.Stock;

public class StockPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	// top, left, bottom, right
	protected static final Insets subTitleInsets = new Insets(0, 0, 5, 0);
	protected static final Insets colorInsets = new Insets(0, 10, 5, 0);
	protected static final Insets stockInsets = new Insets(0, 10, 5, 10);
	protected static final Insets priceInsets = new Insets(0, 20, 5, 10);
	
	protected GameStatus gameStatus;
	
	public StockPanel(GameStatus gameStatus) {
		super();
		this.gameStatus = gameStatus;
		createGUIControl(gameStatus);
	}
	
	public void updateGUIControl() {
		for (Stock stock : gameStatus.getStocks()) {
			for (Component component : getComponents()) {
				if (component instanceof JLabel) {
					JLabel label = (JLabel) component;
					setPrice(stock, label);
				}
			}
		}
	}
	
	protected void setPrice(Stock stock, JLabel label) {
		if ((label.getName() != null)
				&& (label.getName().equals(stock.getName()))) {
			label.setText(stock.getFormattedPrice(gameStatus.getStockPointer()));
		}
	}
	
	protected void createGUIControl(GameStatus gameStatus) {
//		setBorder(BorderFactory.createLoweredBevelBorder());
		setBorder(BorderFactory.createLineBorder(Color.blue, 3));
		setLayout(new GridBagLayout());
		
		int gridy = 0;
		
		Font titleFont = StockMarketFont.getBoldFont(18);
		Font mediumFont = StockMarketFont.getBoldFont(14);
		Font smallFont = StockMarketFont.getBoldFont(12);
		
		JLabel lastTitle = new JLabel("Stocks");
		lastTitle.setFont(titleFont);
		lastTitle.setHorizontalAlignment(JLabel.CENTER);
		addComponent(this, lastTitle, 0, gridy++, 3, 1, subTitleInsets, 
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
		
		gridy = createGUIStockTitle(this, gridy, mediumFont);
		gridy = createGUIStockLines(this, gridy, smallFont, gameStatus);
		
	}
	
	protected int createGUIStockTitle(JPanel panel, int gridy, Font mediumFont) {
		JLabel nullTitle = new JLabel(" ");
		nullTitle.setFont(mediumFont);
		nullTitle.setHorizontalAlignment(JLabel.LEADING);
		addComponent(panel, nullTitle, 0, gridy, 1, 1, colorInsets,
				GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);

		JLabel stockTitle = new JLabel("Stock");
		stockTitle.setFont(mediumFont);
		stockTitle.setHorizontalAlignment(JLabel.LEADING);
		addComponent(panel, stockTitle, 1, gridy, 1, 1, stockInsets,
				GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);

		JLabel priceTitle = new JLabel("Price");
		priceTitle.setFont(mediumFont);
		priceTitle.setHorizontalAlignment(JLabel.TRAILING);
		addComponent(panel, priceTitle, 2, gridy++, 1, 1, priceInsets,
				GridBagConstraints.LINE_END, GridBagConstraints.HORIZONTAL);

		return gridy;
	}
	
	protected int createGUIStockLines(JPanel panel, int gridy, Font smallFont,
			GameStatus gameStatus) {
		List<Stock> stocks = gameStatus.getStocks();
		int group = 0;
		int count = 0;

		while (group < 2) {
			while (count < stocks.size()) {
				Stock stock = stocks.get(count);

				JLabel colorLabel = new JLabel(new ImageIcon(getColor(stock)));
				colorLabel.setFont(smallFont);
				colorLabel.setHorizontalAlignment(JLabel.LEADING);
				addComponent(panel, colorLabel, 0, gridy, 1, 1, colorInsets,
						GridBagConstraints.LINE_START,
						GridBagConstraints.HORIZONTAL);

				JLabel stockLabel = new JLabel(stock.getName());
				stockLabel.setFont(smallFont);
				stockLabel.setHorizontalAlignment(JLabel.LEADING);
				addComponent(panel, stockLabel, 1, gridy, 1, 1, stockInsets,
						GridBagConstraints.LINE_START,
						GridBagConstraints.HORIZONTAL);

				JLabel priceLabel = new JLabel(
						stock.getFormattedPrice(gameStatus.getStockPointer()));
				priceLabel.setFont(smallFont);
				priceLabel.setHorizontalAlignment(JLabel.TRAILING);
				priceLabel.setName(stock.getName());
				addComponent(panel, priceLabel, 2, gridy++, 1, 1, priceInsets,
						GridBagConstraints.LINE_END,
						GridBagConstraints.HORIZONTAL);

				count += 2;
			}
			count = ++group;
		}

		return gridy;
	}
	
	protected BufferedImage getColor(Stock stock) {
		int size = 12;
		BufferedImage colorImage = new BufferedImage(size, size,
				BufferedImage.TYPE_INT_RGB);
		
		Graphics2D g = (Graphics2D) colorImage.getGraphics();
		g.setColor(stock.getBackgroundColor());
		g.fillRect(0, 0, size, size);
		g.dispose();
		
		return colorImage;
	}
	
	protected void addComponent(Container container, Component component,
			int gridx, int gridy, int gridwidth, int gridheight, 
			Insets insets, int anchor, int fill) {
		GridBagConstraints gbc = new GridBagConstraints(gridx, gridy,
				gridwidth, gridheight, 1.0, 1.0, anchor, fill, insets, 0, 0);
		container.add(component, gbc);
	}
	
}
