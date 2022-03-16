package priv.rj.learning.threads.syn.pro;

public class Watcher implements Runnable{
    private Movie movie;

    public Watcher(Movie movie) {
        this.movie = movie;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            movie.watch();
        }
    }
}
