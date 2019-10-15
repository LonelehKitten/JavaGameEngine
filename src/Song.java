import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Song {

    private Clip clip;

    public Song(String path){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void play(int loop) {
        if(loop == 0) clip.start();
        else clip.loop(loop);
    }

    public void stop(){
        clip.close();
    }

    public void pause(){
        clip.stop();
    }

    public boolean isPlaying(){
        return clip.isRunning();
    }
}