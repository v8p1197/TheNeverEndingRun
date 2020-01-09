package it.unisa.theneverendingrun.services.spawn.creation.templates;

import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.services.factories.GameFactory;
import it.unisa.theneverendingrun.services.spawn.creation.resize.Resizable;

public abstract class AbstractCreateSpriteTemplate implements Resizable {

    protected GameFactory factory;

    public AbstractCreateSpriteTemplate(GameFactory factory) {
        this.factory = factory;
    }

    public Sprite create() {
        var sprite = generate();
        resize(sprite);
        return sprite;
    }

    protected abstract void resize(Sprite sprite);

    protected abstract Sprite generate();
}
