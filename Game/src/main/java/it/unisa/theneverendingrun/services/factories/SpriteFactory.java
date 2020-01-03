package it.unisa.theneverendingrun.services.factories;

import it.unisa.theneverendingrun.models.Sprite;

public interface SpriteFactory {

    Sprite createSlidableSprite(float maxWidth);
    Sprite createJumpableSprite(float maxHeight);
    Sprite createJumpableSlideableSprite(float maxHeight, float maxWidth);
}
