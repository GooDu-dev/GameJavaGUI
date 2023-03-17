package asset.scripts;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class App {
    // Frontend
    private String title = "Game Title";
    private static JFrame frame = new Frame();
    
    // Backend
    private static Map<String, Integer> data = new HashMap<String, Integer>();
    private final String CHAPTER="chapter", EPISODE="episode", HIGHEST_SCORE="highest_score";
    
    private final int MAX_CHAPTER=3, MAX_EPISODE=10; 
    public App(){
        loadData();
    }
    public App(String title){
        this.title = title;
        loadData();
    }
    public void run(){
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setResizable(false);
        //mainMenu();
        selectEpisodeMenu();
    }
    public void mainMenu(){
        ((Frame)frame).clearScreen();
        frame.getContentPane().add(Box.createRigidArea(new Dimension(0, frame.getContentPane().getHeight()/5)));
        
        // create game title text
        JLabel title = new JLabel(this.title);
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

        frame.revalidate();
        frame.repaint();
    }
    public void selectChapterMenu(){
        ((Frame)frame).clearScreen();

        frame.getContentPane().add(Box.createRigidArea(new Dimension(0, frame.getContentPane().getHeight()/5)));

        JLabel title = new JLabel(this.title);
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
    public void selectEpisodeMenu(){
        ((Frame)frame).clearScreen();

        frame.getContentPane().add(Box.createRigidArea(new Dimension(0, frame.getContentPane().getHeight()/10)));

        JLabel title = new JLabel(this.title);
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

        frame.revalidate();
        frame.repaint();
    }
    public boolean loadData(){
        try{
            Scanner file = new Scanner(new File("./asset/saved.txt"));
            while(file.hasNextLine()){
                String[] text = file.nextLine().split(":");
                data.put(text[0], Integer.valueOf(text[1]));
            }
            return true;
        }
        catch(FileNotFoundException e){
            System.out.println(e);
        }
        return false;
    }
}