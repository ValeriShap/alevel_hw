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

//    public static class TruckBuilder extends CarBuilder{
//        protected int loadCapacity;
//
//        public CarBuilder getLoadCapacity(int loadCapacity){
//            this.loadCapacity = loadCapacity;
//            return this;
//        }
//        @Override
//        public Car build() {
//            if (count == 0){
//                System.out.println("Car is not create");
//            }
//            return new Truck(manufacturer, color, engine, loadCapacity, type);
//        }
//    }
}
