package it.unisa.theneverendingrun.models.hero;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.utilities.MathUtils;

import java.util.HashMap;
import java.util.Map;

public abstract class Hero extends Sprite {

    /**
     * The number of steps the hero takes to jump
     */
    private static final int JUMP_DURATION = 30;

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
     * The height of the hero
     */
    private float standardHeight;

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
    protected Hero(Texture texture, float x, float y) {
        super(texture);
        setX(x);
        setY(y);
        this.groundX = x;
        this.groundY = y;
        this.dx = 0;

        initAnimations();

        this.moveState = new IdleState(this);
        this.facingState = new RightState(this);
    }








    private Map<Class<?>, Animation<TextureRegion>> animations;
    private Map<Class<?>, Float> deltaTime;


    public void initAnimations() {
        animations = new HashMap<>();
        deltaTime = new HashMap<>();

        var tVect = new TextureRegion[8];
        for (int i = 1; i <= 8; i++) {
            TextureRegion t = new TextureRegion(new Texture("images/hero/run/hero_run_" + i + ".png"));
            tVect[i - 1] = t;
        }

        /*
        var run = new Texture(Gdx.files.internal("runSheet.png"));
        var runTextures = TextureSheets.split(run, 1,8);*/
        var runAnimation = new Animation<>(0.05f, tVect);
        animations.put(IdleState.class, runAnimation);
        deltaTime.put(IdleState.class, 0F);

        // animations.put(2)
    }

    public void updateDelta(float delta) {
        deltaTime.entrySet().forEach((map) -> map.setValue(map.getValue()+delta));
    }

    public void changeState() {

        Texture texture = null;

        if (isSliding()) {
            texture = new Texture("slide.png");
            var region = new TextureRegion(texture);
            if (isLeft())
                region.flip(true, false);
            setRegion(region);

            setSize(texture.getWidth() * ForestHero.SCALE_FACTOR, texture.getHeight() * ForestHero.SCALE_FACTOR);

            return;
        } else {
            texture = new Texture("jump.png");
            setSize(texture.getWidth() * ForestHero.SCALE_FACTOR, texture.getHeight() * ForestHero.SCALE_FACTOR);

        }

        var animation = animations.get(getMoveState().getClass());
        if (animation != null) {
            var delta = deltaTime.get(getMoveState().getClass());
            var frame = animation.getKeyFrame(delta, true);
            var newFrame = new TextureRegion(frame);
            if (isLeft()) {
                int pixels = pixelWidth(newFrame);
                // newFrame.setRegionWidth(pixels);
                //newFrame.set
                newFrame.flip(true, false);
                //System.out.println(getX());
                setX(getX() - pixels);
                //System.out.println(getX());

            }
            setRegion(newFrame);

            return;
        }

        if (isJumping()) {
            texture = new Texture("jump.png");
            var region = new TextureRegion(texture);
            if (isLeft())
                region.flip(true, false);
            setRegion(region);

            setSize(texture.getWidth() * ForestHero.SCALE_FACTOR, texture.getHeight() * ForestHero.SCALE_FACTOR);

            return;
        }

        if (isFalling()) {
            texture = new Texture("fall.png");
            var region = new TextureRegion(texture);
            if (isLeft())
                region.flip(true, false);
            setRegion(region);

            setSize(texture.getWidth() * ForestHero.SCALE_FACTOR, texture.getHeight() * ForestHero.SCALE_FACTOR);
        }
    }

    public void updateImageFrame() {

        var animation = animations.get(getMoveState().getClass());
        if (animation != null) {
            var delta = deltaTime.get(getMoveState().getClass());
            var frame = animation.getKeyFrame(delta, true);
            var newFrame = new TextureRegion(frame);
            if(isLeft()) {
                int pixels = pixelWidth(newFrame);
                // newFrame.setRegionWidth(pixels);
                //newFrame.set
                //System.out.println(getX());
                //setX(getX()-pixels);
               // System.out.println(pixels);
                //translateX(-27);
                //System.out.println(getWidth());
               // setX(getX() - (getWidth() - (pixels*getWidth())));
               setX(getX() - (pixels/getWidth()) - getDx());
                newFrame.flip(true, false);
            }
            setRegion(newFrame);
            return;
        }
      /*  var animation = animations.get(1);
        stateTime += Gdx.graphics.getDeltaTime();
        var region = animation.getKeyFrame(stateTime, true);
        setRegion(region);
        region.flip(true, false);*/
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
    boolean isAboveGround() {
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

    public float getStandardHeight() {
        return standardHeight;
    }

    /* ------------------------------------- SETTERS ------------------------------------- */

    /**
     * dx setter
     *
     * @param dx the horizontal velocity value to set
     */
    public void setDx(float dx) {
        if (dx != 0) updateImageFrame();
        this.dx = dx;
    }

    /**
     * moveState setter
     *
     * @param moveState the new move state to set
     */
    void changeMoveState(HeroMoveState moveState) {
        this.moveState = moveState;
        changeState();
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

    void setStandardHeight(float standardHeight) {
        this.standardHeight = standardHeight;
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

    /* ------------------------------------- SERVICE METHODS ------------------------------------- */

    /**
     * flip method for textures that have more than one image (ex. runSheet.png)
     * @param newFrame is the texture region to flip correctly
     */
    public int pixelWidth(TextureRegion newFrame){
        //newFrame.flip(true, false);
        //setRegion(newFrame);
        var texture = newFrame.getTexture();
        var textureData = texture.getTextureData();
        if (!textureData.isPrepared())
            textureData.prepare();
        var pixMap = textureData.consumePixmap();
        int start = 0;
        for (int i = newFrame.getRegionWidth(); i > 0; i--) {
            for (int j = newFrame.getRegionHeight(); j > 0; j--)
                if (pixMap.getPixel(i, j) != 0x00000000) {
                    start = newFrame.getRegionWidth() - i;
                    break;
                }
            if (start != 0)
                break;
        }
        return start;
    }
}
