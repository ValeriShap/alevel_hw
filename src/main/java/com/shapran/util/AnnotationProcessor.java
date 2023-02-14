package com.shapran.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.shapran.anotations.Autowired;
import com.shapran.anotations.Singleton;
import com.shapran.repository.Crud;
import org.reflections.Reflections;

public class AnnotationProcessor {
    public static final Map<Class, Object> cache = new HashMap<>();

    public void getSingleton(){
        Reflections reflections = new Reflections("com.shapran.repository");
        Set<Class<?>> singletonClass = reflections.getTypesAnnotatedWith(Singleton.class);
        for (Class<?> clas: singletonClass){
            createCache(clas);
        }
    }

    public void getAutowired(){
        Reflections reflections = new Reflections("com.shapran");
        Set<Class<?>> annotatedSingletonClass = reflections.getTypesAnnotatedWith(Singleton.class);
        for (Class<?> clas : annotatedSingletonClass) {
            Constructor<?>[] constructors = clas.getDeclaredConstructors();
            Arrays.stream(constructors)
                    .filter(constructor -> constructor.getDeclaredAnnotation(Autowired.class) != null)
                    .forEach(constructor -> {
                        constructor.setAccessible(true);
                        Class<? extends Crud> repository = constructor.getDeclaredAnnotation(Autowired.class).CrudRepossitory();
                        try {
                            Object o = constructor.newInstance(cache.get(repository));
                            if (!cache.containsKey(clas)) {
                                cache.put(clas, o);
                            }
                        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    });
        }
        }


    private void createCache(Class<?> clas){
        Method getInstance;
        try {
            getInstance = clas.getMethod("getInstance");
            getInstance.setAccessible(true);
            getInstance.invoke(clas);
        }catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e){
            throw new RuntimeException(e);
        }
        cache.put(clas,getInstance);
    }
}
