package it.unisa.theneverendingrun.gameState;

import de.tomgrill.gdxtesting.GdxTestRunner;
import it.unisa.theneverendingrun.engine.GameEngine;
import it.unisa.theneverendingrun.engine.state.PlayState;
import it.unisa.theneverendingrun.engine.state.info.EndedState;
import it.unisa.theneverendingrun.engine.state.info.HelpState;
import it.unisa.theneverendingrun.engine.state.info.MenuState;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(GdxTestRunner.class)
public class GameStateTest {

    GameEngine game = new GameEngine();

    @Test
    public void changeStateTest() {
        var state = game.getState();

        game.initialise();
        Assert.assertTrue(state instanceof MenuState);

        state.onPlay();
        Assert.assertTrue(state instanceof PlayState);

        state.onEnded();
        Assert.assertTrue(state instanceof EndedState);

        state.onMenu();
        Assert.assertTrue(state instanceof MenuState);

        state.onHelp();
        Assert.assertTrue(state instanceof HelpState);
    }
}
