package it.unisa.theneverendingrun.models.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.services.EnemyAnimation;
import it.unisa.theneverendingrun.utilities.MathUtils;

public abstract class AbstractEnemy extends Sprite {

    /**
     * Bottom-left original x coordinate, i.e. where the enemy appears when it's created
     */
    private float groundX;

    /**
     * Bottom-left original y coordinate, i.e. where the enemy appears when it's created
     */
    private float groundY;

    /**
     * The height of the enemy
     */
    private float standardHeight;

    /**
     * STRATEGY PATTERN APPLICATION
     *  The animation to give to the enemy (depending on the collision)
     */
    private EnemyAnimation enemyAnimation;

    /**
     * Enemy constructor. Sets its bottom-left coordinates and speed, while its horizontal velocity is set to 0
     *
     * @param x     bottom-left x coordinate
     * @param y     bottom-left y coordinate
     */

    protected AbstractEnemy(Texture texture, float x, float y) {
        super(texture);
        setX(x);
        setY(y);

        setEnemyAnimation(new EnemyIdle());
        setAnimations();
    }

    private Animation animation;
    private float deltaTime;


    public void setAnimations() {
        animation =  enemyAnimation.setAnimation();

    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
    }

    public void updateDelta(float delta) {
        deltaTime += delta;
    }

    public void updateImageFrame() {

        var frame = animation.getKeyFrame(deltaTime, true);
        var newFrame = new TextureRegion((Texture) frame);
        setRegion(newFrame);
    }

    /* ------------------------------------- GETTERS ------------------------------------- */

    /**
     * groundX getter
     *
     * @return the enemy bottom-left original x coordinate
     */
    public float getGroundX() {
        return groundX;
    }

    /**
     * groundY getter
     *
     * @return the enemy bottom-left original y coordinate
     */
    public float getGroundY() {
        return groundY;
    }

    /**
     * Checks if the enemy is above the ground, i.e. its current y coordinate is above its original y coordinate
     *
     * @return true if the enemy is above the ground, false otherwise
     */
    boolean isAboveGround() {
        return getY() - getGroundY() > MathUtils.DELTA;
    }

    /**
     *  enemyAnimation getter
     * @return the current EnemyAnimation object
     */
    public EnemyAnimation getEnemyAnimation() {
        return enemyAnimation;
    }

    /* ------------------------------------- SETTERS ------------------------------------- */
    /**
     *  enemyAnimation setter
     * @param enemyAnimation is the new EnemyAnimation object to set
     */
    public void setEnemyAnimation(EnemyAnimation enemyAnimation) {
        this.enemyAnimation = enemyAnimation;
    }
}

