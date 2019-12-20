package it.unisa.theneverendingrun.models.spawnables.enemy.impls;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import it.unisa.theneverendingrun.data.AnimationTypes;
import it.unisa.theneverendingrun.models.spawnables.enemy.Enemy;

import java.util.Map;

public class ForestEnemy extends Enemy {

    /**
     *
     * Call the super
     * Forest Enemy don't need texture. At object creation a region is setted
     */
    public ForestEnemy(Map<AnimationTypes, Animation<TextureRegion>> animations, float jumpHeight, float slideDistance) {
        super(animations, jumpHeight, slideDistance);
    }
}
