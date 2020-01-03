package it.unisa.theneverendingrun.models.hero.state.face;

import it.unisa.theneverendingrun.models.hero.AbstractHero;
import it.unisa.theneverendingrun.models.hero.state.HeroFacingState;

/**
 * In this state the hero is facing right
 */
public class RightState extends HeroFacingState {

    /**
     * Sets the hero for holding its right facing state
     *
     * @param hero the hero which right facing state is held
     */
    public RightState(AbstractHero hero) {
        super(hero);
    }

    /**
     * The reaction when the state tries to change from Right to Left.
     * The hero actually changes its facing state to Left
     */
    @Override
    public void onLeft() {
        hero.changeFacingState(new LeftState(hero));
    }

    /**
     * The reaction when the state tries to change from Right to Right: the reaction is null
     */
    @Override
    public void onRight() {
    }

    @Override
    public String toString() {
        return "right";
    }
}
