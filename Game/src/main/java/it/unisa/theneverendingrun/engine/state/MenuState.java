package it.unisa.theneverendingrun.engine.state;

import com.badlogic.gdx.Gdx;
import it.unisa.theneverendingrun.engine.GameEngine;
import it.unisa.theneverendingrun.engine.GameStateType;
import it.unisa.theneverendingrun.models.background.impls.MenuStateBackground;
import it.unisa.theneverendingrun.ui.InteractiveTextButton;

import java.util.Arrays;
import java.util.List;

/**
 * In this state the game displays the main menu, from which the user can start a run, open the help menu or quit
 */
public class MenuState extends InfoGameState {

    public MenuState(GameEngine game) {
        super(game);
    }

    @Override
    protected GameStateType computeStateType() {
        return GameStateType.MENU;
    }

    @Override
    protected void createBackground() {
        background = new MenuStateBackground(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    protected void createTable() {
        super.createTable();

        table.setPosition(0, Gdx.graphics.getHeight() * 0.6f);
    }

    @Override
    protected List<InteractiveTextButton> defineButtons() {
        var newGameButton = new InteractiveTextButton("NEW GAME", skin, "default", this::onPlay);
        var helpButton = new InteractiveTextButton("HELP", skin, "default", this::onHelp);
        var quitButton = new InteractiveTextButton("QUIT", skin, "default", () -> System.exit(0));

        return Arrays.asList(newGameButton, helpButton, quitButton);
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
        game.changeState(new HelpState(game));
    }
}
