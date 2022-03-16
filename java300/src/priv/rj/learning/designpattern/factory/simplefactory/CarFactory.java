package priv.rj.learning.designpattern.factory.simplefactory;

import priv.rj.learning.designpattern.factory.factorymethod.Audi;
import priv.rj.learning.designpattern.factory.factorymethod.Byd;
import priv.rj.learning.designpattern.factory.factorymethod.Car;

/**
 * @author rjjerry
 */
public class CarFactory {

    public static Car createCar(String type){
        if (type.equals("Audi")){
            return new Audi();
        }else if (type.equals("Byd")){
            return new Byd();
        }else {
            return null;
        }
    }

}
