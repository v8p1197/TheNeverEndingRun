package it.unisa.theneverendingrun.models.hero;


/**
 * The State representing if the hero is jumping, sliding, falling or none of them:
 * the states are called Idle, Jump, Slide, Fall and Dead.
 * Also is delegated to compute the sprite image
 */
public abstract class HeroMoveState {

    /**
     * The hero which move state is held
     */
    protected Hero hero;

    /**
     * Sets the hero for holding its move state
     *
     * @param hero the hero which move state is held
     */
    public HeroMoveState(Hero hero) {
        this.hero = hero;
    }

    /**
     * Updates the hero bottom-left coordinates and sprite
     */
    public void move() {
        updateCoordinates();
    }

    /**
     * Updates the hero bottom-left coordinates depending on its current horizontal velocity.
     * (This means that if it is 0, the hero actually doesn't move)
     * Plus, makes the hero fall if it is above the ground
     */
    private void updateCoordinates() {
        if (hero.isRight()) {
            moveRight();
        } else if (hero.isLeft()) {
            moveLeft();
        }
    }

    /**
     * Makes the hero move to the right depending on its current horizontal velocity.
     */
    private void moveRight() {
        hero.setX(hero.getX() + hero.getDx());
    }

    /**
     * Makes the hero move to the left depending on its current horizontal velocity.
     */
    private void moveLeft() {
        hero.setX(hero.getX() - hero.getDx());
    }

    /**
     * The reaction when the state tries to change to Idle
     */
    public abstract void onIdle();

    /**
     * The reaction when the state tries to change to Jump
     */
    public abstract void onJump();

    /**
     * The reaction when the state tries to change to Slide
     */
    public abstract void onSlide();

    /**
     * The reaction when the state tries to change to Fall
     */
    public abstract void onFall();

    /**
     * The reaction when the state tries to change to Dead
     */
    public abstract void onDie();

    public abstract String toString();

}

