package it.unisa.theneverendingrun.models.powerup;

import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.SpriteImplType;

/**
 *
 * A wrapper for {@link Sprite}
 */
public abstract class AbstractPowerUp extends Sprite {

    /* ------------------------------------- CONSTRUCTORS ------------------------------------- */

    /**
     *
     * Create an {@link AbstractPowerUp} starting from anther {@link Sprite}
     *
     * @param sprite the original sprite
     */
    public AbstractPowerUp(Sprite sprite) {
        super(sprite.getScaleFactor());
        set(sprite);
    }

    /**
     *
     * @return the sprite implementation type
     */
    @Override
    public SpriteImplType getSpriteImplType() {
        return SpriteImplType.POWER_UP;
    }

    /**
     *
     * @see PowerUpType
     *
     * @return the the type of the powerup
     */
    public abstract PowerUpType getPowerUpType();
}
