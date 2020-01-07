package it.unisa.theneverendingrun.utilities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public final class TextureUtils {

    public static TextureRegion[] toVector(String path, String extension, int size) {
        if (path == null) throw new NullPointerException("path is null");
        if (path.isEmpty()) throw new IllegalArgumentException("path is not valid");

        if (extension == null) throw new NullPointerException("extension is null");

        if (size < 0) throw new IllegalArgumentException("size is not valid");

        var regions = new TextureRegion[size];

        for(int i = 0; i < size; ++i)
            regions[i] = new TextureRegion(new Texture(path + (i + 1) + "." + extension));

        return regions;
    }
}
