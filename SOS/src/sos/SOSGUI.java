package sos;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;

import javax.swing.*;

import sos.SOSGame.Cell;
import sos.SOSGame.GameRules;
import sos.SOSGame.GameState;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import sos.TopBar;
import sos.LeftBar;
import sos.RightBar;

@SuppressWarnings("serial")
public class SOSGUI extends JFrame {

	public static final int CELL_SIZE = 100;
	public static final int GRID_WIDTH = 8;
	public static final int GRID_WIDTH_HALF = GRID_WIDTH / 2;
	public static boolean doneHumanMove = false;

	public static final int CELL_PADDING = CELL_SIZE / 6;
	public static final int SYMBOL_SIZE = CELL_SIZE - CELL_PADDING * 2;
	public static final int SYMBOL_STROKE_WIDTH = 8;

	private GameBoardCanvas gameBoardCanvas;
	private JLabel gameStatusBar;
	private TopBar topPanel;
	private LeftBar leftPanel;
	private RightBar rightPanel;
	private GameType gameType; 
	private GameRules gameRules;
	
	public enum GameType {
		SIMPLE, GENERAL
	}
	
	private SOSGame game;

	/**
	 * Creates new game.
	 * */
	public SOSGUI() {
		this(new SOSGame());
	}

	/**
	 * Sets up GUI and game.
	 * */
	public SOSGUI(SOSGame game) {
		this.game = game;
		game.setSOSGUI(this);
		setContentPane();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setTitle("SOS Game");
		setVisible(true);
	}
	
	/**
	 * Puts all the GUI pieces into the frame, sets the defaults, and has action listeners for changes.
	 * */
	private void setContentPane() {
		Container contentPane = getContentPane();
		gameBoardCanvas = new GameBoardCanvas();
		gameBoardCanvas.setPreferredSize(new Dimension(CELL_SIZE * game.getTotalRows(), CELL_SIZE * game.getTotalColumns()));
		gameStatusBar = new JLabel("  ");
		gameStatusBar.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));
		gameStatusBar.setBorder(BorderFactory.createEmptyBorder(2, 5, 4, 5));
		leftPanel = new LeftBar();
		leftPanel.setSize(50, 750);
		rightPanel = new RightBar();
		rightPanel.setSize(50, 750);
		topPanel = new TopBar();
		topPanel.setBorder(BorderFactory.createEmptyBorder(2, 5, 4, 5));
		topPanel.getNum().setText(String.valueOf(game.getTotalRows()));

		topPanel.getSimple().setSelected(true);
		contentPane.add(gameBoardCanvas, BorderLayout.CENTER);
		contentPane.add(gameStatusBar, BorderLayout.PAGE_END);
		
		leftPanel.getHuman().setSelected(true);
		leftPanel.getS().setSelected(true);
		rightPanel.getS().setSelected(true);
		rightPanel.getHuman().setSelected(true);
		
		contentPane.add(topPanel, BorderLayout.PAGE_START);
		contentPane.add(leftPanel, BorderLayout.WEST);
		contentPane.add(rightPanel, BorderLayout.EAST);
		
		
		/**
		 * Action listener for if player one changes letter.
		 * */
		leftPanel.S.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.getPlayerOne().setPlayerLetter("S");
			}
			
		});
		
		/**
		 * Action listener for if player one changes letter.
		 * */
		leftPanel.O.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.getPlayerOne().setPlayerLetter("O");
			}
			
		});
		
		/**
		 * Action listener for if player two changes letter.
		 * */
		rightPanel.S.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.getPlayerTwo().setPlayerLetter("S");
			}
			
		});
		
		/**
		 * Action listener for if player two changes letter.
		 * */
		rightPanel.O.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.getPlayerTwo().setPlayerLetter("O");
			}
			
		});
		
		/**
		 * Action listener for reset/start game. This validates and resizes board as necessary,
		 * wipes it clean, keeps game mode choice, and resets defaults.
		 * */
		topPanel.getStartGame().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (!validateBoardSize()) {
					topPanel.getNum().setText("3");
				}
				String size = topPanel.getNum().getText();
				int intSize = Integer.parseInt(size);
				int rowSize = game.getTotalRows();
				boolean larger = intSize > rowSize ? true : false;
				if (!size.equals(String.valueOf(rowSize))) {
					game.setTotalRows(intSize);
					game.setTotalColumns(intSize);
					game.resetSize();
					if(larger) {
						Dimension contentSize = contentPane.getSize();
						contentSize.setSize(contentSize.getWidth()+5, contentSize.getHeight()+5);
						contentPane.setSize(contentSize);
					}
					else {
						Dimension contentSize = contentPane.getSize();
						contentSize.setSize(contentSize.getWidth()-5, contentSize.getHeight()-5);
						contentPane.setSize(contentSize);
					}
					
				}
				
				changeGameMode();
				checkPoints();
				leftPanel.S.setSelected(true);
				rightPanel.S.setSelected(true);
				gameBoardCanvas.setPreferredSize(new Dimension(CELL_SIZE * game.getTotalRows(), CELL_SIZE * game.getTotalColumns()));
				repaint();
			}
		});
	}
	
	/**
	 * Changes game rules if new mode is selected and reset/start game is clicked.
	 * */
	public void changeGameMode() {
		if (topPanel.getSimple().isSelected() && game.getGameRules() == GameRules.GENERAL) {
				game.updateGameRules(GameRules.SIMPLE);
				game = new SimpleGameRules();
				game.setSOSGUI(this);
		}
		else if (topPanel.getGeneral().isSelected() && game.getGameRules() == GameRules.SIMPLE){
				game.updateGameRules(GameRules.GENERAL);
				game = new GeneralGameRules();
				game.setSOSGUI(this);
			}
		if (leftPanel.getHuman().isSelected()) {
			game.setPlayerOne(new HumanPlayer(Color.RED, "S", '1', 0, game));
			game.getPlayerOne().setType('H');
		}
		else if (leftPanel.getComputer().isSelected()) {
			game.setPlayerOne(new AutoPlayer(Color.RED, "S", '1', 0, game, 5000));
			game.getPlayerOne().setType('C');
		}
		if (rightPanel.getHuman().isSelected()) {
			game.setPlayerTwo(new HumanPlayer(Color.BLUE, "S", '2', 0, game));
			game.getPlayerTwo().setType('H');
		}
		else if (rightPanel.getComputer().isSelected()) {
			game.setPlayerTwo(new AutoPlayer(Color.BLUE, "S", '2', 0, game, 6000));
			game.getPlayerTwo().setType('C');
		}
		game.initPartialGame();
	}
	
	public void repaintTheBoard() {
		repaint();
	}

	class GameBoardCanvas extends JPanel {

		/**
		 * Makes a move when a cell is clicked and repaints.
		 * If game state is no longer PLAYING, it resets the whole game.
		 * */
		GameBoardCanvas() {
			addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (game.getGameState() == GameState.PLAYING) {
						if (!game.isPlayerComputer()) {
							int rowSelected = e.getY() / CELL_SIZE;
							int colSelected = e.getX() / CELL_SIZE;
							game.makeMove(rowSelected, colSelected);
							checkPoints();	
							//game.setDoneHumanMove(true);
						}
						else {
							//checkPoints();
						}
					} else {
						game.resetGame();
						checkPoints();
					}
					repaint();
				}
			});
		}

		/**
		 * Paints the board and shows the status bar. 
		 * */
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			setBackground(Color.WHITE);
			drawGridLines(g);
			drawBoard(g);
			printStatusBar();
		}
		
		/**
		 * Draws grid lines of SOS game board.
		 * */
		private void drawGridLines(Graphics g) {
			g.setColor(Color.LIGHT_GRAY);
			for (int row = 1; row < game.getTotalRows(); ++row) {
				g.fillRoundRect(0, CELL_SIZE * row - GRID_WIDTH_HALF, CELL_SIZE * game.getTotalRows() - 1, GRID_WIDTH,
						GRID_WIDTH, GRID_WIDTH);
			}
			for (int col = 1; col < game.getTotalColumns(); ++col) {
				g.fillRoundRect(CELL_SIZE * col - GRID_WIDTH_HALF, 0, GRID_WIDTH,
						CELL_SIZE * game.getTotalColumns() - 1, GRID_WIDTH, GRID_WIDTH);
			}
		}

		/**
		 * Draws the board according to what is in array of cells, whether S, O, or empty.
		 * */
		private void drawBoard(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			Color textColor;
			g2d.setStroke(new BasicStroke(SYMBOL_STROKE_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			for (int row = 0; row < game.getTotalRows(); ++row) {
				for (int col = 0; col < game.getTotalColumns(); ++col) {
					int x1 = col * CELL_SIZE + CELL_PADDING;
					int y1 = row * CELL_SIZE + CELL_PADDING;
					textColor = Color.BLACK;
					if (game.getCell(row, col) == Cell.S) {
						g2d.setColor(textColor);
						int x2 = (col + 1) * CELL_SIZE - CELL_PADDING;
						int y2 = (row + 1) * CELL_SIZE - CELL_PADDING;
						g2d.setFont(new Font("TimesRoman", Font.PLAIN, 100));
						g2d.drawString("S", x1, y2);
					} else if (game.getCell(row, col) == Cell.O) {
						g2d.setColor(textColor);
						g2d.drawOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
					}
				}
			}
			//paints lines on found SOS's on board 
			for(FoundSOS fs : game.getFoundSOS()) {
				float x1 = (float) ((fs.getLast().x+0.25) * CELL_SIZE + CELL_PADDING);
				float y1 = (float) ((fs.getLast().y+0.5) * CELL_SIZE + CELL_PADDING);
				float x2 = (float) ((fs.getFirst().x+0.25) * CELL_SIZE + CELL_PADDING);
				float y2 = (float) ((fs.getFirst().y+0.5) * CELL_SIZE + CELL_PADDING);
				g2d.setColor(fs.getLineColor());
				Line2D lin = new Line2D.Float(y1, x1, y2, x2);
		        g2d.draw(lin);
			}
		}

		/**
		 * Shows the current turn and if someone has won.
		 * */
		private void printStatusBar() {
			if (game.getGameState() == GameState.PLAYING) {
				gameStatusBar.setForeground(Color.BLACK);
				if (game.getTurn() == '1') {
					gameStatusBar.setText("Player One's Turn");
				} else {
					gameStatusBar.setText("Player Two's Turn");
				}
			} else if (game.getGameState() == GameState.DRAW) {
				gameStatusBar.setForeground(Color.RED);
				gameStatusBar.setText("It's a Draw! Click reset to play again.");
			} else if (game.getGameState() == GameState.PLAYERONE_WON) {
				gameStatusBar.setForeground(Color.RED);
				gameStatusBar.setText("Player One Won! Click reset to play again.");
			} else if (game.getGameState() == GameState.PLAYERTWO_WON) {
				gameStatusBar.setForeground(Color.RED);
				gameStatusBar.setText("Player Two Won! Click reset to play again.");
			}
		}

	}
	
	/**
	 * Validates that board size input is only integers, >= 3 and <= 10, and that
	 * the number of characters won't overflow and cause exceptions.
	 * Shows errors if not valid. 
	 * */
	public Boolean validateBoardSize() {
		String num = topPanel.getNum().getText();
		String message;
		if(num.isEmpty()) {
			message = "Board size was empty! Setting to auto size of 3.";
			JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
			        JOptionPane.ERROR_MESSAGE);
			return false;
		} else if(num.length() > 4) {
			message = "Board size was too many characters! Setting to auto size of 3.";
			JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
			        JOptionPane.ERROR_MESSAGE);
			return false;
		}else if(!num.chars().allMatch(Character::isDigit)) {
			message = "Board size does not accept non integer characters! Setting auto size of 3.";
			JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
			        JOptionPane.ERROR_MESSAGE);
			return false;
		} else if(Integer.parseInt(num) < 3) {
			message = "Board size can't be lower than 3! Setting to auto size of 3.";
			JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
			        JOptionPane.ERROR_MESSAGE);
			return false;
		} else if(Integer.parseInt(num) > 10) {
			message = "Board size can't be greater than 10! Setting to auto size of 3.";
			JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
			        JOptionPane.ERROR_MESSAGE);
			return false;
		}else {
			return true;
		}
	}
	
	/**
	 * Returns top panel.
	 * */
	public TopBar getTopPanel() {
		return topPanel;
	}
	
	/**
	 * Returns right panel.
	 * */
	public RightBar getRightPanel() {
		return rightPanel;
	}
	
	/**
	 * Returns left panel.
	 * */
	public LeftBar getLeftPanel() {
		return leftPanel;
	}
	
	/**
	 * Changes panels to have current player points.
	 * */
	public void checkPoints() {
		leftPanel.getPlayerOneActualPoints().setText(String.valueOf(game.getPlayerOne().getPlayerPoints()));
		rightPanel.getPlayerTwoActualPoints().setText(String.valueOf(game.getPlayerTwo().getPlayerPoints()));
		repaint();
	}

	/**
	 * Runs the program.
	 * */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new SOSGUI(new SimpleGameRules());
			}
		});
	}
}

