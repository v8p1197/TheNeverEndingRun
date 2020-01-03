package it.unisa.theneverendingrun.gameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import it.unisa.theneverendingrun.GameEngine;
import it.unisa.theneverendingrun.assets.Fonts;
import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.services.score.BestScores;
import it.unisa.theneverendingrun.services.sounds.SoundManager;
import org.mini2Dx.core.graphics.Graphics;

import java.util.ArrayList;

/**
 * In this state the run is ended (the hero died) and the user can start a new run, go back to the main menu or quit
 */
public abstract class EndedState extends GameState {

    private Sprite background;

    protected Skin skin;

    private Table table;
    private ArrayList<InteractiveTextButton> buttons;
    private SoundManager soundManager;

    private int score;

    public EndedState(GameEngine game, int finalScore) {
        super(game);
        score = finalScore;
        soundManager = SoundManager.getSoundManager();
    }

    @Override
    public void initialise() {
        super.initialise();

        createBackground();

        createTableButtons();

        soundManager.setMusic(1);
    }

    private void createBackground() {
        var texture = computeBackground();
        background = new Sprite(texture);
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        background.flip(false, true);
    }

    protected abstract Texture computeBackground();

    private void createTableButtons() {
        skin = new Skin(Gdx.files.internal("arcade.json"));
        createButtons();
        createTable();
        addButtonsToTable();
    }

    private void createButtons() {
        buttons = new ArrayList<>();

        var newGameButton = new InteractiveTextButton("NEW GAME", skin, "default", this::onPlay);
        var menuButton = new InteractiveTextButton("MENU", skin, "default", this::onMenu);
        var quitButton = new InteractiveTextButton("QUIT", skin, "default", () -> System.exit(0));

        buttons.add(newGameButton);
        buttons.add(menuButton);
        buttons.add(quitButton);
    }

    private void createTable() {
        table = new Table();
        table.setWidth(Gdx.graphics.getWidth());
        table.align(Align.center | Align.top);
    }

    private void addButtonsToTable() {
        table.padTop(30);
        for (var button : buttons) {
            table.add(button).padBottom(30);
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
    public void interpolate(float alpha) { }

    @Override
    public void render(Graphics g) {
        spriteBatch.begin();

        background.draw(spriteBatch);
        drawScores();

        spriteBatch.end();
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
    public void keyAction() {
        ButtonActionHandler.action(buttons);
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
