package it.unisa.theneverendingrun.metersManager;

import de.tomgrill.gdxtesting.GdxTestRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

@RunWith(GdxTestRunner.class)
public class MeterEditorTest {

    private MetersManagerFactory factory = new MetersManagerFactory();

    @Test
    public void testInitialise() {
        Assert.assertEquals(0, MeterEditor.getMeters());
    }

    @Test
    public void update() {
        var metersTravelled = new Random().nextInt(1000);

        for (int i = 1; i <= metersTravelled; i++) {
            var previousMetersCounter = MeterEditor.getMeters();
            MeterEditor.update();
            var currentMetersCounter = MeterEditor.getMeters();

            if (i % MeterEditor.getMetersFactor() == 0) {
                Assert.assertEquals(previousMetersCounter + 1, currentMetersCounter);
            } else {
                Assert.assertEquals(previousMetersCounter, currentMetersCounter);
            }
        }
    }
}