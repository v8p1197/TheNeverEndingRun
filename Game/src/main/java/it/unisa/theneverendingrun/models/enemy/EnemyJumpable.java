package it.unisa.theneverendingrun.models.enemy;

import com.badlogic.gdx.graphics.Texture;

import java.util.concurrent.ThreadLocalRandom;

public class EnemyJumpable extends AbstractEnemy {


    public EnemyJumpable(Texture texture, int srcX, int srcY, float width, float height) {
        super(texture, srcX, srcY);
        super.setSize(width, height);

    }

}
