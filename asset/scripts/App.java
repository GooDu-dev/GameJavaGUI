package asset.scripts;

import asset.scripts.inteface.Type;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.*;
import static java.util.concurrent.TimeUnit.SECONDS;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class App {
    // Frontend
    private static CustomFrame frame;
    private static String title="Game Title";
    private static int time = 0;
    private static Set<String> uniqueLetters;
    private static Map<String, JButton> buttonMap;
    private static JTextArea selectedLettersTextArea;

    // Backend
    public App(){

    }
    public App(String title){
        App.title = title;
    }
    public void run(){
        frame = new CustomFrame(App.title);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setResizable(false);
        frame.mainMenu();
    }
    public static void chapterStage(String word){
        frame.clearScreen();
        JLabel timer = new JLabel("Timer : ");

        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        final Runnable runnable = new Runnable() {
            int countdownStarter = 10;

            public void run() {

                System.out.println(countdownStarter);
                countdownStarter--;
                timer.setText("TImer : " + countdownStarter);

                if (countdownStarter < 0) {
                    gameOver();
                    scheduler.shutdown();
                }
            }
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
        frame.getContentPane().add(timer);
        timer.setFont(new Font(timer.getFont().getName(), timer.getFont().getStyle(), frame.getContentPane().getWidth()/50));
        timer.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        Integer LifeAll = 3;
        JLabel life = new JLabel("Life : " + LifeAll);
        frame.getContentPane().add(life);
        life.setFont(new Font(life.getFont().getName(), life.getFont().getStyle(), frame.getContentPane().getWidth()/50));
        life.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        
        JTextField answer = new JTextField();
        frame.getContentPane().add(answer);
        answer.setFont(new Font(answer.getFont().getName(), answer.getFont().getStyle(), frame.getContentPane().getWidth()/50));
        answer.addActionListener(new ActionListener(){
            Integer lifeCurrent = LifeAll;
            @Override
            public void actionPerformed(ActionEvent ae){
                String getValue = answer.getText();
                Boolean value = checkWord(getValue, word);
                if(value == false){
                    lifeCurrent--;
                    life.setText("Life : " + lifeCurrent);
                } else if(value == true){
                    wordBeforeStart();
                }
                if(lifeCurrent == 0){
                    gameOver();
                }
             }
        });
    }
    public static void wordBeforeStart(){
        frame.clearScreen();
        ArrayList<String> words = new ArrayList<>(Default.fetchWords());
        String wordRandom = words.get(new Random().nextInt(words.size()));
        JLabel word = new JLabel(wordRandom);
        frame.getContentPane().add(word);
        word.setFont(new Font(word.getFont().getName(), word.getFont().getStyle(), frame.getContentPane().getWidth()/50));
        word.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        word.setAlignmentY(JLabel.CENTER_ALIGNMENT);

        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        final Runnable runnable = new Runnable() {
            int countdownStarter = 1;

            public void run() {

                System.out.println(countdownStarter);
                countdownStarter--;

                if (countdownStarter < 0) {
                    chapterStage(wordRandom);
                    scheduler.shutdown();
                }
            }
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
    }
    public static boolean checkWord(String a, String b){
        return a.toUpperCase().equals(b.toUpperCase()) ? true : false;
    }
    public static void gameOver(){
        frame.clearScreen();
        JLabel gameOver = new JLabel("GAME OVER");
        frame.getContentPane().add(gameOver);
        gameOver.setFont(new Font(gameOver.getFont().getName(), gameOver.getFont().getStyle(), frame.getContentPane().getWidth()/50));
        gameOver.setForeground(Color.RED);
        gameOver.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        gameOver.setAlignmentY(JLabel.CENTER_ALIGNMENT);
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        final Runnable runnable = new Runnable() {
            int countdownStarter = 3;

            public void run() {

                System.out.println(countdownStarter);
                countdownStarter--;

                if (countdownStarter < 0) {
                    frame.mainMenu();
                    scheduler.shutdown();
                }
            }
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
    }
}
