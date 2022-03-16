package priv.rj.learning.designpattern.factory.simplefactory;

import priv.rj.learning.designpattern.factory.factorymethod.Audi;
import priv.rj.learning.designpattern.factory.factorymethod.Byd;
import priv.rj.learning.designpattern.factory.factorymethod.Car;

/**
 * 简单工厂类
 * @author rjjerry
 */
public class CarFactory02 {
    public static Car createAudi(){
        return new Audi();
    }
    public static Car createByd(){
        return new Byd();
    }
}
