package it.unisa.theneverendingrun.services.spawn.creation.commands;

import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.services.factories.GameFactory;
import it.unisa.theneverendingrun.services.spawn.creation.AbstractCreateTemplate;
import it.unisa.theneverendingrun.services.spawn.creation.templates.CreateJumpableEnemyTemplate;
import it.unisa.theneverendingrun.services.spawn.creation.templates.CreateJumpableObstacleTemplate;
import it.unisa.theneverendingrun.services.spawn.creation.templates.CreateJumpablePowerUpTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CreateJumpableCommand extends CreateSpriteCommand {

    private List<AbstractCreateTemplate> createTemplates;

    public CreateJumpableCommand(GameFactory gameFactory) {
        super(gameFactory);

        var enemyJumpTemplate = new CreateJumpableEnemyTemplate(gameFactory);
        var powerJumpTemplate = new CreateJumpablePowerUpTemplate(gameFactory);
        var obstaJumpTemplate = new CreateJumpableObstacleTemplate(gameFactory);

        createTemplates = Arrays.asList(enemyJumpTemplate, powerJumpTemplate, obstaJumpTemplate);
    }

    @Override
    public Sprite create() {
        Collections.shuffle(createTemplates);
        return createTemplates.get(ThreadLocalRandom.current().nextInt(createTemplates.size())).create();
    }
}
