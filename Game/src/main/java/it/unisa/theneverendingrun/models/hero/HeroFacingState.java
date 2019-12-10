package it.unisa.theneverendingrun.models.hero;

/**
 * The State representing if the hero is facing left or right: the states are called Left and Right.
 */
public abstract class HeroFacingState {

    /**
     * The hero which facing state is held
     */
    Hero hero;

    /**
     * Sets the hero for holding its facing state
     *
     * @param hero the hero which facing state is held
     */
    public HeroFacingState(Hero hero) {
        this.hero = hero;
    }

    /**
     * The reaction when the state tries to change to Left
     */
    public abstract void onLeft();

    /**
     * The reaction when the state tries to change to Right
     */
    public abstract void onRight();

    public abstract String toString();
}
