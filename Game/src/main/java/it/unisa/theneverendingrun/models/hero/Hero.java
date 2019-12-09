package it.unisa.theneverendingrun.models.hero;

import it.unisa.theneverendingrun.models.Component;
import it.unisa.theneverendingrun.utilities.MathUtils;

public abstract class Hero extends Component {

    /**
     * The number of steps the hero takes to jump
     */
    private static final int JUMP_DURATION = 30;

    /**
     * The number of steps the hero takes to slide
     */
    private static final int SLIDE_DURATION = 60;

    /**
     * Bottom-left original x coordinate, i.e. where the hero appears when it's created
     */
    private double groundX;

    /**
     * Bottom-left original y coordinate, i.e. where the hero appears when it's created
     */
    private double groundY;

    /**
     * The current speed the hero is moving
     */
    private double dx;

    /**
     * The height of the hero
     */
    private double standardHeight = 64; // fixme delete 64

    /**
     * A variable representing if the hero is jumping, sliding or none of them
     */
    private HeroMoveState moveState;
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

    /**
     * Abstract Hero constructor. Sets its bottom-left coordinates and speed, while its horizontal velocity is set to 0
     *
     * @param x     bottom-left x coordinate
     * @param y     bottom-left y coordinate
     */
    protected Hero(double x, double y) {
        super(x, y);
        this.groundX = x;
        this.groundY = y;
        this.dx = 0;

        this.moveState = new IdleState(this);
        this.facingState = new RightState(this);

        // TODO uncomment this line when the image is computed
        // this.standardHeight = getHeight();
    }

    /* ------------------------------------- GETTERS ------------------------------------- */

    /**
     * groundX getter
     *
     * @return the hero bottom-left original x coordinate
     */
    public double getGroundX() {
        return groundX;
    }

    /**
     * groundY getter
     *
     * @return the hero bottom-left original y coordinate
     */
    double getGroundY() {
        return groundY;
    }

    /**
     * Checks if the hero is above the ground, i.e. its current y coordinate is above its original y coordinate
     *
     * @return true if the hero is above the ground, false otherwise
     */
    boolean isAboveGround() {
        return getY() > getGroundY();
    }

    /**
     * dx getter
     *
     * @return the hero horizontal velocity
     */
    public double getDx() {
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
    public boolean isFalling() { return this.getMoveState() instanceof FallState; }


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

    /**
     * checks if the hero is facing right
     *
     * @return true if the hero is facing right, else otherwise
     */
    public boolean isRight() {
        return facingState instanceof RightState;
    }

    /**
     * checks if the hero is facing left
     *
     * @return true if the hero is facing left, else otherwise
     */
    public boolean isLeft() {
        return facingState instanceof LeftState;
    }

    /* ------------------------------------- SETTERS ------------------------------------- */

    /**
     * dx setter
     *
     * @param dx the horizontal velocity value to set
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * moveState setter
     *
     * @param moveState the new move state to set
     */
    void changeMoveState(HeroMoveState moveState) {
        this.moveState = moveState;
    }

    /**
     * jumpCount setter
     *
     * @param jumpCount the jump counter value to set
     */
    void setJumpCount(int jumpCount) {
        this.jumpCount = jumpCount;
    }

    /**
     * slideCount setter
     *
     * @param slideCount the slide counter value to set
     */
    void setSlideCount(int slideCount) {
        this.slideCount = slideCount;
    }

    /**
     * facingState setter
     *
     * @param facingState the new facing state to set
     */
    void changeFacingState(HeroFacingState facingState) {
        this.facingState = facingState;
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
        return 3 * standardHeight;
    }

    /**
     * Computes the coefficient for the jump parabola formula depending on how much the hero can jump
     * and the number of steps the hero takes to jump
     *
     * @return the coefficient for the jump parabola formula
     */
    double getJumpCoefficient() {
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
}
