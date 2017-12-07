package org.kubithon.smgo.client.audio;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundManager {

    private static final SoundManager INSTANCE = new SoundManager();
    private Map<String, Clip>         clips    = new HashMap<>();

    private SoundManager() {}

    public static SoundManager getInstance() {
        return INSTANCE;
    }

    private String loadSound(File file) {
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
            clip.open(inputStream);
            String hash = String.valueOf(clip.hashCode());
            this.clips.put(hash, clip);
            return hash;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void unload(String hash) {
        Clip clip = this.clips.get(hash);
        if (clip != null) {
            clip.close();
            this.clips.remove(hash);
        }
    }

    public void setTime(String hash, int millis) {
        Clip clip = this.clips.get(hash);
        if (clip != null && !clip.isActive())
            clip.setMicrosecondPosition(millis * 1000L);
    }

    public void pause(String hash) {
        Clip clip = this.clips.get(hash);
        if (clip != null && clip.isActive())
            clip.stop();
    }

    public void play(String hash) {
        Clip clip = this.clips.get(hash);
        if (clip != null && !clip.isActive())
            clip.start();
    }

    public String start(File file) {
        String hash = this.loadSound(file);
        this.play(hash);
        return hash;
    }

    public String start(File file, int millis) {
        String hash = this.loadSound(file);
        this.setTime(hash, millis);
        this.play(hash);
        return hash;
    }

    public void stop(String hash) {
        Clip clip = this.clips.get(hash);
        if (clip != null) {
            if (clip.isActive())
                clip.stop();
            this.unload(hash);
        }
    }
}
