package com.shapran.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Random;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "cars")
public abstract class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String manufacturer;
    private Color color;
    protected int count;
    private int price;

    private Type type;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "engine_id")
    private Engine engine;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    private final Random random = new Random();

    public Car() {
    }

    public Car(String manufacturer, Color color, Engine engine, Type type) {
        this.id = UUID.randomUUID().toString();
        this.manufacturer = manufacturer;
        this.color = color;
        this.count = 1;
        this.price = random.nextInt(20500, 73000);
        this.engine = engine;
        this.type = type;
    }

    public Type getType(Type type){
        return type;
    }

   public static abstract class CarBuilder{
        protected String id;
        protected String manufacturer;
        protected Color color;
        protected int count;
        protected int price;
        protected Engine engine;

        public CarBuilder getId(){
            this.id = UUID.randomUUID().toString();
            return this;
        }
        public CarBuilder addManufacturer(String manufacturer){
            this.manufacturer = manufacturer;
            return this;
        }
        public CarBuilder addColor(Color color){
            this.color = color;
            return this;
        }
        public CarBuilder addCount(int count){
            this.count = count;
            return this;
        }
        public CarBuilder addPrice(int price){
            if (price < 1000){
                System.out.println("Price of the car is low");
            }
            this.price = price;
            return this;
        }
        public CarBuilder addEngine(Engine engine){
            this.engine = engine;
            return this;
        }
        public abstract Car build();

    }
}