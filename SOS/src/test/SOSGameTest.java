package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
}
