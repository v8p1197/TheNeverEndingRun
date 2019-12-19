package it.unisa.theneverendingrun.models.background;

import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.models.Sprite;

public abstract class AbstractBackground extends Sprite {

    //***************************** params *****************************//

    /**
     * Store the amount of the scroll.
     */
    private float scrollAmount;

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
    private float originWidth;

    /**
     * Store the original background height
     */
    private float originHeight;




    //***************************** constructors *****************************//

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
        this.originWidth = texture.getWidth();
        this.originHeight = texture.getHeight();
        setScrollAmount(0);
        setScrollingSpeed(scrollingSpeed);
        setScrollWidth(scrollWidth);
        initScroll();
    }

    /**
     * AbstractBackground constructor
     *
     * @param texture the texture of the background
     * @param scrollingSpeed the scrolling speed
     * @param scrollWidth the scrolling width
     */
    public AbstractBackground(Texture texture, float scrollingSpeed, float scrollWidth) {
        super(texture);
        this.originWidth = texture.getWidth();
        this.originHeight = texture.getHeight();
        setScrollAmount(0);
        setScrollingSpeed(scrollingSpeed);
        setScrollWidth(scrollWidth);
        initScroll();
    }



    //***************************** abstract methods *****************************//
    /**
     * Initialize scrolling parameters
     */
    public abstract void initScroll();

    /**
     * Scroll the background
     */
    public abstract void scroll();




    //***************************** getters *****************************//

    /**
     *
     * @return the total scrolled background
     */
    public float getScrollAmount() {
        return scrollAmount;
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
    public float getOriginWidth() {
        return originWidth;
    }

    /**
     * Original background height getter
     */
    public float getOriginHeight() {
        return originHeight;
    }




    //***************************** setters *****************************//

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
     * @param scrollAmount set the actual total scroll amount
     */
    protected void setScrollAmount(float scrollAmount) {
        this.scrollAmount = scrollAmount;
    }
}
