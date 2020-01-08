package it.unisa.theneverendingrun.services.spawn.creation.templates;

import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.services.factories.GameFactory;

public class CreateJumpablePowerUpTemplate extends AbstractCreateSpriteTemplate {

    public CreateJumpablePowerUpTemplate(GameFactory factory) {
        super(factory);
    }

    @Override
    protected Sprite generate() {
        return factory.createPowerUp();
    }
}
