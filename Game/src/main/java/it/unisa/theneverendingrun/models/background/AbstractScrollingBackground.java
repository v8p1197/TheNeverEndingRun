package it.unisa.theneverendingrun.models.background;

import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.models.Sprite;

/**
 * An abstraction of the AbstractBackground class that adds scrolling support.
 * In particular, the scrollingSpeed, scrollingWidth and scrollingAmount parameters are used for scrolling.
 * To underline the importance of scrollingAmount as it allows you to keep track of how much, indeed,
 * the background has moved since its creation.
 * Useful for example for calculating the meters traveled.
 */
public abstract class AbstractScrollingBackground extends Background {

    /* ------------------------------------- PARAMS ------------------------------------- */

    /**
     *
     * Contains the amount of background scrolled horizontally
     * by the {@link AbstractScrollingBackground#scroll()} method.
     */
    private float horizontalScrollAmount;

    /**
     *
     * Contains the amount of background scrolled vertically
     * by the {@link AbstractScrollingBackground#scroll()} method.
     */
    private float verticalScrollAmount;

    /**
     *
     * Contains the speed with which the background should scroll. Usually used in the
     * {@link AbstractScrollingBackground#scroll()} method.
     */
    private float scrollingSpeed;

    /**
     *
     * Contains the amount of background to scroll.
     * Usually used in the {@link AbstractScrollingBackground#scroll()} method.
     */
    private float scrollWidth;


    /* ------------------------------------- CONSTRUCTORS ------------------------------------- */

    /**
     *
     * @see AbstractScrollingBackground#scrollingSpeed
     * @see AbstractScrollingBackground#scrollWidth
     * @see Sprite#Sprite(Texture, int, int, int, int, float)
     *
     * Set the {@link AbstractScrollingBackground#scrollingSpeed},
     * the {@link AbstractScrollingBackground#scrollWidth},
     * the {@link AbstractScrollingBackground#horizontalScrollAmount} and the
     * {@link AbstractScrollingBackground#verticalScrollAmount} to 0
     * and in the end call {@link AbstractScrollingBackground#initScroll()}
     *
     * @see AbstractScrollingBackground#horizontalScrollAmount
     * @see AbstractScrollingBackground#verticalScrollAmount
     * @see AbstractScrollingBackground#scrollWidth
     * @see AbstractScrollingBackground#scrollingSpeed
     *
     * @param scrollingSpeed {@link AbstractScrollingBackground#scrollingSpeed}
     * @param scrollWidth {@link AbstractScrollingBackground#scrollWidth}
     *
     */
    public AbstractScrollingBackground(Texture texture, float scrollingSpeed, float scrollWidth) {
        this(texture, 0, 0, texture.getWidth(), texture.getHeight(), 1, scrollingSpeed, scrollWidth);
    }

    /**
     *
     * @see AbstractScrollingBackground#scrollingSpeed
     * @see AbstractScrollingBackground#scrollWidth
     * @see Sprite#Sprite(Texture, int, int, int, int, float)
     *
     * Set the {@link AbstractScrollingBackground#scrollingSpeed},
     * the {@link AbstractScrollingBackground#scrollWidth},
     * the {@link AbstractScrollingBackground#horizontalScrollAmount} and the
     * {@link AbstractScrollingBackground#verticalScrollAmount} to 0
     * and in the end call {@link AbstractScrollingBackground#initScroll()}
     *
     * @see AbstractScrollingBackground#horizontalScrollAmount
     * @see AbstractScrollingBackground#verticalScrollAmount
     * @see AbstractScrollingBackground#scrollWidth
     * @see AbstractScrollingBackground#scrollingSpeed
     *
     * @param scrollingSpeed {@link AbstractScrollingBackground#scrollingSpeed}
     * @param scrollWidth {@link AbstractScrollingBackground#scrollWidth}
     *
     */
    public AbstractScrollingBackground(Texture texture, float scaleFactor, float scrollingSpeed, float scrollWidth) {
        this(texture, 0, 0, texture.getWidth(), texture.getHeight(), scaleFactor, scrollingSpeed, scrollWidth);
    }

    /**
     *
     * @see AbstractScrollingBackground#scrollingSpeed
     * @see AbstractScrollingBackground#scrollWidth
     * @see Sprite#Sprite(Texture, int, int, int, int, float)
     *
     * Set the {@link AbstractScrollingBackground#scrollingSpeed},
     * the {@link AbstractScrollingBackground#scrollWidth},
     * the {@link AbstractScrollingBackground#horizontalScrollAmount} and the
     * {@link AbstractScrollingBackground#verticalScrollAmount} to 0
     * and in the end call {@link AbstractScrollingBackground#initScroll()}
     *
     * @see AbstractScrollingBackground#horizontalScrollAmount
     * @see AbstractScrollingBackground#verticalScrollAmount
     * @see AbstractScrollingBackground#scrollWidth
     * @see AbstractScrollingBackground#scrollingSpeed
     *
     * @param scrollingSpeed {@link AbstractScrollingBackground#scrollingSpeed}
     * @param scrollWidth {@link AbstractScrollingBackground#scrollWidth}
     *
     */
    public AbstractScrollingBackground(Texture texture, int backgroundWidth, int backgroundHeight, float scrollingSpeed, float scrollWidth) {
        this(texture, 0, 0, backgroundWidth, backgroundHeight, 1, scrollingSpeed, scrollWidth);
    }

    /**
     *
     * @see AbstractScrollingBackground#scrollingSpeed
     * @see AbstractScrollingBackground#scrollWidth
     * @see Sprite#Sprite(Texture, int, int, int, int, float)
     *
     * Set the {@link AbstractScrollingBackground#scrollingSpeed},
     * the {@link AbstractScrollingBackground#scrollWidth},
     * the {@link AbstractScrollingBackground#horizontalScrollAmount} and the
     * {@link AbstractScrollingBackground#verticalScrollAmount} to 0
     * and in the end call {@link AbstractScrollingBackground#initScroll()}
     *
     * @see AbstractScrollingBackground#horizontalScrollAmount
     * @see AbstractScrollingBackground#verticalScrollAmount
     * @see AbstractScrollingBackground#scrollWidth
     * @see AbstractScrollingBackground#scrollingSpeed
     *
     * @param scrollingSpeed {@link AbstractScrollingBackground#scrollingSpeed}
     * @param scrollWidth {@link AbstractScrollingBackground#scrollWidth}
     *
     */
    public AbstractScrollingBackground(Texture texture, int backgroundWidth, int backgroundHeight, float scaleFactor, float scrollingSpeed, float scrollWidth) {
        this(texture, 0, 0, backgroundWidth, backgroundHeight, scaleFactor, scrollingSpeed, scrollWidth);
    }

    /**
     *
     * @see AbstractScrollingBackground#scrollingSpeed
     * @see AbstractScrollingBackground#scrollWidth
     * @see Sprite#Sprite(Texture, int, int, int, int, float)
     *
     * Set the {@link AbstractScrollingBackground#scrollingSpeed},
     * the {@link AbstractScrollingBackground#scrollWidth},
     * the {@link AbstractScrollingBackground#horizontalScrollAmount} and the
     * {@link AbstractScrollingBackground#verticalScrollAmount} to 0
     * and in the end call {@link AbstractScrollingBackground#initScroll()}
     *
     * @see AbstractScrollingBackground#horizontalScrollAmount
     * @see AbstractScrollingBackground#verticalScrollAmount
     * @see AbstractScrollingBackground#scrollWidth
     * @see AbstractScrollingBackground#scrollingSpeed
     *
     * @param scrollingSpeed {@link AbstractScrollingBackground#scrollingSpeed}
     * @param scrollWidth {@link AbstractScrollingBackground#scrollWidth}
     *
     */
    public AbstractScrollingBackground(Texture texture, int srcX, int srcY, int backgroundWidth, int backgroundHeight, float scrollingSpeed, float scrollWidth) {
        this(texture, srcX, srcY, backgroundWidth, backgroundHeight, 1, scrollingSpeed, scrollWidth);
    }

    /**
     *
     * @see AbstractScrollingBackground#scrollingSpeed
     * @see AbstractScrollingBackground#scrollWidth
     * @see Sprite#Sprite(Texture, int, int, int, int, float)
     *
     * Set the {@link AbstractScrollingBackground#scrollingSpeed},
     * the {@link AbstractScrollingBackground#scrollWidth},
     * the {@link AbstractScrollingBackground#horizontalScrollAmount} and the
     * {@link AbstractScrollingBackground#verticalScrollAmount} to 0
     * and in the end call {@link AbstractScrollingBackground#initScroll()}
     *
     * @see AbstractScrollingBackground#horizontalScrollAmount
     * @see AbstractScrollingBackground#verticalScrollAmount
     * @see AbstractScrollingBackground#scrollWidth
     * @see AbstractScrollingBackground#scrollingSpeed
     *
     * @param scrollingSpeed {@link AbstractScrollingBackground#scrollingSpeed}
     * @param scrollWidth {@link AbstractScrollingBackground#scrollWidth}
     *
     */
    public AbstractScrollingBackground(Texture texture, int srcX, int srcY, int backgroundWidth, int backgroundHeight, float scaleFactor, float scrollingSpeed, float scrollWidth) {
        super(texture, srcX, srcY, backgroundWidth, backgroundHeight, scaleFactor);
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
     * @see AbstractScrollingBackground#horizontalScrollAmount
     * @return the total horizontal scrolled background
     */
    public float getHorizontalScrollAmount() {
        return horizontalScrollAmount;
    }

    /**
     *
     * @see AbstractScrollingBackground#scrollingSpeed
     * @return the current scrolling speed
     */
    public float getScrollingSpeed() {
        return scrollingSpeed;
    }

    /**
     *
     * @see AbstractScrollingBackground#scrollWidth
     * @return the width of the screen when scrolled
     */
    public float getScrollWidth() {
        return scrollWidth;
    }

    /**
     *
     * @see AbstractScrollingBackground#verticalScrollAmount
     * @return the total vertical scrolled background
     */
    public float getVerticalScrollAmount() { return verticalScrollAmount; }




    /* ------------------------------------- SETTERS ------------------------------------- */

    /**
     *
     * @see AbstractScrollingBackground#scrollWidth
     * @param scrollWidth the new width when the background scroll
     */
    public void setScrollWidth(float scrollWidth) {
        this.scrollWidth = scrollWidth;
    }

    /**
     *
     * @see AbstractScrollingBackground#scrollingSpeed
     * @param scrollingSpeed the new speed of the scroll
     */
    public void setScrollingSpeed(float scrollingSpeed) {
        this.scrollingSpeed = scrollingSpeed;
    }

    /**
     *
     * This should be protected. It's an important info for meters and score
     *
     * @see AbstractScrollingBackground#horizontalScrollAmount
     * @param horizontalScrollAmount set the actual total horizontal scroll amount
     */
    protected void setHorizontalScrollAmount(float horizontalScrollAmount) {
        this.horizontalScrollAmount = horizontalScrollAmount;
    }

    /**
     *
     * This should be protected.
     *
     * @see AbstractScrollingBackground#verticalScrollAmount
     * @param verticalScrollAmount set the actual total vertical scroll amount
     */
    protected void setVerticalScrollAmount(float verticalScrollAmount) {
        this.verticalScrollAmount = verticalScrollAmount;
    }


}
