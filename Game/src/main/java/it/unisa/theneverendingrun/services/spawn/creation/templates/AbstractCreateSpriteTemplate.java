package it.unisa.theneverendingrun.services.spawn.creation.templates;

import it.unisa.theneverendingrun.services.factories.GameFactory;
import it.unisa.theneverendingrun.services.spawn.creation.AbstractCreateTemplate;

public abstract class AbstractCreateSpriteTemplate extends AbstractCreateTemplate {

    protected GameFactory factory;

    public AbstractCreateSpriteTemplate(GameFactory factory) {
        this.factory = factory;
    }
}
