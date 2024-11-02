package test;

import static org.junit.jupiter.api.Assertions.*;
import javax.swing.JTextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sos.SOSGUI;
import sos.TopBar;
import sos.GeneralGameRules;
import sos.LeftBar;
import sos.RightBar;
import sos.SOSGame;
import sos.SOSGame.GameState;
import sos.SimpleGameRules;

public class SOSGUITest {

    private SOSGUI sosGui; 
    private SOSGame sosGame;
    private TopBar topPanel;
    private RightBar rightPanel;
    private LeftBar leftPanel;
    private JTextField num;

    @BeforeEach
    public void setUp() {
    	sosGui = new SOSGUI(); 
    	sosGame = new SOSGame();
        topPanel = new TopBar(); 
        rightPanel = new RightBar();
        leftPanel = new LeftBar();
    }
    
    @Test
    public void testChangingLeftPlayer() {
    	sosGame.getPlayerOne().setPlayerLetter("O");
    	assertEquals("O", sosGame.getPlayerOne().getPlayerLetter(), "Current player one letter should be O.");
    	
    	sosGame.getPlayerOne().setPlayerLetter("S");
    	assertEquals("S", sosGame.getPlayerOne().getPlayerLetter(), "Current player one letter should be S.");
    }
    
    @Test
    public void testChangingRightPlayer() {
    	sosGame.getPlayerTwo().setPlayerLetter("O");
    	assertEquals("O", sosGame.getPlayerTwo().getPlayerLetter(), "Current player two letter should be O.");
    	
    	sosGame.getPlayerTwo().setPlayerLetter("S");
    	assertEquals("S", sosGame.getPlayerTwo().getPlayerLetter(), "Current player one letter should be S.");
    }

    @Test
    public void testValidSize() {
    	sosGui.getTopPanel().getNum().setText("5");
        assertTrue(sosGui.validateBoardSize(), "Should return true for valid size 5");
    }

    @Test
    public void testSizeTooSmall() {
    	sosGui.getTopPanel().getNum().setText("2");
        assertFalse(sosGui.validateBoardSize(), "Should return false for size less than 3");
    }

    @Test
    public void testSizeTooLarge() {
    	sosGui.getTopPanel().getNum().setText("11");
        assertFalse(sosGui.validateBoardSize(), "Should return false for size greater than 10");
    }

    @Test
    public void testNonNumericInput() {
    	sosGui.getTopPanel().getNum().setText("abc");
        assertFalse(sosGui.validateBoardSize(), "Should return false for non-numeric input");
    }

    @Test
    public void testEmptyInput() {
    	sosGui.getTopPanel().getNum().setText("");
        assertFalse(sosGui.validateBoardSize(), "Should return false for empty input");
    }

    @Test
    public void testInputTooLong() {        
        sosGui.getTopPanel().getNum().setText("12345"); // This is too long
        assertFalse(sosGui.validateBoardSize(), "Should return false for input longer than 4 characters");
    }
    
    @Test
    public void testSimpleGameWin() {
    	sosGame = new SimpleGameRules();
    	sosGui = new SOSGUI(sosGame);
    	sosGame.getPlayerTwo().setPlayerLetter("O");
    	sosGame.makeMove(0, 0);//s
    	sosGame.makeMove(0, 1);//o
    	sosGame.makeMove(0, 2);//s
    	
    	assertEquals(GameState.PLAYERONE_WON, sosGame.getGameState(), "Current game state should be PLAYERONE_WON");
    	
    }
    
    @Test
    public void testGeneralDraw() {
    	sosGame = new GeneralGameRules();
    	sosGui = new SOSGUI(sosGame);
    	sosGame.getPlayerOne().setPlayerPoints(3);
        sosGame.getPlayerTwo().setPlayerPoints(3);
        
        for (int i = 0; i < sosGame.getTotalRows(); i++) {
            for (int j = 0; j < sosGame.getTotalColumns(); j++) {
                sosGame.makeMove(i, j); 
            }
        }
        assertEquals(GameState.DRAW, sosGame.getGameState(), "Current game state should be DRAW");
        
    }
    
    @Test
    public void testGeneralWin() {
    	sosGame = new GeneralGameRules();
    	sosGui = new SOSGUI(sosGame);
        sosGame.getPlayerTwo().setPlayerLetter("O");
        
        for (int i = 0; i < sosGame.getTotalRows(); i++) {
            for (int j = 0; j < sosGame.getTotalColumns(); j++) {
                sosGame.makeMove(i, j); 
            }
        }
        assertEquals(GameState.PLAYERONE_WON, sosGame.getGameState(), "Current game state should be PLAYERONE_WON");
        
    }
    
    @Test
    public void testSimpleDraw() {
    	sosGame = new SimpleGameRules();
    	sosGui = new SOSGUI(sosGame);
        
        for (int i = 0; i < sosGame.getTotalRows(); i++) {
            for (int j = 0; j < sosGame.getTotalColumns(); j++) {
                sosGame.makeMove(i, j); 
            }
        }
        assertEquals(GameState.DRAW, sosGame.getGameState(), "Current game state should be DRAW");
        
    }
}
