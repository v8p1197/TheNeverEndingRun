package it.unisa.theneverendingrun.services.meters;

import de.tomgrill.gdxtesting.GdxTestRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ThreadLocalRandom;


@RunWith(GdxTestRunner.class)
public class MeterEditorTest {

    private MeterEditor meterEditor = new MeterEditor();

    @Test
    public void testInit() {
        Assert.assertEquals(MeterEditor.INITIAL_METERS, meterEditor.getMeters());
    }

    @Test
    public void testUpdate() {
        var steps = ThreadLocalRandom.current()
                .nextInt(10_000, 100_000);

        for (int i = 1; i <= steps; i++) {
            var previousMetersCounter = meterEditor.getMeters();
            meterEditor.compute();
            var currentMetersCounter = meterEditor.getMeters();

            if (i % MeterEditor.UPDATE_DELTA == 0) {
                Assert.assertEquals(previousMetersCounter + MeterEditor.METER_FACTOR, currentMetersCounter);
            } else {
                Assert.assertEquals(previousMetersCounter, currentMetersCounter);
            }
        }
    }

}