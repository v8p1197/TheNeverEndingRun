package it.unisa.theneverendingrun.models.hero;


/**
 * In this state the hero is neither jumping or sliding
 */
public class IdleState extends HeroMoveState {

    /**
     * Sets the hero for holding its idle state, setting his jumping and sliding variables to false
     *
     * @param hero the hero which idle state is held
     */
    public IdleState(Hero hero) {
        super(hero);
    }

    /**
     * The reaction when the state tries to change from Idle to Idle: the reaction is null
     */
    @Override
    public void onIdle() {
    }

    /**
     * The reaction when the state tries to change from Idle to Jump: the hero does start jumping
     */
    @Override
    public void onJump() {
        hero.changeMoveState(new JumpState(hero));
    }

    /**
     * The reaction when the state tries to change from Idle to Slide: the hero does start sliding
     */
    @Override
    public void onSlide() {
        hero.changeMoveState(new SlideState(hero));
    }

    /**
     * The reaction when the state tries to change from Idle to Fall: the hero does start falling
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
    protected String getSpritePath() {
        String spritePath;
        if (hero.isRunning()) {
            spritePath = "sprites/hero/hero_run.png";
        } else {
            spritePath = "sprites/hero/hero_stand.png";
        }
        return spritePath;
    }
}
