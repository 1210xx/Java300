package priv.rj.learning.threads.syn.pro;

public class PLayer implements Runnable{
    private Movie movie;

    public PLayer(Movie movie) {
        this.movie = movie;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (0 == i%2){
                movie.play("左青龙");
            }else {
                movie.play("右白虎");
            }
        }
    }
}
