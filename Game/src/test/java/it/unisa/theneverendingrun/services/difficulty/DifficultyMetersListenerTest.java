package it.unisa.theneverendingrun.services.difficulty;

import de.tomgrill.gdxtesting.GdxTestRunner;
import it.unisa.theneverendingrun.services.meters.MeterEditor;
import it.unisa.theneverendingrun.services.meters.MetersEventType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class DifficultyMetersListenerTest {

    private MeterEditor meterEditor = new MeterEditor();
    private DifficultyMetersListener difficulty = new DifficultyMetersListener();

    @Before
    public void init() {
        meterEditor.getEventManager().subscribe(MetersEventType.METERS_CHANGED, difficulty);
    }

    @Test
    public void testInit() {
        assertEquals(DifficultyMetersListener.INITIAL_DIFFICULTY, difficulty.getDifficultyLevel());
    }

    @Test
    public void testUpdate() {
        var steps = ThreadLocalRandom.current()
                .nextInt(10_000, 100_000);

        for (int i = 0; i < steps; i++) {
            var previousMeters = meterEditor.getMeters();
            var previousDifficulty = difficulty.getDifficultyLevel();

            meterEditor.compute();

            var currentMeters = meterEditor.getMeters();
            var currentDifficulty = difficulty.getDifficultyLevel();

            if (currentMeters != previousMeters && currentMeters % DifficultyMetersListener.METERS_DELTA == 0) {
                assertEquals(Math.min(previousDifficulty + DifficultyMetersListener.DIFFICULTY_FACTOR,
                        Level.LEVEL_MAX.getValue()),
                        currentDifficulty);
            } else {
                assertEquals(previousDifficulty, currentDifficulty);
            }
        }
    }

}