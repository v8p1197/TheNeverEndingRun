package it.unisa.theneverendingrun.models.background;

import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.models.Sprite;

/**
 *
 * An abstraction of the Sprite class that adds scrolling support.
 * In particular, the scrollingSpeed, scrollingWidth and scrollingAmount parameters are used for scrolling.
 * To underline the importance of scrollingAmount as it allows you to keep track of how much, indeed,
 * the background has moved since its creation.
 * Useful for example for calculating the meters traveled.
 */
public abstract class AbstractBackground extends Sprite {

    /* ------------------------------------- PARAMS ------------------------------------- */

    /**
     *
     * Contains the amount of background scrolled horizontally by the {@link AbstractBackground#scroll()} method.
     */
    private float horizontalScrollAmount;

    /**
     *
     * Contains the amount of background scrolled vertically by the {@link AbstractBackground#scroll()} method.
     */
    private float verticalScrollAmount;

    /**
     *
     * Contains the speed with which the background should scroll. Usually used in the
     * {@link AbstractBackground#scroll()} method.
     */
    private float scrollingSpeed;

    /**
     *
     * Contains the amount of background to scroll. Usually used in the {@link AbstractBackground#scroll()} method.
     */
    private float scrollWidth;

    /**
     *
     * Contains the original background width
     */
    private final float textureWidth;

    /**
     *
     * Contains the original background height
     */
    private final float textureHeight;



    /* ------------------------------------- CONSTRUCTORS ------------------------------------- */

    /**
     *
     * @see AbstractBackground#scrollingSpeed
     * @see AbstractBackground#scrollWidth
     * @see Sprite#Sprite(Texture, int, int, int, int, float)
     *
     * Set the {@link AbstractBackground#textureWidth}, the {@link AbstractBackground#textureHeight},
     * the {@link AbstractBackground#scrollingSpeed}, the {@link AbstractBackground#scrollWidth},
     * the {@link AbstractBackground#horizontalScrollAmount} and the
     * {@link AbstractBackground#verticalScrollAmount} to 0 and in the end call {@link AbstractBackground#initScroll()}
     *
     * @see AbstractBackground#horizontalScrollAmount
     * @see AbstractBackground#verticalScrollAmount
     * @see AbstractBackground#textureWidth
     * @see AbstractBackground#textureHeight
     * @see AbstractBackground#scrollWidth
     * @see AbstractBackground#scrollingSpeed
     *
     * @param scrollingSpeed {@link AbstractBackground#scrollingSpeed}
     * @param scrollWidth {@link AbstractBackground#scrollWidth}
     *
     */
    public AbstractBackground(Texture texture, float scrollingSpeed, float scrollWidth) {
        this(texture, 0,0, texture.getWidth(), texture.getHeight(), 1, scrollingSpeed, scrollWidth);
    }

    /**
     *
     * @see AbstractBackground#scrollingSpeed
     * @see AbstractBackground#scrollWidth
     * @see Sprite#Sprite(Texture, int, int, int, int, float)
     *
     * Set the {@link AbstractBackground#textureWidth}, the {@link AbstractBackground#textureHeight},
     * the {@link AbstractBackground#scrollingSpeed}, the {@link AbstractBackground#scrollWidth},
     * the {@link AbstractBackground#horizontalScrollAmount} and the
     * {@link AbstractBackground#verticalScrollAmount} to 0 and in the end call {@link AbstractBackground#initScroll()}
     *
     * @see AbstractBackground#horizontalScrollAmount
     * @see AbstractBackground#verticalScrollAmount
     * @see AbstractBackground#textureWidth
     * @see AbstractBackground#textureHeight
     * @see AbstractBackground#scrollWidth
     * @see AbstractBackground#scrollingSpeed
     *
     * @param scrollingSpeed the speed of the scrolling
     * @param scrollWidth the width of the scrolling
     *
     */
    public AbstractBackground(Texture texture, float scaleFactor, float scrollingSpeed, float scrollWidth) {
        this(texture, 0,0, texture.getWidth(), texture.getHeight(), scaleFactor, scrollingSpeed, scrollWidth);
    }

    /**
     *
     * @see AbstractBackground#scrollingSpeed
     * @see AbstractBackground#scrollWidth
     * @see Sprite#Sprite(Texture, int, int, int, int, float)
     *
     * Set the {@link AbstractBackground#textureWidth}, the {@link AbstractBackground#textureHeight},
     * the {@link AbstractBackground#scrollingSpeed}, the {@link AbstractBackground#scrollWidth},
     * the {@link AbstractBackground#horizontalScrollAmount} and the
     * {@link AbstractBackground#verticalScrollAmount} to 0 and in the end call {@link AbstractBackground#initScroll()}
     *
     * @see AbstractBackground#horizontalScrollAmount
     * @see AbstractBackground#verticalScrollAmount
     * @see AbstractBackground#textureWidth
     * @see AbstractBackground#textureHeight
     * @see AbstractBackground#scrollWidth
     * @see AbstractBackground#scrollingSpeed
     *
     * @param scrollingSpeed {@link AbstractBackground#scrollingSpeed}
     * @param scrollWidth {@link AbstractBackground#scrollWidth}
     *
     */
    public AbstractBackground(Texture texture, int backgroundWidth, int backgroundHeight, float scrollingSpeed, float scrollWidth) {
        this(texture, 0,0, backgroundWidth, backgroundHeight, 1, scrollingSpeed, scrollWidth);
    }

    /**
     *
     * @see AbstractBackground#scrollingSpeed
     * @see AbstractBackground#scrollWidth
     * @see Sprite#Sprite(Texture, int, int, int, int, float)
     *
     * Set the {@link AbstractBackground#textureWidth}, the {@link AbstractBackground#textureHeight},
     * the {@link AbstractBackground#scrollingSpeed}, the {@link AbstractBackground#scrollWidth},
     * the {@link AbstractBackground#horizontalScrollAmount} and the
     * {@link AbstractBackground#verticalScrollAmount} to 0 and in the end call {@link AbstractBackground#initScroll()}
     *
     * @see AbstractBackground#horizontalScrollAmount
     * @see AbstractBackground#verticalScrollAmount
     * @see AbstractBackground#textureWidth
     * @see AbstractBackground#textureHeight
     * @see AbstractBackground#scrollWidth
     * @see AbstractBackground#scrollingSpeed
     *
     * @param scrollingSpeed {@link AbstractBackground#scrollingSpeed}
     * @param scrollWidth {@link AbstractBackground#scrollWidth}
     *
     */
    public AbstractBackground(Texture texture, int backgroundWidth, int backgroundHeight, float scaleFactor, float scrollingSpeed, float scrollWidth) {
        this(texture, 0,0, backgroundWidth, backgroundHeight, scaleFactor, scrollingSpeed, scrollWidth);
    }

    /**
     *
     * @see AbstractBackground#scrollingSpeed
     * @see AbstractBackground#scrollWidth
     * @see Sprite#Sprite(Texture, int, int, int, int, float)
     *
     * Set the {@link AbstractBackground#textureWidth}, the {@link AbstractBackground#textureHeight},
     * the {@link AbstractBackground#scrollingSpeed}, the {@link AbstractBackground#scrollWidth},
     * the {@link AbstractBackground#horizontalScrollAmount} and the
     * {@link AbstractBackground#verticalScrollAmount} to 0 and in the end call {@link AbstractBackground#initScroll()}
     *
     * @see AbstractBackground#horizontalScrollAmount
     * @see AbstractBackground#verticalScrollAmount
     * @see AbstractBackground#textureWidth
     * @see AbstractBackground#textureHeight
     * @see AbstractBackground#scrollWidth
     * @see AbstractBackground#scrollingSpeed
     *
     * @param scrollingSpeed {@link AbstractBackground#scrollingSpeed}
     * @param scrollWidth {@link AbstractBackground#scrollWidth}
     *
     */
    public AbstractBackground(Texture texture, int srcX, int srcY, int backgroundWidth, int backgroundHeight, float scrollingSpeed, float scrollWidth) {
        this(texture, srcX,srcY, backgroundWidth, backgroundHeight, 1, scrollingSpeed, scrollWidth);
    }

    /**
     *
     * @see AbstractBackground#scrollingSpeed
     * @see AbstractBackground#scrollWidth
     * @see Sprite#Sprite(Texture, int, int, int, int, float)
     *
     * Set the {@link AbstractBackground#textureWidth}, the {@link AbstractBackground#textureHeight},
     * the {@link AbstractBackground#scrollingSpeed}, the {@link AbstractBackground#scrollWidth},
     * the {@link AbstractBackground#horizontalScrollAmount} and the
     * {@link AbstractBackground#verticalScrollAmount} to 0 and in the end call {@link AbstractBackground#initScroll()}
     *
     * @see AbstractBackground#horizontalScrollAmount
     * @see AbstractBackground#verticalScrollAmount
     * @see AbstractBackground#textureWidth
     * @see AbstractBackground#textureHeight
     * @see AbstractBackground#scrollWidth
     * @see AbstractBackground#scrollingSpeed
     *
     * @param scrollingSpeed {@link AbstractBackground#scrollingSpeed}
     * @param scrollWidth {@link AbstractBackground#scrollWidth}
     *
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
     *
     * Initialize the scrolling parameters
     */
    public abstract void initScroll();

    /**
     *
     * Scroll the background
     */
    public abstract void scroll();




    /* ------------------------------------- GETTERS ------------------------------------- */

    /**
     *
     * @see AbstractBackground#horizontalScrollAmount
     * @return the total horizontal scrolled background
     */
    public float getHorizontalScrollAmount() {
        return horizontalScrollAmount;
    }

    /**
     *
     * @see AbstractBackground#scrollingSpeed
     * @return the current scrolling speed
     */
    public float getScrollingSpeed() {
        return scrollingSpeed;
    }

    /**
     *
     * @see AbstractBackground#scrollWidth
     * @return the width of the screen when scrolled
     */
    public float getScrollWidth() {
        return scrollWidth;
    }

    /**
     *
     * @see AbstractBackground#textureWidth
     * Original background width getter
     */
    public float getTextureWidth() {
        return textureWidth;
    }

    /**
     *
     * @see AbstractBackground#textureHeight
     * Original background height getter
     */
    public float getTextureHeight() {
        return textureHeight;
    }

    /**
     *
     * @see AbstractBackground#verticalScrollAmount
     * @return the total vertical scrolled background
     */
    public float getVerticalScrollAmount() { return verticalScrollAmount; }




    /* ------------------------------------- SETTERS ------------------------------------- */

    /**
     *
     * @see AbstractBackground#scrollWidth
     * @param scrollWidth the new width when the background scroll
     */
    public void setScrollWidth(float scrollWidth) {
        this.scrollWidth = scrollWidth;
    }

    /**
     *
     * @see AbstractBackground#scrollingSpeed
     * @param scrollingSpeed the new speed of the scroll
     */
    public void setScrollingSpeed(float scrollingSpeed) {
        this.scrollingSpeed = scrollingSpeed;
    }

    /**
     *
     * This should be protected. It's an important info for meters and score
     *
     * @see AbstractBackground#horizontalScrollAmount
     * @param horizontalScrollAmount set the actual total horizontal scroll amount
     */
    protected void setHorizontalScrollAmount(float horizontalScrollAmount) {
        this.horizontalScrollAmount = horizontalScrollAmount;
    }

    /**
     *
     * This should be protected.
     *
     * @see AbstractBackground#verticalScrollAmount
     * @param verticalScrollAmount set the actual total vertical scroll amount
     */
    protected void setVerticalScrollAmount(float verticalScrollAmount) {
        this.verticalScrollAmount = verticalScrollAmount;
    }


}
