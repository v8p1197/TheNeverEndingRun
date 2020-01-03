package it.unisa.theneverendingrun.models.obstacle.impls;

import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.models.obstacle.AbstractObstacle;

/**
 *
 * An implementation of the {@link AbstractObstacle} class that uses predefined constructor.
 * The only one necessaries for this obstacle
 */
public class ForestObstacle extends AbstractObstacle {

    /**
     *
     * @see AbstractObstacle#AbstractObstacle(Texture)
     */
    public ForestObstacle(Texture texture) {
        super(texture);
    }
}
