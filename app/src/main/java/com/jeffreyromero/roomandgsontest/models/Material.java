package com.jeffreyromero.roomandgsontest.models;

public abstract class Material {
    private String subType;
    private String name;
    private double width;
    private double length;
    private double thickness;
    private double spacing;
    private double coverage;
    private double quantity;

    public abstract void calcQuantity(double width, double length);

    public Material(String subType) {
        this.subType = subType;
    }

    public Material(String subType, double length) {
        this.subType = subType;
        this.length = length;
    }

    public Material(String subType, double length, double width) {
        this.subType = subType;
        this.length = length;
        this.width = width;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getThickness() {
        return thickness;
    }

    public void setThickness(double thickness) {
        this.thickness = thickness;
    }

    public double getSpacing() {
        return spacing;
    }

    public void setSpacing(double spacing) {
        this.spacing = spacing;
    }

    public double getCoverage() {
        return coverage;
    }

    public void setCoverage(double coverage) {
        this.coverage = coverage;
    }
}
