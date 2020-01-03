package it.unisa.theneverendingrun.models.background.impls;

import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.background.AbstractBackground;

import static com.badlogic.gdx.graphics.Texture.TextureWrap.MirroredRepeat;
import static com.badlogic.gdx.graphics.Texture.TextureWrap.Repeat;

/**
 *
 * An implementation of the {@link AbstractBackground} class that uses predefined parameters for
 * background creation and scrolling.
 */
public class ForestBackground extends AbstractBackground {

    private static final Texture texture = new Texture("images/forest/backgrounds/background_1.png");

    // Experimental for this Background
    private static final float SCROLLING_SPEED = 0.002F;
    private static final float SCROLLING_WIDTH = 2.0F;

    //calculated for this background (texture background_1)
    /**
     *
     * The real x axis base.
     * For this {@link ForestBackground#texture} the x base isn't 0.
     */
    public static final float BASE_X = 0.3F;

    /**
     *
     * The real y axis base.
     * For this {@link ForestBackground#texture} the y base isn't 0.
     */
    public static final float BASE_Y = 0.0625F;


    /**
     *
     * @see AbstractBackground#AbstractBackground(Texture, int, int, int, int, float, float, float)
     *
     * The forest background is small. We need the screen size for the width and height
     *
     * @param screenWidth the width of the background. Different form original width.
     * @param screenHeight the height of the background. Different form original height.
     */
    public ForestBackground(int screenWidth, int screenHeight) {
        super(texture, 0, 0, screenWidth, screenHeight, SCROLLING_SPEED, SCROLLING_WIDTH);
    }

    /**
     *
     * Duplicate the background, being small, to allow the screen, while scrolling,
     * not to display areas without background and then flip the second part to match the conjunctions
     */
    @Override
    public void initScroll() {
        this.getTexture().setWrap(MirroredRepeat, Repeat);
        this.flip(false, true);
    }

    /**
     *
     * Scroll based on U vertices. We don't use {@link Sprite#scroll(float, float)}
     * because the scroll method reinitialize the background X and Y axis every scroll
     * The scroll save the scroll amount
     */
    @Override
    public void scroll() {
        setHorizontalScrollAmount(getHorizontalScrollAmount() + getScrollWidth() * getScrollingSpeed());
        this.setU(getHorizontalScrollAmount());
        this.setU2(getHorizontalScrollAmount() + getScrollWidth());
    }
}
