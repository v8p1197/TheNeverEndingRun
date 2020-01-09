package it.unisa.theneverendingrun.engine.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import it.unisa.theneverendingrun.engine.GameEngine;
import it.unisa.theneverendingrun.engine.GameState;
import it.unisa.theneverendingrun.models.background.Background;
import it.unisa.theneverendingrun.engine.state.info.strategies.KeyStrategy;
import it.unisa.theneverendingrun.engine.state.info.strategies.impls.NextKeyStrategy;
import it.unisa.theneverendingrun.engine.state.info.strategies.impls.PreviousKeyStrategy;
import it.unisa.theneverendingrun.ui.controls.InteractiveTextButton;
import org.mini2Dx.core.graphics.Graphics;

import java.util.List;

public abstract class InfoGameState extends GameState {

    public static final Skin INFO_GAME_STATE_SKIN = new Skin(Gdx.files.internal("fonts/arcade_font.json"));


    /* ------------------------------------- PARAMS ------------------------------------- */

    protected Background background;
    private List<InteractiveTextButton> buttons;


    /* ------------------------------------- CONSTRUCTOR ------------------------------------- */

    public InfoGameState(GameEngine game) {
        super(game);
    }



    /* ------------------------------------- ENGINE ------------------------------------- */

    @Override
    public void initialise() {
        super.initialise();

        background = createBackground();
        buttons = createButtons();
        createGui(buttons);
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        if (buttons.stream().noneMatch(Button::isChecked)) {
            buttons.get(0).setChecked(true);
        }
    }

    @Override
    public final void render(Graphics g) {
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0, g.getWindowWidth(), g.getWindowHeight());
        draw();
        spriteBatch.end();
    }

    @Override
    public void interpolate(float alpha) { }


    /* ------------------------------------- UI ------------------------------------- */

    protected abstract Background createBackground();

    protected abstract List<InteractiveTextButton> createButtons();

    protected abstract void createGui(List<InteractiveTextButton> buttons);

    protected abstract void draw();

    /* ------------------------------------- BUTTONS HANDLER ------------------------------------- */

    @Override
    public void keyAction() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) || Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            var strategy = new NextKeyStrategy();
            checkNextButton(strategy);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) || Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            var strategy = new PreviousKeyStrategy();
            checkNextButton(strategy);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            buttons.stream().filter(Button::isChecked).findFirst().ifPresent(InteractiveTextButton::click);
        }
    }

    private void checkNextButton(KeyStrategy strategy) {
        buttons.stream()
                .filter(Button::isChecked)
                .findFirst()
                .ifPresent(b -> {
                    b.setChecked(false);
                    var nextIndex = strategy.nextIndex(buttons.indexOf(b), buttons.size());
                    buttons.get(nextIndex).setChecked(true);
                });
    }


}
