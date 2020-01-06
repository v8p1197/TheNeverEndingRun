package it.unisa.theneverendingrun.models.powerup;

import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.SpriteDescriptionType;

public abstract class PowerUp extends Sprite {

    private PowerUpType type;

    /**
     * @return the sprite description
     */
    @Override
    public SpriteDescriptionType getName() {
        return SpriteDescriptionType.POWER_UP;
    }

    public abstract PowerUpType getType();
}
