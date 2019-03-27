package com.jeffreyromero.roomandgsontest.models.materials;


import com.jeffreyromero.roomandgsontest.models.Material;

public class Stud extends Material {

    public Stud(double length) {
        super("Stud", length);
    }

    @Override
    public void calcQuantity(double length, double height) {
        double pieces = length / 2;
        double lengths = height/getLength();
        super.setQuantity(pieces * lengths);
    }
}
