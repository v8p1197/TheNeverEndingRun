package it.unisa.theneverendingrun.models.hero;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import it.unisa.theneverendingrun.models.Animatable;
import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.SpriteDescriptionType;
import it.unisa.theneverendingrun.models.hero.state.HeroFacingState;
import it.unisa.theneverendingrun.models.hero.state.HeroMoveState;
import it.unisa.theneverendingrun.models.hero.state.face.LeftState;
import it.unisa.theneverendingrun.models.hero.state.face.RightState;
import it.unisa.theneverendingrun.models.hero.state.move.*;
import it.unisa.theneverendingrun.utilities.MathUtils;

import java.util.Map;

public abstract class AbstractHero extends Sprite implements Animatable {


    /* ------------------------------------- PARAMS ------------------------------------- */


    /**
     *
     * The number of steps the hero takes to jump
     */
    private static final int JUMP_DURATION = 35;

    /**
     *
     * The number of steps the hero takes to slide
     */
    private static final int SLIDE_DURATION = 45;

    /**
     *
     * Bottom-left original x coordinate, i.e. where the hero appears when it's created
     */
    private float groundX;

    /**
     *
     * Bottom-left original y coordinate, i.e. where the hero appears when it's created
     */
    private float groundY;

    /**
     *
     * The current speed the hero is moving
     */
    private float dx;

    /**
     *
     * @see HeroMoveState
     *
     * A variable representing if the hero is jumping, sliding, falling, dead or none of them
     */
    private HeroMoveState moveState;

    /**
     *
     * @see HeroMoveState
     * @see AbstractHero#moveState
     *
     * A variable representing the previuos state of the hero
     */
    private HeroMoveState previousMoveState;

    /**
     *
     * @see HeroFacingState
     *
     * A variable representing whether the hero is facing left or right
     */
    private HeroFacingState facingState;

    /**
     *
     * A variable representing the jump step the hero actually is in
     */
    private int jumpCount;

    /**
     *
     * A variable representing the slide step the hero actually is in
     */
    private int slideCount;

    /**
     *
     * The number of swords the hero is holding
     */
    private int swords;

    /**
     *
     * The number of shields the hero is holding
     */
    private int shields;


    /* ------------------------------------- CONSTRUCTORS ------------------------------------- */

    /**
     *
     * @see Sprite#Sprite()
     *
     * Abstract Hero constructor.
     * Sets its bottom-left coordinates and speed, while its horizontal velocity is set to 0
     *
     * @param x     bottom-left x coordinate
     * @param y     bottom-left y coordinate
     * @param animations the animations of the hero
     */
    public AbstractHero(float x, float y, Map<HeroStateType, Animation<TextureRegion>> animations) {
        this(1, x, y, animations);
    }


    /**
     *
     * @see Sprite#Sprite()
     *
     * Abstract Hero constructor.
     * Sets its bottom-left coordinates and speed, while its horizontal velocity is set to 0
     *
     * @param x     bottom-left x coordinate
     * @param y     bottom-left y coordinate
     * @param animations the animations of the hero
     */
    public AbstractHero(float scaleFactor, float x, float y, Map<HeroStateType, Animation<TextureRegion>> animations) {
        super(scaleFactor);

        this.groundX = x;
        this.groundY = y;

        setX(x);
        setY(y);
        setDx(0);
        changeMoveState(new StandState(this, animations));
        changeFacingState(new RightState(this));


        var standAnimation = animations.get(HeroStateType.STAND);
        if (standAnimation == null) throw new IllegalArgumentException("No stand animation");

        var texture = standAnimation.getKeyFrames()[0].getTexture();
        setStandardWidth(texture.getWidth() * scaleFactor);
        setStandardHeight(texture.getHeight() * scaleFactor);


    }

    /* ------------------------------------- GETTERS ------------------------------------- */

    /**
     *
     * @see AbstractHero#groundX
     *
     * @return the hero bottom-left original x coordinate
     */
    public float getGroundX() {
        return groundX;
    }

    /**
     *
     * @see AbstractHero#groundY
     *
     * @return the hero bottom-left original y coordinate
     */
    public float getGroundY() {
        return groundY;
    }

    /**
     *
     * @see AbstractHero#dx
     *
     * @return the hero horizontal velocity
     */
    public float getDx() {
        return this.dx;
    }

    /**
     *
     * @see AbstractHero#moveState
     *
     * @return the move state in which the hero is:
     * JumpState if the hero is jumping;
     * SlideState if the hero is sliding
     * DeadState if the hero is dead
     * FallState if the hero is falling
     * IdleState if none of previous is true;
     */
    public HeroMoveState getMoveState() {
        return moveState;
    }

    public HeroMoveState getPreviousMoveState() {
        return previousMoveState;
    }

    /**
     *
     * @see AbstractHero#jumpCount
     *
     * @return the hero jump counter
     */
    public int getJumpCount() {
        return jumpCount;
    }

    /**
     *
     * @see AbstractHero#JUMP_DURATION
     *
     * @return the hero jump duration
     */
    public int getJumpDuration() {
        return JUMP_DURATION;
    }

    /**
     *
     * @see AbstractHero#slideCount
     *
     * @return the hero slide counter
     */
    public int getSlideCount() {
        return slideCount;
    }

    /**
     *
     * @see AbstractHero#SLIDE_DURATION
     *
     * @return the hero slide duration
     */
    public int getSlideDuration() {
        return SLIDE_DURATION;
    }

    /**
     *
     * @see AbstractHero#facingState
     *
     * @return the facing state in which the hero is:
     * LeftState if the hero is facing left;
     * RightState if the hero is facing right
     */
    public HeroFacingState getFacingState() {
        return facingState;
    }

    /**
     *
     * @return the type of the sprite
     */
    @Override
    public SpriteDescriptionType getSpriteType() {
        return SpriteDescriptionType.HERO;
    }

    /**
     *
     * @return the number of shields that the hero has currently collected
     */
    public int getShields() {
        return shields;
    }

    /**
     *
     * @return the number of sword that the hero has currently collected
     */
    public int getSwords() {
        return swords;
    }


    /* ------------------------------------- CHECK ------------------------------------- */


    /**
     *
     * checks if the hero is moving depending on its current horizontal velocity
     *
     * @return true if the hero is moving, else otherwise
     */
    public boolean isMoving() {
        return getDx() != 0;
    }

    /**
     *
     * @return true if the hero is running, false otherwise
     */
    public boolean isRunning() { return this.getMoveState() instanceof RunningState; }

    /**
     *
     * @return true if the hero is jumping, false otherwise
     */
    public boolean isJumping() {
        return this.getMoveState() instanceof JumpState;
    }

    /**
     *
     * @return true if the hero is sliding, false otherwise
     */
    public boolean isSliding() {
        return this.getMoveState() instanceof SlideState;
    }

    /**
     *
     * @return true if the hero was sliding, false otherwise
     */
    public boolean wasSliding() {
        return this.getPreviousMoveState() instanceof SlideState;
    }

    /**
     *
     * @return true if the hero is falling, false otherwise
     */
    public boolean isFalling() {
        return this.getMoveState() instanceof FallState;
    }

    /**
     *
     * @return true if the hero is dead, false otherwise
     */
    public boolean isDead() {
        return this.getMoveState() instanceof DeadState;
    }

    /**
     *
     * checks if the hero is facing right
     *
     * @return true if the hero is facing right, else otherwise
     */
    public boolean isRight() {
        return getFacingState() instanceof RightState;
    }

    /**
     *
     * checks if the hero is facing left
     *
     * @return true if the hero is facing left, else otherwise
     */
    public boolean isLeft() {
        return getFacingState() instanceof LeftState;
    }

    /**
     *
     * @return true if the hero is idle, false otherwise
     */
    public boolean isStanding() {
        return this.getMoveState() instanceof StandState;
    }

    public boolean isIdle() {
        return isStanding() || isRunning();
    }

    /**
     *
     * Checks if the hero is above the ground, i.e. its current y coordinate is above its original y coordinate
     *
     * @return true if the hero is above the ground, false otherwise
     */
    public boolean isAboveGround() {
        return getY() - getGroundY() > MathUtils.DELTA;
    }




    /* ------------------------------------- SETTERS ------------------------------------- */

    /**
     *
     * @see AbstractHero#dx
     *
     * @param dx the horizontal velocity value to set
     */
    public void setDx(float dx) {
        this.dx = dx;
    }

    /**
     *
     * @see AbstractHero#moveState
     *
     * @param moveState the new move state to set
     */
    public void changeMoveState(HeroMoveState moveState) {
        this.previousMoveState = getMoveState();
        this.moveState = moveState;
    }

    /**
     *
     * @see AbstractHero#jumpCount
     *
     * @param jumpCount the jump counter value to set
     */
    public void setJumpCount(int jumpCount) {
        this.jumpCount = jumpCount;
    }

    /**
     *
     * @see AbstractHero#slideCount
     *
     * @param slideCount the slide counter value to set
     */
    public void setSlideCount(int slideCount) {
        this.slideCount = slideCount;
    }

    /**
     *
     * @see AbstractHero#facingState
     *
     * @param facingState the new facing state to set
     */
    public void changeFacingState(HeroFacingState facingState) {
        this.facingState = facingState;
    }

    /**
     *
     * @see AbstractHero#shields
     *
     * @param shields the new number of shields
     */
    public void setShields(int shields) {
        this.shields = shields;
    }

    /**
     *
     * @see AbstractHero#swords
     *
     * @param swords the new number of swords
     */
    public void setSwords(int swords) {
        this.swords = swords;
    }



    /* ------------------------------------- MOVEMENT METHODS ------------------------------------- */

    /**
     *
     * Updates hero's coordinates and image depending on its state
     */
    public void move() {
        getMoveState().move();
    }

    /**
     *
     * Computes how much the hero can jump
     *
     * @return the maximum number of pixels the hero moves in the vertical axis when he jumps
     */
    public float getJumpMaxElevation() {
        return 3 * getStandardHeight();
    }

    /**
     *
     * Computes the coefficient for the jump parabola formula depending on how much the hero can jump
     * and the number of steps the hero takes to jump
     *
     * @return the coefficient for the jump parabola formula
     */
    public double getJumpCoefficient() {
        return getJumpMaxElevation() / MathUtils.sumSquares(getJumpDuration());
    }

    /**
     *
     * Computes how much the hero can move on the horizontal axis while he's jumping,
     * supposing his horizontal velocity is 1
     *
     * @return the maximum number of pixels the hero can travel when he jumps while he's moving on the horizontal axis
     * with a horizontal velocity value of 1
     */
    public double getMaxJumpRange() {
        return 2 * getJumpDuration() + 1;
    }

    /**
     *
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
     *
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
     *
     * Asks the hero to die
     */
    public void die() {
        getMoveState().onDie();
    }




    /* ------------------------------------- ANIMATION ------------------------------------- */

    /**
     *
     * @see Animatable#animate()
     */
    @Override
    public void animate() {

        var animation = getAnimation();
        if (animation == null) throw new NullPointerException("animation is null");

        var frame = animation.getKeyFrame(getStateTime(), true);

        if (frame == null) throw new NullPointerException("animation frame is null");

        if (isLeft())
            frame.flip(true, false);

        setRegion(frame);
    }
}
