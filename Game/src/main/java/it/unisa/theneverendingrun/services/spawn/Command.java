package it.unisa.theneverendingrun.services.spawn;

public interface Command<S> {

    S execute();
}
