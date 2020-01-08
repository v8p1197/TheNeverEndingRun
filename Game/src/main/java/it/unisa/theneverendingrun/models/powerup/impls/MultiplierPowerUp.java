package it.unisa.theneverendingrun.models.powerup.impls;

import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.models.Visitor;
import it.unisa.theneverendingrun.models.obstacle.Obstacle;
import it.unisa.theneverendingrun.models.powerup.AbstractPowerUp;
import it.unisa.theneverendingrun.models.powerup.PowerUpType;

public class MultiplierPowerUp extends AbstractPowerUp {

    /**
     *
     * The multiplier value
     */
    private final int value;

    /**
     *
     * @see AbstractPowerUp#AbstractPowerUp(Texture, float)
     *
     * @param value the value of the multiplier
     */
    public MultiplierPowerUp(Texture texture, int value) {
        this(texture, 1, value);
    }

    /**
     *
     * @see AbstractPowerUp#AbstractPowerUp(Texture, float)

     * @param value the value of the multiplier
     */
    public MultiplierPowerUp(Texture texture, float scaleFactor, int value) {
        super(texture, scaleFactor);
        this.value = value;
    }

    /**
     * @return the the type of the powerup
     * @see PowerUpType
     */
    @Override
    public PowerUpType getPowerUpType() {
        return PowerUpType.MULTIPLIER;
    }

    /**
     *
     * @return the multiplier value
     */
    public int getValue() {
        return value;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitPowerUp(this);
    }

}
