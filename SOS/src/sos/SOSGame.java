package sos;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import sos.SOSGame.Cell;
import sos.SOSGame.GameState;

public class SOSGame {
	protected static int TOTALROWS = 4;
	protected static int TOTALCOLUMNS = 4;
	
	protected SOSGUI sosGui;
	
	public static boolean doneHumanMove = false;
	
	public enum Cell {
		EMPTY, S, O
	}
	public enum Colors {
		RED, BLUE, NONE
	}
	protected Cell[][] grid;
	protected char turn;
	
	protected Player playerOne;
	protected Player playerTwo;
	
	protected List<FoundSOS> foundSOSs;
	
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

	
	public void setSOSGUI(SOSGUI sosGui) {
		this.sosGui = sosGui;
	}
	/**
	 * Initializes cell array, sets default game state to playing,
	 * creates 2 players with default colors, letter, turns, and points.
	 * */
	private void initGame() {
		for (int row = 0; row < TOTALROWS; ++row) {
			for (int col = 0; col < TOTALCOLUMNS; ++col) {
				grid[row][col] = Cell.EMPTY;
			}
		}
		doneHumanMove = false;
		currentGameState = GameState.PLAYING;
		foundSOSs = new ArrayList<FoundSOS>();
		playerOne = new HumanPlayer(Color.RED, "S", '1', 0, this);
		playerOne.setType('H');
		playerTwo = new HumanPlayer(Color.BLUE, "S", '2', 0, this);
		playerTwo.setType('H');
		playerOne.setPlayerLetter("S");
		playerTwo.setPlayerLetter("S");
		turn = '1';
		startGame();
	}
	
	public void initPartialGame() {
		for (int row = 0; row < TOTALROWS; ++row) {
			for (int col = 0; col < TOTALCOLUMNS; ++col) {
				grid[row][col] = Cell.EMPTY;
			}
		}
		doneHumanMove = false;
		currentGameState = GameState.PLAYING;
		foundSOSs = new ArrayList<FoundSOS>();
		turn = '1';
		startGame();
	}
	
	public Cell[][] getGrid() {
		return grid;
	}
	
	public void startGame() {
		playerOne.start();
		playerTwo.start();
	}
	
	public void setDoneHumanMove(boolean done) {
		doneHumanMove = done;
	}
	
	public boolean getDoneHumanMove() {
		return doneHumanMove;
	}
	
	/**
	 * Resets game to defaults.
	 * */
	public void resetGame() {
		initGame();
		
	}
	
	/**
	 * Returns the list of found SOS's.
	 * */
	public List<FoundSOS> getFoundSOS() {
		return foundSOSs;
	}
	
	/**
	 * Returns size of found SOS list.
	 * */
	public int getFoundSOSSize() {
		return foundSOSs.size();
	}
	
	/**
	 * Checks if the the 3 points are already in the found SOS list.
	 * */
	public boolean checkSOSList(Point a, Point b, Point c) {
		if(foundSOSs.size() == 0) {
			return false;
		}
		else {
			for(FoundSOS fs : foundSOSs) {
				List<Point> points = fs.getCoordinates();
				if(points.contains(a) && points.contains(b) && points.contains(c)) {
					return true;
				}
				
			}
			return false;
		}
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
	
	public Player getCurrentPlayer() {
		return turn == '1' ? playerOne : playerTwo;
	}
	
	public Player getOtherPlayer() {
		return turn == '1' ? playerTwo : playerOne;
	}
	
	public boolean isPlayerComputer() {
		boolean comp = false;
		if (turn == '1') {
			comp = playerOne.getType() == 'C' ? true : false;
			
		}
		else {
			comp = playerTwo.getType() == 'C' ? true : false;
		}
		return comp;
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
		//empty
	}
	
	/**
	 * Updates game state. 
	 * */
	protected void updateGameState(GameState state) {
		currentGameState = state;
	}
	
	/**
	 * Checks if any cells are empty. 
	 * */
	protected boolean isBoardFull() {
		for (int row = 0; row < TOTALROWS; ++row) {
			for (int col = 0; col < TOTALCOLUMNS; ++col) {
				if(grid[row][col] == Cell.EMPTY) {
					return false;
				}
			}
		}
		return true;
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
	
	/**
	 * Finds any SOS's on board, checks if it was already found, and returns the list. 
	 * */
	public List<FoundSOS> findSOS(Cell[][] grid, int row, int col, Cell[] tokens) {
		int m = grid.length;
	    int n = grid[0].length;
	    List<Point> points = new ArrayList<Point>();
	    List<FoundSOS> stuff = new ArrayList<FoundSOS>();
	    FoundSOS found;
	    Point p;
	    if (grid[row][col] != tokens[0]) {
	    	return stuff;
	    }
	    else {
	    	p = new Point(row,col);
	    	points.add(p);
	    }
    	int len = tokens.length;
        int[] x = { -1, -1, -1, 0, 0, 1, 1, 1 };
        int[] y = { -1, 0, 1, -1, 1, -1, 0, 1 };

        for (int dir = 0; dir < 8; dir++) {
            int k;
            int currX = row + x[dir];
            int currY = col + y[dir];

            for (k = 1; k < len; k++) {
                if (currX >= m || currX < 0 || currY >= n || currY < 0) {
                    break;
                }
                if (grid[currX][currY] != tokens[k]) {
                    break;
                }
                p = new Point(currX,currY);
            	points.add(p);
                currX += x[dir];
                currY += y[dir];
            }
            if (k == len) {
            	boolean check = checkSOSList(points.get(0), points.get(1), points.get(2));
            	if(!check) {
            		found = new FoundSOS();
                	for (Point pt : points) {
                		found.addCoordinate(pt);
                	}
                	stuff.add(found);
            	}
            	points.remove(2);
            	points.remove(1);
            }
            currX -= x[dir];
            currY -= y[dir];
        }
        return stuff;
	}
}
