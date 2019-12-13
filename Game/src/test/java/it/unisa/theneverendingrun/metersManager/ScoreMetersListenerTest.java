package it.unisa.theneverendingrun.metersManager;


import de.tomgrill.gdxtesting.GdxTestRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

@RunWith(GdxTestRunner.class)
public class ScoreMetersListenerTest {

    private MetersManagerFactory factory = new MetersManagerFactory();

    @Test
    public void testInitialise() {
        Assert.assertEquals(ScoreMetersListener.getInitialScore(), factory.getScore());
    }

    @Test
    public void testUpdate() {
        var steps = new Random().nextInt(10000);

        for (int i = 0; i < steps; i++) {
            factory.updateMeters();
            Assert.assertEquals(ScoreMetersListener.getScoreFactor() * factory.getMeters(), factory.getScore());
        }
    }
}