package Project;

import java.applet.AudioClip;
import java.io.*;
import java.applet.Applet;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import java.util.Scanner;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JApplet;

public class bgm {
    public static AudioClip loadSound(String filename) {
        URL url = null;
        try {
            url = new URL("file:" + filename);
        } catch (MalformedURLException e) {
        }
        return JApplet.newAudioClip(url);
    }
    public void play() {
        AudioClip christmas = loadSound("Project/bgm1.wav");
        christmas.play();
    }
    public void stop() {
        AudioClip christmas = loadSound("Project/bgm1.wav");
        christmas.stop();
    }
}