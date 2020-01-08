package it.unisa.theneverendingrun.services.spawn.creation.templates;

import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.services.factories.GameFactory;
import it.unisa.theneverendingrun.services.spawn.SpriteType;

public class CreateJumpableObstacleTemplate extends AbstractCreateSpriteTemplate {

    public CreateJumpableObstacleTemplate(GameFactory factory) {
        super(factory);
    }

    @Override
    protected Sprite generate() {
        return factory.createObstacle(SpriteType.JUMPABLE);
    }
}
