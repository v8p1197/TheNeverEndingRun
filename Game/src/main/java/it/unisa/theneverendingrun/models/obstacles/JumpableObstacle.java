package it.unisa.theneverendingrun.models.obstacles;

import com.badlogic.gdx.graphics.Texture;

import java.util.concurrent.ThreadLocalRandom;

public class JumpableObstacle extends AbstractObstacle {

    private static Texture textureJumpable = new Texture("images/jumpable.png");

    /**
     * JumpableObstacle constructor. Set the texture, (x,y), max jumpable height, max width and generate the dimension.
     *
     * @param maxJumpHeight max jumpable height
     */
    public JumpableObstacle(float maxJumpHeight, float maxWidth) {
        super(textureJumpable);
        generateDimensions(maxJumpHeight, maxWidth);
    }

    /**
     * Generate randomly the dimension of the jumpable obstacles given min max gap
     */
    private void generateDimensions(float maxJumpHeight, float maxWidth) {
        var minGapHeight = maxJumpHeight * 0.4;
        var maxGapHeight = maxJumpHeight - 2;

        var minGapWidth = maxWidth;
        var maxGapWidth = maxWidth * 3;

        var randomHeight = (float) ThreadLocalRandom.current().nextDouble(minGapHeight, maxGapHeight);
        var randomWidth = (float) ThreadLocalRandom.current().nextDouble(minGapWidth, maxGapWidth);
        setSize(randomWidth, randomHeight);
    }

}
