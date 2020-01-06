package it.unisa.theneverendingrun.models.hero.state.move;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import it.unisa.theneverendingrun.models.hero.AbstractHero;
import it.unisa.theneverendingrun.models.hero.HeroStateType;
import it.unisa.theneverendingrun.models.hero.state.HeroMoveState;

import java.util.Map;

/**
 *
 * In this state the hero is falling down
 */
public class FallState extends HeroMoveState {

    /**
     *
     * A variable representing the fall step the hero actually is in
     */
    private int gravity;

    /**
     *
     * @see HeroMoveState#HeroMoveState(AbstractHero, Map)
     *
     * @see FallState#gravity
     *
     * Set gravity to 1
     */
    public FallState(AbstractHero hero, Map<HeroStateType, Animation<TextureRegion>> animations) {
        this(hero, animations, 1);
    }

    /**
     *
     * @see HeroMoveState#HeroMoveState(AbstractHero, Map)
     *
     * @see FallState#gravity
     *
     * Set gravity to 1
     *
     * @param gravity the fall step the hero actually is in
     */
    public FallState(AbstractHero hero, Map<HeroStateType, Animation<TextureRegion>> animations, int gravity) {
        super(hero, animations);
        this.gravity = gravity;
    }


    /**
     *
     * @see HeroMoveState#move()
     * @see FallState#fall()
     *
     * Actually, the hero have to fall
     */
    @Override
    public void move() {
        super.move();
        fall();
    }

    /**
     *
     * Performs a fall step, updating the hero bottom-left y coordinate according to a parabola-like formula
     */
    private void fall() {
        var newY = (float) Math.max(hero.getGroundY(), hero.getY() - (gravity * gravity) * hero.getJumpCoefficient());
        hero.setY(newY);
        gravity++;
        if (!hero.isAboveGround()) {
            onIdle();
        }
    }

    /**
     *
     * @see HeroMoveState#onStand()
     *
     * The reaction when the state tries to change from Fall to Stand
     * Actually, the hero does change its state to Stand
     */
    @Override
    public void onStand() {
        hero.changeMoveState(new StandState(hero, animations));
    }

    /**
     *
     * @see HeroMoveState#onJump()
     *
     * The reaction when the state tries to change from Fall to Jump.
     * Actually, the hero keeps falling and doesn't change his state
     */
    @Override
    public void onJump() { }

    /**
     *
     * @see HeroMoveState#onSlide()

     * The reaction when the state tries to change from Fall to Slide.
     * Actually, the hero keeps falling and doesn't change his state
     */
    @Override
    public void onSlide() {
    }

    /**
     *
     * @see HeroMoveState#onFall()
     *
     * The reaction when the state tries to change from Fall to Fall
     * Actually, the hero keeps falling so doesn't change his state
     */
    @Override
    public void onFall() {
    }

    /**
     *
     * @see HeroMoveState#onDie()
     *
     * The reaction when the state tries to change from Fall to Dead
     * Actually, the hero does die.
     */
    @Override
    public void onDie() {
        hero.changeMoveState(new DeadState(hero, animations));
    }

    /**
     *
     * @see HeroMoveState#onRun()
     *
     * The reaction when the state tries to change from Fall to Run
     * Actually, the hero does run.
     */
    @Override
    public void onRun() { hero.changeMoveState(new RunningState(hero, animations)); }

    /**
     *
     * @see HeroMoveState#computeStateType()
     * @return the current hero animation type based on the current state
     */
    @Override
    protected HeroStateType computeStateType() {
        return HeroStateType.FALL;
    }
}
