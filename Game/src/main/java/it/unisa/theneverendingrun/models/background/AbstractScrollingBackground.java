package it.unisa.theneverendingrun.models.background;

import com.badlogic.gdx.graphics.Texture;

public abstract class AbstractScrollingBackground extends Background {

    private float scrollAmount;
    private float scrollingSpeed;
    private float scrollWidth;

    AbstractScrollingBackground(Texture texture, int backgroundWidth, int backgroundHeight, float scrollAmount, float scrollingSpeed, float scrollWidth) {
        super(texture, backgroundWidth, backgroundHeight);
        setScrollAmount(scrollAmount);
        setScrollingSpeed(scrollingSpeed);
        setScrollWidth(scrollWidth);
        initScroll();
    }

    AbstractScrollingBackground(Texture texture, int backgroundWidth, int backgroundHeight, float scrollingSpeed, float scrollWidth) {
        this(texture, backgroundWidth, backgroundHeight, 0, scrollingSpeed, scrollWidth);
    }

    /**
     * Initialize scrolling parameters
     */
    public abstract void initScroll();

    public abstract void scroll();

    public float getScrollAmount() {
        return scrollAmount;
    }

    public float getScrollingSpeed() {
        return scrollingSpeed;
    }

    public float getScrollWidth() {
        return scrollWidth;
    }

    public void setScrollWidth(float scrollWidth) {
        this.scrollWidth = scrollWidth;
    }

    public void setScrollingSpeed(float scrollingSpeed) {
        this.scrollingSpeed = scrollingSpeed;
    }

    void setScrollAmount(float scrollAmount) {
        this.scrollAmount = scrollAmount;
    }

}
