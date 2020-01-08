package it.unisa.theneverendingrun.services.spawn.creation.commands;

import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.services.factories.GameFactory;
import it.unisa.theneverendingrun.services.spawn.SpriteType;

public class CreateSlidableCommand extends CreateSpriteCommand {

    public CreateSlidableCommand(GameFactory gameFactory) {
        super(gameFactory);
    }

    @Override
    public Sprite create() {
        return gameFactory.createObstacle(SpriteType.SLIDABLE);
    }
}
