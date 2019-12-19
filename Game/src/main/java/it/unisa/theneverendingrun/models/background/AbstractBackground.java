package it.unisa.theneverendingrun.models.background;

import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.models.Sprite;

public abstract class AbstractBackground extends Sprite {

    /* ------------------------------------- PARAMS ------------------------------------- */

    /**
     * Store the amount of the horizontal scroll.
     */
    private float horizontalScrollAmount;

    /**
     * Store the amount of the vertical scroll
     */
    private float verticalScrollAmount;

    /**
     * Scroll the scroll speed of the background
     */
    private float scrollingSpeed;

    /**
     * Store the amount of the background to scroll
     */
    private float scrollWidth;

    /**
     * Store the original background width
     */
    private float textureWidth;

    /**
     * Store the original background height
     */
    private float textureHeight;



    /* ------------------------------------- CONSTRUCTORS ------------------------------------- */

    /**
     * AbstractBackground constructor
     *
     * @param texture the texture of the background
     * @param scrollingSpeed the scrolling speed
     * @param scrollWidth the scrolling width
     */
    public AbstractBackground(Texture texture, float scrollingSpeed, float scrollWidth) {
        super(texture, 1);
        this.textureWidth = texture.getWidth();
        this.textureHeight = texture.getHeight();
        setHorizontalScrollAmount(0);
        setVerticalScrollAmount(0);
        setScrollingSpeed(scrollingSpeed);
        setScrollWidth(scrollWidth);
        initScroll();

    }

    /**
     * AbstractBackground constructor
     *
     * @param texture the texture of the background
     * @param scaleFactor the scale factor of the background
     * @param scrollingSpeed the scrolling speed
     * @param scrollWidth the scrolling width
     */
    public AbstractBackground(Texture texture, float scaleFactor, float scrollingSpeed, float scrollWidth) {
        super(texture, scaleFactor);
        this.textureWidth = texture.getWidth();
        this.textureHeight = texture.getHeight();
        setHorizontalScrollAmount(0);
        setVerticalScrollAmount(0);
        setScrollingSpeed(scrollingSpeed);
        setScrollWidth(scrollWidth);
        initScroll();
    }

    /**
     * AbstractBackground constructor
     *
     * @param texture the texture of the background
     * @param backgroundWidth the width of the background. Different form original width.
     * @param backgroundHeight the height of the background. Different form original height.
     * @param scrollingSpeed the scrolling speed
     * @param scrollWidth the scrolling width
     */
    public AbstractBackground(Texture texture, int backgroundWidth, int backgroundHeight, float scrollingSpeed, float scrollWidth) {
        super(texture, backgroundWidth, backgroundHeight);
        this.textureWidth = texture.getWidth();
        this.textureHeight = texture.getHeight();
        setHorizontalScrollAmount(0);
        setVerticalScrollAmount(0);
        setScrollingSpeed(scrollingSpeed);
        setScrollWidth(scrollWidth);
        initScroll();

    }

    /**
     * AbstractBackground constructor
     *
     * @param texture the texture of the background
     * @param backgroundWidth the width of the background. Different form original width.
     * @param backgroundHeight the height of the background. Different form original height.
     * @param scaleFactor the scale factor of the background
     * @param scrollingSpeed the scrolling speed
     * @param scrollWidth the scrolling width
     */
    public AbstractBackground(Texture texture, int backgroundWidth, int backgroundHeight, float scaleFactor, float scrollingSpeed, float scrollWidth) {
        super(texture, backgroundWidth, backgroundHeight, scaleFactor);
        this.textureWidth = texture.getWidth();
        this.textureHeight = texture.getHeight();
        setHorizontalScrollAmount(0);
        setVerticalScrollAmount(0);
        setScrollingSpeed(scrollingSpeed);
        setScrollWidth(scrollWidth);
        initScroll();
    }

    /**
     * AbstractBackground constructor
     *
     * @param texture the texture of the background
     * @param srcX the initial x position
     * @param srcY the initial y position
     * @param backgroundWidth the width of the background. Different form original width.
     * @param backgroundHeight the height of the background. Different form original height.
     * @param scrollingSpeed the scrolling speed
     * @param scrollWidth the scrolling width
     */
    public AbstractBackground(Texture texture, int srcX, int srcY, int backgroundWidth, int backgroundHeight, float scrollingSpeed, float scrollWidth) {
        super(texture, srcX, srcY, backgroundWidth, backgroundHeight);
        this.textureWidth = texture.getWidth();
        this.textureHeight = texture.getHeight();
        setHorizontalScrollAmount(0);
        setVerticalScrollAmount(0);
        setScrollingSpeed(scrollingSpeed);
        setScrollWidth(scrollWidth);
        initScroll();
    }

    /**
     * AbstractBackground constructor
     *
     * @param texture the texture of the background
     * @param srcX the initial x position
     * @param srcY the initial y position
     * @param backgroundWidth the width of the background. Different form original width.
     * @param backgroundHeight the height of the background. Different form original height.
     * @param scaleFactor the scale factor of the background
     * @param scrollingSpeed the scrolling speed
     * @param scrollWidth the scrolling width
     */
    public AbstractBackground(Texture texture, int srcX, int srcY, int backgroundWidth, int backgroundHeight, float scaleFactor, float scrollingSpeed, float scrollWidth) {
        super(texture, srcX, srcY, backgroundWidth, backgroundHeight, scaleFactor);
        this.textureWidth = texture.getWidth();
        this.textureHeight = texture.getHeight();
        setHorizontalScrollAmount(0);
        setVerticalScrollAmount(0);
        setScrollingSpeed(scrollingSpeed);
        setScrollWidth(scrollWidth);
        initScroll();
    }




    /* ------------------------------------- ABSTRACT ------------------------------------- */

    /**
     * Initialize scrolling parameters
     */
    public abstract void initScroll();

    /**
     * Scroll the background
     */
    public abstract void scroll();




    /* ------------------------------------- GETTERS ------------------------------------- */

    /**
     *
     * @return the total horizontal scrolled background
     */
    public float getHorizontalScrollAmount() {
        return horizontalScrollAmount;
    }

    /**
     *
     * @return the current scrolling speed
     */
    public float getScrollingSpeed() {
        return scrollingSpeed;
    }

    /**
     *
     * @return the width of the screen when scrolled
     */
    public float getScrollWidth() {
        return scrollWidth;
    }

    /**
     * Original background width getter
     */
    public float getTextureWidth() {
        return textureWidth;
    }

    /**
     * Original background height getter
     */
    public float getTextureHeight() {
        return textureHeight;
    }

    /**
     *
     * @return the total vertical scroll background
     */
    public float getVerticalScrollAmount() { return verticalScrollAmount; }




    /* ------------------------------------- SETTERS ------------------------------------- */

    /**
     *
     * @param scrollWidth the new width when the background scroll
     */
    public void setScrollWidth(float scrollWidth) {
        this.scrollWidth = scrollWidth;
    }

    /**
     *
     * @param scrollingSpeed the new speed of the scroll
     */
    public void setScrollingSpeed(float scrollingSpeed) {
        this.scrollingSpeed = scrollingSpeed;
    }

    /**
     * This should be protected. It's an important info for meters and score
     *
     * @param horizontalScrollAmount set the actual total horizontal scroll amount
     */
    protected void setHorizontalScrollAmount(float horizontalScrollAmount) {
        this.horizontalScrollAmount = horizontalScrollAmount;
    }

    /**
     * This should be protected.
     *
     * @param verticalScrollAmount set the actual total vertical scroll amount
     */
    protected void setVerticalScrollAmount(float verticalScrollAmount) {
        this.verticalScrollAmount = verticalScrollAmount;
    }


}
