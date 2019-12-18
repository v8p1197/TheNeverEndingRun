package it.unisa.theneverendingrun.models.hero;

import com.badlogic.gdx.graphics.Texture;

public class ForestHero extends Hero {

    final static float SCALE_FACTOR = 3.0f;

    private final static Texture texture = new Texture("stand.png");

    public ForestHero(Texture texture, float x, float y) {
        super(texture, x, y);
        setSize(getWidth() * SCALE_FACTOR, getHeight() * SCALE_FACTOR);
        setStandardWidth(getWidth());
        setStandardHeight(getHeight());
    }

    public ForestHero(float baseX, float baseY) {
        this(texture, baseX, baseY);
    }

    /*
    @Override
    public float getWidth() {
        return super.getWidth() * SCALE_FACTOR;
    }

    @Override
    public float getHeight() {
        return super.getHeight() * SCALE_FACTOR;
    }
    */

}
