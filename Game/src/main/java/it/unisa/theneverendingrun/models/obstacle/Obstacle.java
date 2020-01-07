package it.unisa.theneverendingrun.models.obstacle;

import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.Visitor;

/**
 *
 * A wrapper for {@link Sprite}
 */
public class Obstacle extends Sprite {

    /* ------------------------------------- CONSTRUCTORS ------------------------------------- */

    /**
     * @see Sprite#Sprite(float)
     * <p>
     * Create an {@link Obstacle}
     */
    public Obstacle(Texture texture) {
        this(texture, 1);
    }

    /**
     * @see Sprite#Sprite(float)
     * <p>
     * Create an {@link Obstacle}
     */
    public Obstacle(Texture texture, float scaleFactor) {
        super(texture, scaleFactor);
    }



    /* ------------------------------------- VISITOR ------------------------------------- */

    /**
     * @param visitor the action to perform
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visitObstacle(this);
    }


}
