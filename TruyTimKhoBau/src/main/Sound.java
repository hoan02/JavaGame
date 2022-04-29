package main;

import java.io.File;
//import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
    Clip clip;
    FloatControl fc;
    int volumeScale = 3;
    float volume;
    File[] pathSound = new File[20];
    public Sound() {
            pathSound[0] = new File("res/sound/background_sound.wav");
            pathSound[1] = new File("res/sound/coin.wav");
            pathSound[2] = new File("res/sound/unlock.wav");
            pathSound[3] = new File("res/sound/powerup.wav");
            pathSound[4] = new File("res/sound/fanfare.wav");
            pathSound[5] = new File("res/sound/lost.wav");
            pathSound[6] = new File("res/sound/cursor.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais =  AudioSystem.getAudioInputStream(pathSound[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

    public void checkVolume() {
        switch (volumeScale) {
            case 0 -> volume = -80f;
            case 1 -> volume = -20f;
            case 2 -> volume = -12f;
            case 3 -> volume = -5f;
            case 4 -> volume = 1f;
            case 5 -> volume = 6f;
        }
        fc.setValue(volume);
    }
}
