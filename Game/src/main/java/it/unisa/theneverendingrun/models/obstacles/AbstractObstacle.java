package it.unisa.theneverendingrun.models.obstacles;

import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.models.Sprite;

public abstract class AbstractObstacle extends Sprite {


    AbstractObstacle(Texture texture, int srcX, int srcY) {
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
