package org.kubithon.smgo.client.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Tests {
    public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
//        Mixer mixer = AudioSystem.getMixer(null);
//        
//        System.out.println(mixer.getMixerInfo());
//
//        
//        Clip clip = null;
//        for (Info info : mixer.getSourceLineInfo()) {
//            System.out.println(info);
//            Line line = mixer.getLine(info);
//            if (line instanceof Clip) {
//                clip = (Clip) line;
//            }
//        }
//        
//        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("/home/victor/Musique/jaycosmic.mp3"));
//        clip.open(audioInputStream);
        
            Clip clip = AudioSystem.getClip();
            // getAudioInputStream() also accepts a File or InputStream
            AudioInputStream ais = AudioSystem.
                getAudioInputStream( new File("/home/victor/Musique/Ysaye.wav") );
            clip.open(ais);
            clip.setMicrosecondPosition(3000000l);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    // A GUI element to prevent the Clip's daemon Thread
                    // from terminating at the end of the main()
                    JOptionPane.showMessageDialog(null, "Close to exit!");
                }
            });
            
    }
}
