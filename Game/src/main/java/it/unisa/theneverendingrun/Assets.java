package it.unisa.theneverendingrun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Assets {

    public static BitmapFont scoreFont;
    public static BitmapFont metersFont;

    public static void load() {
        var generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/arcade_font.ttf"));
        var parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        loadScoreFont(generator, parameter);
        loadMetersFont(generator, parameter);

        generator.dispose();
    }

    private static void loadScoreFont(FreeTypeFontGenerator generator, FreeTypeFontGenerator.FreeTypeFontParameter parameter) {
        parameter.size = 24;
        parameter.shadowOffsetX = 2;
        parameter.shadowOffsetY = 2;
        parameter.borderWidth = 3;

        basicParameters(generator, parameter);

        scoreFont = generator.generateFont(parameter);
        scoreFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    private static void loadMetersFont(FreeTypeFontGenerator generator, FreeTypeFontGenerator.FreeTypeFontParameter parameter) {
        parameter.size = 16;
        parameter.shadowOffsetX = 2;
        parameter.shadowOffsetY = 2;
        parameter.borderWidth = 2;

        basicParameters(generator, parameter);

        metersFont = generator.generateFont(parameter);
        metersFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    private static void basicParameters(FreeTypeFontGenerator generator, FreeTypeFontGenerator.FreeTypeFontParameter parameter) {
        parameter.flip = false;
        parameter.borderColor = Color.BLACK;
    }
}
