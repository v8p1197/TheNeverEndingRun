package it.unisa.theneverendingrun.services.spawn.creation.resize;

import it.unisa.theneverendingrun.models.Sprite;

public interface ResizeStrategy {

    float getCurrentValue(Sprite sprite);

    void setResizedValue(Sprite sprite, float value);
}
