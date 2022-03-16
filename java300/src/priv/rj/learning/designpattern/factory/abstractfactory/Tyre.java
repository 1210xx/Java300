package priv.rj.learning.designpattern.factory.abstractfactory;

public interface Tyre {
    void revolve();
}

class LuxuryTyre implements Tyre{

    @Override
    public void revolve() {
        System.out.println("stable...");
    }
}

class LowTyre implements Tyre{

    @Override
    public void revolve() {
        System.out.println("unstable...");
    }
}