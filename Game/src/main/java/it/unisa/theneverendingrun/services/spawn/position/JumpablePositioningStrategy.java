package it.unisa.theneverendingrun.services.spawn.position;

import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.SpriteType;
import it.unisa.theneverendingrun.models.hero.Hero;

public class JumpablePositioningStrategy implements PositioningStrategy {
    @Override
    public float getYCoordinate(Sprite newSprite, SpriteType previousSprite, Hero hero) {
        return hero.getGroundY();
    }
}
