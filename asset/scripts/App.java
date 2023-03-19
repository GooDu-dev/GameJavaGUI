package asset.scripts;

import javax.swing.*;
import java.awt.*;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.*;
import static java.util.concurrent.TimeUnit.SECONDS;

public class App {
    // Frontend
    private static CustomFrame frame;
    private static String title="Game Title";
    private static int time = 0;

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
    public void chapterStage(String w){
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
        field.setPanelSize(0.9, 0.5);
        field.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        Player player = new Player(100, "", ObjectType.Player);
        Enemy enemy = new Enemy(100, "", ObjectType.Enemy);


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
    
    public void ShowLetter(String word) {
        HashSet<String> uniqueAlphabet = new HashSet<String>(Arrays.asList(word.split("")));
        HashMap<String, JButton> buttonMap = new HashMap<String, JButton>();
        String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
                            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

        for (String letter : alphabet){ // add a random button for each letter in the alphabet
            JButton charButton = new JButton(letter);
            if(uniqueAlphabet.contains(letter)) charButton.setBackground(Color.RED);
            charButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    if(e.getSource() == charButton){
                        if(uniqueAlphabet.contains(letter)){
                            charButton.setBackground(Color.GREEN); 
                            uniqueAlphabet.remove(letter);
                            if (uniqueAlphabet.isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Correct" + word + "!");
                            }
                        }else{
                            charButton.setBackground(Color.GRAY);
                        }
                    }
                }
            });
            buttonMap.put(letter, charButton);
        }
        //panel click button
        JPanel buttonPanel = new JPanel(new GridLayout(2, 13));
        for (String letter : alphabet) {
            buttonPanel.add(buttonMap.get(letter));
        }
    }
}
