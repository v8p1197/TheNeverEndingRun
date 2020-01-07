package it.unisa.theneverendingrun.models.powerup.impls;

import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.models.Visitor;
import it.unisa.theneverendingrun.models.obstacle.Obstacle;
import it.unisa.theneverendingrun.models.powerup.AbstractPowerUp;
import it.unisa.theneverendingrun.models.powerup.PowerUpType;

public class MultiplierPowerUp extends AbstractPowerUp {

    /**
     * @see AbstractPowerUp#AbstractPowerUp(Texture, float)
     * <p>
     * Create an {@link Obstacle}
     */
    public MultiplierPowerUp(Texture texture) {
        this(texture, 1);
    }

    /**
     * @see AbstractPowerUp#AbstractPowerUp(Texture, float)
     * <p>
     * Create an {@link Obstacle}
     */
    public MultiplierPowerUp(Texture texture, float scaleFactor) {
        super(texture, scaleFactor);
    }

    /**
     * @return the the type of the powerup
     * @see PowerUpType
     */
    @Override
    public PowerUpType getPowerUpType() {
        return PowerUpType.MULTIPLIER;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitPowerUp(this);
    }
}
