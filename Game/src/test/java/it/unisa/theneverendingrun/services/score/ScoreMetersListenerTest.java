package it.unisa.theneverendingrun.services.score;

import de.tomgrill.gdxtesting.GdxTestRunner;
import it.unisa.theneverendingrun.services.meters.MeterEditor;
import it.unisa.theneverendingrun.services.meters.MetersEventType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class ScoreMetersListenerTest {

    private MeterEditor meterEditor = new MeterEditor();
    private ScoreMetersListener score = new ScoreMetersListener();

    @Before
    public void init() {
        meterEditor.getEventManager().subscribe(MetersEventType.METERS_CHANGED, score);
    }

    @Test
    public void testInit() {
        assertEquals(ScoreMetersListener.INITIAL_SCORE, score.getScore());
    }

    @Test
    public void testUpdate() {
        var steps = ThreadLocalRandom.current()
                .nextInt(10_000, 100_000);

        for (int i = 0; i < steps; i++) {
            var previousMeters = meterEditor.getMeters();
            var previousScore = score.getScore();

            meterEditor.compute();

            var currentMeters = meterEditor.getMeters();
            var currentScore = score.getScore();

            if (currentMeters != previousMeters && currentMeters % ScoreMetersListener.METERS_DELTA == 0) {
                Assert.assertEquals(previousScore + ScoreMetersListener.SCORE_FACTOR, currentScore);
            } else {
                Assert.assertEquals(previousScore, currentScore);
            }
        }
    }

}