package it.unisa.theneverendingrun.models.background;

import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.models.Sprite;

/**
 *
 * An abstraction of the AbstractBackground class that adds scrolling support.
 * In particular, the scrollingSpeed, scrollingWidth and scrollingAmount parameters are used for scrolling.
 * To underline the importance of scrollingAmount as it allows you to keep track of how much, indeed,
 * the background has moved since its creation.
 * Useful for example for calculating the meters traveled.
 */
public abstract class ScrollingBackground extends Background {

    /* ------------------------------------- PARAMS ------------------------------------- */

    /**
     *
     * Contains the amount of background scrolled horizontally
     * by the {@link ScrollingBackground#scroll()} method.
     */
    private float horizontalScrollAmount;

    /**
     *
     * Contains the amount of background scrolled vertically
     * by the {@link ScrollingBackground#scroll()} method.
     */
    private float verticalScrollAmount;

    /**
     *
     * Contains the speed with which the background should scroll. Usually used in the
     * {@link ScrollingBackground#scroll()} method.
     */
    private float scrollingSpeed;

    /**
     *
     * Contains the amount of background to scroll.
     * Usually used in the {@link ScrollingBackground#scroll()} method.
     */
    private float scrollWidth;


    /* ------------------------------------- CONSTRUCTORS ------------------------------------- */

    /**
     *
     * @see ScrollingBackground#scrollingSpeed
     * @see ScrollingBackground#scrollWidth
     * @see Sprite#Sprite(Texture, int, int, int, int, float)
     *
     * Set the {@link ScrollingBackground#scrollingSpeed},
     * the {@link ScrollingBackground#scrollWidth},
     * the {@link ScrollingBackground#horizontalScrollAmount} and the
     * {@link ScrollingBackground#verticalScrollAmount} to 0
     * and in the end call {@link ScrollingBackground#initScroll()}
     *
     * @see ScrollingBackground#horizontalScrollAmount
     * @see ScrollingBackground#verticalScrollAmount
     * @see ScrollingBackground#scrollWidth
     * @see ScrollingBackground#scrollingSpeed
     *
     * @param scrollingSpeed {@link ScrollingBackground#scrollingSpeed}
     * @param scrollWidth {@link ScrollingBackground#scrollWidth}
     *
     */
    public ScrollingBackground(Texture texture, float scrollingSpeed, float scrollWidth) {
        this(texture, 0,0, texture.getWidth(), texture.getHeight(), 1, scrollingSpeed, scrollWidth);
    }

    /**
     *
     * @see ScrollingBackground#scrollingSpeed
     * @see ScrollingBackground#scrollWidth
     * @see Sprite#Sprite(Texture, int, int, int, int, float)
     *
     * Set the {@link ScrollingBackground#scrollingSpeed},
     * the {@link ScrollingBackground#scrollWidth},
     * the {@link ScrollingBackground#horizontalScrollAmount} and the
     * {@link ScrollingBackground#verticalScrollAmount} to 0
     * and in the end call {@link ScrollingBackground#initScroll()}
     *
     * @see ScrollingBackground#horizontalScrollAmount
     * @see ScrollingBackground#verticalScrollAmount
     * @see ScrollingBackground#scrollWidth
     * @see ScrollingBackground#scrollingSpeed
     *
     * @param scrollingSpeed {@link ScrollingBackground#scrollingSpeed}
     * @param scrollWidth {@link ScrollingBackground#scrollWidth}
     *
     */
    public ScrollingBackground(Texture texture, float scaleFactor, float scrollingSpeed, float scrollWidth) {
        this(texture, 0,0, texture.getWidth(), texture.getHeight(), scaleFactor, scrollingSpeed, scrollWidth);
    }

    /**
     *
     * @see ScrollingBackground#scrollingSpeed
     * @see ScrollingBackground#scrollWidth
     * @see Sprite#Sprite(Texture, int, int, int, int, float)
     *
     * Set the {@link ScrollingBackground#scrollingSpeed},
     * the {@link ScrollingBackground#scrollWidth},
     * the {@link ScrollingBackground#horizontalScrollAmount} and the
     * {@link ScrollingBackground#verticalScrollAmount} to 0
     * and in the end call {@link ScrollingBackground#initScroll()}
     *
     * @see ScrollingBackground#horizontalScrollAmount
     * @see ScrollingBackground#verticalScrollAmount
     * @see ScrollingBackground#scrollWidth
     * @see ScrollingBackground#scrollingSpeed
     *
     * @param scrollingSpeed {@link ScrollingBackground#scrollingSpeed}
     * @param scrollWidth {@link ScrollingBackground#scrollWidth}
     *
     */
    public ScrollingBackground(Texture texture, int backgroundWidth, int backgroundHeight, float scrollingSpeed, float scrollWidth) {
        this(texture, 0,0, backgroundWidth, backgroundHeight, 1, scrollingSpeed, scrollWidth);
    }

    /**
     *
     * @see ScrollingBackground#scrollingSpeed
     * @see ScrollingBackground#scrollWidth
     * @see Sprite#Sprite(Texture, int, int, int, int, float)
     *
     * Set the {@link ScrollingBackground#scrollingSpeed},
     * the {@link ScrollingBackground#scrollWidth},
     * the {@link ScrollingBackground#horizontalScrollAmount} and the
     * {@link ScrollingBackground#verticalScrollAmount} to 0
     * and in the end call {@link ScrollingBackground#initScroll()}
     *
     * @see ScrollingBackground#horizontalScrollAmount
     * @see ScrollingBackground#verticalScrollAmount
     * @see ScrollingBackground#scrollWidth
     * @see ScrollingBackground#scrollingSpeed
     *
     * @param scrollingSpeed {@link ScrollingBackground#scrollingSpeed}
     * @param scrollWidth {@link ScrollingBackground#scrollWidth}
     *
     */
    public ScrollingBackground(Texture texture, int backgroundWidth, int backgroundHeight, float scaleFactor, float scrollingSpeed, float scrollWidth) {
        this(texture, 0,0, backgroundWidth, backgroundHeight, scaleFactor, scrollingSpeed, scrollWidth);
    }

    /**
     *
     * @see ScrollingBackground#scrollingSpeed
     * @see ScrollingBackground#scrollWidth
     * @see Sprite#Sprite(Texture, int, int, int, int, float)
     *
     * Set the {@link ScrollingBackground#scrollingSpeed},
     * the {@link ScrollingBackground#scrollWidth},
     * the {@link ScrollingBackground#horizontalScrollAmount} and the
     * {@link ScrollingBackground#verticalScrollAmount} to 0
     * and in the end call {@link ScrollingBackground#initScroll()}
     *
     * @see ScrollingBackground#horizontalScrollAmount
     * @see ScrollingBackground#verticalScrollAmount
     * @see ScrollingBackground#scrollWidth
     * @see ScrollingBackground#scrollingSpeed
     *
     * @param scrollingSpeed {@link ScrollingBackground#scrollingSpeed}
     * @param scrollWidth {@link ScrollingBackground#scrollWidth}
     *
     */
    public ScrollingBackground(Texture texture, int srcX, int srcY, int backgroundWidth, int backgroundHeight, float scrollingSpeed, float scrollWidth) {
        this(texture, srcX,srcY, backgroundWidth, backgroundHeight, 1, scrollingSpeed, scrollWidth);
    }

    /**
     *
     * @see ScrollingBackground#scrollingSpeed
     * @see ScrollingBackground#scrollWidth
     * @see Sprite#Sprite(Texture, int, int, int, int, float)
     *
     * Set the {@link ScrollingBackground#scrollingSpeed},
     * the {@link ScrollingBackground#scrollWidth},
     * the {@link ScrollingBackground#horizontalScrollAmount} and the
     * {@link ScrollingBackground#verticalScrollAmount} to 0
     * and in the end call {@link ScrollingBackground#initScroll()}
     *
     * @see ScrollingBackground#horizontalScrollAmount
     * @see ScrollingBackground#verticalScrollAmount
     * @see ScrollingBackground#scrollWidth
     * @see ScrollingBackground#scrollingSpeed
     *
     * @param scrollingSpeed {@link ScrollingBackground#scrollingSpeed}
     * @param scrollWidth {@link ScrollingBackground#scrollWidth}
     *
     */
    public ScrollingBackground(Texture texture, int srcX, int srcY, int backgroundWidth, int backgroundHeight, float scaleFactor, float scrollingSpeed, float scrollWidth) {
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
     * @see ScrollingBackground#horizontalScrollAmount
     * @return the total horizontal scrolled background
     */
    public float getHorizontalScrollAmount() {
        return horizontalScrollAmount;
    }

    /**
     *
     * @see ScrollingBackground#scrollingSpeed
     * @return the current scrolling speed
     */
    public float getScrollingSpeed() {
        return scrollingSpeed;
    }

    /**
     *
     * @see ScrollingBackground#scrollWidth
     * @return the width of the screen when scrolled
     */
    public float getScrollWidth() {
        return scrollWidth;
    }

    /**
     *
     * @see ScrollingBackground#verticalScrollAmount
     * @return the total vertical scrolled background
     */
    public float getVerticalScrollAmount() { return verticalScrollAmount; }




    /* ------------------------------------- SETTERS ------------------------------------- */

    /**
     *
     * @see ScrollingBackground#scrollWidth
     * @param scrollWidth the new width when the background scroll
     */
    public void setScrollWidth(float scrollWidth) {
        this.scrollWidth = scrollWidth;
    }

    /**
     *
     * @see ScrollingBackground#scrollingSpeed
     * @param scrollingSpeed the new speed of the scroll
     */
    public void setScrollingSpeed(float scrollingSpeed) {
        this.scrollingSpeed = scrollingSpeed;
    }

    /**
     *
     * This should be protected. It's an important info for meters and score
     *
     * @see ScrollingBackground#horizontalScrollAmount
     * @param horizontalScrollAmount set the actual total horizontal scroll amount
     */
    protected void setHorizontalScrollAmount(float horizontalScrollAmount) {
        this.horizontalScrollAmount = horizontalScrollAmount;
    }

    /**
     *
     * This should be protected.
     *
     * @see ScrollingBackground#verticalScrollAmount
     * @param verticalScrollAmount set the actual total vertical scroll amount
     */
    protected void setVerticalScrollAmount(float verticalScrollAmount) {
        this.verticalScrollAmount = verticalScrollAmount;
    }


}
