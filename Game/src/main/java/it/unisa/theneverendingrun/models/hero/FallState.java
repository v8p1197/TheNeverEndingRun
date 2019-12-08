package it.unisa.theneverendingrun.models.hero;

public class FallState extends HeroMoveState {

    private int gravity;

    /**
     * Sets the hero for holding its move state
     *
     * @param hero the hero which move state is held
     */
    public FallState(Hero hero) {
        this(hero, 0);
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
        if (!hero.isAboveGround()) {
            hero.setY(hero.getY() - (gravity * gravity) * hero.getJumpCoefficient());
            gravity++;
        } else hero.changeMoveState(new IdleState(hero));
    }

    /**
     * The reaction when the state tries to change from Fall to Idle: the hero actually changes its state to Idle
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
    public void onSlide() { }
}
