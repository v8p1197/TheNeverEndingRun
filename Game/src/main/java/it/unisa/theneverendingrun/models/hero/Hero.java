package it.unisa.theneverendingrun.models.hero;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import it.unisa.theneverendingrun.models.AbstractAnimatedSprite;
import it.unisa.theneverendingrun.models.hero.state.HeroFacingState;
import it.unisa.theneverendingrun.models.hero.state.HeroMoveState;
import it.unisa.theneverendingrun.models.hero.state.face.LeftState;
import it.unisa.theneverendingrun.models.hero.state.face.RightState;
import it.unisa.theneverendingrun.models.hero.state.move.*;
import it.unisa.theneverendingrun.utilities.MathUtils;

import java.util.Map;

public abstract class Hero extends AbstractAnimatedSprite<HeroAnimationType, TextureRegion> {


    /* ------------------------------------- PARAMS ------------------------------------- */


    /**
     * The number of steps the hero takes to jump
     */
    private static final int JUMP_DURATION = 35;

    /**
     * The number of steps the hero takes to slide
     */
    private static final int SLIDE_DURATION = 45;

    /**
     * Bottom-left original x coordinate, i.e. where the hero appears when it's created
     */
    private float groundX;

    /**
     * Bottom-left original y coordinate, i.e. where the hero appears when it's created
     */
    private float groundY;

    /**
     * The current speed the hero is moving
     */
    private float dx;

    /**
     * A variable representing if the hero is jumping, sliding or none of them
     */
    private HeroMoveState moveState, prevMoveState;
    /**
     * A variable representing whether the hero is facing left or right
     */
    private HeroFacingState facingState;

    /**
     * A variable representing the jump step the hero actually is in
     */
    private int jumpCount;

    /**
     * A variable representing the slide step the hero actually is in
     */
    private int slideCount;



    /* ------------------------------------- CONSTRUCTORS ------------------------------------- */

    /**
     * Abstract Hero constructor. Sets its bottom-left coordinates and speed, while its horizontal velocity is set to 0
     * The scale factor is set to 1
     *
     * @param animations the animations of the hero
     */
    protected Hero(Map<HeroAnimationType, Animation<TextureRegion>> animations, float x, float y) {
        this(1, animations, x, y);
    }


    /**
     * Abstract Hero constructor. Sets its bottom-left coordinates and speed, while its horizontal velocity is set to 0
     *
     * @param animations the animations of the hero
     * @param scaleFactor the scale factor of the hero
     * @param x     bottom-left x coordinate
     * @param y     bottom-left y coordinate
     */
    public Hero(float scaleFactor, Map<HeroAnimationType, Animation<TextureRegion>> animations, float x, float y) {
        super(scaleFactor, animations);

        setX(x);
        setY(y);

        this.groundX = x;
        this.groundY = y;
        this.dx = 0;

        changeMoveState(new IdleState(this));
        changeFacingState(new RightState(this));
    }




    /* ------------------------------------- GETTERS ------------------------------------- */

    /**
     * groundX getter
     *
     * @return the hero bottom-left original x coordinate
     */
    public float getGroundX() {
        return groundX;
    }

    /**
     * groundY getter
     *
     * @return the hero bottom-left original y coordinate
     */
    public float getGroundY() {
        return groundY;
    }

    /**
     * Checks if the hero is above the ground, i.e. its current y coordinate is above its original y coordinate
     *
     * @return true if the hero is above the ground, false otherwise
     */
    public boolean isAboveGround() {
        return getY() - getGroundY() > MathUtils.DELTA;
    }

    /**
     * dx getter
     *
     * @return the hero horizontal velocity
     */
    public float getDx() {
        return this.dx;
    }

    /**
     * moveState getter
     *
     * @return the move state in which the hero is:
     * IdleState if the hero is neither jumping or sliding;
     * JumpState if the hero is jumping;
     * SlideState if the hero is sliding
     */
    public HeroMoveState getMoveState() {
        return moveState;
    }

    /**
     * jumpCount getter
     *
     * @return the hero jump counter
     */
    public int getJumpCount() {
        return jumpCount;
    }

    /**
     * JUMP_DURATION getter
     *
     * @return the hero jump duration
     */
    public int getJumpDuration() {
        return JUMP_DURATION;
    }

    /**
     * slideCount getter
     *
     * @return the hero slide counter
     */
    public int getSlideCount() {
        return slideCount;
    }

    /**
     * SLIDE_DURATION getter
     *
     * @return the hero slide duration
     */
    public int getSlideDuration() {
        return SLIDE_DURATION;
    }

    /**
     * facingState getter
     *
     * @return the facing state in which the hero is:
     * LeftState if the hero is facing left;
     * RightState if the hero is facing right
     */
    public HeroFacingState getFacingState() {
        return facingState;
    }




    /* ------------------------------------- CHECK ------------------------------------- */


    /**
     * checks if the hero is moving depending on its current horizontal velocity
     *
     * @return true if the hero is running, else otherwise
     */
    public boolean isRunning() {
        return getDx() != 0;
    }

    /**
     * jumping getter
     *
     * @return true if the hero is jumping, false otherwise
     */
    public boolean isJumping() {
        return this.getMoveState() instanceof JumpState;
    }

    /**
     * sliding getter
     *
     * @return true if the hero is sliding, false otherwise
     */
    public boolean isSliding() {
        return this.getMoveState() instanceof SlideState;
    }

    /**
     * falling getter
     *
     * @return true if the hero is falling, false otherwise
     */
    public boolean isFalling() {
        return this.getMoveState() instanceof FallState;
    }

    /**
     * dead getter
     *
     * @return true if the hero is dead, false otherwise
     */
    public boolean isDead() {
        return this.getMoveState() instanceof DeadState;
    }

    /**
     * checks if the hero is facing right
     *
     * @return true if the hero is facing right, else otherwise
     */
    public boolean isRight() {
        return getFacingState() instanceof RightState;
    }

    /**
     * checks if the hero is facing left
     *
     * @return true if the hero is facing left, else otherwise
     */
    public boolean isLeft() {
        return getFacingState() instanceof LeftState;
    }

    /**
     * idle getter
     *
     * @return true if the hero is idle, false otherwise
     */
    public boolean isIdle() {
        return this.getMoveState() instanceof IdleState;
    }

    /**
     * attack getter
     *
     * @return true if the hero is attacking, false otherwise
     */
    //public boolean isAttacking() { return this.getMoveState() instanceof AttackState; }


    /* ------------------------------------- SETTERS ------------------------------------- */

    /**
     * dx setter
     *
     * @param dx the horizontal velocity value to set
     */
    public void setDx(float dx) {
        this.dx = dx;
    }

    /**
     * moveState setter
     *
     * @param moveState the new move state to set
     */
    public void changeMoveState(HeroMoveState moveState) {
        prevMoveState = this.moveState;
        this.moveState = moveState;
        changeAnimation();
    }

    /**
     * jumpCount setter
     *
     * @param jumpCount the jump counter value to set
     */
    public void setJumpCount(int jumpCount) {
        this.jumpCount = jumpCount;
    }

    /**
     * slideCount setter
     *
     * @param slideCount the slide counter value to set
     */
    public void setSlideCount(int slideCount) {
        this.slideCount = slideCount;
    }

    /**
     * facingState setter
     *
     * @param facingState the new facing state to set
     */
    public void changeFacingState(HeroFacingState facingState) {
        this.facingState = facingState;
        changeAnimation();
    }





    /* ------------------------------------- MOVEMENT METHODS ------------------------------------- */

    /**
     * Updates hero's coordinates and image depending on its state
     */
    public void move() {
        getMoveState().move();
    }

    /**
     * Computes how much the hero can jump
     *
     * @return the maximum number of pixels the hero moves in the vertical axis when he jumps
     */
    public double getJumpMaxElevation() {
        return 3 * getHeight();
    }

    /**
     * Computes the coefficient for the jump parabola formula depending on how much the hero can jump
     * and the number of steps the hero takes to jump
     *
     * @return the coefficient for the jump parabola formula
     */
    public double getJumpCoefficient() {
        return getJumpMaxElevation() / MathUtils.sumSquares(JUMP_DURATION);
    }

    /**
     * Computes how much the hero can move on the horizontal axis while he's jumping,
     * supposing his horizontal velocity is 1
     *
     * @return the maximum number of pixels the hero can travel when he jumps while he's moving on the horizontal axis
     * with a horizontal velocity value of 1
     */
    public double getMaxJumpRange() {
        return 2 * JUMP_DURATION + 1;
    }

    /**
     * Computes how much the hero can move on the horizontal axis while he's sliding,
     * supposing his horizontal velocity is 1
     *
     * @return the maximum number of pixels the hero can travel when he slides while he's moving on the horizontal axis
     * with a horizontal velocity value of 1
     */
    public double getMaxSlideRange() {
        return SLIDE_DURATION;
    }

    /**
     * Computes how much of the jump parabola (in percentage) is completed.
     *
     * @return A value of type double in range [0, 1] depending on how much of the jump parabola is completed:
     * 0 when the jump has just started or has not started yet
     * 0.5 when the hero is on the top of the jump parabola
     * 1 when the jump has finished
     */
    public double getJumpCompletion() {
        if (!isJumping())
            return 0;
        return 0.5 - 1.0 * getJumpCount() / 2 / getJumpDuration();
    }

    /**
     * Asks the hero to die
     */
    public void die() {
        getMoveState().onDie();
    }

    @Override
    public void changeAnimation() {

        HeroAnimationType type = null;
        if (isIdle()) {
            type = HeroAnimationType.IDLE;
        } else if (isDead()) {
            type = HeroAnimationType.DEAD;
        }// else if (isAttacking()) {
        // type = HeroAnimationType.ATTACK;
        //}
        else if (isFalling()) {
            type = HeroAnimationType.FALL;
        } else if (isSliding()) {
            type = HeroAnimationType.SLIDE;
        } else if (isJumping()) {
            type = HeroAnimationType.JUMP;
        } else if (isRunning()) {
            type = HeroAnimationType.RUN;
        }

        if (type == null) return;

        var animation = animations.get(type);
        if (animation == null) return;

        var frame = animation.getKeyFrame(getStateTime(), true);
        if (prevMoveState == moveState)
            flip(true, false);
        this.setRegion(frame);
    }

}
