package it.unisa.theneverendingrun.models.hero;

/**
 * In this state the hero is dead
 */
public class DeadState extends HeroMoveState {

    /**
     * Sets the hero for holding its move state
     *
     * @param hero the hero which move state is held
     */
    public DeadState(Hero hero) {
        super(hero);
    }

    /**
     * Updates the hero bottom-left coordinates and sprite.
     * When the hero is dead, he must not move
     */
    @Override
    public void move() {
    }

    /**
     * The reaction when the state tries to change from Dead to Idle.
     * Actually, the hero is dead so doesn't change his state
     */
    @Override
    public void onIdle() {
    }

    /**
     * The reaction when the state tries to change from Dead to Jump.
     * Actually, the hero is dead so doesn't change his state
     */
    @Override
    public void onJump() {
    }

    /**
     * The reaction when the state tries to change from Dead to Slide.
     * Actually, the hero is dead so doesn't change his state
     */
    @Override
    public void onSlide() {
    }

    /**
     * The reaction when the state tries to change from Dead to Fall.
     * Actually, the hero is dead so doesn't change his state
     */
    @Override
    public void onFall() {
    }

    /**
     * The reaction when the state tries to change from Dead to Dead: the reaction is null
     */
    @Override
    public void onDie() {
    }

    @Override
    public String toString() {
        return "dead";
    }
}
