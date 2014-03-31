package com.ggl.stockmarket.game.view;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import com.ggl.stockmarket.game.model.GameStatus;
import com.ggl.stockmarket.game.model.Occupation;
import com.ggl.stockmarket.game.model.Player;

public class OccupationDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	// top, left, bottom, right
	protected static final Insets subTitleInsets = new Insets(0, 0, 10, 0);
	protected static final Insets stockInsets = new Insets(0, 10, 10, 10);
	protected static final Insets priceInsets = new Insets(0, 20, 10, 10);
	
	protected GameStatus gameStatus;
	
	protected Occupation playerOccupation;
	
	protected StockMarketFrame frame;
	
	public OccupationDialog(StockMarketFrame frame, String title,
			GameStatus gameStatus, Player player) {
		super(frame, true);
		setTitle(title);
		this.frame = frame;
		this.gameStatus = gameStatus;
		this.playerOccupation = null;
		createGUIControl(gameStatus, player);
	}
	
	protected void createGUIControl(GameStatus gameStatus, final Player player) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		
		Font titleFont = StockMarketFont.getBoldFont(18);
		Font mediumFont = StockMarketFont.getBoldFont(14);
		Font smallFont = StockMarketFont.getBoldFont(12);
		
		int gridy = 0;
		
		List<Occupation> occupations = gameStatus.getOccupations();
		
		JLabel lastTitle = new JLabel("Occupations for " + player.getName());
		lastTitle.setFont(titleFont);
		lastTitle.setHorizontalAlignment(JLabel.CENTER);
		addComponent(panel, lastTitle, 0, gridy++, 3, 1, subTitleInsets, 
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
		
		gridy = createGUIOccupationTitle(panel, gridy, mediumFont);
		gridy = createGUIOccupationLines(panel, gridy, smallFont, 
				occupations);
		
		JPanel buttonPanel = new JPanel(new GridLayout(0, 1));
		TitledBorder border = BorderFactory
				.createTitledBorder("Select one");
		border.setTitleFont(mediumFont);
		buttonPanel.setBorder(border);
		
		ActionListener actionListener = new RadioButtonActionListener();
		ButtonGroup buttonGroup = new ButtonGroup();

		for (int i = 0; i < occupations.size(); i++) {
			Occupation occupation = occupations.get(i);
			Occupation testOccupation = player.getOccupation();
			JRadioButton radioButton = new JRadioButton(occupation.getName());
			radioButton.setSelected(false);
			if (testOccupation != null) {
				if (testOccupation.getName().equals(occupation.getName())) {
					radioButton.setSelected(true);
					playerOccupation = testOccupation;
				}
			}
			radioButton.addActionListener(actionListener);
			radioButton.setFont(smallFont);
			buttonPanel.add(radioButton);
			buttonGroup.add(radioButton);
		}
		
		addComponent(panel, buttonPanel, 0, gridy++, 3, 1, subTitleInsets,
				GridBagConstraints.CENTER, GridBagConstraints.NONE);
		
		JButton okButton = new JButton("OK");
		okButton.setFont(smallFont);
		okButton.setHorizontalAlignment(JButton.CENTER);
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				if (playerOccupation == null) return;
				player.setOccupation(playerOccupation);
				setVisible(false);
				dispose();				
			}
			
		});
		addComponent(panel, okButton, 0, gridy++, 3, 1, subTitleInsets,
				GridBagConstraints.CENTER, GridBagConstraints.NONE);
		
		add(panel);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		
		frame.getImagePanel().positionDialog(frame, this);
		
		setVisible(true);
	}
	
	protected int createGUIOccupationTitle(JPanel panel, int gridy, Font mediumFont) {
		JLabel nameTitle = new JLabel("Name");
		nameTitle.setFont(mediumFont);
		nameTitle.setHorizontalAlignment(JLabel.LEADING);
		addComponent(panel, nameTitle, 0, gridy, 1, 1, stockInsets,
				GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);

		JLabel diceRollsTitle = new JLabel("Dice Rolls");
		diceRollsTitle.setFont(mediumFont);
		diceRollsTitle.setHorizontalAlignment(JLabel.LEADING);
		addComponent(panel, diceRollsTitle, 1, gridy, 1, 1, priceInsets,
				GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);
		
		JLabel payAmountTitle = new JLabel("Pay Amount");
		payAmountTitle.setFont(mediumFont);
		payAmountTitle.setHorizontalAlignment(JLabel.TRAILING);
		addComponent(panel, payAmountTitle, 2, gridy++, 1, 1, priceInsets,
				GridBagConstraints.LINE_END, GridBagConstraints.HORIZONTAL);

		return gridy;
	}
	
	protected int createGUIOccupationLines(JPanel panel, int gridy, Font smallFont, 
			List<Occupation> occupations) {
		int count = 0;
		
		while (count < occupations.size()) {
			Occupation occupation = occupations.get(count);

			JLabel nameLabel = new JLabel(occupation.getName());
			nameLabel.setFont(smallFont);
			nameLabel.setHorizontalAlignment(JLabel.LEADING);
			addComponent(panel, nameLabel, 0, gridy, 1, 1, stockInsets,
					GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);
			
			List<Integer> rolls = occupation.getRolls();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < rolls.size(); i++) {
				if (i > 0) sb.append(" or ");
				sb.append(rolls.get(i));
			}

			JLabel rollsLabel = new JLabel(sb.toString());
			rollsLabel.setFont(smallFont);
			rollsLabel.setHorizontalAlignment(JLabel.LEADING);
			addComponent(panel, rollsLabel, 1, gridy, 1, 1, priceInsets,
					GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);
			
			JLabel payAmountLabel = new JLabel(occupation.getFormattedAmount());
			payAmountLabel.setFont(smallFont);
			payAmountLabel.setHorizontalAlignment(JLabel.TRAILING);
			addComponent(panel, payAmountLabel, 2, gridy++, 1, 1, priceInsets,
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
	
	public class RadioButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
			String occupationName = abstractButton.getText();
			playerOccupation = gameStatus.getOccupation(occupationName);
		}
		
	}

}
