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
@Table(name = "passenger_cars")
@OnDelete(action = OnDeleteAction.CASCADE)
public class  PassengerCar extends Car implements CountRestore {
    private int passengerCount;

    public PassengerCar(String manufacturer, Color color, Engine engine, int passengerCount, Type type) {
        super(manufacturer, color, engine, type);
        this.passengerCount = passengerCount;
        getType(Type.CAR);
    }

    public PassengerCar() {
    }

    @Override
    public void restore() {
        this.count = 100;
        System.out.println("Count of passenger car - " + count);
    }

//    public static class PassengerCarBuilder extends CarBuilder{
//        protected int passengerCount;
//
//        public CarBuilder getPassengerCount(int passengerCount){
//            this.passengerCount = passengerCount;
//            return this;
//        }
//        @Override
//        public Car build() {
//            if (count == 0){
//                System.out.println("Car is not create");
//            }
//            return new PassengerCar(manufacturer, color, engine, passengerCount,type);
//        }
//    }
}
