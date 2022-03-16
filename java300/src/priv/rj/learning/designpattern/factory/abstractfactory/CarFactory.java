package priv.rj.learning.designpattern.factory.abstractfactory;

public interface CarFactory {
    Engine createEngine();
    Seat createSeat();
    Tyre createTyre();
}
