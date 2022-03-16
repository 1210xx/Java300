package priv.rj.learning.designpattern.factory.simplefactory;

import priv.rj.learning.designpattern.factory.factorymethod.Audi;
import priv.rj.learning.designpattern.factory.factorymethod.Byd;
import priv.rj.learning.designpattern.factory.factorymethod.Car;

/**
 * 调用者
 *
 * 测试在没有工厂模式下\
 *
 *
 */
public class Clinet {
    public static void main(String[] args) {
        Car c1 = new Audi();
        Car c2 = new Byd();

        c1.run();
        c2.run();
    }
}
