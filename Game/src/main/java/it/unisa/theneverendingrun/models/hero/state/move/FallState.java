package it.unisa.theneverendingrun.models.hero.state.move;

import it.unisa.theneverendingrun.models.hero.Hero;
import it.unisa.theneverendingrun.models.hero.state.HeroMoveState;

/**
 * In this state the hero is falling down
 */
public class FallState extends HeroMoveState {

    /**
     * A variable representing the fall step the hero actually is in
     */
    private int gravity;

    /**
     * Sets the hero for holding its move state
     *
     * @param hero the hero which move state is held
     */
    public FallState(Hero hero) {
        this(hero, 1);
    }

    public FallState(Hero hero, int gravity) {
        super(hero);
        this.gravity = gravity;
    }


    /**
     * Updates the hero bottom-left coordinates and sprite
     */
    @Override
    public void move() {
        super.move();

        fall();
    }

    /**
     * Performs a fall step, updating the hero bottom-left y coordinate according to a parabola-like formula
     */
    private void fall() {
        var newY = (float) Math.max(hero.getGroundY(), hero.getY() - (gravity * gravity) * hero.getJumpCoefficient());
        hero.setY(newY);
        gravity++;
        if (!hero.isAboveGround()) hero.changeMoveState(new IdleState(hero));
    }

    /**
     * The reaction when the state tries to change from Fall to Idle: the hero does change its state to Idle
     */
    @Override
    public void onIdle() {
        hero.changeMoveState(new IdleState(hero));
    }

    /**
     * The reaction when the state tries to change from Fall to Jump.
     * Actually, the hero keeps falling and doesn't change his state
     */
    @Override
    public void onJump() { }

    /**
     * The reaction when the state tries to change from Fall to Slide.
     * Actually, the hero keeps falling and doesn't change his state
     */
    @Override
    public void onSlide() {
    }

    /**
     * The reaction when the state tries to change from Fall to Fall: the reaction is null
     */
    @Override
    public void onFall() {
    }

    /**
     * The reaction when the state tries to change from Fall to Dead: the hero does die
     */
    @Override
    public void onDie() {
        hero.changeMoveState(new DeadState(hero));
    }

    @Override
    public String toString() {
        return "falling " + hero.getFacingState().toString();
    }
}
