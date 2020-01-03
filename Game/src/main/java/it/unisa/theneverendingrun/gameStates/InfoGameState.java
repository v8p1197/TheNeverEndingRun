package it.unisa.theneverendingrun.gameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import it.unisa.theneverendingrun.GameEngine;
import it.unisa.theneverendingrun.models.background.AbstractBackground;
import it.unisa.theneverendingrun.ui.InteractiveTextButton;
import org.mini2Dx.core.graphics.Graphics;

import java.util.ArrayList;
import java.util.List;

public abstract class InfoGameState extends GameState {

    protected AbstractBackground background;
    protected Skin skin;
    protected Table table;
    protected ArrayList<InteractiveTextButton> buttons;

    public InfoGameState(GameEngine game) {
        super(game);
    }

    @Override
    public void initialise() {
        super.initialise();

        createBackground();
        createSkin();
        createTable();
        createButtons();
        addButtonsToTable();
    }

    @Override
    public void interpolate(float alpha) { }

    protected abstract void createBackground();

    protected void createSkin() {
        skin =  new Skin(Gdx.files.internal("fonts/arcade_font.json"));
    }

    protected void createTable() {
        table = new Table();
        table.setWidth(Gdx.graphics.getWidth());
        table.align(Align.center | Align.top);
    }

    private void createButtons() {
        buttons = new ArrayList<>(defineButtons());
    }

    protected abstract List<InteractiveTextButton> defineButtons();

    protected void addButtonsToTable() {
        table.padTop(30);
        for (var button : buttons) {
            table.add(button).padBottom(40);
            table.row();
        }
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        if (buttons.stream().filter(Button::isChecked).toArray().length == 0) {
            buttons.get(0).setChecked(true);
        }
    }

    @Override
    public void render(Graphics g) {
        spriteBatch.begin();

        background.draw(spriteBatch);

        drawInfo();

        spriteBatch.end();
    }

    protected abstract void drawInfo();

    @Override
    public void keyAction() {
        ButtonActionHandler.action(buttons);
    }

}
