package it.unisa.theneverendingrun.services.spawn.creation.resize;

import it.unisa.theneverendingrun.models.Sprite;

import java.util.concurrent.ThreadLocalRandom;

public interface Resizable {

    default void resize(Sprite sprite, float maxValue, ResizeStrategy strategy) {
        var spriteValue = strategy.getCurrentValue(sprite);

        if (spriteValue <= 0 || spriteValue >= maxValue) {
            var min = maxValue * 0.65 - 1;
            var max = maxValue * 0.95 - 1;

            var newValue = maxValue - 1;
            if (min < max)
                newValue = (float) ThreadLocalRandom.current().nextDouble(min, max);

            strategy.setResizedValue(sprite, newValue);
        }
    }

    default void alwaysResize(Sprite sprite, float maxValue, ResizeStrategy strategy) {
        var spriteValue = strategy.getCurrentValue(sprite);

        var min = maxValue * 0.65 - 1;
        var max = maxValue * 0.95 - 1;

        var newValue = spriteValue;
        if (min < max)
            newValue = (float) ThreadLocalRandom.current().nextDouble(min, max);

        strategy.setResizedValue(sprite, newValue);
    }

    default void resizeTo(Sprite sprite, float toSize, ResizeStrategy strategy) {
        if (strategy.getCurrentValue(sprite) != toSize)
            strategy.setResizedValue(sprite, toSize);
    }

}
