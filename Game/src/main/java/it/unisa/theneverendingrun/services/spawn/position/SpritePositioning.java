package it.unisa.theneverendingrun.services.spawn.position;

import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.SpriteType;
import it.unisa.theneverendingrun.models.hero.Hero;
import it.unisa.theneverendingrun.services.spawn.SpawnHolder;

import java.util.AbstractMap;

public class SpritePositioning {

    private static final float FACTOR = 0.25F;

    private float standardHeight;
    private float slideStandardHeight;
    private float jumpElevation;
    private float slideDistance;

    private SpawnHolder holder;

    private AbstractMap.SimpleEntry<SpriteType, Sprite> last;

    public SpritePositioning(SpawnHolder holder, Hero hero) {
        this.holder = holder;

        standardHeight = hero.getStandardHeight();
        slideStandardHeight = hero.getSlideStandardHeight();
        jumpElevation = hero.getJumpMaxElevation();
        slideDistance = hero.getMaxSlideRange();

        last = null;
    }


    public Sprite getSprite(float maxWidth) {

        if (last != null && (maxWidth - last.getValue().getX() - last.getValue().getWidth()) < 0)
            return null;

        var entry = holder.get();
        if (entry == null) return null;

        var sprite = entry.getValue();

        var y = generateY(entry);
        sprite.setY(y);

        if (isXValid(entry, maxWidth)) {
            sprite.setX(maxWidth);
            last = entry;
            return sprite;
        }

        return null;
    }

    private boolean isXValid(AbstractMap.SimpleEntry<SpriteType, Sprite> entry, float maxWidth) {

        if (last == null) return true;
        var lastType = last.getKey();
        var lastSprite = last.getValue();

        var entryType = entry.getKey();
        var entrySprite = entry.getValue();


        if (lastType == SpriteType.JUMPABLE) {
            if (entryType == SpriteType.SLIDABLE) {
                entrySprite.setY(entrySprite.getY() + lastSprite.getHeight());
                return true;
            }
            if (entryType == SpriteType.JUMPABLE)
                return true;
            if (entryType == SpriteType.JUMPABLE_SLIDABLE)
                return true;
            return false;
        }

        if (lastType == SpriteType.SLIDABLE) {
            return entryType == SpriteType.SLIDABLE && lastSprite.getWidth() + entrySprite.getWidth() < slideDistance;
        }

        if (lastType == SpriteType.JUMPABLE_SLIDABLE) {
            if (entryType == SpriteType.SLIDABLE)
                entrySprite.setY(entrySprite.getY() + lastSprite.getHeight() + lastSprite.getY());
            return true;
        }

        return false;
    }

    private float generateY(AbstractMap.SimpleEntry<SpriteType, Sprite> entry) {

        var sprite = entry.getValue();
        var type = entry.getKey();

        var spriteHeight = sprite.getHeight();

        var min = Math.max((1 - FACTOR) * slideStandardHeight + FACTOR * standardHeight, slideStandardHeight + 1);
        var max = Math.min(FACTOR * slideStandardHeight + (1 - FACTOR) * standardHeight, standardHeight - 1);

        /*
                min = Max((1 - FACTOR)*s + FACTOR*h, s + 1)
                max = Min(FACTOR*s + (1-FACTOR)*h, h - 1, min + J - o - s)

                se o > J - s + min - max
                    o = J - s + min - max - 1
                    return min
                else
                    return min < y < max
         */
        if (isJumpableAndSlidable(type)) {

            max = Math.min(max, min + jumpElevation - spriteHeight - slideStandardHeight);

            if (spriteHeight > jumpElevation - slideStandardHeight - min + max) {
                sprite.setSize(sprite.getWidth(), jumpElevation - slideStandardHeight - min + max - 1);
                return min;
            }

            return 0;
            //return (float)ThreadLocalRandom.current().nextDouble(min, max);
        }

        if (isJumpable(type))
            return 0;

        /*
               se h - s = 1
                    return h;
                altrimenti
                    return   Max((1 - FACTOR)*s + FACTOR*h, s + 1) < y < max = Min(FACTOR*s + (1-FACTOR)*h, h - 1)
        */
        if (isSlidable(type)) {
            if (standardHeight - slideStandardHeight == 1)
                return standardHeight;

            return 0;
            //return (float)ThreadLocalRandom.current().nextDouble(min, max);
        }

        throw new IllegalArgumentException("Sprite non valid");
    }


    private boolean isJumpable(SpriteType entry) {
        return entry == SpriteType.JUMPABLE;
    }

    private boolean isSlidable(SpriteType entry) {
        return entry == SpriteType.SLIDABLE;
    }

    private boolean isJumpableAndSlidable(SpriteType entry) {
        return entry == SpriteType.JUMPABLE_SLIDABLE;
    }


}
