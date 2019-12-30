package it.unisa.theneverendingrun.gameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import it.unisa.theneverendingrun.GameEngine;
import it.unisa.theneverendingrun.models.Sprite;
import org.mini2Dx.core.graphics.Graphics;

import java.util.ArrayList;

/**
 * In this state the rules of the game are displayed on screen. The user can go back to the menu, start a new run or quit
 */
public class HelpState extends GameState {
    private Sprite background;

    protected Skin skin;

    private Table table;
    private ArrayList<InteractiveTextButton> buttons;
    private KeyButtonsStrategy strategy;

    public HelpState(GameEngine game) {
        super(game);
    }

    @Override
    public void initialise() {
        super.initialise();

        createBackground();

        createTableButtons();
    }

    private void createTableButtons() {
        skin = new Skin(Gdx.files.internal("menu.json"));
        createButtons();
        createTable();
        addButtonsToTable();
    }

    private void createButtons() {
        buttons = new ArrayList<>();

        var newGameButton = new InteractiveTextButton(new TextButton("NEW GAME", skin, "default"), this::onPlay);
        var helpButton = new InteractiveTextButton(new TextButton("MENU", skin, "default"), this::onMenu);

        buttons.add(newGameButton);
        buttons.add(helpButton);
    }

    private void createTable() {
        table = new Table();
        table.setWidth(Gdx.graphics.getWidth());
        table.align(Align.center | Align.top);
        table.setPosition(0, Gdx.graphics.getHeight() * 0.4f);
    }

    private void addButtonsToTable() {
        table.padTop(30);
        for (var button : buttons) {
            table.add(button).padBottom(40);
            table.row();
        }
    }

    private void createBackground() {
        var texture = computeBackground();
        background = new Sprite(texture);
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        background.flip(false, true);
    }

    private Texture computeBackground() {
        return new Texture("images/help.jpg");
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        if (buttons.stream().filter(Button::isChecked).toArray().length == 0) {
            buttons.get(0).setChecked(true);
        }
    }

    @Override
    public void interpolate(float alpha) {

    }

    @Override
    public void render(Graphics g) {
        spriteBatch.begin();

        background.draw(spriteBatch);
        table.draw(spriteBatch, 1.0f);

        spriteBatch.end();
    }

    @Override
    public void keyAction() {
        ButtonActionHandler.action(buttons, strategy);
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
