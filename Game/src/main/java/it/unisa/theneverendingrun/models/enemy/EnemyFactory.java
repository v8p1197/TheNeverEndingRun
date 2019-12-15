package it.unisa.theneverendingrun.models.enemy;

import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.models.obstacles.*;

public class EnemyFactory {
    private final float MAX_JUMP_HEIGHT;
    private final float MAX_WIDTH;

    private Texture textureEnemy;

    public EnemyFactory(float maxJumpHeight, float maxWidth) {
        MAX_JUMP_HEIGHT = maxJumpHeight;
        MAX_WIDTH = maxWidth;
        textureEnemy = new Texture("images/canepazzo.png");
    }

    public Enemy getEnemy(ObstacleType type, int srcX, int srcY) throws TypeNotPresentException {
        return new EnemyJumpable(textureEnemy, srcX, srcY, MAX_JUMP_HEIGHT, MAX_WIDTH);
    }

}
