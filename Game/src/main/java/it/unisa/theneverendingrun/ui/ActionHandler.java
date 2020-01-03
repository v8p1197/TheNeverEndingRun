package it.unisa.theneverendingrun.ui;

import java.util.List;

public interface ActionHandler<T> {

    void action(List<T> buttons);
}
