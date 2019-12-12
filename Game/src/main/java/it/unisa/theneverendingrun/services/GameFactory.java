package it.unisa.theneverendingrun.services;

import it.unisa.theneverendingrun.models.background.AbstractScrollingBackground;
import it.unisa.theneverendingrun.models.hero.Hero;

public interface GameFactory {

    AbstractScrollingBackground createBackground();

    Hero createHero();

}
