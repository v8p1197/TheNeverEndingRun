package it.unisa.theneverendingrun.services.spawn.creation;

import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.SpriteType;
import it.unisa.theneverendingrun.services.factories.GameFactory;

public class EnemyCreationTemplate extends AbstractCreationTemplate {

    private final GameFactory factory;

    public EnemyCreationTemplate(GameFactory factory) {
        this.factory = factory;
    }

    @Override
    protected SpriteType getType() {
        return SpriteType.JUMPABLE;
    }

    @Override
    protected Sprite getSprite(SpriteType spriteType, float maxHeight, float maxWidth) {
        return factory.createEnemy(maxHeight);
    }

}