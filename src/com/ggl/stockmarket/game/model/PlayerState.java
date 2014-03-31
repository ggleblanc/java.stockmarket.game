package com.ggl.stockmarket.game.model;

public class PlayerState {
	
	protected static final int SELECT_OCCUPATION = 100;
	protected static final int ROLL_DICE = 200;
	protected static final int SELL_STOCK = 300;
	protected static final int MOVE_PIECE = 400;
	protected static final int PERFORM_SQUARE_ACTION = 500;
	protected static final int DICE_ROLLED = 600;
	
	protected volatile int playerState;
	
	public PlayerState() {
		this.playerState = SELECT_OCCUPATION;
	}
	
	public boolean isSelectOccupation() {
		return playerState == SELECT_OCCUPATION;
	}
	
	public boolean isRollDice(boolean playerEmployed) {
		if (playerEmployed) {
			if (playerState == ROLL_DICE) {
				return true;
			}
		} else {
			if (playerState == ROLL_DICE) {
				return true;
			} else if (playerState == SELL_STOCK) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isSellStock() {
		return playerState == SELL_STOCK;
	}
	
	public boolean isMovePiece() {
		return playerState == MOVE_PIECE;
	}
	
	public boolean isPerformSquareAction() {
		return playerState == PERFORM_SQUARE_ACTION;
	}
	
	public void setSelectOccupation() {
		this.playerState = SELECT_OCCUPATION;
	}

	public void setRollDice() {
		this.playerState = ROLL_DICE;
	}
	
	public void setSellStock() {
		this.playerState = SELL_STOCK;
	}
	
	public void setMovePiece() {
		this.playerState = MOVE_PIECE;
	}
	
	public void setPerformSquareAction() {
		this.playerState = PERFORM_SQUARE_ACTION;
	}
	
	protected void setPlayerState(int playerState) {
		this.playerState = playerState;
	}
	
	public synchronized void setDiceRolled() {
		final int playerState = this.playerState;
		this.playerState = DICE_ROLLED;
		
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					sleep(200L);
				} catch (InterruptedException e) {
				} finally {
					setPlayerState(playerState);
				}
			}
		};
		thread.start();
	}
}
