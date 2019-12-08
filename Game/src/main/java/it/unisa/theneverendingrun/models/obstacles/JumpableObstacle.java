package it.unisa.theneverendingrun.models.obstacles;

import com.badlogic.gdx.graphics.Texture;

import java.util.concurrent.ThreadLocalRandom;

public class JumpableObstacle extends AbstractObstacle {

    private double maxJumpHeight;
    private double maxWidth;

    public JumpableObstacle(Texture texture, int srcX, int srcY, double maxJumpHeight, double maxWidth) {
        super(texture, srcX, srcY);
        this.maxJumpHeight = maxJumpHeight;
        this.maxWidth = maxWidth;
        generateDimensions();
    }

    @Override
    public void generateDimensions() {
        var maxGapHeight = maxJumpHeight - 2;
        var minGapHeight = maxJumpHeight * 0.7;
        var maxGapWidth = maxWidth - 1;
        var minGapWidth = maxWidth / 2;

        var randomHeight = (float) ThreadLocalRandom.current().nextDouble(minGapHeight, maxGapHeight);
        var randomWidth = (float) ThreadLocalRandom.current().nextDouble(minGapWidth, maxGapWidth);
        setSize(randomWidth, randomHeight);
    }

}
