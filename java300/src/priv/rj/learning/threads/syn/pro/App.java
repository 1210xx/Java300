package priv.rj.learning.threads.syn.pro;

public class App {
    public static void main(String[] args) {
        Movie movie = new Movie();

        PLayer pLayer = new PLayer(movie);
        Watcher watcher = new Watcher(movie);

        new Thread(pLayer).start();
        new Thread(watcher).start();

    }
}
