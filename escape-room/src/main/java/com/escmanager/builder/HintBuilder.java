package com.escmanager.builder;

import com.escmanager.model.Element;
import com.escmanager.model.Hint;

public class HintBuilder extends ElementBuilder {

    private String theme;

    public HintBuilder setTheme(String theme) {
        this.theme = theme;
        return this;
    }

    @Override
    public Element build() {
        return new Hint(roomId, name, type, price, status, theme);
    }
}
