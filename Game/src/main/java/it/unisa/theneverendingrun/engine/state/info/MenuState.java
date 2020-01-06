package it.unisa.theneverendingrun.engine.state.info;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import it.unisa.theneverendingrun.engine.GameEngine;
import it.unisa.theneverendingrun.engine.state.GameStateType;
import it.unisa.theneverendingrun.engine.state.InfoGameState;
import it.unisa.theneverendingrun.engine.state.PlayState;
import it.unisa.theneverendingrun.models.background.Background;
import it.unisa.theneverendingrun.ui.builder.TableBuilder;
import it.unisa.theneverendingrun.ui.controls.InteractiveTextButton;

import java.util.Arrays;
import java.util.List;

/**
 * In this state the game displays the main menu, from which the user can start a run, open the help menu or quit
 */
public class MenuState extends InfoGameState {

    private static final Texture TEXTURE = new Texture("images/menu_state_background.png");

    private Table table;

    public MenuState(GameEngine game) {
        super(game);
    }

    @Override
    protected Background createBackground() {
        return new Background(TEXTURE, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    protected List<InteractiveTextButton> createButtons() {
        var newGameButton = new InteractiveTextButton("NEW GAME", INFO_GAME_STATE_SKIN, "default", this::onPlay);
        var helpButton = new InteractiveTextButton("HELP", INFO_GAME_STATE_SKIN, "default", this::onHelp);
        var quitButton = new InteractiveTextButton("QUIT", INFO_GAME_STATE_SKIN, "default", () -> System.exit(0));
        return Arrays.asList(newGameButton, helpButton, quitButton);
    }

    @Override
    protected void createGui(List<InteractiveTextButton> buttons) {
        table = new TableBuilder()
                .withWidth(Gdx.graphics.getWidth())
                .align(Align.center | Align.top)
                .withTopPad(30)
                .addInteractiveTextButtonsWithPad(40, buttons)
                .withPosition(0, Gdx.graphics.getHeight() * 0.6f)
                .build();
    }

    @Override
    protected void draw() {
        table.draw(spriteBatch, 1.0f);
    }

    @Override
    protected GameStateType computeStateType() {
        return GameStateType.MENU;
    }

    @Override
    public final void onMenu() {
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
        game.changeState(new HelpState(game));
    }

}
