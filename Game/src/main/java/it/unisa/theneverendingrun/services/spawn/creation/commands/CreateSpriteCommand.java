package it.unisa.theneverendingrun.services.spawn.creation.commands;

import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.services.factories.GameFactory;
import it.unisa.theneverendingrun.services.Command;

public abstract class CreateSpriteCommand implements Command<Sprite> {

    protected GameFactory gameFactory;

    public CreateSpriteCommand(GameFactory gameFactory) {
        this.gameFactory = gameFactory;
    }
}
