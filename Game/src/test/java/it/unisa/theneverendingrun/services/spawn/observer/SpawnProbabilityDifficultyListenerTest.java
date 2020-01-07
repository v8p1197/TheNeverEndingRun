package it.unisa.theneverendingrun.services.spawn.observer;

import de.tomgrill.gdxtesting.GdxTestRunner;
import it.unisa.theneverendingrun.services.difficulty.DifficultyEventType;
import it.unisa.theneverendingrun.services.difficulty.DifficultyMetersListener;
import it.unisa.theneverendingrun.services.meters.MeterEditor;
import it.unisa.theneverendingrun.services.meters.MetersEventType;
import it.unisa.theneverendingrun.utilities.MathUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class SpawnProbabilityDifficultyListenerTest {

    private static void assertEqualsDouble(double expected, double actual) {
        assertEquals(expected, actual, MathUtils.DELTA);
    }

    private MeterEditor meterEditor = new MeterEditor();
    private DifficultyMetersListener difficulty = new DifficultyMetersListener();
    private SpawnProbabilityDifficultyListener spawnProbability = new SpawnProbabilityDifficultyListener();

    @Before
    public void init() {
        meterEditor.getEventManager().subscribe(MetersEventType.METERS_CHANGED, difficulty);
        difficulty.getEventManager().subscribe(DifficultyEventType.LEVEL_CHANGED, spawnProbability);
    }

    @Test
    public void testInit() {
        assertEqualsDouble(SpawnProbabilityDifficultyListener.INITIAL_SPAWN_PROBABILITY,
                spawnProbability.getSpawnProbability());
    }

    @Test
    public void testUpdate() {
        var steps = ThreadLocalRandom.current()
                .nextInt(10_000, 100_000);

        for (var i = 0; i < steps; i++) {
            var previousDifficulty = difficulty.getDifficultyLevel();
            var previousSpawnProbability = spawnProbability.getSpawnProbability();

            meterEditor.compute();

            var currentDifficulty = difficulty.getDifficultyLevel();
            var currentSpawnProbability = spawnProbability.getSpawnProbability();

            if (currentDifficulty != previousDifficulty &&
                    currentDifficulty % SpawnProbabilityDifficultyListener.DIFFICULTY_DELTA == 0) {
                assertEqualsDouble(
                        previousSpawnProbability + SpawnProbabilityDifficultyListener.SPAWN_PROBABILITY_FACTOR,
                        currentSpawnProbability);
            } else {
                assertEqualsDouble(previousSpawnProbability, currentSpawnProbability);
            }
        }
    }
}