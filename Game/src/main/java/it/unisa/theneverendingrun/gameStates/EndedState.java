package it.unisa.theneverendingrun.gameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Align;
import it.unisa.theneverendingrun.GameEngine;
import it.unisa.theneverendingrun.assets.Fonts;
import it.unisa.theneverendingrun.services.score.BestScores;
import it.unisa.theneverendingrun.ui.InteractiveTextButton;

import java.util.Arrays;
import java.util.List;

/**
 * In this state the run is ended (the hero died) and the user can start a new run, go back to the main menu or quit
 */
public abstract class EndedState extends InfoGameState {

    private int score;

    public EndedState(GameEngine game, int finalScore) {
        super(game);
        score = finalScore;
    }

    @Override
    protected GameStateType computeStateType() {
        return GameStateType.ENDED;
    }

    @Override
    protected List<InteractiveTextButton> defineButtons() {
        var newGameButton = new InteractiveTextButton("NEW GAME", skin, "default", this::onPlay);
        var menuButton = new InteractiveTextButton("MENU", skin, "default", this::onMenu);
        var quitButton = new InteractiveTextButton("QUIT", skin, "default", () -> System.exit(0));

        return Arrays.asList(newGameButton, menuButton, quitButton);
    }

    @Override
    protected void addButtonsToTable() {
        table.padTop(30);
        for (var button : buttons) {
            table.add(button).padBottom(30);
            table.row();
        }
    }

    @Override
    protected void drawInfo() {
        drawScores();
    }

    protected void drawScores() {
        var fontY = 0.95f * Gdx.graphics.getHeight();
        var font = Fonts.endScreenTitleFont.draw(
                spriteBatch, computeTitle(),
                0, fontY, Gdx.graphics.getWidth(), Align.center, true);

        fontY -= font.height * 2;
        font = Fonts.endScreenScoreFont.draw(
                spriteBatch, "YOUR SCORE: " + score,
                0, fontY, Gdx.graphics.getWidth(), Align.center, true);

        fontY -= font.height * 2;
        font = Fonts.endScreenScoreFont.draw(
                spriteBatch, "HIGH SCORE: " + BestScores.getInstance().getHighScore(),
                0, fontY, Gdx.graphics.getWidth(), Align.center, true);

        table.setPosition(0, fontY - font.height);
        table.draw(spriteBatch, 1.0f);
    }

    protected abstract String computeTitle();

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
