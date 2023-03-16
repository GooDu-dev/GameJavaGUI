package asset.scripts;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
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
    private Boolean isPlaying;
    private JButton exitButton;
    private JLabel gameStatusLabel;

    // Backend
    private String latest_chapter, highest_score;
    private String[] words;
    private Player player;
    private Enemy enemy;
    private int[] latest_save = new int[2]; // 0 : latest_chapter, 1 : highest_score
    private int ALL_CHAPTER = 30, current_chapter=1;

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
            System.out.println(Arrays.toString(input));
            latest_save[0] = Integer.parseInt(input[0]);
            latest_save[1] = Integer.parseInt(input[1]);
            scan.close();
        }catch(FileNotFoundException e){
            System.out.println("No File");
            File f = new File(Default.DEFAULT_SAVED_PATH);
            try{
                FileWriter fw = new FileWriter(Default.DEFAULT_SAVED_PATH);
                fw.write(1 + ", "+ 0);
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

        clearScreen();

        game_title = new JLabel(title);
        game_title.setFont(new Font(game_title.getFont().getName(), game_title.getFont().getStyle(), 48));
        game_title.setAlignmentX(Component.CENTER_ALIGNMENT);

        start_button = new JButton("Start");
        start_button.setFont(new Font(start_button.getFont().getName(), start_button.getFont().getStyle(), 24));
        start_button.setAlignmentX(Component.CENTER_ALIGNMENT);
        start_button.setSize(new Dimension(100, 50));
        start_button.addActionListener(event -> {
            selectEpisode();
        });

        endless_button = new JButton("Endless");
        endless_button.setFont(new Font(endless_button.getFont().getName(), endless_button.getFont().getStyle(), 24));
        endless_button.setAlignmentX(Component.CENTER_ALIGNMENT);

        exit_button = new JButton("Exit");
        exit_button.setFont(new Font(exit_button.getFont().getName(), exit_button.getFont().getStyle(), 24));
        exit_button.setAlignmentX(Component.CENTER_ALIGNMENT);

        frame.getContentPane().add(Box.createRigidArea(new Dimension(0, frame.getHeight()/4)));
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
    public void selectEpisode(){

        clearScreen();

        int latest_episode = latest_save[0]>=10 ? latest_save[0]/10 : 1 ;

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBorder(new LineBorder(Color.black));
        panel.setMaximumSize(new Dimension(frame.getWidth(), 200));
        for(int i=1; i<4; i++){
            JButton episode = new JButton();
            int chapter = i;
            if(i<=latest_episode){
                episode.setBackground(Color.white);
                episode.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        selectChapter(chapter);
                    }
                });
            }
            else{
                episode.setBackground(Color.gray);
            }
            episode.setPreferredSize(new Dimension(200, 200));
            episode.setFont(new Font(episode.getFont().getName(), episode.getFont().getStyle(), 24));
            panel.add(episode);

        }

        JButton back_button = new JButton("Back");
        back_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createStartMenu();
            }
        });
        game_title.setAlignmentY(Component.CENTER_ALIGNMENT);
        panel.setAlignmentY(Component.CENTER_ALIGNMENT);
        back_button.setAlignmentY(Component.CENTER_ALIGNMENT);
        frame.getContentPane().add(Box.createRigidArea(new Dimension(0, (int)(frame.getHeight()*0.2))));
        frame.getContentPane().add(game_title);
        frame.getContentPane().add(Box.createRigidArea(new Dimension(0, 20)));
        frame.getContentPane().add(panel);
        frame.getContentPane().add(Box.createRigidArea(new Dimension(0, 20)));
        frame.getContentPane().add(back_button);

    }
    public void selectChapter(int current_chapter){

        clearScreen();

        frame.getContentPane().add(Box.createRigidArea(new Dimension(0, (int)(frame.getHeight()*0.05))));
        frame.getContentPane().add(game_title);
        frame.getContentPane().add(Box.createRigidArea(new Dimension(0, 40)));

        JPanel chapter = new JPanel();
        chapter.setPreferredSize(new Dimension(200, 200));
        chapter.setMaximumSize(new Dimension((int)(frame.getWidth()*0.8), frame.getHeight()/2));
        chapter.setLayout(new GridLayout(2, 5));
        for(int i=1; i<11; i++){
            JButton button = new JButton(current_chapter+"-"+i);
            if(current_chapter * i <= latest_save[0]){
                button.setBackground(Color.white);
            }
            else{
                button.setBackground(Color.gray);
            }
            button.setFont(new Font(button.getFont().getName(), button.getFont().getStyle(), 24));
            chapter.add(button);
        }

        frame.getContentPane().add(chapter);
        frame.getContentPane().add(Box.createRigidArea(new Dimension(0, 20)));

        JButton back_button = new JButton("Back");
        back_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectEpisode();
            }
        });
        frame.getContentPane().add(back_button);

    }
    public void clearScreen(){
        frame.getContentPane().removeAll();
        frame.getContentPane().revalidate();
        frame.validate();
        frame.repaint();
    }
}
