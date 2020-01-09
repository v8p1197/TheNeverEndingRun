package it.unisa.theneverendingrun.services.spawn.creation.resize;

import it.unisa.theneverendingrun.models.Sprite;

import java.util.concurrent.ThreadLocalRandom;

public interface Resizable {

    default void resize(Sprite sprite, float maxValue, ResizeStrategy strategy) {
        var spriteValue = strategy.getCurrentValue(sprite);

        if (spriteValue <= 0 || spriteValue >= maxValue) {
            var min = spriteValue * 0.5 - 1;
            var max = spriteValue - 1;

            var newValue = (float) ThreadLocalRandom.current().nextDouble(min, max);
            strategy.setResizedValue(sprite, newValue);
        }
    }

    default void resizeTo(Sprite sprite, float toSize, ResizeStrategy strategy) {
        if (strategy.getCurrentValue(sprite) != toSize)
            strategy.setResizedValue(sprite, toSize);
    }
}
