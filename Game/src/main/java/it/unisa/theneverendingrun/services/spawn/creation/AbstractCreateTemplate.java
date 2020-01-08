package it.unisa.theneverendingrun.services.spawn.creation;

import it.unisa.theneverendingrun.models.Sprite;

public abstract class AbstractCreateTemplate {

    public Sprite create() {
        return generate();
    }


    protected abstract Sprite generate();

}
