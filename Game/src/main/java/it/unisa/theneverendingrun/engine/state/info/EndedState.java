package it.unisa.theneverendingrun.engine.state.info;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import it.unisa.theneverendingrun.Assets;
import it.unisa.theneverendingrun.engine.GameEngine;
import it.unisa.theneverendingrun.engine.state.GameStateType;
import it.unisa.theneverendingrun.engine.state.InfoGameState;
import it.unisa.theneverendingrun.engine.state.PlayState;
import it.unisa.theneverendingrun.services.score.BestScores;
import it.unisa.theneverendingrun.ui.builder.TableBuilder;
import it.unisa.theneverendingrun.ui.controls.InteractiveTextButton;

import java.util.Arrays;
import java.util.List;

/**
 * In this state the run is ended (the hero died) and the user can start a new run, go back to the main menu or quit
 */
public abstract class EndedState extends InfoGameState {

    private Table table;
    private int score;

    public EndedState(GameEngine game, int finalScore) {
        super(game);
        score = finalScore;
    }

    protected abstract String computeTitle();

    @Override
    protected List<InteractiveTextButton> createButtons() {
        var newGameButton = new InteractiveTextButton("NEW GAME", INFO_GAME_STATE_SKIN, "default", this::onPlay);
        var menuButton = new InteractiveTextButton("MENU", INFO_GAME_STATE_SKIN, "default", this::onMenu);
        var quitButton = new InteractiveTextButton("QUIT", INFO_GAME_STATE_SKIN, "default", () -> System.exit(0));

        return Arrays.asList(newGameButton, menuButton, quitButton);
    }

    @Override
    protected void createGui(List<InteractiveTextButton> buttons) {
        table = new TableBuilder()
                .withWidth(Gdx.graphics.getWidth())
                .align(Align.center | Align.top)
                .withTopPad(30)
                .addInteractiveTextButtonWithPad(40, buttons)
                .build();
    }

    @Override
    protected void draw() {
        drawScores();
        table.draw(spriteBatch, 1.0f);
    }

    @Override
    protected GameStateType computeStateType() {
        return GameStateType.ENDED;
    }

    @Override
    public final void onMenu() {
        game.changeState(new MenuState(game));
    }

    @Override
    public final void onPlay() {
        game.changeState(new PlayState(game));
    }

    @Override
    public final void onEnded() {
    }

    @Override
    public final void onHelp() {
    }

    private void drawScores() {
        var fontY = 0.95f * Gdx.graphics.getHeight();
        var font = Assets.fonts.endScreenTitleFont
                .draw(spriteBatch, computeTitle(), 0, fontY, Gdx.graphics.getWidth(), Align.center, true);

        fontY -= font.height * 2;
        font = Assets.fonts.endScreenScoreFont
                .draw(spriteBatch, "YOUR SCORE: " + score,0, fontY, Gdx.graphics.getWidth(), Align.center, true);

        fontY -= font.height * 2;
        font = Assets.fonts.endScreenScoreFont
                .draw(spriteBatch, "HIGH SCORE: " + BestScores.getInstance().getHighScore(),
                        0, fontY, Gdx.graphics.getWidth(), Align.center, true);

        table.setPosition(0, fontY - font.height);
    }

}
