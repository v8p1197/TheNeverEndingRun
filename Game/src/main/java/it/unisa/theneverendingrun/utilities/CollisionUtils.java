package it.unisa.theneverendingrun.utilities;

import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.services.collision.CollisionSideType;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.geom.Rectangle;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public final class CollisionUtils {

    public static boolean areColliding(Sprite a, Sprite b) {
        return a.getCollisionBox().intersects(b.getCollisionBox());
    }

    public static Rectangle computeIntersection(Sprite a, Sprite b) {
        return a.getCollisionBox().intersection(b.getCollisionBox());
    }

    public static CollisionSideType collisionSide(CollisionBox heroCollisionBox, CollisionBox spriteCollisionBox) {

        var boxes = new HashMap<CollisionSideType, CollisionBox>();
        boxes.put(CollisionSideType.LEFT,
                new CollisionBox(heroCollisionBox.getX() - 1, heroCollisionBox.getY(),
                        1, heroCollisionBox.getHeight()));
        boxes.put(CollisionSideType.BOTTOM,
                new CollisionBox(heroCollisionBox.getX(), heroCollisionBox.getY() - 1,
                        heroCollisionBox.getWidth(), 1));
        boxes.put(CollisionSideType.RIGHT,
                new CollisionBox(heroCollisionBox.getX() + heroCollisionBox.getWidth(), heroCollisionBox.getY(),
                        1, heroCollisionBox.getHeight()));
        boxes.put(CollisionSideType.TOP,
                new CollisionBox(heroCollisionBox.getX(), heroCollisionBox.getY() + heroCollisionBox.getHeight(),
                        heroCollisionBox.getWidth(), 1));

        var x = boxes.entrySet().stream()
                .map(entry -> {
                    var intersection = entry.getValue().intersection(spriteCollisionBox);
                    var rectangle = new com.badlogic.gdx.math.Rectangle(intersection.getX(), intersection.getY(),
                            intersection.getWidth(), intersection.getHeight());
                    return new AbstractMap.SimpleEntry<>(entry.getKey(), rectangle.area());
                })
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));

        return Collections.max(x.entrySet(), Map.Entry.comparingByValue()).getKey();

        /*CollisionBox[] boxes = new CollisionBox[4];
        boxes[CollisionSideType.RIGHT.ordinal()] = new CollisionBox(heroCollisionBox.getX() - 1, heroCollisionBox.getY(), 1, heroCollisionBox.getHeight());
        boxes[CollisionSideType.BOTTOM.ordinal()] = new CollisionBox(heroCollisionBox.getX(), heroCollisionBox.getY() - 1, heroCollisionBox.getWidth(), 1);
        boxes[CollisionSideType.LEFT.ordinal()] = new CollisionBox(heroCollisionBox.getX() + heroCollisionBox.getWidth(), heroCollisionBox.getY(), 1, heroCollisionBox.getHeight());
        boxes[CollisionSideType.TOP.ordinal()] = new CollisionBox(heroCollisionBox.getX(), heroCollisionBox.getY() + heroCollisionBox.getHeight(), heroCollisionBox.getWidth(), 1);

        double greatestArea = 0;
        int greatest = -1;

        for (int i = 0; i < boxes.length; i++) {
            var intersection = spriteCollisionBox.intersection(boxes[i]);

            var rectangle = new Rectangle(intersection.getX(), intersection.getY(), intersection.getWidth(), intersection.getHeight());

            var area = rectangle.area();
            if (area > greatestArea) {
                greatestArea = area;
                greatest = i;
            }
        }

        return greatest;*/
    }
}
