package com.escmanager.builder;

import com.escmanager.model.Element;
import com.escmanager.model.Prop;

public class PropBuilder extends ElementBuilder {

    private String materialType;

    public PropBuilder setMaterialType(String materialType) {
        this.materialType = materialType;
        return this;
    }

    @Override
    public Element build() {
        return new Prop(id, roomId, name, type, price, status, dateCreated, lastUpdated, materialType);
    }
}
