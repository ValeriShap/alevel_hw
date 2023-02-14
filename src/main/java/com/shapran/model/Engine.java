package com.shapran.model;

public class Engine {
    protected String type;
    private int power;

    public Engine(String type) {
        this.type = type;
    }

    public int getPower() {
        return power;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void printType(){
        System.out.println("***" + type + "***");
    }
}
