package it.unisa.theneverendingrun.services.spawn.creation.templates;

import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.services.factories.GameFactory;
import it.unisa.theneverendingrun.services.spawn.creation.resize.HeightResizeStrategy;

public class CreateJumpablePowerUpTemplate extends AbstractCreateSpriteTemplate {

    private float maxValue;

    public CreateJumpablePowerUpTemplate(GameFactory factory, float maxValue) {
        super(factory);
        this.maxValue = maxValue;
    }

    @Override
    protected void resize(Sprite sprite) {
        resize(sprite, maxValue, new HeightResizeStrategy());
    }

    @Override
    protected Sprite generate() {
        return factory.createPowerUp();
    }
}
