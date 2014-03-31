package com.ggl.stockmarket.game.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.ggl.stockmarket.game.model.GameStatus;
import com.ggl.stockmarket.game.model.Occupation;
import com.ggl.stockmarket.game.model.Player;
import com.ggl.stockmarket.game.model.board.AbstractSquare;
import com.ggl.stockmarket.game.model.board.BrokerFeeSquare;
import com.ggl.stockmarket.game.model.board.BuyLimitSquare;
import com.ggl.stockmarket.game.model.board.CenterSquare;
import com.ggl.stockmarket.game.model.board.StockholdersMeetingSquare;

public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	protected static final int IMAGE_WIDTH = AbstractSquare.IMAGE_WIDTH * 11
			+ BrokerFeeSquare.IMAGE_WIDTH * 2;
	protected static final int IMAGE_HEIGHT = AbstractSquare.IMAGE_WIDTH * 11
			+ BrokerFeeSquare.IMAGE_WIDTH * 2;

	protected static int PANE_WIDTH = 680;
	protected static int PANE_HEIGHT = 660;
	
	protected boolean displayFullImage;
	
	protected BufferedImage fullImage;
	protected BufferedImage reducedImage;
	
	protected ControlPanel controlPanel;
	
	protected GameStatus gameStatus;
	
	protected JLabel imageLabel;
	
	protected JScrollPane jScrollPane;
	
	public ImagePanel(GameStatus gameStatus) {
		super();
		this.gameStatus = gameStatus;
		createGUIControl(gameStatus);
	}
	
	public void setControlPanel(ControlPanel controlPanel) {
		this.controlPanel = controlPanel;
	}
	
	protected void createGUIControl(GameStatus gameStatus) {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(PANE_WIDTH, PANE_HEIGHT));
		
		fullImage = createImage(gameStatus);
		reducedImage = reduceImage(fullImage);
		displayFullImage = false;
		imageLabel = new JLabel(new ImageIcon(reducedImage));
		imageLabel.addMouseListener(new BoardMouseListener());
		
		jScrollPane = new JScrollPane();
		jScrollPane.setViewportView(imageLabel);
		add(jScrollPane, BorderLayout.CENTER);
	}
	
	public void panelResize(Dimension dimension) {
		PANE_WIDTH  = dimension.width;
		PANE_HEIGHT = dimension.height;
		reducedImage = reduceImage(fullImage);
		imageLabel.setIcon(new ImageIcon(reducedImage));
		setPreferredSize(new Dimension(PANE_WIDTH, PANE_HEIGHT));
		setSize(new Dimension(PANE_WIDTH, PANE_HEIGHT));
//		validate();
	}
	
	protected BufferedImage reduceImage(BufferedImage bufferedImage) {
		int size = Math.min(PANE_HEIGHT, PANE_WIDTH) - 5;
		BufferedImage reducedImage = new BufferedImage(size, size,
				BufferedImage.TYPE_INT_RGB);
		
		Graphics2D g = (Graphics2D) reducedImage.getGraphics();
		g.drawImage(bufferedImage, 0, 0, size, size, 0, 0,
				bufferedImage.getWidth(), bufferedImage.getHeight(), null);
		g.dispose();
		
		return reducedImage;
	}
	
	protected BufferedImage createImage(GameStatus gameStatus) {
		BufferedImage bufferedImage = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
		List<AbstractSquare> board = gameStatus.getBoard();
		List<Occupation> occupations = gameStatus.getOccupations();
		
		drawBoardTop(g, board, occupations.get(1));
		drawBoardLeft(g, board, occupations.get(2));
		drawBoardBottom(g, board, occupations.get(3));
		drawBoardRight(g, board, occupations.get(0));
		
		drawBoardCenter(g, gameStatus);
		
		drawLevel1StockholdersMeeting(g, gameStatus);
		drawLevel2StockholdersMeeting(g, gameStatus);
		drawLevel3StockholdersMeeting(g, gameStatus);
		drawLevel4StockholdersMeeting(g, gameStatus);
		
		g.dispose();
		
		return bufferedImage;
	}

	protected void drawBoardTop(Graphics g, List<AbstractSquare> board,
			Occupation occupation) {
		int x = 0;
		int y = 0;
		for (int i = 18; i < 31; i++) {
			AbstractSquare abstractSquare = board.get(i);
			BufferedImage image = abstractSquare.drawImage();

			if (i == 18) {
				image = createResizedCopy(image, image.getWidth(),
						image.getHeight(), 90, 0, 0);
			} else if (i == 19) {
				BufferedImage occupationImage = drawOccupation(g, occupation,
						x, y + image.getHeight(), 180);
				occupation.setOccupationLocation(new Rectangle(x, y
						+ image.getHeight(), occupationImage.getWidth(),
						occupationImage.getHeight()));
				image = createResizedCopy(image, image.getWidth(),
						image.getHeight(), 180, 0, 0);
			} else {
				image = createResizedCopy(image, image.getWidth(),
						image.getHeight(), 180, 0, 0);
			}
			g.drawImage(image, x, y, null);
			abstractSquare.setBoardLocation(new Rectangle(x, y, image
					.getWidth(), image.getHeight()));
			x += image.getWidth();
		}
	}
	
	protected void drawBoardLeft(Graphics g, List<AbstractSquare> board,
			Occupation occupation) {
		int x = 0;
		int y = BrokerFeeSquare.IMAGE_HEIGHT;
		for (int i = 17; i > 5; i--) {
			AbstractSquare abstractSquare = board.get(i);
			BufferedImage image = abstractSquare.drawImage();

			if (i == 8) {
				BufferedImage occupationImage = drawOccupation(g, occupation,
						x + image.getHeight(), y, 90);
				occupation.setOccupationLocation(new Rectangle( 
						x + image.getHeight(), y, occupationImage.getWidth(),
						occupationImage.getHeight()));
				image = createResizedCopy(image, image.getWidth(),
						image.getHeight(), 90, 0, 0);
			} else if (i != 6) {
				image = createResizedCopy(image, image.getWidth(),
						image.getHeight(), 90, 0, 0);
			}
			g.drawImage(image, x, y, null);
			abstractSquare.setBoardLocation(new Rectangle(x, y, image
					.getWidth(), image.getHeight()));
			y += image.getHeight();
		}
	}
	
	protected void drawBoardBottom(Graphics g, List<AbstractSquare> board,
			Occupation occupation) {
		int x = BrokerFeeSquare.IMAGE_WIDTH;
		int y = IMAGE_HEIGHT - BrokerFeeSquare.IMAGE_HEIGHT;
		for (int i = 5; i >= 0; i--) {
			AbstractSquare abstractSquare = board.get(i);
			y = getYCoordinatePosition(abstractSquare);
			BufferedImage image = abstractSquare.drawImage();
			g.drawImage(image, x, y, null);
			abstractSquare.setBoardLocation(new Rectangle(x, y, image
					.getWidth(), image.getHeight()));
			x += image.getWidth();
		}
		for (int i = 47; i >= 43; i--) {
			AbstractSquare abstractSquare = board.get(i);
			y = getYCoordinatePosition(abstractSquare);
			BufferedImage image = abstractSquare.drawImage();
			
			if (i == 44) {
				BufferedImage occupationImage = drawOccupation(g, occupation,
						x, y - image.getWidth() * 2, 0);
				occupation.setOccupationLocation(new Rectangle( 
						x, y - image.getWidth() * 2, occupationImage.getWidth(),
						occupationImage.getHeight()));
			}
			
			g.drawImage(image, x, y, null);
			abstractSquare.setBoardLocation(new Rectangle(x, y, image
					.getWidth(), image.getHeight()));
			x += image.getWidth();
		}
	}
	
	protected void drawBoardRight(Graphics g, List<AbstractSquare> board, 
			Occupation occupation) {
		int x = IMAGE_WIDTH - BrokerFeeSquare.IMAGE_HEIGHT;
		int y = BrokerFeeSquare.IMAGE_WIDTH;
		for (int i = 31; i < 43; i++) {
			AbstractSquare abstractSquare = board.get(i);
			x = getXCoordinatePosition(abstractSquare);
			BufferedImage image = abstractSquare.drawImage();
			
			if (i == 31) {
				BufferedImage occupationImage = drawOccupation(g, occupation,
						x - image.getWidth() * 2, y, 270);
				occupation.setOccupationLocation(new Rectangle( 
						x - image.getWidth() * 2, y, occupationImage.getWidth(),
						occupationImage.getHeight()));
			}
			
			image = createResizedCopy(image, image.getWidth(),
						image.getHeight(), 270, 0, 0);
			g.drawImage(image, x, y, null);
			abstractSquare.setBoardLocation(new Rectangle(x, y, image
					.getWidth(), image.getHeight()));
			y += image.getHeight();
		}
	}

	protected void drawBoardCenter(Graphics g, GameStatus gameStatus) {
		CenterSquare centerSquare = gameStatus.getCenterSquare();
		BufferedImage image = centerSquare.drawImage();
		int x = (IMAGE_WIDTH - image.getWidth()) / 2;
		int y = (IMAGE_HEIGHT - image.getHeight()) / 2;
		g.drawImage(image, x, y, null);
		centerSquare.setSquareLocation(new Rectangle(x, y, image.getWidth(),
				image.getHeight()));
	}
	
	protected int getXCoordinatePosition(AbstractSquare abstractSquare) {
		int x;
		if (abstractSquare instanceof BuyLimitSquare) {
			x = IMAGE_WIDTH - BuyLimitSquare.IMAGE_HEIGHT;
		} else {
			x = IMAGE_WIDTH - AbstractSquare.IMAGE_HEIGHT;
		}
		return x;
	}
	
	protected int getYCoordinatePosition(AbstractSquare abstractSquare) {
		int y;
		if (abstractSquare instanceof BuyLimitSquare) {
			y = IMAGE_HEIGHT - BuyLimitSquare.IMAGE_HEIGHT;
		} else {
			y = IMAGE_HEIGHT - AbstractSquare.IMAGE_HEIGHT;
		}
		return y;
	}
	
	protected BufferedImage drawOccupation(Graphics g, Occupation occupation,
			int x1, int y1, int angle) {
		BufferedImage occupationImage = occupation.drawImage();
		occupationImage = createResizedCopy(occupationImage,
				occupationImage.getWidth(), occupationImage.getHeight(), 
				angle, 0, 0);
		g.drawImage(occupationImage, x1, y1, null);
		return occupationImage;
	}
	
	protected void drawLevel1StockholdersMeeting(Graphics g,
			GameStatus gameStatus) {
		List<AbstractSquare> board = gameStatus.getBoard();
		BuyLimitSquare buyLimitSquare = (BuyLimitSquare) board.get(15);
		StockholdersMeetingSquare[][] stockholdersMeetingSquare = gameStatus
				.getStockholdersMeeting();
		StockholdersMeetingSquare[] stockholdersMeetingRow = stockholdersMeetingSquare[buyLimitSquare
				.getStockholdersMeetingLevel()];
		BufferedImage image = stockholdersMeetingRow[0].drawImage(new Insets(2,
				4, 2, 4));
		Rectangle r = buyLimitSquare.getBoardLocation();
		image = createResizedCopy(image, image.getWidth(), image.getHeight(),
				90, 0, 0);
		int x = r.x + BuyLimitSquare.IMAGE_HEIGHT;
		int y = r.y;
		g.drawImage(image, x, y, null);
		stockholdersMeetingRow[0].setBoardLocation(new Rectangle(x, y, image
				.getWidth(), image.getHeight()));

		image = stockholdersMeetingRow[1].drawImage(new Insets(4, 4, 2, 2));
		image = createResizedCopy(image, image.getWidth(), image.getHeight(),
				90, 0, 0);
		x += image.getWidth();
		g.drawImage(image, x, y, null);
		stockholdersMeetingRow[1].setBoardLocation(new Rectangle(x, y, image
				.getWidth(), image.getHeight()));

		for (int i = 2; i < 7; i++) {
			image = stockholdersMeetingRow[i].drawImage(new Insets(4, 2, 4, 2));
			image = createResizedCopy(image, image.getWidth(),
					image.getHeight(), 90, 0, 0);
			y += image.getHeight();
			g.drawImage(image, x, y, null);
			stockholdersMeetingRow[i].setBoardLocation(new Rectangle(x, y,
					image.getWidth(), image.getHeight()));
		}

		image = stockholdersMeetingRow[7].drawImage(new Insets(4, 2, 2, 4));
		image = createResizedCopy(image, image.getWidth(), image.getHeight(),
				90, 0, 0);
		y += image.getHeight();
		g.drawImage(image, x, y, null);
		stockholdersMeetingRow[7].setBoardLocation(new Rectangle(x, y, image
				.getWidth(), image.getHeight()));

		image = stockholdersMeetingRow[8].drawImage(new Insets(2, 4, 2, 4));
		image = createResizedCopy(image, image.getWidth(), image.getHeight(),
				90, 0, 0);
		x -= image.getWidth();
		g.drawImage(image, x, y, null);
		stockholdersMeetingRow[8].setBoardLocation(new Rectangle(x, y, image
				.getWidth(), image.getHeight()));
	}
	
	protected void drawLevel2StockholdersMeeting(Graphics g,
			GameStatus gameStatus) {
		List<AbstractSquare> board = gameStatus.getBoard();
		BuyLimitSquare buyLimitSquare = (BuyLimitSquare) board.get(39);
		StockholdersMeetingSquare[][] stockholdersMeetingSquare = gameStatus
				.getStockholdersMeeting();
		StockholdersMeetingSquare[] stockholdersMeetingRow = stockholdersMeetingSquare[buyLimitSquare
				.getStockholdersMeetingLevel()];
		BufferedImage image = stockholdersMeetingRow[0].drawImage(new Insets(2,
				4, 2, 4));
		Rectangle r = buyLimitSquare.getBoardLocation();
		image = createResizedCopy(image, image.getWidth(), image.getHeight(),
				270, 0, 0);
		int x = r.x - image.getWidth();
		int y = r.y;
		g.drawImage(image, x, y, null);
		stockholdersMeetingRow[0].setBoardLocation(new Rectangle(x, y, image
				.getWidth(), image.getHeight()));

		image = stockholdersMeetingRow[1].drawImage(new Insets(4, 4, 2, 2));
		image = createResizedCopy(image, image.getWidth(), image.getHeight(),
				270, 0, 0);
		x -= image.getWidth();
		g.drawImage(image, x, y, null);
		stockholdersMeetingRow[1].setBoardLocation(new Rectangle(x, y, image
				.getWidth(), image.getHeight()));

		for (int i = 2; i < 7; i++) {
			image = stockholdersMeetingRow[i].drawImage(new Insets(4, 2, 4, 2));
			image = createResizedCopy(image, image.getWidth(),
					image.getHeight(), 270, 0, 0);
			y -= image.getHeight();
			g.drawImage(image, x, y, null);
			stockholdersMeetingRow[i].setBoardLocation(new Rectangle(x, y,
					image.getWidth(), image.getHeight()));
		}

		image = stockholdersMeetingRow[7].drawImage(new Insets(4, 2, 2, 4));
		image = createResizedCopy(image, image.getWidth(), image.getHeight(),
				270, 0, 0);
		y -= image.getHeight();
		g.drawImage(image, x, y, null);
		stockholdersMeetingRow[7].setBoardLocation(new Rectangle(x, y, image
				.getWidth(), image.getHeight()));

		image = stockholdersMeetingRow[8].drawImage(new Insets(2, 4, 2, 4));
		image = createResizedCopy(image, image.getWidth(), image.getHeight(),
				270, 0, 0);
		x += image.getWidth();
		g.drawImage(image, x, y, null);
		stockholdersMeetingRow[8].setBoardLocation(new Rectangle(x, y, image
				.getWidth(), image.getHeight()));
	}

	protected void drawLevel3StockholdersMeeting(Graphics g,
			GameStatus gameStatus) {
		List<AbstractSquare> board = gameStatus.getBoard();
		BuyLimitSquare buyLimitSquare = (BuyLimitSquare) board.get(27);
		StockholdersMeetingSquare[][] stockholdersMeetingSquare = gameStatus
				.getStockholdersMeeting();
		StockholdersMeetingSquare[] stockholdersMeetingRow = stockholdersMeetingSquare[buyLimitSquare
				.getStockholdersMeetingLevel()];
		BufferedImage image = stockholdersMeetingRow[0].drawImage(new Insets(2,
				4, 2, 4));
		Rectangle r = buyLimitSquare.getBoardLocation();
		int x = r.x;
		int y = r.y + BuyLimitSquare.IMAGE_HEIGHT;
		image = createResizedCopy(image, image.getWidth(), image.getHeight(),
				180, 0, 0);
		g.drawImage(image, x, y, null);
		stockholdersMeetingRow[0].setBoardLocation(new Rectangle(x, y, image
				.getWidth(), image.getHeight()));

		image = stockholdersMeetingRow[1].drawImage(new Insets(4, 4, 2, 2));
		y += image.getHeight();
		image = createResizedCopy(image, image.getWidth(), image.getHeight(),
				180, 0, 0);
		g.drawImage(image, x, y, null);
		stockholdersMeetingRow[1].setBoardLocation(new Rectangle(x, y, image
				.getWidth(), image.getHeight()));

		for (int i = 2; i < 7; i++) {
			image = stockholdersMeetingRow[i].drawImage(new Insets(4, 2, 4, 2));
			x -= image.getWidth();
			image = createResizedCopy(image, image.getWidth(),
					image.getHeight(), 180, 0, 0);
			g.drawImage(image, x, y, null);
			stockholdersMeetingRow[i].setBoardLocation(new Rectangle(x, y,
					image.getWidth(), image.getHeight()));
		}

		image = stockholdersMeetingRow[7].drawImage(new Insets(4, 2, 2, 4));
		x -= image.getWidth();
		image = createResizedCopy(image, image.getWidth(), image.getHeight(),
				180, 0, 0);
		g.drawImage(image, x, y, null);
		stockholdersMeetingRow[7].setBoardLocation(new Rectangle(x, y, image
				.getWidth(), image.getHeight()));

		image = stockholdersMeetingRow[8].drawImage(new Insets(2, 4, 2, 4));
		y -= image.getHeight();
		image = createResizedCopy(image, image.getWidth(), image.getHeight(),
				180, 0, 0);
		g.drawImage(image, x, y, null);
		stockholdersMeetingRow[8].setBoardLocation(new Rectangle(x, y, image
				.getWidth(), image.getHeight()));
	}

	protected void drawLevel4StockholdersMeeting(Graphics g,
			GameStatus gameStatus) {
		List<AbstractSquare> board = gameStatus.getBoard();
		BuyLimitSquare buyLimitSquare = (BuyLimitSquare) board.get(3);
		StockholdersMeetingSquare[][] stockholdersMeetingSquare = gameStatus
				.getStockholdersMeeting();
		StockholdersMeetingSquare[] stockholdersMeetingRow = stockholdersMeetingSquare[buyLimitSquare
				.getStockholdersMeetingLevel()];
		BufferedImage image = stockholdersMeetingRow[0].drawImage(new Insets(2,
				4, 2, 4));
		Rectangle r = buyLimitSquare.getBoardLocation();
		int x = r.x;
		int y = r.y - image.getHeight();
		g.drawImage(image, x, y, null);
		stockholdersMeetingRow[0].setBoardLocation(new Rectangle(x, y, image
				.getWidth(), image.getHeight()));

		image = stockholdersMeetingRow[1].drawImage(new Insets(4, 4, 2, 2));
		y -= image.getHeight();
		g.drawImage(image, x, y, null);
		stockholdersMeetingRow[1].setBoardLocation(new Rectangle(x, y, image
				.getWidth(), image.getHeight()));

		for (int i = 2; i < 7; i++) {
			image = stockholdersMeetingRow[i].drawImage(new Insets(4, 2, 4, 2));
			x += image.getWidth();
			g.drawImage(image, x, y, null);
			stockholdersMeetingRow[i].setBoardLocation(new Rectangle(x, y,
					image.getWidth(), image.getHeight()));
		}

		image = stockholdersMeetingRow[7].drawImage(new Insets(4, 2, 2, 4));
		x += image.getWidth();
		g.drawImage(image, x, y, null);
		stockholdersMeetingRow[7].setBoardLocation(new Rectangle(x, y, image
				.getWidth(), image.getHeight()));

		image = stockholdersMeetingRow[8].drawImage(new Insets(2, 4, 2, 4));
		y += image.getHeight();
		g.drawImage(image, x, y, null);
		stockholdersMeetingRow[8].setBoardLocation(new Rectangle(x, y, image
				.getWidth(), image.getHeight()));
	}
	
	protected BufferedImage createResizedCopy(BufferedImage image,
			int scaledWidth, int scaledHeight, int currentAngle, int x, int y) {
		BufferedImage scaledBI = null;

		// If we are going from vertical to horizontal...
		if (currentAngle == 90 || currentAngle == -270 || currentAngle == -90
				|| currentAngle == 270) {
			// First apply the scaling.
			scaledBI = new BufferedImage(scaledWidth, scaledHeight,
					BufferedImage.TYPE_INT_RGB); 
			Graphics2D g = scaledBI.createGraphics();
			g.drawImage(image, x, y, scaledWidth, scaledHeight, null);
			g.dispose();
			// Then rotate it
			scaledBI = changeOrientation(scaledBI, currentAngle); 
		} else {
			scaledBI = new BufferedImage(scaledWidth, scaledHeight,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g = scaledBI.createGraphics();
			g.rotate(Math.toRadians(currentAngle), scaledWidth / 2,
					scaledHeight / 2);
			g.drawImage(image, x, y, scaledWidth, scaledHeight, null);
			g.dispose();
		}

		return scaledBI;
	}
	 
	 
	protected BufferedImage changeOrientation(BufferedImage image,
			int currentAngle) {
		int j = image.getWidth();
		int i = image.getHeight();
		BufferedImage rotatedBI = new BufferedImage(i, j,
				BufferedImage.TYPE_INT_RGB);
		int p = 0;
		// If we are rotating right
		if (currentAngle == 90 || currentAngle == -270)
			for (int x1 = 0; x1 < j; x1++) {
				for (int y1 = 0; y1 < i; y1++) {
					p = image.getRGB(x1, y1);
					rotatedBI.setRGB(i - 1 - y1, x1, p);
				}
			}
		else
			// We are rotating left.
			for (int x1 = 0; x1 < j; x1++)
				for (int y1 = 0; y1 < i; y1++) {
					p = image.getRGB(x1, y1);
					rotatedBI.setRGB(y1, j - 1 - x1, p);
				}

		return rotatedBI;
	}

	
	public void positionDialog(JFrame frame, JDialog dialog) {
		Rectangle f = frame.getBounds();
		Rectangle p = this.getBounds();
		Rectangle d = dialog.getBounds();
		
		d.x = f.x + p.x + (p.width - d.width) / 2;
		d.y = f.y + p.y + (p.height - d.height) / 2;
		
		dialog.setBounds(d);
	}
	
	protected Point convertToFullImage(Point p) {
		double factor = (double) fullImage.getHeight()
				/ (double) reducedImage.getHeight();
		Point q = new Point();
		q.x = (int) (factor * p.x + 0.5D);
		q.y = (int) (factor * p.y + 0.5D);
		return q;
	}
	
	public class BoardMouseListener extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent mouseEvent) {
			Point point = mouseEvent.getPoint();
			if (mouseEvent.getButton () == MouseEvent.BUTTON1) {
				leftButtonPressed(mouseEvent, point);
			} else if (mouseEvent.getButton () == MouseEvent.BUTTON3) {
				rightButtonPressed(mouseEvent, point);
			}
		}
		
		protected void rightButtonPressed(MouseEvent mouseEvent, Point point) {
			if (displayFullImage) {
				imageLabel.setIcon(new ImageIcon(reduceImage(
						gameStatus.drawMarkers(fullImage))));
				displayFullImage = false;
			} else {
				imageLabel.setIcon(new ImageIcon(
						gameStatus.drawMarkers(fullImage)));
				imageLabel.scrollRectToVisible(
						getSquareLocation(convertToFullImage(point)));
				displayFullImage = true;
			}
		}
		
		protected Rectangle getSquareLocation(Point point) {
			List<AbstractSquare> board = gameStatus.getBoard();
			for (int i = 0; i < board.size(); i++) {
				Rectangle r = board.get(i).getBoardLocation();
				if (r.contains(point)) {
					return r;
				}
			}
			return new Rectangle(point.x, point.y, 300, 300);
		}
		
		protected void leftButtonPressed(MouseEvent mouseEvent, Point point) {
			if (displayFullImage) {
				actions(mouseEvent, point);
			} else {
				Point fullPoint = convertToFullImage(point);
				actions(mouseEvent, fullPoint);
			}
		}
		
		protected void actions(MouseEvent mouseEvent, Point point) {
			int playerTurn = gameStatus.getPlayerTurn();
			Player player = gameStatus.getPlayer(playerTurn);
			
			if (player.isEmployed()) {
				if (gameStatus.isOccupationOver(player.getCash())) {
					controlPanel.getDicePanel().setMessage(Color.blue,
							"Select a Start square");
					player.setEmployed(false);
				} else {
					if (checkOccupations(player, point)) {
						controlPanel.getDicePanel().setMessage(Color.blue, "");
						drawMarkers(point);
						return;
					}
					if (player.getOccupation() == null) {
						controlPanel.getDicePanel().setMessage(Color.blue,
								"Select an occupation");
					} else {
						if (rollDice(player, point)) {
							controlPanel.getDicePanel().setMessage(Color.blue, "");
							occupationDice(point);
							nextPlayerTurn(point);
						}
					}
					return;
				}
			}
			
			if (!player.isEmployed()) {
				if (gameStatus.isWinner(player.getNetWorth(gameStatus))) {
					// TODO Write winner dialog
					return;
				} else {
					if (!player.isValidBoardPosition()) {
						if (checkStartPositions(point)) {
							controlPanel.getDicePanel().setMessage(Color.blue, "");
							drawMarkers(point);
						}
					} else {
						if (player.countStocks() > 0) {
							// TODO Write sell stock dialog
						}
						if (rollDice(player, point)) {
							occupationDice(point);
							gameStatus.movePlayer(player);
							
							AbstractSquare abstractSquare = gameStatus.getBoard().get(
									player.getBoardPosition());
							// TODO Write square description dialog
							abstractSquare.execute(gameStatus);
							controlPanel.getStockPanel().updateGUIControl();
							
							nextPlayerTurn(point);
							drawMarkers(point);
						}
					}
				}
			}
		}

		protected void occupationDice(Point point) {
			gameStatus.checkOccupationPayAmount();
			controlPanel.getPlayerSummaryPanel().updateGUIControl();
		}

		protected void nextPlayerTurn(Point point) {
			gameStatus.nextPlayerTurn();
			controlPanel.getDicePanel().setTurnTitle(gameStatus);
			drawMarkers(point);
		}
		
		protected boolean checkOccupations(Player player, Point point) {
			List<Occupation> occupations = gameStatus.getOccupations();
			for (int i = 0; i < occupations.size(); i++) {
				Occupation occupation = occupations.get(i);
				Rectangle r = occupation.getOccupationLocation();
				if (r.contains(point)) {
					player.setOccupation(occupation);
					player.getPlayerState().setRollDice();
					return true;
				}
			}
			return false;
		}
		
		protected boolean rollDice(Player player, Point point) {
//			System.out.println("rollDice");
			Rectangle r = gameStatus.getCenterSquare().getRollDiceLocation();
			if (r.contains(point)) {
//				System.out.println("rollDice success");
//				gameStatus.setDie1(0);
//				gameStatus.setDie2(0);
//				controlPanel.getDicePanel().setDice(gameStatus);
//				controlPanel.getDicePanel().repaint();
//				try {
//					Thread.sleep(250L);
//				} catch (InterruptedException e) {
//				}
				gameStatus.rollDice();
				controlPanel.getDicePanel().setDice(gameStatus);
				player.getPlayerState().setDiceRolled();
				return true;
			}
			return false;
		}

		protected boolean checkStartPositions(Point point) {
			List<AbstractSquare> board = gameStatus.getBoard();
			for (int i = 0; i < board.size(); i += 12) {
				Rectangle r = board.get(i).getBoardLocation();
				if (r.contains(point)) {
					Player player = gameStatus.getPlayer(gameStatus.getPlayerTurn());
					player.setBoardPosition(i);
					return true;
				}
			}
			return false;
		}

		protected void drawMarkers(Point point) {
			BufferedImage image = gameStatus.drawMarkers(fullImage);
			if (displayFullImage) {
				imageLabel.setIcon(new ImageIcon(image));
				imageLabel.scrollRectToVisible(getSquareLocation
						(convertToFullImage(point)));
			} else {
				BufferedImage smallImage = reduceImage(image);
				imageLabel.setIcon(new ImageIcon(smallImage));
			}
		}
	}

}
