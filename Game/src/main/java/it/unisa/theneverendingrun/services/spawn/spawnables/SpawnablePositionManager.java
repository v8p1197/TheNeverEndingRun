package it.unisa.theneverendingrun.services.spawn.spawnables;

public class SpawnablePositionManager {

  //  private Map<ObstacleType, List<Obstacle>> obstaclesToAdd;

    /*private final float baseX;
    private final float baseY;

    private Spawnable lastSpawnable;
    private Spawnable penultimateSpawnable;

    public SpawnablePositionManager(float baseX, float baseY) {
        this.baseX = baseX;
        this.baseY = baseY;

        lastSpawnable = null;
        penultimateSpawnable = null;
    }

    public Spawnable a() {

    }






    private float generateX(float maxX) {

        float limitMinX = baseX;

        if (!isFirst()) {
            limitMinX = lastSpawnable.getX() + lastSpawnable.getWidth();
        }

        return (float) ThreadLocalRandom.current().nextDouble(limitMinX, maxX);
    }

    private boolean isFirst() {
        return lastSpawnable == null;
    }











    private float maxSlideDistance;
    private float maxJumpDistance;
    private float maxJumpHeight;
    private float slideHeight;

    private Batch batch;



    public ObstaclePositioningManager(float baseX, float baseY,
                                      float maxSlideDistance, float slideHeight,
                                      float maxJumpDistance, float maxJumpHeight,
                                      Batch batch) {

        this.baseX = baseX;
        this.baseY = baseY;
        this.maxSlideDistance = maxSlideDistance;
        this.maxJumpDistance = maxJumpDistance;
        this.maxJumpHeight = maxJumpHeight;
        this.slideHeight = slideHeight;
        this.batch = batch;

        obstaclesToAdd = new HashMap<>();

        lastSpawnable = null;
        penultimateSpawnable = null;
    }


    @Override
    CoordinateXType getCoordinateXType(float coordinateX) {
        if (coordinateX < baseX) throw new IllegalArgumentException("coordinateX cannot be minus then baseX");

        if(isFirst())
            return CoordinateXType.FAR;

        if (areFar(coordinateX, lastSpawnable.getX() + lastSpawnable.getWidth()))
            return CoordinateXType.FAR;

        if (areNear(coordinateX, lastSpawnable.getX() + lastSpawnable.getWidth()))
            return CoordinateXType.NEAR;

        return CoordinateXType.INTERMEDIATE;
    }


    @Override
    boolean isCreable(CoordinateXType type) {

        if (isFirst() || penultimateSpawnable == null) return true;
        if (type == CoordinateXType.FAR) return true;

        //TODO: For now not allow intermediate obstacle
        if (type == CoordinateXType.INTERMEDIATE) return false;

        //TODO: For now allow max 2 near obstacles
        return !areNear(lastSpawnable.getX(), penultimateSpawnable.getX() + penultimateSpawnable.getWidth());

    }

    @Override
    ObstacleType[] getPossibleComponentTypes(CoordinateXType type) {

        if (type == null) throw new NullPointerException("the coordinate x type cannot be null");

        if (type == CoordinateXType.FAR)
            return ObstacleType.values();

        if (type == CoordinateXType.NEAR) {

            if (isJumpable(lastSpawnable.getY() + lastSpawnable.getHeight()) && isSlidable(lastSpawnable.getY()))
                return ObstacleType.values();

            if (isJumpable(lastSpawnable.getY() + lastSpawnable.getHeight()))
                return ObstacleType.values();

            if (isSlidable(lastSpawnable.getY()))
                return new ObstacleType[] { ObstacleType.SLIDEABLE, ObstacleType.JUMPABLE_AND_SLIDEABLE };
        }


        throw new IllegalArgumentException("Intermediate Obstacle are not allowed");
    }

    @Override
    ObstacleType getComponentType(ObstacleType[] types) {
        if (types == null) throw new NullPointerException("the obstacles type cannot be null");

        var componentTypes = Arrays.asList(types);
        Collections.shuffle(componentTypes);
        var componentTypesSize = componentTypes.size();
        var componentTypesIndex = ThreadLocalRandom.current().nextInt(componentTypesSize);
        return componentTypes.get(componentTypesIndex);
    }

    @Override
    Obstacle getComponent(ObstacleType type) {

        if (type == null) throw new NullPointerException("the obstacle type cannot be null");

        var obstacleList = obstaclesToAdd.get(type);
        Collections.shuffle(obstacleList);
        var obstaclesToAddSize = obstacleList.size();
        var obstaclesToAddIndex = ThreadLocalRandom.current().nextInt(obstaclesToAddSize);

        return obstacleList.get(obstaclesToAddIndex);
    }






    @Override
    float generateYCoordinate(ObstacleType type, CoordinateXType xType, Obstacle obstacle) {

        if (type == null) throw new NullPointerException("the obstacle type cannot be null");
        if (xType == null) throw new NullPointerException("the coordinate x type cannot be null");
        if (obstacle == null) throw new NullPointerException("the obstacle cannot be null");

        if (type == ObstacleType.JUMPABLE) {
            if (xType == CoordinateXType.NEAR && isJumpable(lastSpawnable.getY() + lastSpawnable.getHeight()))
                return (float)ThreadLocalRandom.current().nextDouble(baseY, lastSpawnable.getY() + lastSpawnable.getHeight());

            return (float)ThreadLocalRandom.current().nextDouble(baseY,  baseY + slideHeight - 1);

        }


        if (type == ObstacleType.SLIDEABLE) {
            if (xType == CoordinateXType.NEAR) {
                if (isJumpable(lastSpawnable.getY() + lastSpawnable.getHeight()))
                    return slideHeight + 1 + lastSpawnable.getY() + lastSpawnable.getHeight();
                else if(isSlidable(lastSpawnable.getY()) && lastSpawnable.getWidth() + obstacle.getWidth() <= maxSlideDistance)
                    return slideHeight + 1;
            } else {
                return slideHeight + 1; //larghezza slide
            }
        }

        if (xType == CoordinateXType.NEAR) {

        } else {
            return (float)ThreadLocalRandom.current().nextDouble(slideHeight + 1, maxJumpHeight - obstacle.getHeight());
        }
    }



    @Override
    void settingComponent(Obstacle component, float[] coordinates) {
        if (component == null) throw new NullPointerException("the component cannot be null");
        if (coordinates == null) throw new NullPointerException("the coordinates cannot be null");
        component.setPosition(coordinates[0], coordinates[1]);
    }

    @Override
    void drawComponent(Obstacle component) {
        if (component == null) throw new NullPointerException("the component cannot be null");
        component.draw(batch);
        penultimateSpawnable = lastSpawnable;
        lastSpawnable = component;
    }





    private boolean isSlidable(float y) {
        return y > slideHeight;
    }

    private boolean isJumpable(float y) {
        return y < maxJumpHeight;
    }

    private boolean areNear(float x1, float x2) {
        return Math.abs(x1 - x2) <= MathUtils.DELTA;
    }

    private boolean areFar(float x1, float x2) {
        return Math.abs(x1 - x2) > getMaxDistance();
    }

    private float getMaxDistance() {
        return Math.max(maxSlideDistance, maxJumpDistance);
    }
*/

}
