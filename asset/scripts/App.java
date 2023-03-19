package asset.scripts;

import asset.scripts.CustomFrame;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class App {
    // Frontend
    private static CustomFrame frame;
    private static String title="Game Title";
    private JLabel game_title;
    private JLabel option_exit;
    
    // Backend
    private static Map<String, Integer> data = new HashMap<String, Integer>();
    private final String CHAPTER="chapter", EPISODE="episode", HIGHEST_SCORE="highest_score";
    
    private final int MAX_CHAPTER=3, MAX_EPISODE=10;
    private int[] latest_save; 
    public App(){
        loadData();
    }
    public App(String title){
        App.title = title;
        loadData();
    }
    public void run(){
        frame = new CustomFrame(App.title);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setResizable(false);
        frame.chapterMenu();;
        //selectChapterMenu();
    }
    public void mainMenu(){
        frame.clearScreen();
        frame.getContentPane().add(Box.createRigidArea(new Dimension(0, frame.getContentPane().getHeight()/5)));
        
        // create game title text
        JLabel title = new JLabel(App.title);
        title.setFont(new Font(title.getFont().getName(), title.getFont().getStyle(), frame.getContentPane().getWidth()/20));
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        frame.getContentPane().add(title);
        frame.getContentPane().add(Box.createRigidArea(new Dimension(0, frame.getContentPane().getHeight()/20)));

        // create start button
        JButton startButton = new JButton("Start");
        startButton.setFont(new Font(startButton.getFont().getName(), startButton.getFont().getStyle(), frame.getContentPane().getWidth()/50));
        startButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        frame.getContentPane().add(startButton);
        frame.getContentPane().add(Box.createRigidArea(new Dimension(0, frame.getContentPane().getHeight()/25)));

        // create chapter button
        JButton chapterButton = new JButton("Chapter");
        chapterButton.setFont(new Font(chapterButton.getFont().getName(), chapterButton.getFont().getStyle(), frame.getContentPane().getWidth()/50));
        chapterButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        frame.getContentPane().add(chapterButton);
        frame.getContentPane().add(Box.createRigidArea(new Dimension(0, frame.getContentPane().getHeight()/25)));
        
        // create exit button
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font(exitButton.getFont().getName(), exitButton.getFont().getStyle(), frame.getContentPane().getWidth()/50));
        exitButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        frame.getContentPane().add(exitButton);
        frame.getContentPane().add(Box.createRigidArea(new Dimension(0, frame.getContentPane().getHeight()/25)));
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == exitButton){
                    WindowExit_Main();
                }
            }
        });

        frame.revalidate();
        frame.repaint();
    }
    public void selectChapterMenu(){
        frame.clearScreen();

        frame.getContentPane().add(Box.createRigidArea(new Dimension(0, frame.getContentPane().getHeight()/5)));

        JLabel title = new JLabel(App.title);
        title.setFont(new Font(title.getFont().getName(), title.getFont().getStyle(), frame.getContentPane().getWidth()/25));
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        frame.getContentPane().add(title);

        frame.getContentPane().add(Box.createRigidArea(new Dimension(0, frame.getContentPane().getHeight()/10)));

        JPanel chapter_container = new JPanel();
        chapter_container.setLayout(new BoxLayout(chapter_container, BoxLayout.X_AXIS));
        chapter_container.setMaximumSize(new Dimension((int)(frame.getContentPane().getWidth()*0.5), (int)(frame.getContentPane().getHeight()*0.2)));
        chapter_container.setSize(new Dimension((int)(frame.getContentPane().getWidth()*0.5), (int)(frame.getContentPane().getHeight()*0.2)));
        chapter_container.setMinimumSize(new Dimension((int)(frame.getContentPane().getWidth()*0.5), (int)(frame.getContentPane().getHeight()*0.2)));
        for(int i=0; i<MAX_CHAPTER; i++){
            JButton chapter = new JButton(String.valueOf(i+1));
            chapter_container.add(chapter);
            chapter.setFont(new Font(chapter.getFont().getName(), chapter.getFont().getStyle(), frame.getContentPane().getWidth()/50));
            chapter.setMaximumSize(new Dimension((int)(chapter.getParent().getWidth()*0.3), (int)(chapter.getParent().getHeight())));
            chapter.setSize(new Dimension((int)(chapter.getParent().getWidth()*0.3), (int)(chapter.getParent().getHeight())));
            chapter.setMinimumSize(new Dimension((int)(chapter.getParent().getWidth()*0.3   ), (int)(chapter.getParent().getHeight())));
            if(i<data.get(CHAPTER)){
                chapter.setBackground(Color.white);
            }
            else{
                chapter.setBackground(Color.lightGray);
            }
            if(i<MAX_CHAPTER-1) chapter_container.add(Box.createRigidArea(new Dimension((int)(chapter_container.getWidth()*0.05), 0)));
        }
        frame.getContentPane().add(chapter_container);
        frame.getContentPane().add(Box.createRigidArea(new Dimension(0, frame.getContentPane().getHeight()/10)));

        JButton exitButton = new JButton("Back");
        exitButton.setFont(new Font(exitButton.getFont().getName(), exitButton.getFont().getStyle(), frame.getContentPane().getWidth()/50));
        exitButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        frame.getContentPane().add(exitButton);

        frame.revalidate();
        frame.repaint();
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
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        wordBeforeStart("test word");
                        countdown(10);
                    }
                });
            }
        }
    }
    public void selectEpisodeMenu(){
        frame.clearScreen();

        frame.getContentPane().add(Box.createRigidArea(new Dimension(0, frame.getContentPane().getHeight()/10)));

        JLabel title = new JLabel(App.title);
        title.setFont(new Font(title.getFont().getName(), title.getFont().getStyle(), frame.getContentPane().getWidth()/25));
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        frame.getContentPane().add(title);

        frame.getContentPane().add(Box.createRigidArea(new Dimension(0, frame.getContentPane().getHeight()/10)));

        JPanel episode_container = new JPanel();
        episode_container.setLayout(new GridLayout(2, 5));
        episode_container.setMaximumSize(new Dimension((int)(frame.getContentPane().getWidth()*0.5), (int)(frame.getContentPane().getHeight()*0.4)));
        episode_container.setSize(new Dimension((int)(frame.getContentPane().getWidth()*0.5), (int)(frame.getContentPane().getHeight()*0.4)));
        episode_container.setMinimumSize(new Dimension((int)(frame.getContentPane().getWidth()*0.5), (int)(frame.getContentPane().getHeight()*0.4)));
        for(int i=0; i<MAX_EPISODE; i++){
            JButton episode = new JButton(String.valueOf(i+1));
            episode_container.add(episode);
            episode.setMaximumSize(new Dimension((int)(episode.getParent().getWidth()*0.2), episode.getParent().getHeight()));
            episode.setSize(new Dimension((int)(episode.getParent().getWidth()*0.2), episode.getParent().getHeight()));
            episode.setMinimumSize(new Dimension((int)(episode.getParent().getWidth()*0.2), episode.getParent().getHeight()));
            episode.setFont(new Font(episode.getFont().getName(), episode.getFont().getStyle(), frame.getContentPane().getWidth()/50));
            if(i<data.get(EPISODE)){
                episode.setBackground(Color.WHITE);
            }
            else{
                episode.setBackground(Color.GRAY);
            }
        }
        frame.getContentPane().add(episode_container);
        
        frame.getContentPane().add(Box.createRigidArea(new Dimension(0, frame.getContentPane().getHeight()/10)));

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font(backButton.getFont().getName(), backButton.getFont().getStyle(), frame.getContentPane().getWidth()/50));
        backButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        frame.getContentPane().add(backButton);
    }
    public void wordBeforeStart(String w){
        clearScreen();
        JLabel word = new JLabel(w);
        frame.getContentPane().add(word);
    }
    public void chapterStage(String w){
        clearScreen();
        
        JLabel countdown = new JLabel("Timer :");
        countdown.setFont(new Font(countdown.getFont().getName(),countdown.getFont().getStyle(), 30));
        countdown.setForeground(Color.white);
        
        JPanel upStage = new JPanel();
        JPanel downStage = new JPanel();
        upStage.setBackground(Color.DARK_GRAY);
        downStage.setBackground(Color.BLUE);
        upStage.add(countdown);

        frame.add(upStage);
        frame.add(downStage);
    }
    public void clearScreen(){
        frame.getContentPane().removeAll();
        frame.getContentPane().revalidate();
        frame.validate();
        frame.repaint();
    }
    public void countdown(int s){
        int seconds = s;
        while(seconds > 0){
            seconds--;
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(seconds);
        }
    }
    public void WindowExit_Main() {
        option_exit = new JLabel("Are you sure you want to exit?");
        option_exit.setHorizontalAlignment(JLabel.CENTER);
        int option = JOptionPane.showConfirmDialog(frame,option_exit,null, 0,JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.YES_OPTION) {
            System.exit(0);
            frame.revalidate();
            frame.repaint();
        }
    }
    public void WindowExit_During_Game(){
        option_exit = new JLabel("Are you sure you want to main menu?");
        option_exit.setHorizontalAlignment(JLabel.CENTER);
        int option = JOptionPane.showConfirmDialog(frame,option_exit,null, 0,JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.YES_OPTION) {
            mainMenu();
        }
    }
    public boolean loadData(){
            try (Scanner file = new Scanner(new File("./asset/saved.txt"))) {
                while(file.hasNextLine()){
                    String[] text = file.nextLine().split(":");
                    data.put(text[0], Integer.valueOf(text[1]));
                }
            } catch (NumberFormatException | FileNotFoundException e) {
                e.printStackTrace();
            }
            return true;
    }
    /* public void showLetters(){ I'm working don't delete please
        String [] letter = new String[10];
        for (String c : letter) {
            JButton char_of_word = new JButton(c);
            char_of_word.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    if(e.getSource() == char_of_word){
                        System.out.println(c);
                    }
                }
            });
        }
    } */
}
