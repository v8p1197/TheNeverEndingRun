package it.unisa.theneverendingrun.utilities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public final class TextureUtils {

    public static TextureRegion[] toVector(String path, String extension, int size) {
        var regions = new TextureRegion[size];

        for(int i = 0; i < size; ++i)
            regions[i] = new TextureRegion(new Texture(path + (i + 1) + extension));

        return regions;
    }
}
