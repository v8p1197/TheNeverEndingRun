package it.unisa.theneverendingrun.models.powerup;

import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.Visitor;

/**
 * A wrapper for {@link Sprite}
 */
public abstract class AbstractPowerUp extends Sprite {

    /* ------------------------------------- CONSTRUCTORS ------------------------------------- */

    /**
     * @see Sprite#Sprite(Texture, float)
     * <p>
     * Create an {@link AbstractPowerUp}
     */
    public AbstractPowerUp(Texture texture) {
        this(texture, 1);
    }

    /**
     * @see Sprite#Sprite(Texture, float)
     * <p>
     * Create an {@link AbstractPowerUp}
     */
    public AbstractPowerUp(Texture texture, float scaleFactor) {
        super(texture, scaleFactor);
    }


    /**
     * Create an {@link AbstractPowerUp} starting from anther {@link Sprite}
     *
     * @param sprite the original sprite
     */
    public AbstractPowerUp(Sprite sprite) {
        super(sprite.getScaleFactor());
        set(sprite);
    }

    /**
     * @return the the type of the powerup
     * @see PowerUpType
     */
    public abstract PowerUpType getPowerUpType();

    @Override
    public void accept(Visitor visitor) {
        visitor.visitPowerUp(this);
    }
}
