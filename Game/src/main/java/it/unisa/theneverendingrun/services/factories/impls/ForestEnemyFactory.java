package it.unisa.theneverendingrun.services.factories.impls;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import it.unisa.theneverendingrun.data.AnimationType;
import it.unisa.theneverendingrun.data.EnemyFrameType;
import it.unisa.theneverendingrun.models.spawnables.Spawnable;
import it.unisa.theneverendingrun.models.spawnables.decorators.JumpableSpawnable;
import it.unisa.theneverendingrun.models.spawnables.enemy.impls.ForestEnemy;
import it.unisa.theneverendingrun.services.factories.SpawnableFactory;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class ForestEnemyFactory implements SpawnableFactory {

    private static final String ENEMIES_FRAME_PATH = "images/forest/enemies/";

    private static final int GOLEM_IDLE_FRAME_COUNT = 13;
    private static final int GOLEM_ATTACK_FRAME_COUNT = 13;
    private static final int GOLEM_DEATH_FRAME_COUNT = 13;
    private static final int WOLF_IDLE_FRAME_COUNT = 13;
    private static final int WOLF_ATTACK_FRAME_COUNT = 13;
    private static final int WOLF_DEATH_FRAME_COUNT = 13;
    private static final int WITCH_IDLE_FRAME_COUNT = 13;
    private static final int WITCH_ATTACK_FRAME_COUNT = 13;
    private static final int WITCH_DEATH_FRAME_COUNT = 13;

    private static final TextureRegion[] GOLEM_IDLE_FRAMES = new TextureRegion[GOLEM_IDLE_FRAME_COUNT];
    private static final TextureRegion[] GOLEM_ATTACK_FRAMES = new TextureRegion[GOLEM_ATTACK_FRAME_COUNT];
    private static final TextureRegion[] GOLEM_DEATH_FRAMES = new TextureRegion[GOLEM_DEATH_FRAME_COUNT];
    private static final TextureRegion[] WOLF_IDLE_FRAMES = new TextureRegion[WOLF_IDLE_FRAME_COUNT];
    private static final TextureRegion[] WOLF_ATTACK_FRAMES = new TextureRegion[WOLF_ATTACK_FRAME_COUNT];
    private static final TextureRegion[] WOLF_DEATH_FRAMES = new TextureRegion[WOLF_DEATH_FRAME_COUNT];
    private static final TextureRegion[] WITCH_IDLE_FRAMES = new TextureRegion[WITCH_IDLE_FRAME_COUNT];
    private static final TextureRegion[] WITCH_ATTACK_FRAMES = new TextureRegion[WITCH_ATTACK_FRAME_COUNT];
    private static final TextureRegion[] WITCH_DEATH_FRAMES = new TextureRegion[WITCH_DEATH_FRAME_COUNT];

    static {
        createTextureRegion(GOLEM_IDLE_FRAMES, "golem/golem_idle_", "png");
        createTextureRegion(GOLEM_ATTACK_FRAMES, "golem/golem_attack_", "png");
        createTextureRegion(GOLEM_DEATH_FRAMES, "golem/golem_death_", "png");
        createTextureRegion(WOLF_IDLE_FRAMES, "wolf/wolf_idle_", "png");
        createTextureRegion(WOLF_ATTACK_FRAMES, "wolf/wolf_idle_", "png");
        createTextureRegion(WOLF_DEATH_FRAMES, "wolf/wolf_death_", "png");
        createTextureRegion(WITCH_IDLE_FRAMES, "witch/witch_idle_", "png");
        createTextureRegion(WITCH_ATTACK_FRAMES, "witch/witch_attack_", "png");
        createTextureRegion(WITCH_DEATH_FRAMES, "witch/witch_death_", "png");

    }

    private static void createTextureRegion(TextureRegion[] regions, String path, String extension) {
        for(int i = 1; i <= regions.length; ++i) {
            regions[i] = new TextureRegion(new Texture(ENEMIES_FRAME_PATH + path + i + extension));
        }
    }





    @Override
    public Spawnable createSlidableSpawnable(float jumpHeight, float slideDistance) {
        throw new UnsupportedOperationException("No slide enemy exists");
        //return new SlidableSpawnable(new ForestEnemy(null, jumpHeight, slideDistance));
    }

    @Override
    public Spawnable createJumpableSpawnable(float jumpHeight, float slideDistance) {
        return new JumpableSpawnable(new ForestEnemy(createAnimations(), jumpHeight, slideDistance));
    }

    @Override
    public Spawnable createJumpableSlideableSpawnable(float jumpHeight, float slideDistance) {
        throw new UnsupportedOperationException("No slide and jump enemy exists");
        //return new JumpableSpawnable(new SlidableSpawnable(new ForestEnemy(null, jumpHeight, slideDistance)));
    }





    private Map<AnimationType, Animation<TextureRegion>> createAnimations() {
        var animations = new HashMap<AnimationType, Animation<TextureRegion>>();

        var enemies = Arrays.stream(EnemyFrameType.values()).filter(EnemyFrameType::isJumpable).collect(Collectors.toList());
        if (enemies.size() == 0) throw new ArrayIndexOutOfBoundsException("Enemies is empty");
        Collections.shuffle(enemies);
        var enemyFrameType = enemies.get(ThreadLocalRandom.current().nextInt(0, enemies.size()));


        switch (enemyFrameType) {
            case WOLF:
                animations.put(AnimationType.IDLE, new Animation<>(2F, WOLF_IDLE_FRAMES));
                animations.put(AnimationType.ATTACK, new Animation<>(2F, WOLF_ATTACK_FRAMES));
                animations.put(AnimationType.DEAD, new Animation<>(2F, WOLF_DEATH_FRAMES));
                break;

            case GOLEM:
                animations.put(AnimationType.IDLE, new Animation<>(2F, GOLEM_IDLE_FRAMES));
                animations.put(AnimationType.ATTACK, new Animation<>(2F, GOLEM_ATTACK_FRAMES));
                animations.put(AnimationType.DEAD, new Animation<>(2F, GOLEM_DEATH_FRAMES));
                break;

            case WITCH:
                animations.put(AnimationType.IDLE, new Animation<>(2F, WITCH_IDLE_FRAMES));
                animations.put(AnimationType.ATTACK, new Animation<>(2F, WITCH_ATTACK_FRAMES));
                animations.put(AnimationType.DEAD, new Animation<>(2F, WITCH_DEATH_FRAMES));
                break;

            default: throw new InvalidParameterException("No valid enemy");
        }

        return animations;
    }
}
