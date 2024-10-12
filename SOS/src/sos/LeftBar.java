package sos;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class LeftBar extends JPanel{
	public JLabel PlayerOneLabel;
	public ButtonGroup radio;
	public JRadioButton S;
	public JRadioButton O;
	
	/**
	 * Sets up and initializes player 1's panel.
	 * */
	public LeftBar() {
		PlayerOneLabel = new JLabel("Player 1");
		this.add(PlayerOneLabel);
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
	 * Returns player 1's label.
	 * */
	public JLabel getPlayerOneLabel() {
		return PlayerOneLabel;
	}

	/**
	 * Sets player 1's label.
	 * */
	public void setPlayerOneLabel(JLabel playerOneLabel) {
		PlayerOneLabel = playerOneLabel;
	}

	/**
	 * Returns player 1's radio button group that holds letter choice buttons.
	 * */
	public ButtonGroup getRadio() {
		return radio;
	}

	/**
	 * Sets player 1's radio button group that holds letter choice buttons.
	 * */
	public void setRadio(ButtonGroup radio) {
		this.radio = radio;
	}

	/**
	 * Returns player 1's S radio button.
	 * */
	public JRadioButton getS() {
		return S;
	}

	/**
	 * Sets player 1's S radio button.
	 * */
	public void setS(JRadioButton s) {
		S = s;
	}

	/**
	 * Returns player 1's O radio button.
	 * */
	public JRadioButton getO() {
		return O;
	}

	/**
	 * 
	 * Sets player 1's O radio button.
	 * */
	public void setO(JRadioButton o) {
		O = o;
	}
	
	
}
