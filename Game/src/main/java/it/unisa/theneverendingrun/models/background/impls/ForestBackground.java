package it.unisa.theneverendingrun.models.background.impls;

import com.badlogic.gdx.graphics.Texture;

public class ForestBackground extends PlayStateBackground {

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
     * @param screenWidth  the width of the background. Different form original width.
     * @param screenHeight the height of the background. Different form original height.
     * @see PlayStateBackground#PlayStateBackground(Texture, int, int, float, float)
     *
     * The forest background is small. We need the screen size for the width and height
     */
    public ForestBackground(int screenWidth, int screenHeight) {
        super(texture, screenWidth, screenHeight, SCROLLING_SPEED, SCROLLING_WIDTH);
    }
}