package it.unisa.theneverendingrun.models.hero;

import com.badlogic.gdx.graphics.Texture;

public class ForestHero extends Hero {

    private final static Texture texture = new Texture("images/pape.png");

    public ForestHero(float baseX, float baseY) {
        super(texture, baseX, baseY);
    }
}
