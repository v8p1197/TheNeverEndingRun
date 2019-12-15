package it.unisa.theneverendingrun.models.enemy;

import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.models.obstacles.*;

public class EnemyFactory {
    private final float STANDARD_HEIGHT;
    private final float STANDARD_WIDTH;


    private Texture textureGolem;
    private Texture textureWolf;

    public EnemyFactory(float standardHeight, float standardWidth) {
        STANDARD_HEIGHT = standardHeight;
        STANDARD_WIDTH = standardWidth;
        textureGolem = new Texture("images/enemies/Golem.png");
        textureWolf = new Texture("images/enemies/Wolf.png");
    }

    public AbstractEnemy getEnemy(EnemyType type, int srcX, int srcY) throws TypeNotPresentException {
        switch (type) {
            case Golem:
                return new EnemyJumpable(textureGolem, srcX, srcY, (float) (this.STANDARD_WIDTH * 1.5), (float) (this.STANDARD_HEIGHT * 1.5));
            case Wolf:
                return new EnemyJumpable(textureWolf, srcX, srcY, this.STANDARD_HEIGHT, this.STANDARD_WIDTH);
            default:
                throw new IllegalArgumentException("The enemy type requested is not correct");
        }
    }

}