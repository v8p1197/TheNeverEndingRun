package it.unisa.theneverendingrun.services.spawn.position;

import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.SpriteType;
import it.unisa.theneverendingrun.models.hero.Hero;

public interface PositioningStrategy {

    float getYCoordinate(Sprite newSprite, SpriteType previousSprite, Hero hero);

}
