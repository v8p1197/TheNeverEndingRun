package it.unisa.theneverendingrun.models.background;

import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.models.Sprite;

public class Background extends Sprite {

    /**
     * Store the background width
     */
    private int originBackgroundWidth;

    /**
     * Store the background height
     */
    private int originBackgroundHeight;

    /**
     * Background constructor. Set the initial height and width
     *
     * @param texture   texture of the background
     * @param srcWidth  source width of the background
     * @param srcHeight source height of the background
     */
    Background(Texture texture, int srcWidth, int srcHeight) {
        super(texture, srcWidth, srcHeight);
        setOriginBackgroundWidth(texture.getWidth());
        setOriginBackgroundHeight(texture.getHeight());
    }

    @Override
    public void setTexture(Texture texture) {
        super.setTexture(texture);
        setOriginBackgroundWidth(texture.getWidth());
        setOriginBackgroundHeight(texture.getHeight());
    }

    /**
     * Background width getter
     */
    public int getOriginBackgroundWidth() {
        return originBackgroundWidth;
    }

    /**
     * Background width setter
     *
     * @param originBackgroundWidth width to set
     */
    private void setOriginBackgroundWidth(int originBackgroundWidth) {
        this.originBackgroundWidth = originBackgroundWidth;
    }

    /**
     * Background height getter
     */
    public int getOriginBackgroundHeight() {
        return originBackgroundHeight;
    }

    /**
     * Background height setter
     *
     * @param originBackgroundHeight height to set
     */
    private void setOriginBackgroundHeight(int originBackgroundHeight) {
        this.originBackgroundHeight = originBackgroundHeight;
    }
}
