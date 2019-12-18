package it.unisa.theneverendingrun.models.obstacles;

import com.badlogic.gdx.graphics.Texture;

import java.util.concurrent.ThreadLocalRandom;

public class JumpableSlidableObstacle extends AbstractObstacle {


    public JumpableSlidableObstacle(Texture texture, float maxJumpHeight, float maxSlideDistance) {
        super(texture);
        generateDimensions(maxJumpHeight, maxSlideDistance);
    }

    private void generateDimensions(double maxJumpHeight, float maxSlideDistance) {

        var minGapHeight = maxJumpHeight * 0.4;
        var maxGapHeight = maxJumpHeight * 0.6;

        var minGapWidth = maxSlideDistance * 0.5;
        var maxGapWidth = maxSlideDistance - 2;

        var randomHeight = (float) ThreadLocalRandom.current().nextDouble(minGapHeight, maxGapHeight);
        var randomWidth = (float) ThreadLocalRandom.current().nextDouble(minGapWidth, maxGapWidth);

        setSize(randomWidth, randomHeight);
    }


}
