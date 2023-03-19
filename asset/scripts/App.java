package asset.scripts;

import asset.scripts.CustomFrame;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class App {
    // Frontend
    private static CustomFrame frame;
    private static String title="Game Title";
    private Set<String> uniqueAlphabet;
    private Map<String, JButton> buttonMap;

    // Backend
    private int chapter=0, episode=0, highest_score=0;
    private String word;
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
    // public void confirmExit() {
    //     JLabel message = new JLabel("Are you sure you want to exit?");
    //     message.setHorizontalAlignment(JLabel.CENTER);
    //     int choice = JOptionPane.showConfirmDialog(frame, message, null, JOptionPane.YES_NO_OPTION);
    //     if (choice == JOptionPane.YES_OPTION) {
    //         System.exit(0);
    //     }
    // }    
    // public void windowExitDuringGame() {
    //     JLabel optionExit = new JLabel("Are you sure you want to go back to the main menu?");
    //     optionExit.setHorizontalAlignment(JLabel.CENTER);
    //     int option = JOptionPane.showConfirmDialog(frame, optionExit, null, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
    //     if (option == JOptionPane.YES_OPTION) {
    //         frame.mainMenu();
    //     }
    // }
    
    public void ShowLetter(String word) {
        HashSet<String> uniqueAlphabet;
        uniqueAlphabet = new HashSet<String>(Arrays.asList(word.split("")));
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
                        String uniqueAlphabet;
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
