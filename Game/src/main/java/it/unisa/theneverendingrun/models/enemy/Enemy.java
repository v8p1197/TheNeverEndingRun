package it.unisa.theneverendingrun.models.enemy;

import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.models.Sprite;

public abstract class Enemy extends Sprite {

    Enemy(Texture texture, int srcX, int srcY) {
        super(texture);
        setX(srcX);
        setY(srcY);
    }

    abstract void generateDimensions();

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
    }


}
