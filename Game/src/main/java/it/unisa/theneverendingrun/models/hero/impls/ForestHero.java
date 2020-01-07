package it.unisa.theneverendingrun.models.hero.impls;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import it.unisa.theneverendingrun.models.hero.AbstractHero;
import it.unisa.theneverendingrun.models.hero.HeroStateType;
import it.unisa.theneverendingrun.utilities.TextureUtils;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * An implementation of the {@link AbstractHero} class that uses predefined constructor.
 * The only one necessaries for this hero
 */
public class ForestHero extends AbstractHero {

    //experimental for this implementation
    private final static float SCALE_FACTOR = 3.0f;

    private static final Map<HeroStateType, Animation<TextureRegion>> ANIMATIONS;

    static {
        final String HERO_FRAME_PATH = "images/forest/hero/";

        final var DEATH_FRAME_COUNT = 13;
        final var FALL_FRAME_COUNT = 13;
        final var IDLE_FRAME_COUNT = 13;
        final var JUMP_FRAME_COUNT = 13;
        final var RUN_FRAME_COUNT = 8;
        final var SLIDE_FRAME_COUNT = 13;

        final var DEATH_FRAMES = TextureUtils.toVector(HERO_FRAME_PATH + "hero_death/hero_death_", "png", DEATH_FRAME_COUNT);
        final var FALL_FRAMES = TextureUtils.toVector(HERO_FRAME_PATH + "hero_jump/hero_jump_", "png", FALL_FRAME_COUNT);
        final var STAND_FRAMES = TextureUtils.toVector(HERO_FRAME_PATH + "hero_stand/hero_idle_", "png", IDLE_FRAME_COUNT);
        final var JUMP_FRAMES = TextureUtils.toVector(HERO_FRAME_PATH + "hero_jump/hero_jump_", "png", JUMP_FRAME_COUNT);
        final var RUN_FRAMES = TextureUtils.toVector(HERO_FRAME_PATH + "hero_run/hero_run_", "png", RUN_FRAME_COUNT);
        final var SLIDE_FRAMES = TextureUtils.toVector(HERO_FRAME_PATH + "hero_slide/hero_slide_", "png", SLIDE_FRAME_COUNT);

        ANIMATIONS = new HashMap<>();
        ANIMATIONS.put(HeroStateType.DEAD, new Animation<>(2F, DEATH_FRAMES));
        ANIMATIONS.put(HeroStateType.FALL, new Animation<>(2F, FALL_FRAMES));
        ANIMATIONS.put(HeroStateType.STAND, new Animation<>(2F, STAND_FRAMES));
        ANIMATIONS.put(HeroStateType.JUMP, new Animation<>(2F, JUMP_FRAMES));
        ANIMATIONS.put(HeroStateType.RUN, new Animation<>(2F, RUN_FRAMES));
        ANIMATIONS.put(HeroStateType.SLIDE, new Animation<>(2F, SLIDE_FRAMES));
    }

    /**
     *
     * @see AbstractHero#AbstractHero(float, float, float, Map)
     */
    public ForestHero(float x, float y) {
        super(SCALE_FACTOR, x, y, ANIMATIONS);
    }
}
