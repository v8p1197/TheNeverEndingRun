package it.unisa.theneverendingrun.services.spawn.creation.templates;

import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.SpriteType;
import it.unisa.theneverendingrun.services.factories.GameFactory;
import it.unisa.theneverendingrun.services.spawn.creation.resize.HeightResizeStrategy;
import it.unisa.theneverendingrun.services.spawn.creation.resize.WidthResizeStrategy;

public class CreateJumpableObstacleTemplate extends AbstractCreateSpriteTemplate {

    private float maxHeight;
    private float maxWidth;

    public CreateJumpableObstacleTemplate(GameFactory factory, float maxWidth, float maxHeight) {
        super(factory);
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
    }

    @Override
    protected void resize(Sprite sprite) {
        alwaysResize(sprite, maxHeight, new HeightResizeStrategy());
        //alwaysResize(sprite, sprite.getWidth()*maxWidth, new WidthResizeStrategy());
    }

    @Override
    protected Sprite generate() {
        return factory.createObstacle(SpriteType.JUMPABLE);
    }
}
