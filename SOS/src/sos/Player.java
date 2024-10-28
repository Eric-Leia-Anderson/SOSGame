package sos;

import java.awt.Color;

public class Player {
	
	private int playerTurn;
	private Color playerColor;
	private String playerLetter;
	private int playerPoints;


	/**
	 * Sets up Player with color, letter, turn, and points.
	 * */
	public Player(Color color, String letter, int turn, int points) {
		playerColor = color;
		playerLetter = letter;
		playerTurn = turn;
		playerPoints = points;
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
	
	/**
	 * Gets player points.
	 * */
	public int getPlayerPoints() {
		return playerPoints;
	}

	/**
	 * Sets player points..
	 * */
	public void setPlayerPoints(int playerPoints) {
		this.playerPoints = playerPoints;
	}
}


