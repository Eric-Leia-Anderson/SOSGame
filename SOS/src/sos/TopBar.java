package sos;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import sos.SOSGame.GameState;

public class TopBar extends JPanel {
		private ButtonGroup radio;
		private JRadioButton simple;
		private JRadioButton general;
		private JTextField num;
		private JLabel boardSizeLabel;
		private JButton startGame;
		
		/**
		 * Constructor that initializes game mode buttons, board size input and label,
		 * and aligns them.
		 * */
		public TopBar() {
			radio = new ButtonGroup();
			simple = new JRadioButton("Simple");
			general = new JRadioButton("General");
			boardSizeLabel = new JLabel("Board Size");
			startGame = new JButton("Start Game/ Reset Game");
			num = new JTextField();
			num.setColumns(2);
			boardSizeLabel.setLabelFor(num);
			radio.add(simple);
			radio.add(general);
			simple.setVerticalAlignment(SwingConstants.TOP);
			this.add(simple);
			this.add(general);
			this.add(num);
			this.add(boardSizeLabel);
			this.add(startGame);
			setVisible(true);
			
		}

		/**
		 * Returns radio button group that holds the game mode buttons. 
		 * */
		public ButtonGroup getRadio() {
			return radio;
		}

		/**
		 * Sets radio button group that holds the game mode buttons.
		 * */
		public void setRadio(ButtonGroup radio) {
			this.radio = radio;
		}

		/**
		 * Returns the simple game mode radio button.
		 * */
		public JRadioButton getSimple() {
			return simple;
		}

		/**
		 * Sets the simple game mode radio button.
		 * */
		public void setSimple(JRadioButton simple) {
			this.simple = simple;
		}

		/**
		 * Returns the general game mode radio button.
		 * */
		public JRadioButton getGeneral() {
			return general;
		}

		/**
		 * Sets the general game mode radio button.
		 * */
		public void setGeneral(JRadioButton general) {
			this.general = general;
		}

		/**
		 * Returns board size text field.
		 * */
		public JTextField getNum() {
			return num;
		}

		/**
		 * Sets board size text field.
		 * */
		public void setNum(JTextField num) {
			this.num = num;
		}

		/**
		 * Returns board size label.
		 * */
		public JLabel getBoardSizeLabel() {
			return boardSizeLabel;
		}

		/**
		 * Sets board size label.
		 * */
		public void setBoardSizeLabel(JLabel boardSizeLabel) {
			this.boardSizeLabel = boardSizeLabel;
		}

		/**
		 * Returns reset/start game button.
		 * */
		public JButton getStartGame() {
			return startGame;
		}

		/**
		 * Sets reset/start game button.
		 * */
		public void setStartGame(JButton startGame) {
			this.startGame = startGame;
		}
}
