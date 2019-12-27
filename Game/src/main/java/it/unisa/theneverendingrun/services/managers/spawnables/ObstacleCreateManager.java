package it.unisa.theneverendingrun.services.managers.spawnables;

import it.unisa.theneverendingrun.data.SpawnableEventType;
import it.unisa.theneverendingrun.data.SpawnableType;
import it.unisa.theneverendingrun.models.spawnables.Spawnable;
import it.unisa.theneverendingrun.services.factories.GameFactory;
import it.unisa.theneverendingrun.services.events.listener.SpawnableEventListener;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ObstacleCreateManager implements Runnable, SpawnableEventListener {

    private static final Logger LOGGER = Logger.getLogger(ObstacleCreateManager.class.getName());

    private Lock lock;

    private SpawnableCreateManager obstacleCreateManager;

    private float jumpHeight;
    private float slideDistance;




    public ObstacleCreateManager(GameFactory factory, SpawnableManager spawnableManager, int maxCapacity, float jumpHeight, float slideDistance) {
        this.slideDistance = slideDistance;

        lock = new ReentrantLock();
        updateJumpHeight(jumpHeight);

        obstacleCreateManager = new ObstacleCreateManagerTemplate(factory, spawnableManager, maxCapacity);

        spawnableManager.getEventManager().subscribe(SpawnableEventType.DRAWED, this);
    }

    public void updateJumpHeight(float jumpHeight) {
        lock.lock();
        this.jumpHeight = jumpHeight;
        lock.unlock();
    }



    @Override
    public void run() {
        if (obstacleCreateManager.isFull()) {
            try {
                wait();
            } catch (InterruptedException e) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
            }
        }
        lock.lock();
        obstacleCreateManager.create(jumpHeight, slideDistance);
        lock.unlock();
    }





    @Override
    public void update(SpawnableEventType type, Spawnable value) {
        notify();
    }





    private static class ObstacleCreateManagerTemplate extends SpawnableCreateManager implements SpawnableEventListener {

        private final GameFactory factory;

        ObstacleCreateManagerTemplate(GameFactory factory, SpawnableManager spawnableManager, int maxCapacity) {
            super(spawnableManager, maxCapacity);
            this.factory = factory;

            spawnableManager.getEventManager().subscribe(SpawnableEventType.DRAWED, this);
        }

        @Override
        public Spawnable getSpawnable(SpawnableType spawnableType, float jumpHeight, float slideDistance) {
            return factory.createObstacle(getType(), jumpHeight, slideDistance);
        }

        @Override
        public void update(SpawnableEventType type, Spawnable value) {
            addSpace();
        }
    }


}
