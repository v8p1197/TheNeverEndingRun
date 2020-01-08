package it.unisa.theneverendingrun.services.spawn.creation.commands;

import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.services.factories.GameFactory;
import it.unisa.theneverendingrun.services.spawn.creation.CreateCommand;

public abstract class CreateSpriteCommand implements CreateCommand<Sprite> {

    protected GameFactory gameFactory;

    public CreateSpriteCommand(GameFactory gameFactory) {
        this.gameFactory = gameFactory;
    }
}
