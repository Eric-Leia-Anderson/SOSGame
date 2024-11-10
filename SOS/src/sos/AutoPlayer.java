package sos;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;

import sos.SOSGame.Cell;
import sos.SOSGame.GameState;

public class AutoPlayer extends Player{
	
	private Timer timer;
    private int delay;
    private Cell[][] grid;

	public AutoPlayer(Color color, String letter, char turn, int points, SOSGame game, int delay) {
		super(color, letter, turn, points, game);
		this.delay = delay;
	}
	@Override
    public void start() {
        timer = new Timer(delay, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				makeMove();
			}
        });
        timer.start();
    }

    private void makeMove() {
    	if(game.getTurn() == playerTurn) {
    		autoPlay();
    	}
    }
    
    @Override
    public void stop() {
        if (timer != null) {
            timer.stop();
        }
    }
	
	public void startPlaying() {
		while(game.currentGameState == GameState.PLAYING) {
			if(game.getTurn() == playerTurn) {
				autoPlay();
			}
		}
	}

	public void autoPlay() {
				int[] autoMove = makeMove(game.grid);
				if(autoMove[0] != -1 && autoMove[1] != -1 && autoMove[2] != -1) {
					int row = autoMove[0];
					int col = autoMove[1];
					String token = autoMove[2] == 1 ? "O" : "S";
					playerLetter = token;
					game.makeMove(row, col);
			}
			else {
					Point point = makeRandomMove(game.grid);
					if(point != null) {
						Random rand = new Random();
						int check = rand.nextInt(10);
						String token; 
						if(check <= 5) {
							token = "S";
						}
						else {
							token = "O";
						}
						playerLetter = token;
						game.makeMove(point.x, point.y);
					}
			}
	}

	@Override
	public int[] makeMove(Cell[][] grid) {
		int[] move = new int[] {-1, -1, -1};
		Cell[] tokens = new Cell[] {Cell.S, Cell.EMPTY, Cell.S};
		Cell[] tokens2 = new Cell[] {Cell.EMPTY, Cell.O, Cell.S};
		Cell[] tokens3 = new Cell[] {Cell.S, Cell.O, Cell.EMPTY};
		Cell[] tokens4 = new Cell[] {Cell.EMPTY, Cell.O, Cell.EMPTY};
		Cell[] tokens5 = new Cell[] {Cell.S, Cell.EMPTY, Cell.EMPTY};
		Cell[] tokens6 = new Cell[] {Cell.EMPTY, Cell.EMPTY, Cell.S};
        List<Point> points = new ArrayList<Point>();
        List<FoundSOS> found = new ArrayList<FoundSOS>();
        FoundSOS sosFound;
        int m = grid.length;
        int n = grid[0].length;
        boolean foundMove = false;
        Random rand = new Random();
		int check;
		//boolean skip = false; 
		check = rand.nextInt(10);
        

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
            	found = findMove(grid, i, j, tokens);
            	if(found.size() > 0) {
            		for(FoundSOS fs : found) {
            			points = fs.getCoordinates();
            			if(points.size() == 3) {
            				Point p = points.get(1);
            				move[0] = p.x;
            				move[1] = p.y;
            				move[2] = 1;//1 means O, 0 means S
            				foundMove = true;
            				return move;
            			}
            		}
            	}
            }
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
            	found = findMove(grid, i, j, tokens2);
            	if(found.size() > 0) {
            		for(FoundSOS fs : found) {
            			points = fs.getCoordinates();
            			if(points.size() == 3) {
            				Point p = points.get(0);
            				move[0] = p.x;
            				move[1] = p.y;
            				move[2] = 0;//1 means O, 0 means S
            				foundMove = true;
            				return move;
            			}
            		}
            	}
            }
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
            	found = findMove(grid, i, j, tokens3);
            	if(found.size() > 0) {
            		for(FoundSOS fs : found) {
            			points = fs.getCoordinates();
            			if(points.size() == 3) {
            				Point p = points.get(2);
            				move[0] = p.x;
            				move[1] = p.y;
            				move[2] = 0;//1 means O, 0 means S
            				foundMove = true;
            				return move;
            			}
            		}
            	}
            }
        }
        
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//            	found = findMove(grid, i, j, tokens4);
//            	if(found.size() > 0) {
//            		for(FoundSOS fs : found) {
//            			points = fs.getCoordinates();
//            			if(points.size() == 3) {
//            				Point p = points.get(0);
//            				move[0] = p.x;
//            				move[1] = p.y;
//            				move[2] = 0;//1 means O, 0 means S
//            				foundMove = true;
//            				return move;
//            			}
//            		}
//            	}
//            }
//        }
        
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//            	found = findMove(grid, i, j, tokens5);
//            	if(found.size() > 0) {
//            		for(FoundSOS fs : found) {
//            			points = fs.getCoordinates();
//            			if(points.size() == 3) {
//            				Point p = points.get(1);
//            				move[0] = p.x;
//            				move[1] = p.y;
//            				move[2] = 0;//1 means O, 0 means S
//            				foundMove = true;
//            				return move;
//            			}
//            		}
//            	}
//            }
//        }
        return move;
	}
	
	/**
	 * Finds any SOS's on board, checks if it was already found, and returns the list. 
	 * */
	public List<FoundSOS> findMove(Cell[][] grid, int row, int col, Cell[] tokens) {
		int m = grid.length;
	    int n = grid[0].length;
	    List<Point> points = new ArrayList<Point>();
	    List<FoundSOS> stuff = new ArrayList<FoundSOS>();
	    FoundSOS found;
	    Point p;
	    if (grid[row][col] != tokens[0]) {
	    	return stuff;
	    }
	    else {
	    	p = new Point(row,col);
	    	points.add(p);
	    }
    	int len = tokens.length;
        int[] x = { -1, -1, -1, 0, 0, 1, 1, 1 };
        int[] y = { -1, 0, 1, -1, 1, -1, 0, 1 };

        for (int dir = 0; dir < 8; dir++) {
            int k;
            int currX = row + x[dir];
            int currY = col + y[dir];

            for (k = 1; k < len; k++) {
                if (currX >= m || currX < 0 || currY >= n || currY < 0) {
                    break;
                }
                if (grid[currX][currY] != tokens[k]) {
                    break;
                }
                p = new Point(currX,currY);
            	points.add(p);
                currX += x[dir];
                currY += y[dir];
            }
            if (k == len) {
            		found = new FoundSOS();
                	for (Point pt : points) {
                		found.addCoordinate(pt);
                	}
                	stuff.add(found);
            	points.remove(2);
            	points.remove(1);
            }
            currX -= x[dir];
            currY -= y[dir];
        }
        return stuff;
	}
	
	@Override
	public Point makeRandomMove(Cell[][] grid) {
		Point point = null;
		Random rand = new Random();
		int check;
		boolean skip = false; 
		check = rand.nextInt(10);
		if(check <= 5) {
			skip = true;
		}
		for(int i = 0; i < grid.length; i++) {
			check = rand.nextInt(10);
			if(check <= 5) {
				skip = true;
			}
			else {
				skip = false;
			}
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == Cell.EMPTY && !skip) {
					point = new Point(i, j);
					return point;
				}
			}
		}
		for(int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == Cell.EMPTY) {
					point = new Point(i, j);
					return point;
				}
			}
		}
		return point;
	}
}
