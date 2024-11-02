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

public class SimpleGameRules extends SOSGame{
	
	/**
	 * Calls SOSGame constructor and sets rules to simple.
	 * */
	public SimpleGameRules() {
		super();
		this.currentGameRules = GameRules.SIMPLE;
	}
	
	/**
	 * Makes a move on board, if SOS is found then the current player has won and game is over.
	 * If there is no SOS and the board is full, then it is a draw.
	 * If there is no SOS and the board is not full, it becomes the next player's turn.
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
			checkSOS(color, token);
			if (getFoundSOSSize() > 0)
			{
				updateGameState((turn == '1' ? GameState.PLAYERONE_WON : GameState.PLAYERTWO_WON));
			}
			else if(isBoardFull())
			{
				updateGameState(GameState.DRAW);
			}
			else {
				turn = (turn == '1') ? '2' : '1';
			}
		} else if(grid[row][column] == Cell.O || grid[row][column] == Cell.S) {
			String message = "Someone already made a move here, try again. ";
			JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
			        JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Checks for SOS. If found, it stops looking.
	 * */
	public void checkSOS(Color color, Cell token) {
		Cell[] tokens = new Cell[] {Cell.S, Cell.O, Cell.S};
        List<Point> points = new ArrayList<Point>();
        List<FoundSOS> found = new ArrayList<FoundSOS>();
        FoundSOS sosFound;
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
            	found = findSOS(grid, i, j, tokens);
            	if(found.size() > 0) {
            		for(FoundSOS fs : found) {
            			points = fs.getCoordinates();
            			if(points.size() == 3) {
            				fs.setLineColor(color);
            				foundSOSs.add(fs);
            				break;
            			}
            		}
            	}
            }
        }
	}
}
