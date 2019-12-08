package it.unisa.theneverendingrun.models.obstacles;

import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.models.Sprite;

public abstract class AbstractObstacle extends Sprite {

    public AbstractObstacle(Texture texture, int srcX, int srcY) {
        super(texture);
        setX(srcX);
        setY(srcY);
    }

    public abstract void generateDimensions();
}
