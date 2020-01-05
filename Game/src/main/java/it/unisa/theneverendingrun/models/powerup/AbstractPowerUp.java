package it.unisa.theneverendingrun.models.powerup;

import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.models.Sprite;

public abstract class AbstractPowerUp extends Sprite {

    /* ------------------------------------- CONSTRUCTORS ------------------------------------- */

    /**
     * @see Sprite#Sprite()
     * <p>
     * Create an {@link AbstractPowerUp}
     */
    public AbstractPowerUp() {
        this(1);
    }

    /**
     * @see Sprite#Sprite(float)
     * <p>
     * Create an {@link AbstractPowerUp}
     */
    public AbstractPowerUp(float scaleFactor) {
        super(scaleFactor);
    }

    /**
     * @see Sprite#Sprite(float)
     * <p>
     * Create an {@link AbstractPowerUp}
     */
    public AbstractPowerUp(Texture texture) {
        this(texture, 1);
    }

    /**
     * @see Sprite#Sprite(float)
     * <p>
     * Create an {@link AbstractPowerUp}
     */
    public AbstractPowerUp(Texture texture, float scaleFactor) {
        super(texture, scaleFactor);
    }
}
