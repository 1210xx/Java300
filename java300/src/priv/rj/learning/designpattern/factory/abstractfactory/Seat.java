package priv.rj.learning.designpattern.factory.abstractfactory;

public interface Seat {
    void massage();
}

class LuxurySeat implements Seat{

    @Override
    public void massage() {
        System.out.println("massage...");
    }

}

class LowSeat implements Seat{

    @Override
    public void massage() {
        System.out.println("uncomfotable...");
    }

}