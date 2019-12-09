package it.unisa.theneverendingrun.models.hero;


/**
 * In this state the hero is sliding
 */
public class SlideState extends HeroMoveState {

    /**
     * Sets the hero for holding its slide state, setting its slide variable to true
     * and his slide counter variable to its initial value
     *
     * @param hero the hero which slide state is held
     */
    public SlideState(Hero hero) {
        super(hero);

        hero.setSlideCount(0);
    }

    /**
     * Updates the hero bottom-left coordinates and sprite
     */
    @Override
    public void move() {
        super.move();

        slide();
    }

    /**
     * Performs a slide step: none of the hero coordinates change
     */
    private void slide() {
        int slideCount = hero.getSlideCount();

        if (slideCount < hero.getSlideDuration()) {
            hero.setSlideCount(slideCount + 1);
        } else {
            hero.changeMoveState(new IdleState(hero));
        }
    }

    /**
     * The reaction when the state tries to change from Slide to Idle: the hero does change its state to Idle
     */
    @Override
    public void onIdle() {
        hero.changeMoveState(new IdleState(hero));
    }

    /**
     * The reaction when the state tries to change from Slide to Jump: the hero does start jumping
     */
    @Override
    public void onJump() {
        hero.changeMoveState(new JumpState(hero));
    }

    /**
     * The reaction when the state tries to change from Slide to Slide: the reaction is null
     */
    @Override
    public void onSlide() {
    }

    /**
     * The reaction when the state tries to change from Slide to Fall: the hero does start falling
     */
    @Override
    public void onFall() {
        hero.changeMoveState(new FallState(hero));
    }

    /**
     * Computes the proper sprite source path depending on the current hero horizontal velocity
     *
     * @return the sprite source path
     */
    @Override
    protected String getSpritePath() { return "sprites/hero/hero_slide.png";}
}
