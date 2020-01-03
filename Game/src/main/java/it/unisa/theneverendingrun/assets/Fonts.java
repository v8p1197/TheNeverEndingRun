package it.unisa.theneverendingrun.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Fonts {

    public static BitmapFont scoreFont;
    public static BitmapFont meterFont;
    public static BitmapFont endScreenTitleFont;
    public static BitmapFont endScreenScoreFont;

    static void load() {
        scoreFont = loadScoreFont();
        meterFont = loadMeterFont();
        endScreenTitleFont = loadEndScreenTitleFont();
        endScreenScoreFont = loadEndScreenScoreFont();
    }

    private static BitmapFont loadEndScreenScoreFont() {
        //Generate a font object for font.ttf at size 40px
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 24;
        parameter.flip = false;
        parameter.color = Color.WHITE;

        return applyCommonParameters(parameter);
    }

    private static BitmapFont loadEndScreenTitleFont() {
        //Generate a font object for font.ttf at size 40px
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 32;
        parameter.flip = false;
        parameter.color = Color.RED;

        return applyCommonParameters(parameter);
    }

    private static BitmapFont loadScoreFont() {
        //Generate a font object for font.ttf at size 40px
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 24;
        parameter.flip = false;
        parameter.color = Color.WHITE;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 2;
        parameter.shadowOffsetX = 2;
        parameter.shadowOffsetY = 2;
        parameter.shadowColor = Color.BLACK;

        return applyCommonParameters(parameter);
    }

    private static BitmapFont loadMeterFont() {
        //Generate a font object for font.ttf at size 40px
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 18;
        parameter.flip = false;
        parameter.color = Color.WHITE;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 2;
        parameter.shadowOffsetX = 1;
        parameter.shadowOffsetY = 1;
        parameter.shadowColor = Color.BLACK;

        return applyCommonParameters(parameter);
    }

    private static BitmapFont applyCommonParameters(FreeTypeFontGenerator.FreeTypeFontParameter parameter) {
        //The following settings allow the font to scale smoothly
        parameter.magFilter = Texture.TextureFilter.Linear;
        parameter.minFilter = Texture.TextureFilter.Linear;

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/ARCADE_N.ttf"));

        var font = generator.generateFont(parameter);
        font.setUseIntegerPositions(false);

        generator.dispose();

        return font;
    }
}
