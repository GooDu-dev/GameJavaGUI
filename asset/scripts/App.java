package asset.scripts;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.ComponentSampleModel;
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
    private static String title="Game Title";
    private JLabel game_title;
    private JButton start_button, endless_button, exit_button;

    // Backend
    private String latest_chapter, highest_score;
    private String[] words;
    private Player player;
    private Enemy enemy;
    private int[] latest_save = new int[2]; // 0 : latest_chapter, 1 : highest_score
    private int ALL_CHAPTER = 30;

    public App(){

    }
    public App(String title){
        this.title = title;
    }
    public void run(){
        createFrame();
        loadSaved();
        loadWord();
        createStartMenu();
    }
    public void createFrame(){
        frame = new JFrame(title);
        frame.setSize(APP_WIDTH, APP_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setResizable(false);
        frame.setVisible(true);
    }
    public void loadSaved(){
        try{
            Scanner scan = new Scanner(new File(Default.DEFAULT_SAVED_PATH));
            String _input = scan.nextLine();
            String[] input = _input.split(", ");
            latest_save[0] = Integer.parseInt(input[0]);
            latest_save[1] = Integer.parseInt(input[1]);
            scan.close();
        }catch(FileNotFoundException e){
            File f = new File(Default.DEFAULT_SAVED_PATH);
            try{
                FileWriter fw = new FileWriter(Default.DEFAULT_SAVED_PATH);
                fw.write(0 + ", "+ 0);
                fw.close();
            }catch(IOException er){ return; }
        }
    }
    public void loadWord(){
        Scanner scan = new Scanner(Default.DEFAULT_WORD_PATH);
        words = scan.nextLine().split(",");
        scan.close();
    }
    public void createStartMenu(){
        game_title = new JLabel(title);
        game_title.setFont(new Font(game_title.getFont().getName(), game_title.getFont().getStyle(), 48));
        game_title.setAlignmentX(Component.CENTER_ALIGNMENT);
        game_title.setBorder(new EmptyBorder(APP_HEIGHT/4, 0, 0, 0));

        start_button = new JButton("Start");
        start_button.setFont(new Font(start_button.getFont().getName(), start_button.getFont().getStyle(), 24));
        start_button.setAlignmentX(Component.CENTER_ALIGNMENT);
        start_button.setSize(new Dimension(100, 50));
        start_button.addActionListener(event -> {
            select_chapter();
        });

        endless_button = new JButton("Endless");
        endless_button.setFont(new Font(endless_button.getFont().getName(), endless_button.getFont().getStyle(), 24));
        endless_button.setAlignmentX(Component.CENTER_ALIGNMENT);

        exit_button = new JButton("Exit");
        exit_button.setFont(new Font(exit_button.getFont().getName(), exit_button.getFont().getStyle(), 24));
        exit_button.setAlignmentX(Component.CENTER_ALIGNMENT);

        frame.getContentPane().add(game_title);
        frame.getContentPane().add(Box.createRigidArea(new Dimension(0, 20)));
        frame.getContentPane().add(start_button);
        frame.getContentPane().add(Box.createRigidArea(new Dimension(0, 20)));
        frame.getContentPane().add(endless_button);
        frame.getContentPane().add(Box.createRigidArea(new Dimension(0, 20)));
        frame.getContentPane().add(exit_button);

        frame.revalidate();
        frame.repaint();
    }

    public void select_chapter(){
        frame.getContentPane().removeAll();
        frame.getContentPane().revalidate();
        frame.validate();
        frame.repaint();

        game_title.setBorder(new EmptyBorder(APP_HEIGHT/20, 0, 0, 0));
        frame.getContentPane().add(game_title);
        frame.getContentPane().add(Box.createRigidArea(new Dimension(0, 40)));

        JLabel chapter = new JLabel("Chapter");
        chapter.setMinimumSize(new Dimension(500, 300));
        chapter.setLayout(new GridLayout());
        for(int i=0; i<3; i++) {
            for (int j = 0; j < 10; j++) {
                JButton stage = new JButton((i + 1) + "-" + (j + 1));
                if (i * j < latest_save[0]) {
                    stage.setBackground(Color.white);
                } else {
                    stage.setBackground(Color.darkGray);
                }
                stage.setBounds(stage.getX(), stage.getY(), 100, 100);
                GridBagConstraints setting = new GridBagConstraints();
                setting.gridx = j;
                setting.gridy = i;
                chapter.add(stage, setting);
            }
        }

        JButton test = new JButton("Test");
        frame.getContentPane().add(test);

        frame.getContentPane().add(chapter);
    }
}
