package it.unisa.theneverendingrun.services.spawn.creation.resize;

import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.services.spawn.creation.resize.ResizeStrategy;

public class HeightResizeStrategy implements ResizeStrategy {
    @Override
    public float getCurrentValue(Sprite sprite) {
        return sprite.getHeight();
    }

    @Override
    public void setResizedValue(Sprite sprite, float value) {
        sprite.setSize(sprite.getWidth(), value);
    }
}
