package asset.scripts;

import asset.scripts.inteface.Type;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.*;
import static java.util.concurrent.TimeUnit.SECONDS;
import java.awt.Color;
import java.util.Random;

public class App {
    // Frontend
    private static Integer score = 0;
    private static CustomFrame frame;
    private static String title="Game Title";
    private static int time = 0;
    private static Integer LifeAll = 3;

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

        frame.getContentPane().add(Box.createRigidArea(new Dimension(0, (int)(frame.getContentPane().getHeight()/4))));

        JLabel timer = new JLabel("Timer : ");

        CustomPanel container = new CustomPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));

        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        final Runnable runnable = new Runnable() {
            int countdownStarter = 10;

            public void run() {

                System.out.println(countdownStarter);
                countdownStarter--;
                timer.setText("TImer : " + countdownStarter + " ");

                if (countdownStarter < 0) {
                    scheduler.shutdown();
                    LifeAll--;
                    wordBeforeStart();
                }
            }
        };
        //frame.getContentPane().add(timer);
        container.add(timer);
        timer.setFont(new Font(timer.getFont().getName(), timer.getFont().getStyle(), frame.getContentPane().getWidth()/50));
        timer.setAlignmentX(JLabel.LEFT);

        JLabel life = new JLabel("Life : " + LifeAll + " ");
        //frame.getContentPane().add(life);
        container.add(life);
        life.setFont(new Font(life.getFont().getName(), life.getFont().getStyle(), frame.getContentPane().getWidth()/50));
        life.setAlignmentX(JLabel.LEFT);

        JLabel currentScore = new JLabel("Score : " + score + " ");
        currentScore.setFont(new Font(currentScore.getFont().getName(), currentScore.getFont().getStyle(), frame.getContentPane().getWidth()/50));
        currentScore.setAlignmentX(JLabel.LEFT);

        //frame.getContentPane().add(currentScore);
        container.add(currentScore);
        currentScore.setAlignmentX(JLabel.RIGHT_ALIGNMENT);

        frame.getContentPane().add(container);

        JTextField answer = new JTextField();
        frame.getContentPane().add(answer);
        answer.setMaximumSize(new Dimension((int)(answer.getParent().getWidth()*0.6), (int)(answer.getParent().getHeight()*0.3)));
        answer.setSize(new Dimension((int)(answer.getParent().getWidth()*0.6), (int)(answer.getParent().getHeight()*0.3)));
        answer.setMinimumSize(new Dimension((int)(answer.getParent().getWidth()*0.6), (int)(answer.getParent().getHeight()*0.3)));
        answer.setAlignmentX(JTextField.CENTER_ALIGNMENT);
        answer.setFont(new Font(answer.getFont().getName(), answer.getFont().getStyle(), frame.getContentPane().getWidth()/50));
        answer.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                String getValue = answer.getText();
                Boolean value = checkWord(getValue, word);
                if(value == false){
                    LifeAll--;
                    life.setText("Life : " + LifeAll);
                } else if(value == true){
                    score++;
                    wordBeforeStart();
                }
                if(LifeAll <= 0){
                    gameOver();
                }
            }
            
        });
        
        scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
    }
    public static void wordBeforeStart(){
        frame.clearScreen();
        ArrayList<String> words = new ArrayList<>(Default.fetchWords());
        String wordRandom = words.get(new Random().nextInt(words.size()));
        CustomLabel word = new CustomLabel(wordRandom);
        frame.getContentPane().add(word);
        word.setFontSize(15);
        word.setBorder(BorderFactory.createEmptyBorder((int)(word.getParent().getHeight()/3), 0, 0, 0));
        word.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        word.setAlignmentY(JLabel.CENTER_ALIGNMENT);

        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        final Runnable runnable = new Runnable() {
            int countdownStarter = 1;

            public void run() {

                System.out.println(countdownStarter);
                countdownStarter--;

                if (countdownStarter < 0) {
                    scheduler.shutdown();
                    chapterStage(wordRandom);
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
        CustomLabel gameOver = new CustomLabel("GAME OVER");
        frame.getContentPane().add(gameOver);
        gameOver.setFontSize(20);
        gameOver.setLabelSize(0.5, 1);
        // gameOver.setBorder(BorderFactory.createEmptyBorder((int)(gameOver.getParent().getHeight()/2), 0, 0, 0));
        gameOver.setForeground(Color.RED);
        gameOver.setHorizontalAlignment(SwingConstants.CENTER);
        gameOver.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        gameOver.setAlignmentY(JLabel.CENTER);
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        final Runnable runnable = new Runnable() {
            int countdownStarter = 3;

            public void run() {

                System.out.println(countdownStarter);
                countdownStarter--;

                if (countdownStarter < 0) {
                    scheduler.shutdown();
                    frame.mainMenu();
                }
            }
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
        LifeAll = 3;
    }
}
