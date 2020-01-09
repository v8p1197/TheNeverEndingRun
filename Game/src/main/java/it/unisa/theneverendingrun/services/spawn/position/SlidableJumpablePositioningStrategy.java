package it.unisa.theneverendingrun.services.spawn.position;

import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.SpriteType;
import it.unisa.theneverendingrun.models.hero.Hero;

public class SlidableJumpablePositioningStrategy implements PositioningStrategy {
    @Override
    public float getYCoordinate(Sprite newSprite, Sprite previousSprite, SpriteType previousSpriteType, Hero hero, float maxWidth) {
        /*float y = hero.getGroundY() +
                (float) ThreadLocalRandom.current().nextDouble(hero.getSlideStandardHeight() + 1,
                        hero.getStandardHeight() - 1);*/
        var y = hero.getGroundY() + hero.getSlideStandardHeight() + 1;

        if (y + newSprite.getHeight() > hero.getJumpMaxElevation())
            newSprite.setSize(newSprite.getWidth(), hero.getJumpMaxElevation() - y);

        if (previousSpriteType == SpriteType.JUMPABLE && previousSprite.getX() + previousSprite.getWidth() >= maxWidth - 1) {
            y += previousSprite.getHeight();
        }
        return y;
    }

    @Override
    public float getXCoordinate(Sprite newSprite, Sprite previousSprite, SpriteType previousSpriteType, Hero hero, float maxWidth) {
        return maxWidth;
    }
}
