
import model.Engine;
import service.CarService;
import model.Car;

public class Main {
    public static void main(String[] args) {

        CarService carService = new CarService();
        Car car1 = carService.create();
        carService.printf(car1);
        Car car2 = carService.create();
        carService.printf(car2);
        Car car3 = carService.create();
        carService.printf(car3);

        CarService.check(car1);
        CarService.check(car2);
        CarService.check(car3);


    }
}
