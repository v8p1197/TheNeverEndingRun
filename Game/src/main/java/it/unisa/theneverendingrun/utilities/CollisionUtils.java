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
        if (a == null) throw new NullPointerException("Sprite a is null");
        if (b == null) throw new NullPointerException("Sprite b is null");

        return a.getCollisionBox().intersects(b.getCollisionBox());
    }

    public static Rectangle computeIntersection(Sprite a, Sprite b) {
        if (a == null) throw new NullPointerException("Sprite a is null");
        if (b == null) throw new NullPointerException("Sprite b is null");

        return a.getCollisionBox().intersection(b.getCollisionBox());
    }

    public static CollisionSideType collisionSide(CollisionBox a, CollisionBox b) {
        if (a == null) throw new NullPointerException("a collision box is null");
        if (b == null) throw new NullPointerException("b collision box is null");

        var boxes = new HashMap<CollisionSideType, CollisionBox>();
        boxes.put(CollisionSideType.LEFT, new CollisionBox(a.getX() - 1, a.getY(), 1, a.getHeight()));
        boxes.put(CollisionSideType.BOTTOM, new CollisionBox(a.getX(), a.getY() - 1, a.getWidth(), 1));
        boxes.put(CollisionSideType.RIGHT, new CollisionBox(a.getX() + a.getWidth(), a.getY(),1, a.getHeight()));
        boxes.put(CollisionSideType.TOP, new CollisionBox(a.getX(), a.getY() + a.getHeight(), a.getWidth(), 1));

        return boxes.entrySet()
                .stream()
                .map(entry -> {
                    var intersection = entry.getValue().intersection(b);
                    var rectangle = new com.badlogic.gdx.math.Rectangle(intersection.getX(), intersection.getY(),
                            intersection.getWidth(), intersection.getHeight());
                    return new AbstractMap.SimpleEntry<>(entry.getKey(), rectangle.area());
                })
                .max((c1, c2) -> Float.compare(c1.getValue(), c2.getValue()))
                .map(AbstractMap.SimpleEntry::getKey)
                .orElse(null)
                ;

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
