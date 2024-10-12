package sos;

import java.awt.Color;

public class Player {
	
	private int playerTurn;
	private Color playerColor;
	private String playerLetter;

	/**
	 * Sets up Player with color, letter, and turn.
	 * */
	public Player(Color color, String letter, int turn) {
		playerColor = color;
		playerLetter = letter;
		playerTurn = turn;
	}
	
	/**
	 * Returns player turn.
	 * */
	public int getTurn() {
		return playerTurn;
	}
	
	/**
	 * Sets player turn.
	 * */
	public void setTurn(int turn) {
		playerTurn = turn;
	}
	
	/**
	 * Returns player color.
	 * */
	public Color getPlayerColor() {
		return playerColor;
	}
	
	/**
	 * Returns player letter.
	 * */
	public String getPlayerLetter() {
		return playerLetter;
	}
	
	/**
	 * Sets player color.
	 * */
	public void setPlayerColor(Color color) {
		playerColor = color;
	}
	
	/**
	 * Sets player letter choice.
	 * */
	public void setPlayerLetter(String letter) {
		playerLetter = letter;
	}
}


