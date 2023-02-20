package com.shapran.service.hibernateService;

import com.shapran.model.Engine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class EngineHibernateService {
    private static final Random random = new Random();
    private static final String[] engineType = {"Gas", "Diesel", "Electric"};
    private final static Logger LOGGER = LoggerFactory.getLogger(EngineHibernateService.class);

    public Engine createEngine(){
        Engine engine = new Engine();
        engine.setPower(random.nextInt(1000));
        engine.setType(engineType[random.nextInt(engineType.length)]);
        LOGGER.info("Engine was created with power{} and type{}", engine.getPower(), engine.getType());
        return engine;
    }
}
