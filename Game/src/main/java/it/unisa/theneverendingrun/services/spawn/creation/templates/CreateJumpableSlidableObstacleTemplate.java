package it.unisa.theneverendingrun.services.spawn.creation.templates;

import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.SpriteType;
import it.unisa.theneverendingrun.services.factories.GameFactory;
import it.unisa.theneverendingrun.services.spawn.creation.resize.HeightResizeStrategy;
import it.unisa.theneverendingrun.services.spawn.creation.resize.WidthResizeStrategy;

public class CreateJumpableSlidableObstacleTemplate extends AbstractCreateSpriteTemplate {

    private float maxWidth;
    private float maxHeight;

    public CreateJumpableSlidableObstacleTemplate(GameFactory factory, float maxWidth, float maxHeight) {
        super(factory);
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
    }

    @Override
    protected void resize(Sprite sprite) {
        alwaysResize(sprite, maxHeight, new HeightResizeStrategy());
        alwaysResize(sprite, maxWidth, new WidthResizeStrategy());
    }

    @Override
    protected Sprite generate() {
        return factory.createObstacle(SpriteType.JUMPABLE_SLIDABLE);
    }
}
