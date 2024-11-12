package sos;

import java.awt.Color;

import sos.SOSGame.Cell;
import sos.SOSGame.GameState;

public class HumanPlayer extends Player {

	/**
     * Calls Player constructor.
     */
	public HumanPlayer(Color color, String letter, char turn, int points, SOSGame game) {
		super(color, letter, turn, points, game);
	}
	
	/**
	 * Human player doesn't need this. 
	 */
	@Override
	public void start() {
		//do nothing
	}

	/**
	 * Human player doesn't need to do anything. 
	 */
	@Override
	protected void stop() {
		// do nothing
		
	}
}
