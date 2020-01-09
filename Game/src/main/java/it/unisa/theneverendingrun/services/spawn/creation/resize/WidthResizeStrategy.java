package it.unisa.theneverendingrun.services.spawn.creation.resize;

import it.unisa.theneverendingrun.models.Sprite;

public class WidthResizeStrategy implements ResizeStrategy {

    @Override
    public float getCurrentValue(Sprite sprite) {
        return sprite.getWidth();
    }

    @Override
    public void setResizedValue(Sprite sprite, float value) {
        sprite.setSize(value, sprite.getHeight());
    }
}
