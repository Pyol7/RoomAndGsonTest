package com.jeffreyromero.roomandgsontest.models.materials;


import com.jeffreyromero.roomandgsontest.models.Material;

public class WallPanel extends Material {

    public WallPanel(double length, double width) {
        super("Wall Panel", length, width);
    }

    @Override
    public void calcQuantity(double length, double height) {
        double area = length*height;
        super.setQuantity(area / (getLength() * getWidth()));
    }

}
