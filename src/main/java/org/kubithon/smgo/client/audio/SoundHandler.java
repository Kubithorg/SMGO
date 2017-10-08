package org.kubithon.smgo.client.audio;

import static javax.sound.sampled.AudioSystem.getAudioInputStream;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioFormat.Encoding;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;

public class SoundHandler
{

    public static ArrayList                     soundList;
    public static HashMap<String, SoundManager> soundPlaying;
    public static int                           streamBuffer = 256;

    public static void initalize() {
        SoundHandler.soundList = new ArrayList();
        SoundHandler.soundPlaying = new HashMap<String, SoundManager>();
    }

    public static void playSoundFromStream(final int x, final int y, final int z, final int dimension, final float playRadius, final String localName,
            final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Connection to stream " + localName + "...");
                    Sound snd = new Sound(x, y, z, dimension, playRadius);
                    snd.setInfo(url, localName);
                    SoundHandler.soundPlaying.put(localName, new SoundManager(snd));
                    IcyURLConnection icyUrlConnection = new IcyURLConnection(new URL(url));
                    icyUrlConnection.setInstanceFollowRedirects(true);
                    icyUrlConnection.connect();
                    playSourceDataLine(snd, x, y, z, dimension, new BufferedInputStream(icyUrlConnection.getInputStream()));
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("Failed to stream: " + url);
                }
                SoundHandler.soundPlaying.remove(x + "," + y + "," + z + "," + dimension);
            }
        }).start();
    }

    private static void playSourceDataLine(Sound snd, int x, int y, int z, int dimension, BufferedInputStream object) {
        try {
            final AudioInputStream in = convertToPCM(getAudioInputStream(object));
            final AudioFormat outFormat = getOutFormat(in.getFormat());
            final Info info = new Info(SourceDataLine.class, outFormat);
            System.out.println("Playing stream" + snd.local);
            final SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
            try {
                if (line != null) {
                    line.open(outFormat);
                    if (snd.volume == null) {
                        snd.volume = (FloatControl) line.getControl(FloatControl.Type.MASTER_GAIN);
                        snd.volume.setValue(-20F);
                    }
                    line.start();
                    stream(getAudioInputStream(outFormat, in), line, x, y, z, dimension);
                    line.drain();
                    line.stop();
                }
            } finally {
                line.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to stream: URL: " + snd.url);
        }
    }

    private static AudioFormat getOutFormat(AudioFormat inFormat) {
        final int ch = inFormat.getChannels();
        final float rate = inFormat.getSampleRate();
        return new AudioFormat(Encoding.PCM_SIGNED, rate, 16, ch, ch * 2, rate, false);
    }

    private static void stream(AudioInputStream in, SourceDataLine line, int x, int y, int z, int dimension) throws IOException {
        final byte[] buffer = new byte[SoundHandler.streamBuffer];
        for (int n = 0; n != -1; n = in.read(buffer, 0, buffer.length)) {
            if (SoundHandler.soundPlaying.get(x + "," + y + "," + z + "," + dimension) == null)
                return;
            line.write(buffer, 0, n);
        }
    }

    private static int getSixteenBitSample(int high, int low) {
        return (high << 8) + (low & 0x00ff);
    }

    private static AudioInputStream convertToPCM(AudioInputStream audioInputStream) {
        AudioFormat format = audioInputStream.getFormat();
        if (format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED && format.getEncoding() != AudioFormat.Encoding.PCM_UNSIGNED) {
            AudioFormat targetFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, format.getSampleRate(), 16, format.getChannels(),
                    format.getChannels() * 2, format.getSampleRate(), format.isBigEndian());
            audioInputStream = AudioSystem.getAudioInputStream(targetFormat, audioInputStream);
        }
        return audioInputStream;
    }
}