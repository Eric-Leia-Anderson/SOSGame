package test;

import static org.junit.jupiter.api.Assertions.*;
import javax.swing.JTextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sos.SOSGUI;
import sos.TopBar;
import sos.LeftBar;
import sos.RightBar;
import sos.SOSGame;

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
    	topPanel.getNum().setText("5");
        assertTrue(sosGui.validateBoardSize(), "Should return true for valid size 5");
    }

    @Test
    public void testSizeTooSmall() {
        topPanel.getNum().setText("2");
        assertFalse(sosGui.validateBoardSize(), "Should return false for size less than 3");
    }

    @Test
    public void testSizeTooLarge() {
    	topPanel.getNum().setText("11");
        assertFalse(sosGui.validateBoardSize(), "Should return false for size greater than 10");
    }

    @Test
    public void testNonNumericInput() {
    	topPanel.getNum().setText("abc");
        assertFalse(sosGui.validateBoardSize(), "Should return false for non-numeric input");
    }

    @Test
    public void testEmptyInput() {
    	topPanel.getNum().setText("");
        assertFalse(sosGui.validateBoardSize(), "Should return false for empty input");
    }

    @Test
    public void testInputTooLong() {
    	topPanel.getNum().setText("1234"); // This is valid
        assertTrue(sosGui.validateBoardSize(), "Should return true for valid size 1234");
        
        topPanel.getNum().setText("12345"); // This is too long
        assertFalse(sosGui.validateBoardSize(), "Should return false for input longer than 4 characters");
    }
}
