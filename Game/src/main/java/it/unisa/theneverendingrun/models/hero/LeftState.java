package it.unisa.theneverendingrun.models.hero;

/**
 * In this state the hero is facing left
 */
public class LeftState extends HeroFacingState {

    /**
     * Sets the hero for holding its left facing state
     *
     * @param hero the hero which left facing state is held
     */
    public LeftState(Hero hero) {
        super(hero);
    }

    /**
     * The reaction when the state tries to change from Left to Left: the reaction is null
     */
    @Override
    public void onLeft() {
    }

    /**
     * The reaction when the state tries to change from Left to Right.
     * The hero actually changes its facing state to Right
     */
    @Override
    public void onRight() {
        hero.changeFacingState(new RightState(hero));
    }

    @Override
    public String toString() {
        return "left";
    }
}
