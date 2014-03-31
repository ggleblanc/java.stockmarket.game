package com.ggl.stockmarket.game.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ggl.stockmarket.game.model.GameStatus;
import com.ggl.stockmarket.game.model.Player;

public class PlayerSummaryPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	// top, left, bottom, right
	protected static final Insets subTitleInsets = new Insets(0, 0, 10, 0);
	protected static final Insets numberInsets = new Insets(0, 10, 5, 0);
	protected static final Insets stockInsets = new Insets(0, 10, 5, 10);
	protected static final Insets priceInsets = new Insets(0, 20, 10, 10);
	
	protected GameStatus gameStatus;
	
	public PlayerSummaryPanel(GameStatus gameStatus) {
		super();
		this.gameStatus = gameStatus;
		createGUIControl(gameStatus);
	}
	
	public void updateGUIControl() {
		for (Player player : gameStatus.getPlayers()) {
			for (Component component : getComponents()) {
				if (component instanceof JLabel) {
					JLabel label = (JLabel) component;
					setFormattedNetWorth(player, label);
				}
			}
		}
	}

	protected void setFormattedNetWorth(Player player, JLabel label) {
		if ((label.getName() != null)
				&& (label.getName().equals(player.getName()))) {
			label.setText(player.getFormattedNetWorth(gameStatus));
		}
	}
	
	protected void createGUIControl(GameStatus gameStatus) {
		setBorder(BorderFactory.createLineBorder(Color.blue, 3));
		setLayout(new GridBagLayout());
		
		int gridy = 0;
		
		Font titleFont = StockMarketFont.getBoldFont(18);
		Font mediumFont = StockMarketFont.getBoldFont(14);
		Font smallFont = StockMarketFont.getBoldFont(12);
		
		JLabel lastTitle = new JLabel("Players");
		lastTitle.setFont(titleFont);
		lastTitle.setHorizontalAlignment(JLabel.CENTER);
		addComponent(this, lastTitle, 0, gridy++, 3, 1, subTitleInsets, 
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
		
		gridy = createGUIPlayerTitle(this, gridy, mediumFont);
		gridy = createGUIPlayerLines(this, gridy, smallFont, gameStatus);
	}
	
	protected int createGUIPlayerTitle(JPanel panel, int gridy, Font mediumFont) {
		JLabel dummyTitle = new JLabel(" ");
		dummyTitle.setFont(mediumFont);
		dummyTitle.setHorizontalAlignment(JLabel.LEADING);
		addComponent(panel, dummyTitle, 0, gridy, 1, 1, numberInsets,
				GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);

		JLabel nameTitle = new JLabel("Name");
		nameTitle.setFont(mediumFont);
		nameTitle.setHorizontalAlignment(JLabel.LEADING);
		addComponent(panel, nameTitle, 1, gridy, 1, 1, stockInsets,
				GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);
		
		JLabel netWorthTitle = new JLabel("Net Worth");
		netWorthTitle.setFont(mediumFont);
		netWorthTitle.setHorizontalAlignment(JLabel.TRAILING);
		addComponent(panel, netWorthTitle, 2, gridy++, 1, 1, priceInsets,
				GridBagConstraints.LINE_END, GridBagConstraints.HORIZONTAL);

		return gridy;
	}
	
	protected int createGUIPlayerLines(JPanel panel, int gridy, Font smallFont, 
			GameStatus gameStatus) {
		List<Player> players = gameStatus.getPlayers();
		int count = 0;
		
		while (count < players.size()) {
			Player player = players.get(count);
			
			JLabel numberLabel = new JLabel(Integer.toString(count + 1));
			numberLabel.setFont(smallFont);
			numberLabel.setHorizontalAlignment(JLabel.LEADING);
			addComponent(panel, numberLabel, 0, gridy, 1, 1, numberInsets,
					GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);

			JButton nameButton = new JButton(player.getName());
			nameButton.setFont(smallFont);
			nameButton.setHorizontalAlignment(JButton.CENTER);
			addComponent(panel, nameButton, 1, gridy, 1, 1, stockInsets,
					GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);

			JLabel netWorthLabel = new JLabel(player.getFormattedNetWorth(gameStatus));
			netWorthLabel.setFont(smallFont);
			netWorthLabel.setHorizontalAlignment(JLabel.TRAILING);
			netWorthLabel.setName(player.getName());
			addComponent(panel, netWorthLabel, 2, gridy++, 1, 1, priceInsets,
					GridBagConstraints.LINE_END, GridBagConstraints.HORIZONTAL);

			count++;
		}	
		
		return gridy;
	}
	
	
	protected void addComponent(Container container, Component component,
			int gridx, int gridy, int gridwidth, int gridheight, 
			Insets insets, int anchor, int fill) {
		GridBagConstraints gbc = new GridBagConstraints(gridx, gridy,
				gridwidth, gridheight, 1.0, 1.0, anchor, fill, insets, 0, 0);
		container.add(component, gbc);
	}
}
