package priv.rj.learning.io.pattern;

public class Voice {
    private int voice = 10;

    public Voice() {
    }

    public Voice(int voice) {
        this.voice = voice;
    }

    public int getVoice() {
        return voice;
    }

    public void setVoice(int voice) {
        this.voice = voice;
    }

    public void say(){
        System.out.println(voice);
    }
}
