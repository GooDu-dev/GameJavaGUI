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
        frame.mainMenu();
        //selectChapterMenu();
    }
    public void wordBeforeStart(String w){
        frame.clearScreen();
        JLabel word = new JLabel(w);
        frame.getContentPane().add(word);
    }
    public void chapterStage(String w){
        frame.clearScreen();
        
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
            frame.mainMenu();
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
