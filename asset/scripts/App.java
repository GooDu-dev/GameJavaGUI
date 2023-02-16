package asset.scripts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class App {
    private static JFrame frame;
    private static final int APP_WIDTH=800, APP_HEIGHT=600;
    private static String title;

    public App(){
        title = "New Game";
    }
    public App(String title){
        this.title = title;
    }
    public void run(){
        createFrame();
    }
    public void createFrame(){
        frame = new JFrame(title);
        frame.setSize(APP_WIDTH, APP_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
    }
}
