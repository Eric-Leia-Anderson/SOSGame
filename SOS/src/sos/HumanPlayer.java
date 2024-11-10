package sos;

import java.awt.Color;

import sos.SOSGame.Cell;
import sos.SOSGame.GameState;

public class HumanPlayer extends Player {

	public HumanPlayer(Color color, String letter, char turn, int points, SOSGame game) {
		super(color, letter, turn, points, game);
	}
	
	@Override
	public void start() {
		//do nothing
	}

	@Override
	protected void stop() {
		// do nothing
		
	}


}
