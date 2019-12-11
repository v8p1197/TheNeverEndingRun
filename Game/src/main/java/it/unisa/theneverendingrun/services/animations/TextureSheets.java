package it.unisa.theneverendingrun.services.animations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Arrays;

public class TextureSheets {

    public static TextureRegion[] split(Texture sheet, int rows, int cols) {

        var textureMatrix = TextureRegion.split(sheet, sheet.getWidth() / cols, sheet.getHeight() / rows);
        return Arrays.stream(textureMatrix).flatMap(Arrays::stream).toArray(TextureRegion[]::new);
    }

    public static TextureRegion[] split(Texture sheet, float frameWidth, float frameHeight) {
        var cols = sheet.getWidth() / frameWidth;
        var rows = sheet.getHeight() / frameHeight;
        return split(sheet, rows, cols);
    }



}
