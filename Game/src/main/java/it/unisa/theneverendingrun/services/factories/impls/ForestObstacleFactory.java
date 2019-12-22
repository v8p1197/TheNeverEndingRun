package it.unisa.theneverendingrun.services.factories.impls;

import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.models.spawnables.Spawnable;
import it.unisa.theneverendingrun.models.spawnables.decorators.JumpableSpawnable;
import it.unisa.theneverendingrun.models.spawnables.decorators.SlidableSpawnable;
import it.unisa.theneverendingrun.models.spawnables.obstacle.impls.ForestObstacle;
import it.unisa.theneverendingrun.services.factories.SpawnableFactory;

public class ForestObstacleFactory implements SpawnableFactory {

    private static final Texture SLIDABLE_TEXTURE = new Texture("images/forest/obstacles/slidable.png");
    private static final Texture JUMPABLE_TEXTURE = new Texture("images/forest/obstacles/jumpable.png");
    private static final Texture SLIDABLE_JUMPABLE_TEXTURE = new Texture("images/forest/obstacles/jumpable_slidable.png");

    @Override
    public Spawnable createSlidableSpawnable(float jumpHeight, float slideDistance) {
        return new SlidableSpawnable(new ForestObstacle(SLIDABLE_TEXTURE, jumpHeight, slideDistance));
    }

    @Override
    public Spawnable createJumpableSpawnable(float jumpHeight, float slideDistance) {
        return new JumpableSpawnable(new ForestObstacle(JUMPABLE_TEXTURE, jumpHeight, slideDistance));
    }

    @Override
    public Spawnable createJumpableSlideableSpawnable(float jumpHeight, float slideDistance) {
        return new JumpableSpawnable(new SlidableSpawnable(new ForestObstacle(SLIDABLE_JUMPABLE_TEXTURE, jumpHeight, slideDistance)));
    }
}
