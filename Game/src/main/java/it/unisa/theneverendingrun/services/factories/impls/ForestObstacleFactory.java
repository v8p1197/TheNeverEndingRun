package it.unisa.theneverendingrun.services.factories.impls;

import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.models.JumpableSprite;
import it.unisa.theneverendingrun.models.SlidableSprite;
import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.obstacle.AbstractObstacle;
import it.unisa.theneverendingrun.models.obstacle.impls.ForestObstacle;
import it.unisa.theneverendingrun.services.factories.SpriteFactory;

public class ForestObstacleFactory implements SpriteFactory {

    private static final Texture SLIDABLE_TEXTURE = new Texture("images/forest/obstacles/slidable.png");
    private static final Texture JUMPABLE_TEXTURE = new Texture("images/forest/obstacles/jumpable.png");
    private static final Texture SLIDABLE_JUMPABLE_TEXTURE = new Texture("images/forest/obstacles/jumpable_slidable.png");

    @Override
    public Sprite createSlidableSprite(float maxWidth) {
        return new SlidableSprite(new ForestObstacle(SLIDABLE_TEXTURE), maxWidth);
    }

    @Override
    public Sprite createJumpableSprite(float maxHeight) {
        return new JumpableSprite(new ForestObstacle(JUMPABLE_TEXTURE), maxHeight);
    }

    @Override
    public Sprite createJumpableSlideableSprite(float maxHeight, float maxWidth) {
        return new JumpableSprite(new SlidableSprite(new ForestObstacle(SLIDABLE_JUMPABLE_TEXTURE), maxWidth), maxHeight);
    }
}
