package priv.rj.learning.designpattern.factory.simplefactory;

import priv.rj.learning.designpattern.factory.factorymethod.Car;

/**
 * 调用者
 * 简单工厂模式下
 */
public class Client02 {
    public static void main(String[] args) {
        Car c1 = CarFactory.createCar("Audi");
        Car c2 = CarFactory.createCar("Byd");

        c1.run();
        c2.run();

    }
}
