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

        if (jumpCount >= -hero.getJumpDuration()) {
            int up = jumpCount < 0 ? -1 : 1;
            var newY = (float) (hero.getY() + (jumpCount * jumpCount) * hero.getJumpCoefficient() * up);
            hero.setY(newY);
            hero.setJumpCount(jumpCount - 1);
        } else {
            if (hero.isAboveGround())
                hero.changeMoveState(new FallState(hero, Math.abs(jumpCount)));
            else
                hero.changeMoveState(new IdleState(hero));
        }
    }

    /**
     * The reaction when the state tries to change from Jump to Idle: the hero does change its state to Idle
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

    /**
     * The reaction when the state tries to change from Jump to Fall.
     * Actually, the hero does start falling.
     */
    @Override
    public void onFall() {
        hero.changeMoveState(new FallState(hero));
    }

}
