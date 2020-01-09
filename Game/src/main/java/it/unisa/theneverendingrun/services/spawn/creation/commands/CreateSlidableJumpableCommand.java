package it.unisa.theneverendingrun.services.spawn.creation.commands;

import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.services.factories.GameFactory;
import it.unisa.theneverendingrun.services.spawn.creation.templates.AbstractCreateSpriteTemplate;
import it.unisa.theneverendingrun.services.spawn.creation.templates.CreateJumpableSlidableObstacleTemplate;

public class CreateSlidableJumpableCommand extends CreateSpriteCommand {

    private AbstractCreateSpriteTemplate template;

    public CreateSlidableJumpableCommand(GameFactory gameFactory, float maxWidth, float maxHeight) {
        super(gameFactory);
        template = new CreateJumpableSlidableObstacleTemplate(gameFactory, maxWidth, maxHeight);
    }

    @Override
    public Sprite execute() {
        return template.create();
    }
}
