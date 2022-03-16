package priv.rj.learning.designpattern.factory.factorymethod;

public class  BydFactory implements CarFactory {
    @Override
    public Car createCar() {
        return new Byd();
    }
}
