package it.unisa.theneverendingrun.models.background;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import static com.badlogic.gdx.graphics.Texture.TextureWrap.MirroredRepeat;
import static com.badlogic.gdx.graphics.Texture.TextureWrap.Repeat;

public class ForestScrollingBackground extends AbstractScrollingBackground {

    private static final Texture texture = new Texture("images/backgrounds/forest.png");

    public ForestScrollingBackground(float scrollingSpeed, float scrollWidth) {
        super(texture, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), scrollingSpeed, scrollWidth);
    }

    @Override
    public void initScroll() {
        this.getTexture().setWrap(MirroredRepeat, Repeat);
        this.flip(false, true);
    }

    @Override
    public void scroll() {
        setScrollAmount(getScrollAmount() + getScrollWidth()*getScrollingSpeed());
        this.setU(getScrollAmount());
        this.setU2(getScrollAmount() + getScrollWidth());
    }
}
