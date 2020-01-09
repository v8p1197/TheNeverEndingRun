package it.unisa.theneverendingrun.services.spawn.creation.templates;

import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.SpriteType;
import it.unisa.theneverendingrun.services.factories.GameFactory;
import it.unisa.theneverendingrun.services.spawn.creation.resize.HeightResizeStrategy;
import it.unisa.theneverendingrun.services.spawn.creation.resize.WidthResizeStrategy;

public class CreateSlidableObstacleTemplate extends AbstractCreateSpriteTemplate {

    private float maxValue;
    private float maxHeight;

    public CreateSlidableObstacleTemplate(GameFactory factory, float maxWidth, float maxHeight) {
        super(factory);
        this.maxValue = maxWidth;
        this.maxHeight = maxHeight;
    }

    @Override
    protected void resize(Sprite sprite) {
        alwaysResize(sprite, maxValue, new WidthResizeStrategy());
        resizeTo(sprite, maxHeight, new HeightResizeStrategy());
    }

    @Override
    protected Sprite generate() {
        return factory.createObstacle(SpriteType.SLIDABLE);
    }
}
