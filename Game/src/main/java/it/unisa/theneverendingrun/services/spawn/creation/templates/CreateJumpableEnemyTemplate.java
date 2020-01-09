package it.unisa.theneverendingrun.services.spawn.creation.templates;

import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.services.factories.GameFactory;
import it.unisa.theneverendingrun.services.spawn.creation.resize.HeightResizeStrategy;

public class CreateJumpableEnemyTemplate extends AbstractCreateSpriteTemplate {

    private float maxValue;

    public CreateJumpableEnemyTemplate(GameFactory factory, float maxHeight) {
        super(factory);
        this.maxValue = maxHeight;
    }

    @Override
    protected void resize(Sprite sprite) {
        resize(sprite, maxValue, new HeightResizeStrategy());
    }

    @Override
    protected Sprite generate() {
        return factory.createEnemy();
    }
}
