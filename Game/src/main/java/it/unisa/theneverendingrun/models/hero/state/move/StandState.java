package it.unisa.theneverendingrun.models.hero.state.move;


import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import it.unisa.theneverendingrun.models.hero.AbstractHero;
import it.unisa.theneverendingrun.models.hero.HeroStateType;
import it.unisa.theneverendingrun.models.hero.state.HeroMoveState;

import java.util.Map;

/**
 *
 * In this state the hero is standing
 */
public class StandState extends HeroMoveState {

    /**
     *
     * @see HeroMoveState#HeroMoveState(AbstractHero, Map)
     */
    public StandState(AbstractHero hero, Map<HeroStateType, Animation<TextureRegion>> animations) {
        super(hero, animations);
    }

    /**
     *
     * @see HeroMoveState#move()
     *
     * Actually, the hero is stand so doesn't move
     */
    @Override
    public void move() {
    }

    /**
     *
     * @see HeroMoveState#onJump()
     *
     * The reaction when the state tries to change from Stand to Idle
     * The hero is Idle so do nothing
     */
    @Override
    public void onStand() {
    }

    /**
     *
     * @see HeroMoveState#onJump()
     *
     * The reaction when the state tries to change from Stand to Jump
     * Actually, the hero does start jumping
     */
    @Override
    public void onJump() {
        hero.changeMoveState(new JumpState(hero, animations));
    }

    /**
     *
     * @see HeroMoveState#onSlide()
     *
     * The reaction when the state tries to change from Stand to Slide
     * Actually, the hero does start sliding
     */
    @Override
    public void onSlide() { hero.changeMoveState(new SlideState(hero, animations)); }

    /**
     *
     * @see HeroMoveState#onFall()
     *
     * The reaction when the state tries to change from Stand to Fall
     * Actually, the hero does start falling
     */
    @Override
    public void onFall() {
        hero.changeMoveState(new FallState(hero, animations));
    }

    /**
     *
     * @see HeroMoveState#onDie()
     *
     * The reaction when the state tries to change from Stand to Dead
     * Actually, the hero does die
     */
    @Override
    public void onDie() {
        hero.changeMoveState(new DeadState(hero, animations));
    }

    /**
     *
     * @see HeroMoveState#onFall()
     *
     * The reaction when the state tries to change from Stand to Run
     * Actually, the hero does run
     */
    @Override
    public void onRun() {
        hero.changeMoveState(new RunningState(hero, animations));
    }

    /**
     *
     * @see HeroMoveState#getStateType()
     * @return the current hero animation type based on the current state
     */
    @Override
    protected HeroStateType getStateType() {
        return HeroStateType.STAND;
    }
}
