package priv.rj.learning.designpattern.factory.factorymethod;

/**
 * 调用者
 *
 * @author rjjerry
 */
public class Client {
    public static void main(String[] args) {
        Car c1 = new AudiFactory().createCar();
        Car c2 = new BydFactory().createCar();
        Car c3 = new BenzFactory().createCar();
        c1.run();
        c2.run();
        c3.run();
    }
}
