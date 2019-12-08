package it.unisa.theneverendingrun.models.services;

import it.unisa.theneverendingrun.models.background.AbstractScrollingBackground;
import it.unisa.theneverendingrun.models.background.ForestScrollingBackground;

public class ForestFactory implements GameFactory {

    private static final float SCROLLING_SPEED = 0.002F;
    private static final float SCROLLING_WIDTH = 2.0F;

    @Override
    public AbstractScrollingBackground createBackground() {
        return new ForestScrollingBackground(SCROLLING_SPEED, SCROLLING_WIDTH);
    }
}
