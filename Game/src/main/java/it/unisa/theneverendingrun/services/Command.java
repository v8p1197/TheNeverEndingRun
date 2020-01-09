package it.unisa.theneverendingrun.services;

public interface Command<S> {

    S execute();
}
