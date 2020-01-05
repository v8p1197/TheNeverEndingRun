package it.unisa.theneverendingrun.models.powerup.impl;

import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.models.powerup.AbstractPowerUp;

/**
 * An implementation of the {@link AbstractPowerUp} class that uses predefined constructor.
 * The only one necessaries for this obstacle
 */
public class ForestPowerUp extends AbstractPowerUp {

    /**
     * @see AbstractPowerUp#AbstractPowerUp(Texture)
     */
    public ForestPowerUp(Texture texture) {
        super(texture);
    }
}