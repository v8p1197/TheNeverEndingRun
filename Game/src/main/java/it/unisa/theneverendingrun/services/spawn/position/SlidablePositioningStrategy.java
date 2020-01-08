package it.unisa.theneverendingrun.services.spawn.position;

import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.SpriteType;
import it.unisa.theneverendingrun.models.hero.Hero;

import java.util.concurrent.ThreadLocalRandom;

public class SlidablePositioningStrategy implements PositioningStrategy {
    @Override
    public float getYCoordinate(Sprite newSprite, SpriteType previousSprite, Hero hero) {
        return hero.getGroundY() + (float) ThreadLocalRandom.current().nextDouble(hero.getSlideStandardHeight() + 1,
                hero.getStandardHeight() - 1);
    }
}
