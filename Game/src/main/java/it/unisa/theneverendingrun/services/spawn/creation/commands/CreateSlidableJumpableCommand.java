package it.unisa.theneverendingrun.services.spawn.creation.commands;

import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.SpriteType;
import it.unisa.theneverendingrun.services.factories.GameFactory;

public class CreateSlidableJumpableCommand extends CreateSpriteCommand {

    public CreateSlidableJumpableCommand(GameFactory gameFactory) {
        super(gameFactory);
    }

    @Override
    public Sprite create() {
        return gameFactory.createObstacle(SpriteType.JUMPABLE_SLIDABLE);
    }
}
