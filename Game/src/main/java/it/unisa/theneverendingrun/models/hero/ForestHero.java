package it.unisa.theneverendingrun.models.hero;

import com.badlogic.gdx.graphics.Texture;

public class ForestHero extends Hero {

    private final static float SCALE_FACTOR = 1.8F;

    private final static Texture texture = new Texture("stand.png");

    public ForestHero(Texture texture, float x, float y) {
        super(texture, x, y);
        setSize(getWidth(), getHeight());
    }

    public ForestHero(float baseX, float baseY) {
        super(texture, baseX, baseY);
    }

    @Override
    public float getWidth() {
        return super.getWidth() * SCALE_FACTOR;
    }

    @Override
    public float getHeight() {
        return super.getHeight() * SCALE_FACTOR;
    }
}
