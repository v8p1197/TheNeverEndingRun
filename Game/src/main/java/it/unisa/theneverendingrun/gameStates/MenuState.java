package it.unisa.theneverendingrun.gameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import it.unisa.theneverendingrun.GameEngine;
import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.services.sounds.SoundManager;
import org.mini2Dx.core.graphics.Graphics;

import java.util.ArrayList;

/**
 * In this state the game displays the main menu, from which the user can start a run, open the help menu or quit
 */
public class MenuState extends GameState {

    private Sprite background;

    protected Skin skin;

    private Table table;
    private ArrayList<InteractiveTextButton> buttons;

    private SoundManager soundManager;

    public MenuState(GameEngine game) {
        super(game);
        soundManager = SoundManager.getSoundManager();
    }

    @Override
    public void initialise() {
        super.initialise();

        createBackground();

        createTableButtons();
        soundManager.setMusic(2);
    }

    private void createTableButtons() {
        skin = new Skin(Gdx.files.internal("menu.json"));
        createButtons();
        createTable();
        addButtonsToTable();
    }

    private void createButtons() {
        buttons = new ArrayList<>();

        var newGameButton = new InteractiveTextButton("NEW GAME", skin, "default", this::onPlay);
        var helpButton = new InteractiveTextButton("HELP", skin, "default", this::onHelp);
        var quitButton = new InteractiveTextButton("QUIT", skin, "default", () -> System.exit(0));

        buttons.add(newGameButton);
        buttons.add(helpButton);
        buttons.add(quitButton);
    }

    private void createTable() {
        table = new Table();
        table.setWidth(Gdx.graphics.getWidth());
        table.align(Align.center | Align.top);
        table.setPosition(0, Gdx.graphics.getHeight() * 0.6f);
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
        return new Texture("images/menu.jpg");
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
        ButtonActionHandler.action(buttons);
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
