package it.unisa.theneverendingrun.models.powerup.impl;

import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.models.powerup.AbstractPowerUp;

/**
 * An implementation of the {@link AbstractPowerUp} class that uses predefined constructor.
 * The only one necessaries for this obstacle
 */
public class Shield extends AbstractPowerUp {

    /**
     * @see AbstractPowerUp#AbstractPowerUp(Texture)
     */
    public Shield(Texture texture) {
        super(texture);
    }
}