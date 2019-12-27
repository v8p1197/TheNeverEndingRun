package it.unisa.theneverendingrun.models.spawnables.obstacle.impls;

import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.models.spawnables.obstacle.AbstractObstacle;

/**
 *
 * An implementation of the {@link AbstractObstacle} class that uses predefined constructor.
 * The only one necessaries for this obstacle
 */
public class ForestObstacle extends AbstractObstacle {

    /**
     *
     * @see AbstractObstacle#AbstractObstacle(Texture, float, float) 
     *
     */
    public ForestObstacle(Texture texture, float jumpHeight, float slideDistance) {
        super(texture, jumpHeight, slideDistance);
    }
}
