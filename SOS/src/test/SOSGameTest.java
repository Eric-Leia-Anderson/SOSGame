package test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sos.AutoPlayer;
import sos.HumanPlayer;
import sos.SOSGame;

public class SOSGameTest {

    private SOSGame sosGame;

    @BeforeEach
    public void setUp() {
        sosGame = new SOSGame();
    }

    @Test
    public void testUpdateGameRulesToSimple() {
        sosGame.updateGameRules(SOSGame.GameRules.SIMPLE);
        assertEquals(SOSGame.GameRules.SIMPLE, sosGame.getGameRules(), "Current rule should be SIMPLE");
    }

    @Test
    public void testUpdateGameRulesToGeneral() {
        sosGame.updateGameRules(SOSGame.GameRules.GENERAL);
        assertEquals(SOSGame.GameRules.GENERAL, sosGame.getGameRules(), "Current rule should be GENERAL");
    }
    
    @Test
    public void testCreateComputerPlayer() {
    	sosGame.setPlayerOne(new AutoPlayer(Color.RED, "S", '1', 0, sosGame, 5000));
    	sosGame.getPlayerOne().setType('C');
    	assertTrue(sosGame.isPlayerComputer(), "Current player, 1, should be a computer.");
    }
    
    @Test
    public void testComputerMove() {
    	sosGame.setPlayerOne(new AutoPlayer(Color.RED, "S", '1', 0, sosGame, 500));
    	sosGame.getPlayerOne().setType('C');
    	sosGame.setPlayerOne(new HumanPlayer(Color.BLUE, "S", '2', 0, sosGame));
    	sosGame.getPlayerOne().setType('H');
    	
    	assertEquals('2', sosGame.getCurrentPlayer().getTurn(), "Current player should be '2' because computer player 1 already went.");
    	
    }
}
