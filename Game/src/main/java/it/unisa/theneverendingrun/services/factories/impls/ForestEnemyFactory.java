package it.unisa.theneverendingrun.services.factories.impls;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import it.unisa.theneverendingrun.models.JumpableSprite;
import it.unisa.theneverendingrun.models.enemy.Enemy;
import it.unisa.theneverendingrun.models.enemy.EnemyStateType;
import it.unisa.theneverendingrun.utilities.TextureUtils;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class ForestEnemyFactory {

    private static final Map<EnemyStateType, Animation<TextureRegion>> GOLEM_ANIMATION;
    private static final Map<EnemyStateType, Animation<TextureRegion>> WOLF_ANIMATION;
    private static final Map<EnemyStateType, Animation<TextureRegion>> WITCH_ANIMATION;

    static {

        final String ENEMIES_FRAME_PATH = "images/forest/enemies/";

        final int GOLEM_IDLE_FRAME_COUNT = 13;
        final int GOLEM_ATTACK_FRAME_COUNT = 13;
        final int GOLEM_DEATH_FRAME_COUNT = 13;
        final int WOLF_IDLE_FRAME_COUNT = 13;
        final int WOLF_ATTACK_FRAME_COUNT = 13;
        final int WOLF_DEATH_FRAME_COUNT = 13;
        final int WITCH_IDLE_FRAME_COUNT = 13;
        final int WITCH_ATTACK_FRAME_COUNT = 13;
        final int WITCH_DEATH_FRAME_COUNT = 13;

        final var GOLEM_IDLE_FRAMES = TextureUtils.toVector(ENEMIES_FRAME_PATH + "golem/golem_idle_", "png", GOLEM_IDLE_FRAME_COUNT);
        final var GOLEM_ATTACK_FRAMES = TextureUtils.toVector(ENEMIES_FRAME_PATH + "golem/golem_attack_", "png", GOLEM_ATTACK_FRAME_COUNT);
        final var GOLEM_DEATH_FRAMES = TextureUtils.toVector(ENEMIES_FRAME_PATH + "golem/golem_death_", "png", GOLEM_DEATH_FRAME_COUNT);
        final var WOLF_IDLE_FRAMES = TextureUtils.toVector(ENEMIES_FRAME_PATH + "wolf/wolf_idle_", "png", WOLF_IDLE_FRAME_COUNT);
        final var WOLF_ATTACK_FRAMES = TextureUtils.toVector(ENEMIES_FRAME_PATH + "wolf/wolf_idle_", "png", WOLF_ATTACK_FRAME_COUNT);
        final var WOLF_DEATH_FRAMES = TextureUtils.toVector(ENEMIES_FRAME_PATH + "wolf/wolf_death_", "png", WOLF_DEATH_FRAME_COUNT);
        final var WITCH_IDLE_FRAMES = TextureUtils.toVector(ENEMIES_FRAME_PATH + "witch/witch_idle_", "png", WITCH_IDLE_FRAME_COUNT);
        final var WITCH_ATTACK_FRAMES = TextureUtils.toVector(ENEMIES_FRAME_PATH + "witch/witch_attack_", "png", WITCH_ATTACK_FRAME_COUNT);
        final var WITCH_DEATH_FRAMES = TextureUtils.toVector(ENEMIES_FRAME_PATH + "witch/witch_death_", "png", WITCH_DEATH_FRAME_COUNT);

        GOLEM_ANIMATION = new HashMap<>();
        GOLEM_ANIMATION.put(EnemyStateType.IDLE, new Animation<>(2F, GOLEM_IDLE_FRAMES));
        GOLEM_ANIMATION.put(EnemyStateType.ATTACK, new Animation<>(2F, GOLEM_ATTACK_FRAMES));
        GOLEM_ANIMATION.put(EnemyStateType.DEAD, new Animation<>(2F, GOLEM_DEATH_FRAMES));

        WOLF_ANIMATION = new HashMap<>();
        WOLF_ANIMATION.put(EnemyStateType.IDLE, new Animation<>(2F, WOLF_IDLE_FRAMES));
        WOLF_ANIMATION.put(EnemyStateType.ATTACK, new Animation<>(2F, WOLF_ATTACK_FRAMES));
        WOLF_ANIMATION.put(EnemyStateType.DEAD, new Animation<>(2F, WOLF_DEATH_FRAMES));

        WITCH_ANIMATION = new HashMap<>();
        WITCH_ANIMATION.put(EnemyStateType.IDLE, new Animation<>(2F, WITCH_IDLE_FRAMES));
        WITCH_ANIMATION.put(EnemyStateType.ATTACK, new Animation<>(2F, WITCH_ATTACK_FRAMES));
        WITCH_ANIMATION.put(EnemyStateType.DEAD, new Animation<>(2F, WITCH_DEATH_FRAMES));
    }

    public Enemy createEnemy(float maxHeight) {
        return new Enemy(new JumpableSprite(maxHeight, true), createAnimations());
    }

    private Map<EnemyStateType, Animation<TextureRegion>> createAnimations() {

        //For now only Jumpable exist
        var enemies = Arrays.stream(ForestEnemyFrameType.values()).filter(ForestEnemyFrameType::isJumpable).collect(Collectors.toList());

        if (enemies.size() == 0) throw new ArrayIndexOutOfBoundsException("Enemies is empty");

        Collections.shuffle(enemies);
        var enemyFrameType = enemies.get(ThreadLocalRandom.current().nextInt(enemies.size()));

        switch (enemyFrameType) {
            case WOLF:
                return WOLF_ANIMATION;

            case GOLEM:
                return GOLEM_ANIMATION;

            case WITCH:
                return WITCH_ANIMATION;

            default: throw new InvalidParameterException("No valid enemy");
        }
    }
}
