package it.unisa.theneverendingrun.models.powerup.impls;

import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.powerup.AbstractPowerUp;
import it.unisa.theneverendingrun.models.powerup.PowerUpType;

/**
 *
 * An implementation of {@link AbstractPowerUp}
 */
public class Shield extends AbstractPowerUp {

    /**
     *
     * see {@link AbstractPowerUp#AbstractPowerUp(Sprite)}
     *
     * @param sprite the original sprite
     */
    public Shield(Sprite sprite) {
        super(sprite);
    }

    /**
     * @return the the type of the powerup
     *
     * @see PowerUpType
     */
    @Override
    public PowerUpType getPowerUpType() {
        return PowerUpType.SHIELD;
    }
}
