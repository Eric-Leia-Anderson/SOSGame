package sos;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class RightBar extends JPanel{
	public JLabel PlayerTwoLabel;
	public ButtonGroup radio;
	public JRadioButton S;
	public JRadioButton O;
	
	/**
	 * Sets up and initializes player 2's panel.
	 * */
	public RightBar() {
		PlayerTwoLabel = new JLabel("Player 2");
		this.add(PlayerTwoLabel);
		setVisible(true);
		
		radio = new ButtonGroup();
		S = new JRadioButton("S");
		O = new JRadioButton("O");
		radio.add(S);
		radio.add(O);
		this.add(S);
		this.add(O);
	}
	
	/**
	 * Returns player 2's label.
	 * */
	public JLabel getPlayerTwoLabel() {
		return PlayerTwoLabel;
	}

	/**
	 * Sets player 2's label.
	 * */
	public void setPlayerTwoLabel(JLabel playerTwoLabel) {
		PlayerTwoLabel = playerTwoLabel;
	}

	/**
	 * Returns player 2's radio button group that holds letter choice buttons.
	 * */
	public ButtonGroup getRadio() {
		return radio;
	}

	/**
	 * Sets player 2's radio button group that holds letter choice buttons.
	 * */
	public void setRadio(ButtonGroup radio) {
		this.radio = radio;
	}

	/**
	 * Returns player 2's S radio button.
	 * */
	public JRadioButton getS() {
		return S;
	}

	/**
	 * Sets player 2's S radio button.
	 * */
	public void setS(JRadioButton s) {
		S = s;
	}

	/**
	 * Returns player 2's O radio button.
	 * */
	public JRadioButton getO() {
		return O;
	}

	/**
	 * Sets player 2's O radio button.
	 * */
	public void setO(JRadioButton o) {
		O = o;
	}
}
