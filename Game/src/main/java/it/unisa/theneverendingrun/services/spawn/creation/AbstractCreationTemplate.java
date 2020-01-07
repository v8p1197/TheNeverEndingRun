package it.unisa.theneverendingrun.services.spawn.creation;

import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.SpriteType;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public abstract class AbstractCreationTemplate {

    public AbstractMap.SimpleEntry<SpriteType, Sprite> create(float maxHeight, float maxWidth) {
        return new AbstractMap.SimpleEntry<>(getType(), getSprite(getType(), maxHeight, maxWidth));
    }

    protected SpriteType getType() {
        final var spawnableTypesList = Arrays.asList(SpriteType.values());
        Collections.shuffle(spawnableTypesList);
        return spawnableTypesList.get(ThreadLocalRandom.current().nextInt(spawnableTypesList.size()));
    }

    protected abstract Sprite getSprite(SpriteType spriteType, float maxHeight, float maxWidth);
}
