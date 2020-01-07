package it.unisa.theneverendingrun.services.speed;

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
public class SpeedDifficultyListenerTest {

    private static void assertEqualsDouble(double expected, double actual) {
        assertEquals(expected, actual, MathUtils.DELTA);
    }

    private MeterEditor meterEditor = new MeterEditor();
    private DifficultyMetersListener difficulty = new DifficultyMetersListener();
    private SpeedDifficultyListener speed = new SpeedDifficultyListener();

    @Before
    public void init() {
        meterEditor.getEventManager().subscribe(MetersEventType.METERS_CHANGED, difficulty);
        difficulty.getEventManager().subscribe(DifficultyEventType.LEVEL_CHANGED, speed);
    }

    @Test
    public void testInit() {
        assertEqualsDouble(SpeedDifficultyListener.INITIAL_SPEED, speed.getSpeed());
    }

    @Test
    public void testUpdate() {
        var steps = ThreadLocalRandom.current()
                .nextInt(10_000, 100_000);

        for (var i = 0; i < steps; i++) {
            var previousDifficulty = difficulty.getDifficultyLevel();
            var previousSpeed = speed.getSpeed();

            meterEditor.compute();

            var currentDifficulty = difficulty.getDifficultyLevel();
            var currentSpeed = speed.getSpeed();

            if (currentDifficulty != previousDifficulty && currentDifficulty % SpeedDifficultyListener.DIFFICULTY_DELTA == 0) {
                assertEqualsDouble(previousSpeed + SpeedDifficultyListener.SPEED_FACTOR, currentSpeed);
            } else {
                assertEqualsDouble(previousSpeed, currentSpeed);
            }
        }
    }
}