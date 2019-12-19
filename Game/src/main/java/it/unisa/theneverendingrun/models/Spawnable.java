package it.unisa.theneverendingrun.models;

import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.models.hero.Hero;

public abstract class Spawnable extends Sprite {

    public Spawnable(Texture texture) {
        super(texture);
    }

    public abstract void beginCollision(Hero hero);

    public abstract void endCollision(Hero hero);


}
