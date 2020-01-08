package it.unisa.theneverendingrun.models.hero.state.move;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import it.unisa.theneverendingrun.models.hero.Hero;
import it.unisa.theneverendingrun.models.hero.HeroStateType;
import it.unisa.theneverendingrun.models.hero.state.HeroMoveState;

import java.util.Map;

/**
 *
 * In this state the hero is dead
 */
public class DeadState extends HeroMoveState {

    /**
     * @see HeroMoveState#HeroMoveState(Hero, Map)
     */
    public DeadState(Hero hero, Map<HeroStateType, Animation<TextureRegion>> animations) {
        super(hero, animations);
    }

    /**
     *
     * @see HeroMoveState#move()
     *
     * Actually, the hero is dead so doesn't move
     */
    @Override
    public void move() {
    }

    /**
     *
     * @see HeroMoveState#onStand()
     *
     * The reaction when the state tries to change from Dead to Run
     * Actually, the hero is dead so doesn't change his state
     */
    @Override
    public void onStand() {
    }

    /**
     *
     * @see HeroMoveState#onJump()
     *
     * The reaction when the state tries to change from Dead to Run
     * Actually, the hero is dead so doesn't change his state
     */
    @Override
    public void onJump() {
    }

    /**
     *
     * @see HeroMoveState#onSlide()
     *
     * The reaction when the state tries to change from Dead to Run
     * Actually, the hero is dead so doesn't change his state
     */
    @Override
    public void onSlide() {
    }

    /**
     *
     * @see HeroMoveState#onFall()
     *
     * The reaction when the state tries to change from Dead to Run
     * Actually, the hero is dead so doesn't change his state
     */
    @Override
    public void onFall() {
    }

    /**
     *
     * @see HeroMoveState#onDie()
     *
     * The reaction when the state tries to change from Dead to Run
     * Actually, the hero is dead so doesn't change his state
     */
    @Override
    public void onDie() {
    }

    /**
     *
     * @see HeroMoveState#onRun()
     *
     * The reaction when the state tries to change from Dead to Run
     * Actually, the hero is dead so doesn't change his state
     */
    @Override
    public void onRun() {

    }


    /**
     *
     * @see HeroMoveState#getStateType()
     * @return the current hero animation type based on the current state
     */
    @Override
    protected HeroStateType getStateType() {
        return HeroStateType.DEAD;
    }

    @Override
    public String toString() {
        return "Dead";
    }
}
