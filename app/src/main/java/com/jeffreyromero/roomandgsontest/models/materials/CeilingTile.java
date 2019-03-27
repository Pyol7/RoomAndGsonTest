package com.jeffreyromero.roomandgsontest.models.materials;

import com.jeffreyromero.roomandgsontest.models.Material;

public class CeilingTile extends Material {

    public CeilingTile(double length, double width) {
        super("Ceiling Tile", length, width);
    }

    @Override
    public void calcQuantity(double length, double width) {
        super.setQuantity((length * width)/(getLength() * getWidth()));
    }
}
