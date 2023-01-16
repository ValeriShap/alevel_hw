package com.shapran.service;

import com.shapran.exception.UserInputException;
import com.shapran.model.*;
import com.shapran.repository.CarArrayRepository;
import com.shapran.util.RandomGenerator;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CarService {
    private final CarArrayRepository carArrayRepository;
    private final RandomGenerator randomGenerator = new RandomGenerator();
    private Random random = new Random();

    public CarService(final CarArrayRepository carArrayRepository) {
        this.carArrayRepository = carArrayRepository;
    }

    public Car createCar(Type type) {
        Car car = new PassengerCar();
        car.setManufacturer(randomGenerator.randomString());
        car.setEngine(new Engine(randomGenerator.randomString()));
        car.setColor(randomGenerator.randomColor());
        car.setCount(randomGenerator.randomNumber());
        car.setPrice(randomGenerator.randomNumber());
        carArrayRepository.save(car);
        return car;

    }
    public boolean carEquals(Car bmw, Car audi) {
        if (bmw.getClass().equals(audi.getClass()) && bmw.hashCode() == audi.hashCode()) {
            return bmw.equals(audi);
        } else {
            return false;
        }
    }

    public void printManufacturerAndCount(Car car){
        Optional.ofNullable(car).ifPresent(x -> {
            System.out.printf("Manufaccturer: %s, count: %d%n", x.getManufacturer(), x.getCount());
        });
    }

    public void printColor(Car car){
        Car car1 = Optional.ofNullable(car).orElse(createCar(Type.CAR));
        System.out.printf("Color of: " + car1.getId() + " " + car1.getColor());
    }

    public void checkCount(Car car) throws UserInputException {
        Optional<Car> carOptional = Optional.ofNullable(car);
        Car filterCar = carOptional
                .filter(c-> c.getCount() > 10)
                .orElseThrow(UserInputException:: new);
        System.out.printf("Manufaccturer: %s, count: %d%n" + filterCar.getManufacturer(), filterCar.getCount());
    }

    public void printEngineInfo(Car car){
        Optional<Car> carOptional = Optional.ofNullable(car);
        Car randomCar = carOptional.orElseGet(() -> {
            System.out.print("Create a new random car");
            return createRandomCar();
        });
        carOptional.map(Engine -> {
            return Engine.getEngine().getPower();
        }).ifPresent(power -> System.out.println("Engine power: " + power));
    }

    public void printInfo(Car car){
        Optional<Car> carOptional = Optional.ofNullable(car);
        carOptional.ifPresentOrElse(
                b -> print(b),
                () -> print(createRandomCar()));
    }

    public void createCar(Type type, int count) {
        for (int i = 0; i < count; i++) {
            createCar(type);
        }
    }

    public int create() {
        int count = randomGenerator.randomNumberCar();
        if (count <= 0) {
            return -1;
        } else {
            for (int i = 1; i <= count; i++) {
                print(createCar(Type.CAR));
            }
            return count;
        }
    }

    public void printAll() {
        final Car[] all = carArrayRepository.getAll();
        System.out.println(Arrays.toString(all));
    }

    public Car[] getAll() {
        return carArrayRepository.getAll();
    }

    public Car find(final String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }
        return carArrayRepository.getById(id);
    }

    public void delete(final String id) {
        if (id == null || id.isEmpty()) {
            return;
        }
        carArrayRepository.delete(id);
    }

    public void print(Car car) {
        System.out.println(car);
    }

    public Car createRandomCar(){
        return createCar(randomGenerator.randomType());
    }

    public static void check(Car car) {
        if (car.getEngine() == null) {
            System.out.println("Engine is null");
            return;
        }
            if (car.getCount() > 0 && car.getEngine().getPower() > 200) {
                System.out.println("The car is ready for sale");
            } else if (car.getCount() == 0 && car.getEngine().getPower() < 200) {
                System.out.println("Car is not available and low power");
            } else if (car.getCount() == 0) {
                System.out.println("Car is not available");
            } else if
                (car.getEngine().getPower() < 200) {
                    System.out.println("Low power");
                }else {
                System.out.println("The engine is 0");
            }
            }

            public PassengerCar  createPassengerCar(Type car){
        PassengerCar passengerCar = new PassengerCar();
        Color color = randomGenerator.randomColor();
        return passengerCar;
            }

            public HashMap<String, Integer> getMapManufacturerAndCount(Car[] cars){
                HashMap<String,Integer> map = new  HashMap<>();
                for (Car car: cars){
                    map.put(car.getManufacturer(), car.getCount());
                }
                return map;
            }

            public Map<String, List<Car>> getMapEngineAndPower(Car[] cars){
                Map<String, List<Car>> collect = new HashMap<>();
                for (Car car: cars )
                        if (collect.containsKey(car.getEngine().getType())){
                            collect.get(car.getEngine().getType()).add(car);
                        }else {
                            List<Car> carList = new ArrayList<>();
                            carList.add(car);
                            collect.put(car.getEngine().getType(), carList);
                        }
                return collect;
            }

            public void findManafacturerByPrice(List<Car> cars, int price){
        cars.stream()
                .filter(car -> car.getPrice() > price)
                .forEach(car -> System.out.println("Manafacturer: " + car.getManufacturer()));
            }

            public int countSum(List<Car> cars){
        int sum = cars.stream()
                .map(Car::getCount)
                .reduce(0, (first, last) -> first + last);
        return sum;
            }

            public LinkedHashMap<String, Type> mapToMap(List<Car> cars){
        LinkedHashMap<String, Type> mapCar = cars.stream()
                .sorted(Comparator.comparing(Car::getManufacturer))
                .distinct()
                .collect(Collectors.toMap(Car::getId, Car ::getType, (key1, key2) -> key1, LinkedHashMap::new));
        return mapCar;
            }

            public void statistic(List<Car> cars){
        IntSummaryStatistics statistics = cars.stream()
                .mapToInt(Car::getPrice)
                .summaryStatistics();
                System.out.println("Statistic from price: " + statistics);
            }

            public void priceCheck(List<Car> cars){
                Predicate<Car> isPricy = e -> e.getPrice() > 35000;
                cars.stream()
                        .filter(isPricy).forEach(System.out::println);
            }

            public Car mapToObject(Map<String, Object> map){
                Function<Map<String, Object>, Car> fieldsMap = (map1 -> {
                    if (map1.get("Type") ==Type.CAR){
                        return new PassengerCar();
                    }else if (map1.get("Type") == Type.TRUCK){
                        return new Truck();
                    }else{
                        throw new NullPointerException("No such type");
                    }
                });

                return fieldsMap.andThen(x ->{
                 x.setManufacturer((String) map.get("Manufacturer"));
                 x.setColor((Color) map.get("Color"));
                 x.setCount((int) map.get("Count"));
                 x.setPrice((int) map.get("Price"));
                 return x;
                })
                        .apply(map);
            }

            public Map<Color, Integer> innerList(List<List<Car>> cars){
       Map<Color,Integer> sortedCar = cars.stream()
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(Car::getColor))
                .peek((car) -> {
                    System.out.println(car);
                })
                .collect(Collectors.toMap(Car::getColor, Car::getCount, (item, lastItem) -> item));
        return sortedCar;
            }

            public Map<String,Object> fromFileToCar(String file){
                ClassLoader loader = Thread.currentThread().getContextClassLoader();
                InputStream input = loader.getResourceAsStream(file);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
                Map<String,Object> map = new HashMap<>();
                List<String> collect = bufferedReader.lines().toList();
                Pattern pattern = Pattern.compile("(\\w|-)+");
                String key = null;
                for (String s: collect){
                    Matcher matcher = pattern.matcher(s);
                    if (matcher.find()){
                         key = matcher.group();
                    }
                    if (matcher.find()){
                        String value = matcher.group();
                        map.put(key,value);
                    }
                }
                return map;
            }


//            public Truck createTruck(){
//        Truck truck = new Truck();
//        Color color = randomColor();
//        return truck;}

        }


