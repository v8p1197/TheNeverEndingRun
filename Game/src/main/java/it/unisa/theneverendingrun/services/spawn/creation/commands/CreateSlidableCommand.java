package it.unisa.theneverendingrun.services.spawn.creation.commands;

import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.services.factories.GameFactory;
import it.unisa.theneverendingrun.services.spawn.creation.templates.AbstractCreateSpriteTemplate;
import it.unisa.theneverendingrun.services.spawn.creation.templates.CreateSlidableObstacleTemplate;

public class CreateSlidableCommand extends CreateSpriteCommand {

    private AbstractCreateSpriteTemplate obstacleTemplate;

    public CreateSlidableCommand(GameFactory gameFactory, float maxWidth, float maxHeight) {
        super(gameFactory);
        obstacleTemplate = new CreateSlidableObstacleTemplate(gameFactory, maxWidth, maxHeight);
    }

    @Override
    public Sprite execute() {
        return obstacleTemplate.create();
    }
}
