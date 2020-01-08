package it.unisa.theneverendingrun.services.spawn.position;

import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.SpriteType;
import it.unisa.theneverendingrun.models.hero.Hero;

import java.util.concurrent.ThreadLocalRandom;

public class SlidableJumpablePositioningStrategy implements PositioningStrategy {
    @Override
    public float getYCoordinate(Sprite newSprite, Sprite previousSprite, SpriteType previousSpriteType, Hero hero, float maxWidth) {
        return hero.getGroundY() + (float) ThreadLocalRandom.current().nextDouble(hero.getSlideStandardHeight() + 1,
                hero.getJumpMaxElevation() - newSprite.getHeight()); //todo fixme
    }

    @Override
    public float getXCoordinate(Sprite newSprite, Sprite previousSprite, SpriteType previousSpriteType, Hero hero, float maxWidth) {
        return maxWidth;
    }
}
