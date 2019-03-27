package com.jeffreyromero.roomandgsontest.models.materials;


import com.jeffreyromero.roomandgsontest.models.Material;

public class WallAngle extends Material {

    public WallAngle(double length) {
        super("Wall Angle", length);
    }

    @Override
    public void calcQuantity(double length, double width) {
        double par = (length * 2) + (width * 2);
        super.setQuantity(par / getLength());
    }
}
