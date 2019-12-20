package it.unisa.theneverendingrun.models.spawnables.obstacle.impls;

import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.models.spawnables.obstacle.Obstacle;

public class ForestObstacle extends Obstacle {

    /**
     *
     * It call the super. The forest obstacle don't need the scale factor. (1 no scale)
     */
    public ForestObstacle(Texture texture, float jumpHeight, float slideDistance) {
        super(texture, jumpHeight, slideDistance);
    }
}
