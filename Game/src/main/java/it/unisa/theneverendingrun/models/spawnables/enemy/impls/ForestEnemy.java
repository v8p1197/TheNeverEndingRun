package it.unisa.theneverendingrun.models.spawnables.enemy.impls;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import it.unisa.theneverendingrun.data.AnimationType;
import it.unisa.theneverendingrun.models.spawnables.enemy.AbstractEnemy;

import java.util.Map;

/**
 *
 * An implementation of the {@link AbstractEnemy} class that uses predefined constructor.
 * The only one necessaries for this enemy
 */
public class ForestEnemy extends AbstractEnemy {

    /**
     *
     * @see AbstractEnemy#AbstractEnemy(Map, float, float) 
     *
     */
    public ForestEnemy(Map<AnimationType, Animation<TextureRegion>> animations, float jumpHeight, float slideDistance) {
        super(animations, jumpHeight, slideDistance);
    }
}
