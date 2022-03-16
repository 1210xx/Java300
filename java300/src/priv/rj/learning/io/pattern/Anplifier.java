package priv.rj.learning.io.pattern;

public class Anplifier {
    private Voice voice;

    public Anplifier() {
    }

    public Anplifier(Voice voice) {
        this.voice = voice;
    }

    public void say(){
        System.out.println(voice.getVoice() * 100);
    }
}
