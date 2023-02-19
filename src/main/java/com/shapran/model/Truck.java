package com.shapran.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "truck")
@OnDelete(action = OnDeleteAction.CASCADE)
public class Truck extends Car implements CountRestore{
    private int loadCapacity;

    public Truck(String manufacturer, Color color, Engine engine, int loadCapacity, Type type) {
        super(manufacturer, color, engine, type );
        this.loadCapacity = loadCapacity;
        getType(Type.TRUCK);
    }

    public Truck() {

    }

    @Override
    public void restore() {
        this.count = 50;
        System.out.println("Count of truck - " + count);
    }
}
