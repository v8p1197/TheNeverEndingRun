package it.unisa.theneverendingrun.models.obstacles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.concurrent.ThreadLocalRandom;

public class SlidableObstacle extends AbstractObstacle {

    private float maxSlideDistance;

    public SlidableObstacle(Texture texture, int srcX, int srcY, float maxSlideDistance) {
        super(texture, srcX, srcY);
        setMaxSlideDistance(maxSlideDistance);

    }

    private void setMaxSlideDistance(float maxSlideDistance) {
        this.maxSlideDistance = maxSlideDistance;
    }


    @Override
    public void generateDimensions() {
        var maxGap = maxSlideDistance - 2;
        var minGap = (maxSlideDistance / 2) - 2;

        var dimension = (float)ThreadLocalRandom.current().nextDouble(maxGap - minGap);
        setSize(dimension, Gdx.graphics.getHeight() - getY());
    }

}
