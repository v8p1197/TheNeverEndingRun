package it.unisa.theneverendingrun.gameStates;

import com.badlogic.gdx.Gdx;
import it.unisa.theneverendingrun.GameEngine;
import it.unisa.theneverendingrun.models.background.impls.HelpStateBackground;
import it.unisa.theneverendingrun.ui.InteractiveTextButton;

import java.util.Arrays;
import java.util.List;

/**
 *
 * In this state the rules of the game are displayed on screen.
 * The user can go back to the menu, start a new run or quit
 */
public class HelpState extends InfoGameState {

    public HelpState(GameEngine game) {
        super(game);
    }

    @Override
    protected GameStateType computeStateType() {
        return GameStateType.HELP;
    }

    @Override
    protected void createBackground() {
        background = new HelpStateBackground(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    protected void createTable() {
        super.createTable();

        table.setPosition(0, Gdx.graphics.getHeight() * 0.4f);
    }

    @Override
    protected List<InteractiveTextButton> defineButtons() {
        var newGameButton = new InteractiveTextButton("NEW GAME", skin, "default", this::onPlay);
        var helpButton = new InteractiveTextButton("MENU", skin, "default", this::onMenu);

        return Arrays.asList(newGameButton, helpButton);
    }

    @Override
    protected void drawInfo() {
        drawTable();
    }

    private void drawTable() {
        table.draw(spriteBatch, 1.0f);
    }

    @Override
    public void onMenu() {
        game.changeState(new MenuState(game));
    }

    @Override
    public void onPlay() {
        game.changeState(new PlayState(game));
    }

    @Override
    public void onEnded() {
    }

    @Override
    public void onHelp() {
    }
}
