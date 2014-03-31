package com.ggl.stockmarket.game.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ggl.stockmarket.game.model.GameStatus;

public class DicePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	// top, left, bottom, right
	protected static final Insets subTitleInsets = new Insets(10, 0, 10, 0);
	protected static final Insets mediumInsets = new Insets(0, 10, 0, 10);
	protected static final Insets die_1Insets = new Insets(0, 10, 10, 10);
	protected static final Insets die_2Insets = new Insets(0, 10, 10, 10);
	
	protected boolean diceRoll;
	
	protected GameStatus gameStatus;
	
	protected JLabel turnTitle;
	protected JLabel diceLabel_1;
	protected JLabel diceLabel_2;
	protected JLabel message;
	
	protected StockMarketFrame frame;
	
	public DicePanel(GameStatus gameStatus) {
		super();
		this.gameStatus = gameStatus;
		this.diceRoll = false;
		gameStatus.setDie1(0);
		gameStatus.setDie2(0);
		createGUIControl(gameStatus);
	}
	
	protected void createGUIControl(final GameStatus gameStatus) {
		setBorder(BorderFactory.createLineBorder(Color.blue, 3));
		setLayout(new GridBagLayout());
		
		int gridy = 0;
		
		Font titleFont = StockMarketFont.getBoldFont(18);
		Font mediumFont = StockMarketFont.getBoldFont(14);
		Font diceFont = StockMarketFont.getBoldFont(36);
		
		turnTitle = new JLabel(createTurnText(gameStatus));
		turnTitle.setFont(mediumFont);
		turnTitle.setHorizontalAlignment(JLabel.CENTER);
		addComponent(this, turnTitle, 0, gridy++, 2, 1, mediumInsets,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
		
		JLabel lastTitle = new JLabel("Dice");
		lastTitle.setFont(titleFont);
		lastTitle.setHorizontalAlignment(JLabel.CENTER);
		addComponent(this, lastTitle, 0, gridy++, 2, 1, subTitleInsets, 
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
		
		diceLabel_1 = new JLabel(Integer.toString(gameStatus.getDie1()));
		diceLabel_1.setFont(diceFont);
		diceLabel_1.setHorizontalAlignment(JLabel.CENTER);
		addComponent(this, diceLabel_1, 0, gridy, 1, 1, die_1Insets, 
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
		
		diceLabel_2 = new JLabel(Integer.toString(gameStatus.getDie2()));
		diceLabel_2.setFont(diceFont);
		diceLabel_2.setHorizontalAlignment(JLabel.CENTER);
		addComponent(this, diceLabel_2, 1, gridy++, 1, 1, die_2Insets, 
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
		
		message = new JLabel();
		message.setFont(mediumFont);
		message.setHorizontalAlignment(JLabel.CENTER);
		addComponent(this, message, 0, gridy++, 2, 1, subTitleInsets, 
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
	}
	
	protected void addComponent(Container container, Component component,
			int gridx, int gridy, int gridwidth, int gridheight, 
			Insets insets, int anchor, int fill) {
		GridBagConstraints gbc = new GridBagConstraints(gridx, gridy,
				gridwidth, gridheight, 1.0, 1.0, anchor, fill, insets, 0, 0);
		container.add(component, gbc);
	}
	
	public void setTurnTitle(GameStatus gameStatus) {
		turnTitle.setText(createTurnText(gameStatus));
	}
	
	public void setDice(GameStatus gameStatus) {
		diceLabel_1.setText(Integer.toString(gameStatus.getDie1()));
		diceLabel_2.setText(Integer.toString(gameStatus.getDie2()));
	}
	
	public void setMessage(Color color, String text) {
		message.setText(text);
		message.setForeground(color);
	}
	
	protected String createTurnText(GameStatus gameStatus) {
		String text = gameStatus.getPlayer(gameStatus.getPlayerTurn())
				.getName() + "'s turn";
		return text;
	}

}
