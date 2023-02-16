package asset.scripts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class App implements Default{
    // Frontend
    private static JFrame frame;
    private static final int APP_WIDTH=800, APP_HEIGHT=600;
    private static String title="New Game";

    // Backend
    private String lastest_chapter, highest_score;
    private String[] words;
    private Player player;
    private Enemy enemy;

    public App(){

    }
    public App(String title){
        this.title = title;
    }
    public void run(){
        createFrame();
        loadSaved();
        loadWord();
        createEnv();
    }
    public void createFrame(){
        frame = new JFrame(title);
        frame.setSize(APP_WIDTH, APP_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
    }
    public void loadSaved(){
        try{
            Scanner scan = new Scanner(new File(Default.DEFAULT_SAVED_PATH));
            String _input = scan.nextLine();
            System.out.println(_input);
            String[] input = _input.split(", ");
            System.out.println(Arrays.toString(input));
            lastest_chapter = input[0];
            highest_score = input[1];
            scan.close();
        }catch(FileNotFoundException e){
            File f = new File(Default.DEFAULT_SAVED_PATH);
            try{
                FileWriter fw = new FileWriter(f);
                fw.write("0");
                fw.write("0");
                fw.close();
            }catch(IOException er){ return; }
        }
    }
    public void loadWord(){
        Scanner scan = new Scanner(Default.DEFAULT_WORD_PATH);
        words = scan.nextLine().split(",");
        scan.close();
    }
    public void createEnv(){
        frame.getContentPane().setBackground(Color.black);

    }
}
