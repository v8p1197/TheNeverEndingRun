package it.unisa.theneverendingrun.services.factories.impls;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.SpriteType;
import it.unisa.theneverendingrun.models.background.AbstractScrollingBackground;
import it.unisa.theneverendingrun.models.background.Background;
import it.unisa.theneverendingrun.models.background.impls.PlayStateBackground;
import it.unisa.theneverendingrun.models.enemy.Enemy;
import it.unisa.theneverendingrun.models.enemy.EnemyStateType;
import it.unisa.theneverendingrun.models.hero.Hero;
import it.unisa.theneverendingrun.models.hero.HeroStateType;
import it.unisa.theneverendingrun.models.powerup.PowerUpType;
import it.unisa.theneverendingrun.models.powerup.impls.MultiplierPowerUp;
import it.unisa.theneverendingrun.models.powerup.impls.Shield;
import it.unisa.theneverendingrun.models.powerup.impls.Sword;
import it.unisa.theneverendingrun.services.factories.GameFactory;
import it.unisa.theneverendingrun.utilities.TextureUtils;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class ForestFactory implements GameFactory {


    private int screenWidth;
    private int screenHeight;


    public ForestFactory(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }



    /* -------------------------------- BACKGROUND -------------------------------- */

    private static final Texture BACKGROUND_TEXTURE = new Texture("images/forest/backgrounds/background_1.png");

    // Experimental for this Background
    private static final float SCROLLING_SPEED = 0.002F;
    private static final float SCROLLING_WIDTH = 2.0F;

    //calculated for this background (texture background_1)
    /**
     * The real x axis base.
     * For this {@link Background} the x base isn't 0.
     */
    public static final float BASE_X = 0.3F;

    /**
     * The real y axis base.
     * For this {@link Background} the y base isn't 0.
     */
    public static final float BASE_Y = 0.0625F;

    @Override
    public AbstractScrollingBackground createBackground() {
        return new PlayStateBackground(BACKGROUND_TEXTURE, screenWidth, screenHeight, SCROLLING_SPEED, SCROLLING_WIDTH);
    }




    /* -------------------------------- HERO -------------------------------- */


    //experimental for this implementation
    private final static float SCALE_FACTOR = 3.0f;

    public static final Map<HeroStateType, Animation<TextureRegion>> HERO_ANIMATIONS;

    static {
        final String HERO_FRAME_PATH = "images/forest/hero/";

        final var DEATH_FRAME_COUNT = 13;
        final var FALL_FRAME_COUNT = 1;
        final var IDLE_FRAME_COUNT = 13;
        final var JUMP_FRAME_COUNT = 1;
        final var RUN_FRAME_COUNT = 8;
        final var SLIDE_FRAME_COUNT = 16;

        final var DEATH_FRAMES = TextureUtils.toVector(HERO_FRAME_PATH + "hero_death/hero_death_", "png", DEATH_FRAME_COUNT);
        final var FALL_FRAMES = TextureUtils.toVector(HERO_FRAME_PATH + "hero_fall/hero_fall_", "png", FALL_FRAME_COUNT);
        final var IDLE_FRAMES = TextureUtils.toVector(HERO_FRAME_PATH + "hero_stand/hero_idle_", "png", IDLE_FRAME_COUNT);
        final var JUMP_FRAMES = TextureUtils.toVector(HERO_FRAME_PATH + "hero_jump/hero_jump_", "png", JUMP_FRAME_COUNT);
        final var RUN_FRAMES = TextureUtils.toVector(HERO_FRAME_PATH + "hero_run/hero_run_", "png", RUN_FRAME_COUNT);
        final var SLIDE_FRAMES = TextureUtils.toVector(HERO_FRAME_PATH + "hero_slide/hero_slide_", "png", SLIDE_FRAME_COUNT);

        HERO_ANIMATIONS = new HashMap<>();
        HERO_ANIMATIONS.put(HeroStateType.DEAD, new Animation<>(2F, DEATH_FRAMES));
        HERO_ANIMATIONS.put(HeroStateType.FALL, new Animation<>(0.05F, FALL_FRAMES));
        HERO_ANIMATIONS.put(HeroStateType.STAND, new Animation<>(0.05F, IDLE_FRAMES));
        HERO_ANIMATIONS.put(HeroStateType.JUMP, new Animation<>(0.05F, JUMP_FRAMES));
        HERO_ANIMATIONS.put(HeroStateType.RUN, new Animation<>(0.075F, RUN_FRAMES));
        HERO_ANIMATIONS.put(HeroStateType.SLIDE, new Animation<>(0.05F, SLIDE_FRAMES));
    }

    @Override
    public Hero createHero() {
        var textureStand = HERO_ANIMATIONS.get(HeroStateType.STAND).getKeyFrames()[0].getTexture();
        var standardWidth = textureStand.getWidth();
        var standardHeight = textureStand.getHeight();

        var slideTexture = HERO_ANIMATIONS.get(HeroStateType.SLIDE).getKeyFrames()[0].getTexture();
        var slideStandardWidth = slideTexture.getWidth();
        var slideStandardHeight = slideTexture.getHeight();

        var hero = new Hero(SCALE_FACTOR,
                BASE_X * screenWidth, BASE_Y * screenHeight,
                standardWidth, standardHeight, slideStandardWidth, slideStandardHeight,
                HERO_ANIMATIONS);

        return hero;
    }



    /* -------------------------------- OBSTACLE -------------------------------- */


    private static final Texture OBSTACLE_SLIDABLE_TEXTURE = new Texture("images/forest/obstacles/slidable.png");
    private static final Texture OBSTACLE_JUMPABLE_TEXTURE = new Texture("images/forest/obstacles/jumpable.png");
    private static final Texture OBSTACLE_SLIDABLE_JUMPABLE_TEXTURE = new Texture("images/forest/obstacles/jumpable_slidable.png");


    @Override
    public Sprite createObstacle(SpriteType spriteType, float maxHeight, float maxWidth) {
       /* switch (spriteType) {
            case JUMPABLE:
                return new JumpableSprite(new Obstacle(OBSTACLE_JUMPABLE_TEXTURE), maxHeight);
            case SLIDABLE:
                return new SlidableSprite(new Obstacle(OBSTACLE_SLIDABLE_TEXTURE), maxWidth);
            case JUMPABLE_SLIDABLE:
                return new JumpableSprite(new SlidableSprite(new Obstacle(OBSTACLE_SLIDABLE_JUMPABLE_TEXTURE), maxWidth), maxHeight);
            default:
                throw new IllegalArgumentException("Obstacle type is not valid");
        }*/
        throw new IllegalArgumentException("Obstacle type is not valid");
    }




    /* -------------------------------- ENEMY -------------------------------- */


    private static final Map<EnemyStateType, Animation<TextureRegion>> GOLEM_ANIMATION;
    private static final Map<EnemyStateType, Animation<TextureRegion>> WOLF_ANIMATION;

    static {

        final String ENEMIES_FRAME_PATH = "images/forest/enemies/";

        final int GOLEM_IDLE_FRAME_COUNT = 13;
        final int GOLEM_ATTACK_FRAME_COUNT = 13;
        final int GOLEM_DEATH_FRAME_COUNT = 13;
        final int WOLF_IDLE_FRAME_COUNT = 13;
        final int WOLF_ATTACK_FRAME_COUNT = 13;
        final int WOLF_DEATH_FRAME_COUNT = 13;

        final var GOLEM_IDLE_FRAMES = TextureUtils.toVector(ENEMIES_FRAME_PATH + "golem/golem_idle_", "png", GOLEM_IDLE_FRAME_COUNT);
        final var GOLEM_ATTACK_FRAMES = TextureUtils.toVector(ENEMIES_FRAME_PATH + "golem/golem_attack_", "png", GOLEM_ATTACK_FRAME_COUNT);
        final var GOLEM_DEATH_FRAMES = TextureUtils.toVector(ENEMIES_FRAME_PATH + "golem/golem_death_", "png", GOLEM_DEATH_FRAME_COUNT);
        final var WOLF_IDLE_FRAMES = TextureUtils.toVector(ENEMIES_FRAME_PATH + "wolf/wolf_idle_", "png", WOLF_IDLE_FRAME_COUNT);
        final var WOLF_ATTACK_FRAMES = TextureUtils.toVector(ENEMIES_FRAME_PATH + "wolf/wolf_attack_", "png", WOLF_ATTACK_FRAME_COUNT);
        final var WOLF_DEATH_FRAMES = TextureUtils.toVector(ENEMIES_FRAME_PATH + "wolf/wolf_death_", "png", WOLF_DEATH_FRAME_COUNT);

        GOLEM_ANIMATION = new HashMap<>();
        GOLEM_ANIMATION.put(EnemyStateType.IDLE, new Animation<>(0.02F, GOLEM_IDLE_FRAMES));
        GOLEM_ANIMATION.put(EnemyStateType.ATTACK, new Animation<>(0.02F, GOLEM_ATTACK_FRAMES));
        GOLEM_ANIMATION.put(EnemyStateType.DEAD, new Animation<>(0.02F, GOLEM_DEATH_FRAMES));

        WOLF_ANIMATION = new HashMap<>();
        WOLF_ANIMATION.put(EnemyStateType.IDLE, new Animation<>(0.02F, WOLF_IDLE_FRAMES));
        WOLF_ANIMATION.put(EnemyStateType.ATTACK, new Animation<>(0.02F, WOLF_ATTACK_FRAMES));
        WOLF_ANIMATION.put(EnemyStateType.DEAD, new Animation<>(0.02F, WOLF_DEATH_FRAMES));

    }


    @Override
    public Sprite createEnemy(float maxHeight) {
        return new Enemy(SCALE_FACTOR, createAnimations());
    }
    private Map<EnemyStateType, Animation<TextureRegion>> createAnimations() {

        var enemies = Arrays.stream(ForestEnemyFrameType.values()).collect(Collectors.toList());

        if (enemies.size() == 0) throw new ArrayIndexOutOfBoundsException("Enemies is empty");

        Collections.shuffle(enemies);
        var enemyFrameType = enemies.get(ThreadLocalRandom.current().nextInt(enemies.size()));

        switch (enemyFrameType) {
            case WOLF:
                return WOLF_ANIMATION;

            case GOLEM:
                return GOLEM_ANIMATION;

            default:
                throw new InvalidParameterException("No valid enemy");
        }
    }




    /* -------------------------------- POWER UP -------------------------------- */

    private static final Texture SWORD_TEXTURE = new Texture("images/forest/powerups/power_up_sword.png");
    private static final Texture SHIELD_TEXTURE = new Texture("images/forest/powerups/power_up_shield.png");
    private static final Texture MULTIPLIER_TEXTURE = new Texture("images/forest/powerups/power_up_x2.png");


    @Override
    public Sprite createPowerUp(float maxWidth, float maxHeight) {

        switch (getPowerUpType()) {
            case SWORD:
                return new Sword(SWORD_TEXTURE, SCALE_FACTOR);
            case SHIELD:
                return new Shield(SHIELD_TEXTURE, SCALE_FACTOR);
            case MULTIPLIER:
                return new MultiplierPowerUp(MULTIPLIER_TEXTURE, SCALE_FACTOR, 2);
            default:
                throw new IllegalArgumentException("Power Up type is not valid!");
        }
    }

    private PowerUpType getPowerUpType() {
        var list = Arrays.asList(PowerUpType.values());
        Collections.shuffle(list);
        return list.get(ThreadLocalRandom.current().nextInt(list.size()));
    }


}

