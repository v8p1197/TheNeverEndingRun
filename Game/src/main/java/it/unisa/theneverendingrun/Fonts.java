package it.unisa.theneverendingrun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Fonts {

    public static BitmapFont scoreFont;

    public static void load() {
        loadScoreFont();
        //
    }

    private static void loadScoreFont() {
        //Generate a font object for font.ttf at size 40px
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 40;
        parameter.flip = false;

        //The following settings allow the font to scale smoothly
        parameter.magFilter = Texture.TextureFilter.Linear;
        parameter.minFilter = Texture.TextureFilter.Linear;

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Arcadepix.ttf"));

        scoreFont = generator.generateFont(parameter);
        scoreFont.setUseIntegerPositions(false);

        generator.dispose();
    }

}
