package com.shapran.service.hibernateService;

import com.shapran.model.Engine;

import java.util.Random;

public class EngineHibernateService {
    private static final Random random = new Random();
    private static final String[] engineType = {"Gas", "Diesel", "Electric"};

    public Engine createEngine(){
        Engine engine = new Engine();
        engine.setPower(random.nextInt(1000));
        engine.setType(engineType[random.nextInt(engineType.length)]);
        return engine;
    }
}
