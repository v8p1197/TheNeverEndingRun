package it.unisa.theneverendingrun.models.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FileTextureData;
import it.unisa.theneverendingrun.models.Spawnable;
import it.unisa.theneverendingrun.models.SpawnableTypes;
import it.unisa.theneverendingrun.models.hero.Hero;
import it.unisa.theneverendingrun.services.EnemyAnimation;

public abstract class AbstractEnemy extends Spawnable {

    /**
     * STRATEGY PATTERN APPLICATION
     * The animation to give to the enemy (depending on the collision)
     */
    private EnemyAnimation enemyAnimation;


    private boolean attacking = false;

    /**
     * Enemy constructor. Sets its bottom-left coordinates and speed, while its horizontal velocity is set to 0
     *
     * @param x bottom-left x coordinate
     * @param y bottom-left y coordinate
     */

    private Animation animation;
    private float deltaTime;

    private String path;

    public AbstractEnemy(Texture texture) {
        super(texture);
        String texturePath = ((FileTextureData) texture.getTextureData()).getFileHandle().path();
        this.path = texturePath.substring(0, texturePath.length() - 10);
        setEnemyAnimation(new EnemyIdle());
    }

    public void setAnimations() {
        animation = enemyAnimation.setAnimation(path);
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
        var newFrame = new TextureRegion((TextureRegion) frame);
        setRegion(newFrame);
    }

    /* ------------------------------------- GETTERS ------------------------------------- */

    /**
     * enemyAnimation getter
     *
     * @return the current EnemyAnimation object
     */
    public EnemyAnimation getEnemyAnimation() {
        return enemyAnimation;
    }

    /* ------------------------------------- SETTERS ------------------------------------- */

    /**
     * enemyAnimation setter
     *
     * @param enemyAnimation is the new EnemyAnimation object to set
     */
    public void setEnemyAnimation(EnemyAnimation enemyAnimation) {
        this.enemyAnimation = enemyAnimation;
    }

    private void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    /* ------------------------------------- COLLISION ------------------------------------- */

    @Override
    public void reactToCollision(Hero hero) {

        this.attack();
        //hero.die();

    }

    private void attack() {
        if (!this.isAttacking()) {
            this.setEnemyAnimation(new EnemyAttack());
            this.setAnimations();
            setAttacking(true);
        } else if (this.isAttacking()) {

        }
    }


    private boolean isAttacking() {
        return attacking;
    }
}

