package it.unisa.theneverendingrun.models.background.impls;

import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.background.AbstractScrollingBackground;
import it.unisa.theneverendingrun.models.background.Background;

import static com.badlogic.gdx.graphics.Texture.TextureWrap.MirroredRepeat;
import static com.badlogic.gdx.graphics.Texture.TextureWrap.Repeat;

/**
 * An implementation of the {@link Background} class that uses predefined parameters for
 * background creation and scrolling.
 */
public class PlayStateBackground extends AbstractScrollingBackground {


    /**
     *
     * @see AbstractScrollingBackground#AbstractScrollingBackground(Texture, int, int, int, int, float, float, float)
     *
     * The forest background is small. We need the screen size for the width and height
     */
    public PlayStateBackground(Texture texture, float scrollingSpeed, float scrollingWidth) {
        super(texture, scrollingSpeed, scrollingWidth);
    }

    /**
     *
     * @see AbstractScrollingBackground#AbstractScrollingBackground(Texture, int, int, int, int, float, float, float)
     *
     * The forest background is small. We need the screen size for the width and height
     *
     * @param screenWidth the width of the background. Different form original width.
     * @param screenHeight the height of the background. Different form original height.
     */
    public PlayStateBackground(Texture texture,
                               int screenWidth, int screenHeight, float scrollingSpeed, float scrollingWidth) {
        super(texture, 0, 0, screenWidth, screenHeight, scrollingSpeed, scrollingWidth);
    }

    /**
     *
     * Duplicate the background, being small, to allow the screen, while scrolling,
     * not to display areas without background and then flip the second part to match the conjunctions
     */
    @Override
    public void initScroll() {
        this.getTexture().setWrap(MirroredRepeat, Repeat);
        this.flip(true, false);
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
