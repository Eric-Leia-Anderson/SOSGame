package sos;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class RightBar extends JPanel{
	public JLabel PlayerTwoLabel;
	public ButtonGroup radio;
	public ButtonGroup radio2;
	public JRadioButton S;
	public JRadioButton O;
	public JLabel PlayerTwoPoints;
	public JLabel ActualPoints;
	public JRadioButton Human;
	public JRadioButton Computer;
	
	/**
	 * Sets up and initializes player 2's panel.
	 * */
	public RightBar() {
		PlayerTwoLabel = new JLabel("Player 2");
		this.add(PlayerTwoLabel);
		PlayerTwoPoints = new JLabel("Points");
		this.add(PlayerTwoPoints);
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
	
	/**
	 * Returns player 2's point label.
	 * */
	public JLabel getPlayerTwoPoints() {
		return PlayerTwoPoints;
	}

	/**
	 * Sets player 2's point label.
	 * */
	public void setPlayerTwoPoints(JLabel playerTwoPoints) {
		PlayerTwoPoints = playerTwoPoints;
	}
	
	/**
	 * Returns player 2's actual point label.
	 * */
	public JLabel getPlayerTwoActualPoints() {
		return ActualPoints;
	}

	/**
	 * Sets player 2's actual point label.
	 * */
	public void setPlayerTwoActualPoints(JLabel actualPoints) {
		ActualPoints = actualPoints;
	}

	public ButtonGroup getRadio2() {
		return radio2;
	}

	public void setRadio2(ButtonGroup radio2) {
		this.radio2 = radio2;
	}

	public JRadioButton getHuman() {
		return Human;
	}

	public void setHuman(JRadioButton human) {
		Human = human;
	}

	public JRadioButton getComputer() {
		return Computer;
	}

	public void setComputer(JRadioButton computer) {
		Computer = computer;
	}
	
	
}
