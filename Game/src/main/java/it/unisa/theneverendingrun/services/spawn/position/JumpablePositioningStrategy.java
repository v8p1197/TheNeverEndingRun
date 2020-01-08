package it.unisa.theneverendingrun.services.spawn.position;

import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.SpriteType;
import it.unisa.theneverendingrun.models.hero.Hero;

public class JumpablePositioningStrategy implements PositioningStrategy {
    @Override
    public float getYCoordinate(Sprite newSprite, Sprite previousSprite, SpriteType previousSpriteType, Hero hero, float maxWidth) {
        return hero.getGroundY();
    }

    @Override
    public float getXCoordinate(Sprite newSprite, Sprite previousSprite, SpriteType previousSpriteType, Hero hero, float maxWidth) {
        return maxWidth;
    }
}
