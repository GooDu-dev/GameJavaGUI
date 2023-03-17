package asset.scripts;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.*;

public class Frame extends JFrame{
    private String title = "Game Title";
    private int width=800, height=600;
    public Frame(){
        DEFAULT_SETTING();
    }
    public Frame(String title){
        this.title = title;
        DEFAULT_SETTING();
    }
    public void DEFAULT_SETTING(){
        this.setTitle(title);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(width, height);
        // set frame to full screen
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(this);
    }
    public void clearScreen(){
        this.getContentPane().removeAll();
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }
}
