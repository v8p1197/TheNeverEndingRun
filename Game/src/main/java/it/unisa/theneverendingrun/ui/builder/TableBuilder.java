package it.unisa.theneverendingrun.ui.builder;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import it.unisa.theneverendingrun.ui.controls.InteractiveTextButton;

import java.util.List;

public class TableBuilder {

    private Table table;

    public TableBuilder() {
        table = new Table();
    }

    public TableBuilder withWidth(float width) {
        table.setWidth(width);
        return this;
    }
    public TableBuilder align(int align) {
        align(align);
        return this;
    }

    public TableBuilder withTopPad(float pad) {
        table.padTop(pad);
        return this;
    }

    public TableBuilder addInteractiveTextButtonWithPad(float pad, List<InteractiveTextButton> actors) {
        for (var actor : actors) {
            table.add(actor).padBottom(pad);
            table.row();
        }
        return this;
    }

    public TableBuilder withPosition(float x, float y) {
        table.setPosition(x, y);
        return this;
    }

    public Table build() {
        return table;
    }
}
