package org.kubithon.smgo.client.audio;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SoundManager
{

    public int           current        = -1;
    public List<Sound>   songs          = new ArrayList<Sound>();
    public List<Integer> completedSongs = new ArrayList<Integer>();
    private final Random rand;

    public SoundManager(Sound sound) {
        this.songs.add(sound);
        this.rand = new Random();
    }

    public Sound getCurrentSong() {
        return this.current == -1 ? null : this.current < this.songs.size() ? this.songs.get(this.current) : null;
    }

    public void addSong(Sound sound) {
        this.songs.add(sound);
    }

    public Sound nextSong() {
        if (this.current != -1)
            this.completedSongs.add(this.current);
        if (this.completedSongs.size() > 0 && !(this.completedSongs.size() >= this.songs.size()))
            while (this.completedSongs.contains(this.current))
                this.current = this.rand.nextInt(this.songs.size());
        else
            this.current++;
        if (this.current >= this.songs.size()) {
            this.completedSongs.clear();
            for (Sound s : this.songs)
                s.volume = null;
            this.current = 0;
        }
        return this.getCurrentSong();
    }
}
