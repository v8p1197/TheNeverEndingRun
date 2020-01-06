package it.unisa.theneverendingrun.models.obstacle;

import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.SpriteDescriptionType;

/**
 *
 * A wrapper for {@link Sprite}
 */
public abstract class AbstractObstacle extends Sprite {

    /* ------------------------------------- CONSTRUCTORS ------------------------------------- */

    /**
     * @see Sprite#Sprite()
     *
     * Create an {@link AbstractObstacle}
     */
    public AbstractObstacle() {
        this(1);
    }

    /**
     * @see Sprite#Sprite(float)
     *
     * Create an {@link AbstractObstacle}
     */
    public AbstractObstacle(float scaleFactor) {
        super(scaleFactor);
    }

    /**
     * @see Sprite#Sprite(float)
     *
     * Create an {@link AbstractObstacle}
     */
    public AbstractObstacle(Texture texture) {
        this(texture, 1);
    }

    /**
     * @see Sprite#Sprite(float)
     *
     * Create an {@link AbstractObstacle}
     */
    public AbstractObstacle(Texture texture, float scaleFactor) {
        super(texture, scaleFactor);
    }

    @Override
    public SpriteDescriptionType getName() {
        return SpriteDescriptionType.OBSTACLE;
    }
}
