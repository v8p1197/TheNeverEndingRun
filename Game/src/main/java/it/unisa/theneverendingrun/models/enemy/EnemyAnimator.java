package it.unisa.theneverendingrun.models.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class EnemyAnimator implements EnemyFightStateListener {

    private final int ANIMATION_FRAME_NUMBER = 13;
    private TextureRegion[] tVect = new TextureRegion[ANIMATION_FRAME_NUMBER];
    private float stateTime = 0;
    private Animation animation;

    @Override
    public void update(EnemyEventType eventType, AbstractEnemy enemy) {
        if (enemy.isAttacking()) {
            animation = getAnimation(enemy, "attack");
        } else if (enemy.isDead()) {
            // set death animation, to implement
        } else {
            // set idle animation
            animation = getAnimation(enemy, "idle");
        }
    }

    private Animation getAnimation(AbstractEnemy enemy, String stateDescription) {
        String commonPath = enemy.getCommonPath();
        for (int i = 1; i <= ANIMATION_FRAME_NUMBER; i++) {
            TextureRegion t = new TextureRegion(new Texture(commonPath + stateDescription + "_" + i + ".png"));
            tVect[i - 1] = t;
        }
        return new Animation(0.1f, tVect);
    }

    public void updateImageFrame(AbstractEnemy enemy) {
        var looping = false;
        if (enemy.getFightState() instanceof EnemyIdleState) {
            looping = true;
        }
        stateTime += Gdx.graphics.getDeltaTime();
        var frame = animation.getKeyFrame(stateTime, looping);
        var newFrame = new TextureRegion((TextureRegion) frame);
        enemy.setRegion(newFrame);
    }

    public Animation getAnimation() {
        return animation;
    }

    public float getStateTime() {
        return stateTime;
    }

    public void resetStateTime() {
        this.stateTime = 0;
    }

    public void updateStateTime(float deltaTime) {
        this.stateTime += deltaTime;
    }
}
