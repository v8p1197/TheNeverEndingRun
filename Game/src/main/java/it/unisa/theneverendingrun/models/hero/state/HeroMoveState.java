package it.unisa.theneverendingrun.models.hero.state;


import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import it.unisa.theneverendingrun.Assets;
import it.unisa.theneverendingrun.models.hero.AbstractHero;
import it.unisa.theneverendingrun.models.hero.HeroStateType;

import java.util.Map;

/**
 *
 * The State representing if the hero is jumping, sliding, falling or none of them:
 * the states are called Idle, Jump, Slide, Fall and Dead.
 * Also is delegated to compute the sprite image
 */
public abstract class HeroMoveState {

    /**
     *
     * The hero which move state is held
     */
    protected AbstractHero hero;

    /**
     *
     * Animations of the hero
     */
    protected final Map<HeroStateType, Animation<TextureRegion>> animations;

    /**
     *
     * Sets the hero for holding its move state
     *
     * @param hero the hero which move state is held
     * @param animations the possible animations of the hero
     */
    public HeroMoveState(AbstractHero hero, Map<HeroStateType, Animation<TextureRegion>> animations) {
        this.hero = hero;
        this.animations = animations;

        setAnimation();
        playSound();
    }

    /**
     *
     * Updates the hero bottom-left coordinates and sprite
     */
    public void move() {
        updateCoordinates();
    }

    /**
     *
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
     *
     * Makes the hero move to the right depending on its current horizontal velocity.
     */
    private void moveRight() {
        hero.setX(hero.getX() + hero.getDx());
    }

    /**
     *
     * Makes the hero move to the left depending on its current horizontal velocity.
     */
    private void moveLeft() {
        hero.setX(hero.getX() - hero.getDx());
    }

    /**
     *
     * The reaction when the state tries to change to Idle
     */
    public abstract void onStand();

    /**
     *
     * The reaction when the state tries to change to Jump
     */
    public abstract void onJump();

    /**
     *
     * The reaction when the state tries to change to Slide
     */
    public abstract void onSlide();

    /**
     *
     * The reaction when the state tries to change to Fall
     */
    public abstract void onFall();

    /**
     *
     * The reaction when the state tries to change to Dead
     */
    public abstract void onDie();

    /**
     *
     * The reaction when the state tries to change to Run
     */
    public abstract void onRun();

    /**
     * The reaction when the state tries to change to Attack
     */
    public abstract void onAttack();

    /**
     *
     * Change the animation of the hero depending on the particular state of the hero
     */
    private void setAnimation() {
        var type = computeStateType();
        if (type == null) return;

        var animation = animations.get(type);
        if (animation == null) return;

        hero.setAnimation(animation);
    }

    private void playSound() {
        var type = computeStateType();
        if (type == null) return;

        var sound = Assets.sounds.sounds.get(type);
        if (sound == null) return;

        sound.play();
    }

    /**
     *
     * @see HeroStateType
     * @return the current hero animation type based on the current state
     */
    protected abstract HeroStateType computeStateType();

}

