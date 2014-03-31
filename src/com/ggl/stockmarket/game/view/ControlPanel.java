package com.ggl.stockmarket.game.view;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.ggl.stockmarket.game.model.GameStatus;

public class ControlPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	protected StockPanel stockPanel;
	
	protected PlayerSummaryPanel playerSummaryPanel;
	
	protected DicePanel dicePanel;
	
	protected GameStatus gameStatus;
	
	public ControlPanel(GameStatus gameStatus) {
		super();
		this.gameStatus = gameStatus;
		createGUIControl(gameStatus);
	}
	
	protected void createGUIControl(GameStatus gameStatus) {
//		setBorder(BorderFactory.createLineBorder(Color.green));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//		setPreferredSize(new Dimension(250, 0));
		
		stockPanel = new StockPanel(gameStatus);
		add(stockPanel);
		
		JPanel filler = new JPanel();
		add(filler);
		
		playerSummaryPanel = new PlayerSummaryPanel(gameStatus);
		add(playerSummaryPanel);
		
		filler = new JPanel();
		add(filler);
		
		dicePanel = new DicePanel(gameStatus);
		add(dicePanel);
	}
	
	public PlayerSummaryPanel getPlayerSummaryPanel() {
		return playerSummaryPanel;
	}
	
	public Dimension getStockPanelPreferredSize() {
		return stockPanel.getPreferredSize();
	}
	
	public Dimension getPlayerSummaryPanelPreferredSize() {
		return playerSummaryPanel.getPreferredSize();
	}
	
	public StockPanel getStockPanel() {
		return stockPanel;
	}

	public DicePanel getDicePanel() {
		return dicePanel;
	}

	public void setDicePanel(DicePanel dicePanel) {
		this.dicePanel = dicePanel;
	}
	
}
