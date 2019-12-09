package it.unisa.theneverendingrun.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;
import org.mini2Dx.core.graphics.SpriteSheet;

public class SpriteHandler {

    /**
     * The array of sprites needed for the animation
     */
    private static org.mini2Dx.core.graphics.Sprite[] sprites;

    /**
     * Gives an array of sprites from a given texture,
     * divided in frames using the width of every sprite (need to be the same for every sprite)
     * @param filePath      the directory of the image used to extract the texture
     * @param spriteWidth   is the width of every sprite contained into the texture
     * @return an array of sprites of the same dimension
     */
    public static Sprite[] getSprites(String filePath, int spriteWidth){
        var strip = new Texture(Gdx.files.internal(filePath));
        sprites = new Sprite[strip.getWidth()/spriteWidth];
        var height = strip.getHeight();
        var spriteSheet = new SpriteSheet(strip, spriteWidth, height);
        for (int i = 0; i < spriteSheet.getTotalFrames(); i++) {
            sprites[i] = spriteSheet.getSprite(i);
            sprites[i].flip(false, true);
        }
        return sprites;
    }
}
