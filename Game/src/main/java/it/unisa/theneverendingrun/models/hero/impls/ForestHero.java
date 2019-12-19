package it.unisa.theneverendingrun.models.hero.impls;

import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.models.hero.Hero;

public class ForestHero extends Hero {

    //experimental for this implementation
    private final static float SCALE_FACTOR = 3.0f;

    private final static Texture texture = new Texture("images/forest/hero/stand/stand_temp.png");

    public ForestHero(float x, float y) {
        super(texture, SCALE_FACTOR, x, y);
    }
}
