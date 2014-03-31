package com.ggl.stockmarket.game.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.ggl.stockmarket.game.model.GameStatus;

public class StockMarketFrame extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	
	protected static final boolean DEBUG = false;
	
	protected static int FRAME_WIDTH = 1000;
	protected static int FRAME_HEIGHT = 750;
	
	protected ControlPanel controlPanel;
	
	protected GameStatus gameStatus;
	
	protected ImagePanel imagePanel;
	
	protected JPanel framePanel;
	
	public StockMarketFrame(GameStatus gameStatus) {
		super();
		this.gameStatus = gameStatus;
		createGUIControl();
	}
	
	@Override
	public void run() {
		//TODO Write player dialogs
		framePanel = new JPanel();
		framePanel.setLayout(new FlowLayout());
		
		imagePanel = new ImagePanel(gameStatus);
		framePanel.add(imagePanel);
		
		controlPanel = new ControlPanel(gameStatus);
		framePanel.add(controlPanel);
		
		imagePanel.setControlPanel(controlPanel);
		
		add(framePanel);
		addComponentListener(new FrameListener());
		validate();
		
		resizeImagePanel(framePanel, controlPanel);
		
		if (DEBUG) {
			printSizes();
		}
				
	}

	protected void printSizes() {
		System.out.println("Frame:         " + getPreferredSize() + "; "
				+ getSize());
		System.out.println("Frame panel:   " + framePanel.getPreferredSize()
				+ "; " + framePanel.getSize());
		System.out.println("Image panel:   " + imagePanel.getPreferredSize()
				+ "; " + imagePanel.getSize());
		System.out.println("Control panel: " + controlPanel.getPreferredSize()
				+ "; " + controlPanel.getSize());
		System.out.println("Stocks panel:  "
				+ controlPanel.getStockPanelPreferredSize());
		System.out.println("Players panel: "
				+ controlPanel.getPlayerSummaryPanelPreferredSize());
	}

	protected void resizeImagePanel(JPanel framePanel, JPanel controlPanel) {
		Dimension d = new Dimension();
		d.width = framePanel.getWidth() - controlPanel.getWidth() - 15;
		d.height = framePanel.getHeight() - 8;
		imagePanel.panelResize(d);
		validate();
	}

//	protected void createStartPositionDialog() {
//		String text = "Left click on one of the START squares ";
//		text += "to continue the game.";
//		JOptionPane optionPane = new JOptionPane(text,
//				JOptionPane.INFORMATION_MESSAGE, JOptionPane.OK_OPTION);
//		JDialog dialog = optionPane.createDialog("Select START Square");
//		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
////		dialog.setModalityType(ModalityType.MODELESS);
//		dialog.setVisible(true);
//	}
	
	protected void createGUIControl() {
		setBounds(calculateBounds());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Stock Market Game");
		
		setVisible(true);

	}
	
	protected Rectangle calculateBounds() {
		Rectangle r = new Rectangle();
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		
		r.x = (d.width - FRAME_WIDTH) / 2;
		r.y = (d.height - FRAME_HEIGHT) / 2;
		r.width = FRAME_WIDTH;
		r.height = FRAME_HEIGHT;
		
		return r;
	}

	public ImagePanel getImagePanel() {
		return imagePanel;
	}
	
	public class FrameListener extends ComponentAdapter {
		
		@Override
		public void componentResized(ComponentEvent event) {
			resizeImagePanel(framePanel, controlPanel);
		}
		
	}

}
