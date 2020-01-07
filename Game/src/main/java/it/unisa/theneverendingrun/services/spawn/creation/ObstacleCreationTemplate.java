package it.unisa.theneverendingrun.services.spawn.creation;

import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.SpriteType;

public class ObstacleCreationTemplate extends AbstractCreationTemplate {

    // TODO delete this method
    @Override
    protected Sprite getSprite(SpriteType spriteType, float maxHeight, float maxWidth) {
        return null;
    }

   /* private final GameFactory factory;

    protected ObstacleCreationTemplate(GameFactory factory) {
        this.factory = factory;
    }

    @Override
    protected Sprite getSprite(SpriteType spriteType, float maxHeight, float maxWidth) {
        return factory.createObstacle(spriteType, maxHeight, maxWidth);
    }*/
}
