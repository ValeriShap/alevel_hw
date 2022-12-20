package com.shapran.util;

import com.shapran.model.Car;
import com.shapran.repository.CarArrayRepository;

public class AlgorithmUtil {
    static CarArrayRepository repository = new CarArrayRepository();
    public static Car[] bubbleSort(Car[] cars){
        boolean sort = false;
        for (int i = 0; i < cars.length-1; i++){
            if ((cars[i].getId().compareTo(cars[i+1].getId()))> 0){
                Car min = cars[i+1];
                cars[i+1] = cars[i];
                cars[i] = min;
                sort = true;
            }
        }
        return cars;
    }

    public int binarySearch(Car[] cars, Car searchCar) {
        int firstIndex = 0;
        int lastIndex = cars.length - 1;

        while (firstIndex <= lastIndex){
            int middleIndex = (firstIndex + lastIndex) / 2;
            if (cars[middleIndex].getId().compareTo(searchCar.getId()) == 0){
                return middleIndex;
            }else if (cars[middleIndex].getId().compareTo(searchCar.getId()) < 0){
                firstIndex = middleIndex + 1;
            }else if (cars[middleIndex].getId().compareTo(searchCar.getId()) > 0){
                lastIndex = middleIndex - 1;
            }

        }
        return -1;
    }
}
