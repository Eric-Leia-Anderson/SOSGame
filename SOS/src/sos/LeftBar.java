package sos;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class LeftBar extends JPanel{
	public JLabel PlayerOneLabel;
	public ButtonGroup radio;
	public ButtonGroup radio2;
	public JRadioButton S;
	public JRadioButton O;
	public JLabel PlayerOnePoints;
	public JLabel ActualPoints;
	public JRadioButton Human;
	public JRadioButton Computer;
	
	/**
	 * Sets up and initializes player 1's panel.
	 * */
	public LeftBar() {
		PlayerOneLabel = new JLabel("Player 1");
		this.add(PlayerOneLabel);
		PlayerOnePoints = new JLabel("Points");
		this.add(PlayerOnePoints);
		ActualPoints = new JLabel("0");
		this.add(ActualPoints);
		setVisible(true);
		radio = new ButtonGroup();
		S = new JRadioButton("S");
		O = new JRadioButton("O");
		radio2 = new ButtonGroup();
		Human = new JRadioButton("Human");
		Computer = new JRadioButton("Computer");
		radio.add(S);
		radio.add(O);
		radio2.add(Human);
		radio2.add(Computer);
		this.add(S);
		this.add(O);
		this.add(Human);
		this.add(Computer);
		
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
	
	/**
	 * Returns player 1's point label.
	 * */
	public JLabel getPlayerOnePoints() {
		return PlayerOnePoints;
	}

	/**
	 * Sets player 1's point label.
	 * */
	public void setPlayerOnePoints(JLabel playerOnePoints) {
		PlayerOnePoints = playerOnePoints;
	}
	
	/**
	 * Returns player 1's actual point label.
	 * */
	public JLabel getPlayerOneActualPoints() {
		return ActualPoints;
	}

	/**
	 * Sets player 1's actual point label.
	 * */
	public void setPlayerOneActualPoints(JLabel actualPoints) {
		ActualPoints = actualPoints;
	}

	/**
	 * Gets player 1's radio group 2.
	 * */
	public ButtonGroup getRadio2() {
		return radio2;
	}

	/**
	 * Sets player 1's radio group 2.
	 * */
	public void setRadio2(ButtonGroup radio2) {
		this.radio2 = radio2;
	}

	/**
	 * Gets player 1's human radio button.
	 * */
	public JRadioButton getHuman() {
		return Human;
	}

	/**
	 * Sets player 1's human radio button.
	 * */
	public void setHuman(JRadioButton human) {
		Human = human;
	}

	/**
	 * Gets player 1's computer radio button.
	 * */
	public JRadioButton getComputer() {
		return Computer;
	}

	/**
	 * Sets player 1's computer radio button.
	 * */
	public void setComputer(JRadioButton computer) {
		Computer = computer;
	}
}
