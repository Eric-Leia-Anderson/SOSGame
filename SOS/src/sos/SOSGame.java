package sos;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SOSGame {
	protected static int TOTALROWS = 4;
	protected static int TOTALCOLUMNS = 4;
	
	public enum Cell {
		EMPTY, S, O
	}
	protected Cell[][] grid;
	protected char turn;
	
	protected Player playerOne;
	protected Player playerTwo;
	
	public enum GameRules {
		SIMPLE, GENERAL
	}
	
	public enum GameState {
		PLAYING, DRAW, PLAYERONE_WON, PLAYERTWO_WON
	}

	protected GameState currentGameState;
	
	protected GameRules currentGameRules;

	/**
	 * Initializes game and creates cell array.
	 * */
	public SOSGame() {
		grid = new Cell[TOTALROWS][TOTALCOLUMNS];
		initGame();
	}

	/**
	 * Initializes cell array, sets default game state to playing, sets default game rules to simple,
	 * creates 2 players with default colors, letter, and turns.
	 * */
	private void initGame() {
		for (int row = 0; row < TOTALROWS; ++row) {
			for (int col = 0; col < TOTALCOLUMNS; ++col) {
				grid[row][col] = Cell.EMPTY;
			}
		}
		currentGameState = GameState.PLAYING;
		currentGameRules = GameRules.SIMPLE;
		playerOne = new Player(Color.RED, "S", 1);
		playerTwo = new Player(Color.BLUE, "S", 2);
		playerOne.setPlayerLetter("S");
		playerTwo.setPlayerLetter("S");
		turn = '1';
	}
	
	/**
	 * Resets game to defaults.
	 * */
	public void resetGame() {
		initGame();
		
	}
	
	/**
	 * Resets the size of the cell array.
	 * */
	public void resetSize() {
		grid = new Cell[TOTALROWS][TOTALCOLUMNS];
	}

	/**
	 * Returns total number of rows.
	 * */
	public int getTotalRows() {
		return TOTALROWS;
	}

	/**
	 * Returns total number of columns.
	 * */
	public int getTotalColumns() {
		return TOTALCOLUMNS;
	}

	/**
	 * Sets total number of rows.
	 * */
	public void setTotalRows(int rows) {
		TOTALROWS = rows;
	}

	/**
	 * Sets total number of columns.
	 * */
	public void setTotalColumns(int columns) {
		TOTALCOLUMNS = columns;
	}
	
	/**
	 * Returns a cell.
	 * @precond: none
	 * @postcond: returns Cell.EMPTY, Cell.S, or Cell.O if row >= 0 && row
	 *            < TOTALROWS && column >= 0 && column < TOTALCOLUMN otherwise null
	 * 
	 */
	public Cell getCell(int row, int column) {
		if (row >= 0 && row < TOTALROWS && column >= 0 && column < TOTALCOLUMNS) {
			return grid[row][column];
		} else {
			return null;
		}
	}
	
	/**
	 * Returns turn.
	 * */
	public char getTurn() {
		return turn;
	}
	
	/**
	 * Returns player 1.
	 * */
	public Player getPlayerOne() {
		return playerOne;
	}

	/**
	 * Sets player 1.
	 * */
	public void setPlayerOne(Player playerOne) {
		this.playerOne = playerOne;
	}

	/**
	 * Returns player 2.
	 * */
	public Player getPlayerTwo() {
		return playerTwo;
	}

	/**
	 * Sets player 2.
	 * */
	public void setPlayerTwo(Player playerTwo) {
		this.playerTwo = playerTwo;
	}


	/**
	 * Makes a move on the board if possible.
	 * @precond: none
	 * @postcond: if (row, column) is a valid empty cell, then the player's token
	 *            has been placed in the cell and the turn has changed to the other
	 *            player. If cell is empty, then show error message and don't change turn.
	 * 
	 */
	public void makeMove(int row, int column) {
		if (row >= 0 && row < TOTALROWS && column >= 0 && column < TOTALCOLUMNS && grid[row][column] == Cell.EMPTY) {
			Cell token;
			if (turn == '1') {
				token = playerOne.getPlayerLetter().equals(Cell.S.name()) ? Cell.S : Cell.O;
			}
			else {
				token = playerTwo.getPlayerLetter().equals(Cell.S.name()) ? Cell.S : Cell.O;
			}
			grid[row][column] = token;
			updateGameState(turn, row, column);
			turn = (turn == '1') ? '2' : '1';
		} else if(grid[row][column] == Cell.O || grid[row][column] == Cell.S) {
			String message = "Someone already made a move here, try again. ";
			JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
			        JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Updates game state. NOT IMPLEMENTED.
	 * */
	private void updateGameState(char turn, int row, int column) {
		//need to implement
	}

	/**
	 * Checks if draw. NOT IMPLEMENTED.
	 * */
	private boolean isDraw() {
		//need to implement
		return true;
	}

	/**
	 * Checks if someone won. NOT IMPLEMENTED.
	 * */
	private boolean hasWon(char turn, int row, int column) {
		//need to implement
		return false;
	}

	/**
	 * Returns game state.
	 * */
	public GameState getGameState() {
		return currentGameState;
	}
	
	/**
	 * Updates game rules.
	 * */
	public void updateGameRules(GameRules rules) {
		currentGameRules = rules;
	}
	
	/**
	 * Sets game rules. 
	 * */
	public GameRules getGameRules() {
		return currentGameRules;
	}
}
