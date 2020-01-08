package it.unisa.theneverendingrun.services.factories;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.SpriteType;
import it.unisa.theneverendingrun.models.background.AbstractScrollingBackground;
import it.unisa.theneverendingrun.models.hero.Hero;
import it.unisa.theneverendingrun.models.hero.HeroStateType;

import java.util.List;
import java.util.Map;

public interface GameFactory {

    AbstractScrollingBackground createBackground();

    Hero createHero();

    Map<HeroStateType, Animation<TextureRegion>> getHeroAnimations();

    Sprite createObstacle(SpriteType spriteType);

    Sprite createEnemy();

    Sprite createPowerUp();

    List<Texture> getPowerUpTextures();
}
