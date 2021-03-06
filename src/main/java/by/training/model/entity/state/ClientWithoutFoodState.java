package by.training.model.entity.state;

import by.training.model.entity.Client;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ClientWithoutFoodState implements ClientState {

    private static final Lock LOCK = new ReentrantLock(true);
    private static ClientWithoutFoodState instance = null;
    private static AtomicBoolean isInitialized = new AtomicBoolean(false);

    private ClientWithoutFoodState(){
    }

    public static ClientWithoutFoodState getInstance() {
        if (!isInitialized.get()) {
            LOCK.lock();
            try {
                if (instance == null) {
                    instance = new ClientWithoutFoodState();
                    isInitialized.set(true);
                }
            } finally {
                LOCK.unlock();
            }
        }
        return instance;
    }

    @Override
    public void changeState(Client client) {
        ClientWithFoodState newStat = ClientWithFoodState.getInstance();
        client.setState(newStat);
    }
}
