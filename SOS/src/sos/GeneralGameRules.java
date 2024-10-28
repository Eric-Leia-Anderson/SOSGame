package sos;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import sos.SOSGame.Cell;
import sos.SOSGame.Colors;
import sos.SOSGame.GameRules;
import sos.SOSGame.GameState;

public class GeneralGameRules extends SOSGame{
	
	/**
	 * Calls SOSGame constructor and sets rules to general.
	 * */
	public GeneralGameRules() {
		super();
		this.currentGameRules = GameRules.GENERAL;
	}
	
	/**
	 * Makes a move on board. If SOS's are found, then the current player continues in turns.
	 * If there are no SOS's and the board is full, then it is a draw.
	 * If there are no SOS's and the board is not full, it becomes the next player's turn.
	 * If there are SOS's and the board is full, the winning player if shown.
	 * */
	@Override
	public void makeMove(int row, int column) {
		if (row >= 0 && row < TOTALROWS && column >= 0 && column < TOTALCOLUMNS && grid[row][column] == Cell.EMPTY) {
			Cell token;
			Color color;
			if (turn == '1') {
				token = playerOne.getPlayerLetter().equals(Cell.S.name()) ? Cell.S : Cell.O;
				color = playerOne.getPlayerColor();
			}
			else {
				token = playerTwo.getPlayerLetter().equals(Cell.S.name()) ? Cell.S : Cell.O;
				color = playerTwo.getPlayerColor();
			}
			grid[row][column] = token;
			boolean checked = checkSOS(color, token);
			updatePoints();
			if(!checked) {
				turn = (turn == '1') ? '2' : '1';
			}
			if(isBoardFull()) {
				if(playerOne.getPlayerPoints() > playerTwo.getPlayerPoints()) {
					updateGameState(GameState.PLAYERONE_WON);
				}
				else if(playerTwo.getPlayerPoints() > playerOne.getPlayerPoints()) {
					updateGameState(GameState.PLAYERTWO_WON);
				}
				else {
					updateGameState(GameState.DRAW);
				}
			}
		} else if(grid[row][column] == Cell.O || grid[row][column] == Cell.S) {
			String message = "Someone already made a move here, try again. ";
			JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
			        JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Checks for all SOS's.
	 * */
	public boolean checkSOS(Color color, Cell token) {
		Cell[] tokens = new Cell[] {Cell.S, Cell.O, Cell.S};
        List<Point> points = new ArrayList<Point>();
        List<FoundSOS> found = new ArrayList<FoundSOS>();
        FoundSOS sosFound;
        int m = grid.length;
        int n = grid[0].length;
        boolean changeTurn = false;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
            	found = findSOS(grid, i, j, tokens);
            	if(found.size() > 0) {
            		for(FoundSOS fs : found) {
            			points = fs.getCoordinates();
            			if(points.size() == 3) {
                    		changeTurn = true;
            				fs.setLineColor(color);
            				foundSOSs.add(fs);
            			}
            		}
            	}
            }
        }
        return changeTurn;
	}
	
	/**
	 * Count and update player points.
	 * */
	public void updatePoints() {
		int countOne = 0;
		int countTwo = 0;
		Color playerOneColor = playerOne.getPlayerColor();
		Color playerTwoColor = playerTwo.getPlayerColor();
		for(FoundSOS fs : foundSOSs) {
			if(fs.lineColor == playerOneColor) {
				countOne++;
			}
			else {
				countTwo++;
			}
		}
		playerOne.setPlayerPoints(countOne);
		playerTwo.setPlayerPoints(countTwo);
	}
}
