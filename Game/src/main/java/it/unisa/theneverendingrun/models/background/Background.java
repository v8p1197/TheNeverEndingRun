package it.unisa.theneverendingrun.models.background;

import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.models.Sprite;

public class Background extends Sprite {

    private int originBackgroundWidth;
    private int originBackgroundHeight;

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

    public int getOriginBackgroundWidth() {
        return originBackgroundWidth;
    }

    private void setOriginBackgroundWidth(int originBackgroundWidth) {
        this.originBackgroundWidth = originBackgroundWidth;
    }

    public int getOriginBackgroundHeight() {
        return originBackgroundHeight;
    }

    private void setOriginBackgroundHeight(int originBackgroundHeight) {
        this.originBackgroundHeight = originBackgroundHeight;
    }
}
