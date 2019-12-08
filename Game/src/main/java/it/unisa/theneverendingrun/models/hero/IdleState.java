package it.unisa.theneverendingrun.models.hero;


/**
 * In this state the hero is neither jumping or sliding
 */
public class IdleState extends HeroMoveState {

    /**
     * Sets the hero for holding its idle state, setting his jumping and sliding variables to false
     *
     * @param hero the hero which idle state is held
     */
    public IdleState(Hero hero) {
        super(hero);
    }

    /**
     * The reaction when the state tries to change from Idle to Idle
     */
    @Override
    public void onIdle() {
    }

    /**
     * The reaction when the state tries to change from Idle to Jump: the hero actually changes its state to Jump
     */
    @Override
    public void onJump() {
        hero.changeMoveState(new JumpState(hero));
    }

    /**
     * The reaction when the state tries to change from Idle to Slide: the hero actually changes its state to Slide
     */
    @Override
    public void onSlide() {
        hero.changeMoveState(new SlideState(hero));
    }
}
