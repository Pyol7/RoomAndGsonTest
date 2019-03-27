package com.jeffreyromero.roomandgsontest.models.materials;


import com.jeffreyromero.roomandgsontest.models.Material;

public class CeilingPanel extends Material {

    public CeilingPanel(double length, double width) {
        super("Ceiling Panel", length, width);
    }

    @Override
    public void calcQuantity(double length, double width) {
        double area = length*width;
        super.setQuantity(area / (getLength() * getWidth()));
    }

}
