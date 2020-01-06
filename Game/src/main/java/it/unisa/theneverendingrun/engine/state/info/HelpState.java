package it.unisa.theneverendingrun.engine.state.info;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import it.unisa.theneverendingrun.engine.GameEngine;
import it.unisa.theneverendingrun.engine.state.GameStateType;
import it.unisa.theneverendingrun.engine.state.InfoGameState;
import it.unisa.theneverendingrun.engine.state.PlayState;
import it.unisa.theneverendingrun.models.background.AbstractBackground;
import it.unisa.theneverendingrun.models.background.impls.HelpStateBackground;
import it.unisa.theneverendingrun.ui.builder.TableBuilder;
import it.unisa.theneverendingrun.ui.controls.InteractiveTextButton;
import org.mini2Dx.core.graphics.Graphics;

import java.util.Arrays;
import java.util.List;

/**
 *
 * In this state the rules of the game are displayed on screen.
 * The user can go back to the menu, start a new run or quit
 */
public class HelpState extends InfoGameState {

    private Table table;

    public HelpState(GameEngine game) {
        super(game);
    }

    @Override
    protected AbstractBackground createBackground() {
        return new HelpStateBackground(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    protected List<InteractiveTextButton> createButtons() {
        var newGameButton = new InteractiveTextButton("NEW GAME", INFO_GAME_STATE_SKIN, "default", this::onPlay);
        var helpButton = new InteractiveTextButton("MENU", INFO_GAME_STATE_SKIN, "default", this::onMenu);

        return Arrays.asList(newGameButton, helpButton);
    }

    @Override
    protected void createGui(List<InteractiveTextButton> buttons) {
        table = new TableBuilder()
                .withWidth(Gdx.graphics.getWidth())
                .align(Align.center | Align.top)
                .withTopPad(30)
                .addInteractiveTextButtonWithPad(40, buttons)
                .withPosition(0, Gdx.graphics.getHeight() * 0.4f)
                .build();
    }

    @Override
    protected void draw() {
        table.draw(spriteBatch, 1.0f);
    }


    @Override
    protected GameStateType computeStateType() {
        return GameStateType.HELP;
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
}
