package priv.rj.learning.designpattern.factory.abstractfactory;

public interface Engine {
    void run();
    void start();
}

class LuxuryEngine implements Engine{

    @Override
    public void run() {
        System.out.println("run fast");
    }

    @Override
    public void start() {
        System.out.println("fast start, auto start");
    }
}

class LowEngine implements Engine{

    @Override
    public void run() {
        System.out.println("run slow");
    }

    @Override
    public void start() {
        System.out.println("low start, no  auto start");
    }
}