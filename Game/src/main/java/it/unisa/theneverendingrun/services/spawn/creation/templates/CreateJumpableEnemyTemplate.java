package it.unisa.theneverendingrun.services.spawn.creation.templates;

import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.services.factories.GameFactory;

public class CreateJumpableEnemyTemplate extends AbstractCreateSpriteTemplate {

    public CreateJumpableEnemyTemplate(GameFactory factory) {
        super(factory);
    }

    @Override
    protected Sprite generate() {
        return factory.createEnemy();
    }
}
