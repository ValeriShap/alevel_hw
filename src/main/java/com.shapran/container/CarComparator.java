package com.shapran.container;

import com.shapran.model.Car;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CarComparator <T extends Car> {
    private T car;

    public CarComparator() {
        this.car = car;
    }

    public int toCompareCount(T car1, T car2){
        car1.getCount();
        car2.getCount();
        if (car1.getCount() < car2.getCount()){
            return car2.getCount();
        }else{
            return car1.getCount();
        }
    }
}
