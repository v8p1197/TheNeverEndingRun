package it.unisa.theneverendingrun.models.background.impls;


import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.models.background.AbstractBackground;

/**
 *
 * An implementation of the {@link AbstractBackground} class that uses predefined parameters for
 * background creation.
 */
public class MenuStateBackground extends AbstractBackground {

    private static final Texture texture = new Texture("images/menu_state_background.png");

    /**
     *
     * @see AbstractBackground#AbstractBackground(Texture, int, int, int, int, float)
     *
     * @param screenWidth the width of the background. Different form original width.
     * @param screenHeight the height of the background. Different form original height.
     */
    public MenuStateBackground(int screenWidth, int screenHeight) {
        super(texture, 0, 0, screenWidth, screenHeight);
    }
}
