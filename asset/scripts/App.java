package asset.scripts;

import asset.scripts.inteface.Type;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.*;
import static java.util.concurrent.TimeUnit.SECONDS;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Map;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class App {
    // Frontend
    private static CustomFrame frame;
    private static String title="Game Title";
    private static int time = 0;
    private Set<String> uniqueLetters;
    private Map<String, JButton> buttonMap;
    private JTextArea selectedLettersTextArea;

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
    public void wordBeforeStart(String w){
        frame.clearScreen();
        JLabel word = new JLabel(w);
        frame.getContentPane().add(word);
    }
    public static void chapterStage(int level){
        frame.clearScreen();
        JLabel timer = new JLabel("Timer : ");
        Timer t = new Timer(1000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                time++;
                timer.setText("Timer : "+time);
            }
        });
        frame.getContentPane().add(timer);
        timer.setFont(new Font(timer.getFont().getName(), timer.getFont().getStyle(), frame.getContentPane().getWidth()/50));
        timer.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
        t.start();

        CustomPanel field = new CustomPanel();
        frame.getContentPane().add(field);
        field.setLayout(new BoxLayout(field, BoxLayout.X_AXIS));
        field.setPanelSize(0.6, 0.5);
        field.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        // create player
        Player player = new Player(3, "", Type.ObjectType.Player);
        field.add(player);
        player.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        // create enemy
        Enemy enemy = new Enemy(3, "", Type.ObjectType.Enemy);
        field.add(enemy);
        enemy.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
        
        CustomPanel board = new CustomPanel();
        frame.getContentPane().add(board);
        board.setLayout(new BoxLayout(board, BoxLayout.Y_AXIS));
        

        CustomPanel h_container = new CustomPanel();
        frame.getContentPane().add(h_container);
        h_container.setLayout(new BoxLayout(h_container, BoxLayout.X_AXIS));
        for(int i=0; i<player.getHp(); i++){
            CustomLabel heart = new CustomLabel();
            frame.getContentPane().add(heart);
            if(i < player.getHp()-1){
                h_container.add(Box.createRigidArea(new Dimension((int)(h_container.getWidth()*0.1), 0)));
            }
        }
        
    }
    public void countdown(int s){
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        final Runnable runnable = new Runnable() {
            int countdown = s;
            public void run() {
                System.out.println(countdown);
                countdown--;
                if (countdown <+ 0) {
                    scheduler.shutdown();
                }
            }
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
    }
    
    public void showWords(String word) {
        this.uniqueLetters = new HashSet<String>(Arrays.asList(word.split("")));
        this.buttonMap = new HashMap<String, JButton>();
        this.selectedLettersTextArea = new JTextArea(2, 20);

        // create an array of all letters A-Z
        String[] alphabet = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
                "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

        // add a random button for each letter in the alphabet
        for(String letter : alphabet){
            JButton charButton = new JButton(letter);
            if(uniqueLetters.contains(letter)){
                charButton.setBackground(Color.RED);
            }
            charButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    if(e.getSource() == charButton){
                        if(uniqueLetters.contains(letter)){
                            charButton.setBackground(Color.GREEN);
                            removeLetter(letter);
                            // add the selected letter
                            selectedLettersTextArea.append(letter);
                            selectedLettersTextArea.append(" ");
                            if(uniqueLetters.isEmpty()){
                                JOptionPane.showMessageDialog(null, "Congratulations! You guessed the word " + word + "!");
                            }
                        }else{
                            charButton.setBackground(Color.GRAY);
                        }
                    }
                }
            });
            buttonMap.put(letter, charButton);
        }
        // create a panel to click button
        JPanel buttonPanel = new JPanel(new GridLayout(2, 13));
        for (String letter : alphabet) {
            buttonPanel.add(buttonMap.get(letter));
        }
        JPanel mainPanel = new JPanel();
        mainPanel.add(buttonPanel);
        mainPanel.add(selectedLettersTextArea);
        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }
    public void removeLetter(String letter) {
        uniqueLetters.remove(letter);
    }
}
