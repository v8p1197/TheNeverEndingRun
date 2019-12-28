package it.unisa.theneverendingrun.models.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.FileTextureData;
import it.unisa.theneverendingrun.models.Spawnable;
import it.unisa.theneverendingrun.models.hero.Hero;

public abstract class AbstractEnemy extends Spawnable {

    EnemyEventManager events = new EnemyEventManager(EnemyEventType.ENEMY_FIGHT_STATE_CHANGED);

    private EnemyFightState fightState;
    private boolean destroyed = false;

    /**
     * Enemy constructor. Sets its bottom-left coordinates and speed, while its horizontal velocity is set to 0
     *
     * @param x bottom-left x coordinate
     * @param y bottom-left y coordinate
     */

    private EnemyAnimator animator;


    private String commonPath;

    public AbstractEnemy(Texture texture) {
        super(texture);

        this.animator = new EnemyAnimator();
        events.subscribe(EnemyEventType.ENEMY_FIGHT_STATE_CHANGED, animator);

        String texturePath = ((FileTextureData) texture.getTextureData()).getFileHandle().path();
        this.commonPath = texturePath.substring(0, texturePath.length() - 10);

        changeFightState(new EnemyIdleState(this));
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
    }


    /* ------------------------------------- GETTERS ------------------------------------- */


    public EnemyFightState getFightState() {
        return fightState;
    }

    public boolean isAttacking() {
        return getFightState() instanceof EnemyAttackState;
    }

    public boolean isDead() {
        return getFightState() instanceof EnemyDeadState;
    }

    public String getCommonPath() {
        return commonPath;
    }

    public EnemyAnimator getAnimator() {
        return animator;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    /* ------------------------------------- SETTERS ------------------------------------- */


    public void changeFightState(EnemyFightState fightState) {
        this.fightState = fightState;
        animator.resetStateTime();
        events.notify(EnemyEventType.ENEMY_FIGHT_STATE_CHANGED, this);
    }

    public void setDestroyed() {
        destroyed = false;
    }


    /* ------------------------------------- COLLISION ------------------------------------- */

    @Override
    public void beginCollision(Hero hero) {
       /* if(//proiettile && hero.getShield>0){
        destroyed=true;
        hero.minShield();
        */
        this.getFightState().onAttack();
        hero.die();
    }

    @Override
    public void endCollision(Hero hero) {
        this.getFightState().onIdle();
    }

}

