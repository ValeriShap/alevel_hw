package com.shapran.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "engine")
public class Engine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String engine_id;
    protected String type;
    private int power;

    public Engine(String type, int power) {
        this.type = type;
        this.power = power;
        //this.engine_id = UUID.randomUUID().toString();

    }
    public Engine(String type, int power, String engine_id) {
        this.type = type;
        this.power = power;
        this.engine_id = engine_id;
    }

    public Engine() {
    }


    public void printType(){
        System.out.println("***" + type + "***");
    }
}
