package it.unisa.theneverendingrun.models.hero.state.move;


import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import it.unisa.theneverendingrun.models.hero.AbstractHero;
import it.unisa.theneverendingrun.models.hero.HeroStateType;
import it.unisa.theneverendingrun.models.hero.state.HeroMoveState;

import java.util.Map;

/**
 *
 * In this state the hero is sliding
 */
public class SlideState extends HeroMoveState {

    /**
     *
     * @see HeroMoveState#HeroMoveState(AbstractHero, Map)
     *
     * Sets the hero for holding its slide state, setting its slide variable to true
     * and his slide counter variable to its initial value
     */
    public SlideState(AbstractHero hero, Map<HeroStateType, Animation<TextureRegion>> animations) {
        super(hero, animations);

        hero.setSlideCount(0);
    }

    /**
     *
     * @see HeroMoveState#move()
     * @see SlideState#slide()
     *
     * Updates the hero bottom-left coordinates and sprite
     */
    @Override
    public void move() {
        super.move();

        slide();
    }

    /**
     *
     * Performs a slide step: none of the hero coordinates change
     */
    private void slide() {
        int slideCount = hero.getSlideCount();

        if (slideCount < AbstractHero.getSlideDuration()) {
            hero.setSlideCount(slideCount + 1);
        } else {
            if (hero.isMoving()) {
                onRun();
            } else {
                onStand();
            }
        }
    }

    /**
     *
     * @see HeroMoveState#onStand()

     * The reaction when the state tries to change from Slide to Stand.
     * Actually, the hero does change its state to Idle
     */
    @Override
    public void onStand() {
        hero.changeMoveState(new StandState(hero, animations));
    }

    /**
     *
     * @see HeroMoveState#onJump()
     *
     * The reaction when the state tries to change from Slide to Jump
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
     * The reaction when the state tries to change from Slide to Slide
     * Actually, the hero keeps sliding and doesn't change his state
     */
    @Override
    public void onSlide() {
    }

    /**
     *
     * @see HeroMoveState#onFall()
     *
     * The reaction when the state tries to change from Slide to Fall
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
     * The reaction when the state tries to change from Slide to Dead.
     * Actually, the hero does die
     */
    @Override
    public void onDie() {
        hero.changeMoveState(new DeadState(hero, animations));
    }

    /**
     *
     * @see HeroMoveState#onRun()
     *
     * The reaction when the state tries to change from Slide to Run
     * Actually, the hero does run
     */
    @Override
    public void onRun() {
        hero.changeMoveState(new RunningState(hero, animations));
    }

    /**
     *
     * @return the current hero animation type based on the current state
     * @see HeroStateType
     */
    @Override
    protected HeroStateType computeStateType() {
        return HeroStateType.SLIDE;
    }
}
