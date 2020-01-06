package it.unisa.theneverendingrun.models.powerup.impls;

import it.unisa.theneverendingrun.models.powerup.PowerUp;
import it.unisa.theneverendingrun.models.powerup.PowerUpType;

public class Shield extends PowerUp {

    @Override
    public PowerUpType getType() {
        return PowerUpType.SHIELD;
    }
}
