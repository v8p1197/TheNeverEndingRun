package it.unisa.theneverendingrun.models.hero;


/**
 * In this state the hero is jumping
 */
public class JumpState extends HeroMoveState {

    /**
     * Sets the hero for holding its jump state, setting his jumping variable to true
     * and his jump counter variable to its initial value
     *
     * @param hero the hero which jump state is held
     */
    public JumpState(Hero hero) {
        super(hero);

        hero.setJumpCount(hero.getJumpDuration());
    }

    /**
     * Updates the hero bottom-left coordinates and sprite
     */
    @Override
    public void move() {
        super.move();

        jump();
    }

    /**
     * Performs a jump step, updating the hero bottom-left y coordinate according to a parabola-like formula
     */
    private void jump() {
        int jumpCount = hero.getJumpCount();

        if (jumpCount >= -hero.getJumpDuration() || hero.isAboveGround()) {
            int up = jumpCount < 0 ? -1 : 1;
            hero.setY(hero.getY() + (jumpCount * jumpCount) * hero.getJumpCoefficient() * up);
            hero.setJumpCount(jumpCount - 1);
        } else {
            hero.changeMoveState(new IdleState(hero));
        }
    }

    /**
     * The reaction when the state tries to change from Jump to Idle
     */
    @Override
    public void onIdle() {
        hero.changeMoveState(new IdleState(hero));
    }

    /**
     * The reaction when the state tries to change from Jump to Jump: the reaction is null
     */
    @Override
    public void onJump() {
    }

    /**
     * The reaction when the state tries to change from Jump to Slide.
     * Actually, the hero keeps jumping and doesn't change his state
     */
    @Override
    public void onSlide() {
    }

}
