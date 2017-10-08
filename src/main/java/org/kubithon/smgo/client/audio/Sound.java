package org.kubithon.smgo.client.audio;

import javax.sound.sampled.FloatControl;

public class Sound
{
    public String       url, local;
    public int          x, y, z, dimension;
    public float        playRadius = 800F;
    public FloatControl volume     = null;

    public Sound() {}

    public Sound(int x, int y, int z, int dimension, float playRadius) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.dimension = dimension;
        this.playRadius = playRadius;
    }

    public Sound setInfo(String url, String local) {
        this.url = url;
        this.local = local;
        return this;
    }

    @Override
    public String toString() {
        return this.url + "\2476" + this.local;
    }

    public static Sound fromString(String str) {
        String[] strr = str.split("\2476");
        Sound snd = new Sound();
        snd.url = strr[0];
        snd.local = strr[1];
        return snd;
    }
}
