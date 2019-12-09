package it.unisa.theneverendingrun;

import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.factory.ForestFactory;
import it.unisa.theneverendingrun.factory.RunFactory;
import it.unisa.theneverendingrun.models.hero.Hero;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;


public class GameEngine extends BasicGame {

    public static final String GAME_IDENTIFIER = "it.unisa.theneverendingrun";
    private HandlingInput input;

    private Hero hero;


    @Override
    public void initialise() {
        input = new HandlingInput();
    }

    @Override
    public void update(float delta) {
        input.getKeyWASD(hero);
    }

    @Override
    public void interpolate(float alpha) {
    }

    @Override
    public void render(Graphics g) {
    }
}
