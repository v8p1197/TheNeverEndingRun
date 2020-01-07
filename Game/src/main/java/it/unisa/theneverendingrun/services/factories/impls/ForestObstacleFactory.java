package it.unisa.theneverendingrun.services.factories.impls;

import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.models.JumpableSprite;
import it.unisa.theneverendingrun.models.SlidableSprite;
import it.unisa.theneverendingrun.models.SpriteType;
import it.unisa.theneverendingrun.models.obstacle.Obstacle;

public class ForestObstacleFactory {

    private static final Texture SLIDABLE_TEXTURE = new Texture("images/forest/obstacles/slidable.png");
    private static final Texture JUMPABLE_TEXTURE = new Texture("images/forest/obstacles/jumpable.png");
    private static final Texture SLIDABLE_JUMPABLE_TEXTURE = new Texture("images/forest/obstacles/jumpable_slidable.png");

    public ForestObstacleFactory() {
    }

    public Obstacle createObstacle(SpriteType type, float maxHeight, float maxWidth) {
        switch (type) {
            case JUMPABLE:
                return new Obstacle(new JumpableSprite(JUMPABLE_TEXTURE, maxHeight, true));
            case SLIDABLE:
                return new Obstacle(new SlidableSprite(SLIDABLE_TEXTURE, maxWidth, true));
            case JUMPABLE_SLIDABLE:
                return new Obstacle(new JumpableSprite(new SlidableSprite(SLIDABLE_JUMPABLE_TEXTURE, maxWidth, true), maxHeight, true));
            default:
                throw new IllegalArgumentException("Obstacle type is not valid");
        }
    }
}
