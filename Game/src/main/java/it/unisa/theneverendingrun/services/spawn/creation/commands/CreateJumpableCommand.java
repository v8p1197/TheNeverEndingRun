package it.unisa.theneverendingrun.services.spawn.creation.commands;

import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.services.factories.GameFactory;
import it.unisa.theneverendingrun.services.spawn.creation.templates.AbstractCreateSpriteTemplate;
import it.unisa.theneverendingrun.services.spawn.creation.templates.CreateJumpableEnemyTemplate;
import it.unisa.theneverendingrun.services.spawn.creation.templates.CreateJumpableObstacleTemplate;
import it.unisa.theneverendingrun.services.spawn.creation.templates.CreateJumpablePowerUpTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CreateJumpableCommand extends CreateSpriteCommand {

    private List<AbstractCreateSpriteTemplate> createTemplates;

    private AbstractCreateSpriteTemplate enemyJumpTemplate;
    private AbstractCreateSpriteTemplate powerJumpTemplate;
    private AbstractCreateSpriteTemplate obstacleJumpTemplate;

    public CreateJumpableCommand(GameFactory gameFactory, float maxWidth, float maxHeight) {
        super(gameFactory);
        enemyJumpTemplate = new CreateJumpableEnemyTemplate(gameFactory, maxHeight);
        powerJumpTemplate = new CreateJumpablePowerUpTemplate(gameFactory, maxHeight);
        obstacleJumpTemplate = new CreateJumpableObstacleTemplate(gameFactory, maxWidth, maxHeight);
        createTemplates = Arrays.asList(enemyJumpTemplate, powerJumpTemplate, obstacleJumpTemplate);
    }

    @Override
    public Sprite execute() {
        Collections.shuffle(createTemplates);
        var random = ThreadLocalRandom.current().nextDouble(0, 1);
        if (random <= 0.2){
            return powerJumpTemplate.create();
        }
        if (random <= 0.7){
            return obstacleJumpTemplate.create();
        }
        if(random <= 1){
            return enemyJumpTemplate.create();
        }
        return null;
    }
}
