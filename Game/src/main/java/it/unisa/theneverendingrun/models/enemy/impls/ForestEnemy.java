package it.unisa.theneverendingrun.models.enemy.impls;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import it.unisa.theneverendingrun.models.enemy.AbstractEnemy;
import it.unisa.theneverendingrun.models.enemy.EnemyAnimationType;

import java.util.Map;

/**
 *
 * An implementation of the {@link AbstractEnemy} class that uses predefined constructor.
 * The only one necessaries for this enemy
 */
public class ForestEnemy extends AbstractEnemy {

    /**
     *
     * @see AbstractEnemy#AbstractEnemy(Map)
     */
    public ForestEnemy(Map<EnemyAnimationType, Animation<TextureRegion>> animations) {
        super(animations);
    }
}
