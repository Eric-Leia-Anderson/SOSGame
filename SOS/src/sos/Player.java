package sos;

import java.awt.Color;
import java.awt.Point;

import sos.SOSGame.Cell;

public abstract class Player {
	
	protected char playerTurn;
	protected Color playerColor;
	protected String playerLetter;
	protected int playerPoints;
	protected char type;
	protected SOSGame game;


	/**
	 * Sets up Player with color, letter, turn, and points.
	 * */
	public Player(Color color, String letter, char turn, int points, SOSGame game) {
		playerColor = color;
		playerLetter = letter;
		playerTurn = turn;
		playerPoints = points;
		this.game = game;
	}
	//make this abstract
	public int[] makeMove(Cell[][] grid) {
		return null;
	}
	
	public abstract void start();
	
	public Point makeRandomMove(Cell[][] grid) {
		return null;
	}
	
	/**
	 * Returns player turn.
	 * */
	public char getTurn() {
		return playerTurn;
	}
	
	/**
	 * Sets player turn.
	 * */
	public void setTurn(char turn) {
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
	 * Sets player points.
	 * */
	public void setPlayerPoints(int playerPoints) {
		this.playerPoints = playerPoints;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}
	protected abstract void stop();
	
	
}


