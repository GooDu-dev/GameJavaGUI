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
    
public class WordGame extends JFrame {

    private String word;
    private Set<String> uniqueLetters;
    private Map<String, JButton> buttonMap;
    private JTextArea selectedLettersTextArea;

    public void showWords(String word) {
        this.word = word;
        this.uniqueLetters = new HashSet<String>(Arrays.asList(word.split("")));
        this.buttonMap = new HashMap<String, JButton>();
        this.selectedLettersTextArea = new JTextArea(2, 20);

        // create an array of all letters A-Z
        String[] alphabet = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
                "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

        // add a random button for each letter in the alphabet
        for (String letter : alphabet) {
            JButton charButton = new JButton(letter);
            if (uniqueLetters.contains(letter)) {
                charButton.setBackground(Color.RED);
            }
            charButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == charButton) {
                        if (uniqueLetters.contains(letter)) {
                            charButton.setBackground(Color.GREEN);
                            removeLetter(letter);
                            // add the selected letter
                            selectedLettersTextArea.append(letter);
                            selectedLettersTextArea.append(" ");
                            if (uniqueLetters.isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Congratulations! You guessed the word " + word + "!");
                            }
                        } else {
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
        add(mainPanel);
        pack();
        setVisible(true);
    }
    public void removeLetter(String letter) {
        uniqueLetters.remove(letter);
    }
}
}