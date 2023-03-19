package asset.scripts;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.HashMap;

public class CustomFrame extends JFrame{
    private String title = "Game Title";
    private int width=800, height=600;
    public CustomFrame(){
        DEFAULT_SETTING();
    }
    public CustomFrame(String title){
        this.title = title;
        DEFAULT_SETTING();
    }
    public void DEFAULT_SETTING(){
        this.setTitle(title);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(width, height);
        // set frame to full screen
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(this);
    }
    public void mainMenu(){
        clearScreen();

        getContentPane().add(Box.createRigidArea(new Dimension(0, getContentPane().getHeight()/5)));

        // create title
        CustomLabel title = new CustomLabel(this.title);
        getContentPane().add(title);
        title.setFontSize(25);
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        getContentPane().add(Box.createRigidArea(new Dimension(0, getContentPane().getHeight()/10)));

        // create start button
        CustomButton start = new CustomButton("Start");
        getContentPane().add(start);
        start.setAlignmentX(JButton.CENTER_ALIGNMENT);
        start.setFontSize(50);
        start.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                
            }
        });

        getContentPane().add(Box.createRigidArea(new Dimension(0, getContentPane().getHeight()/25)));

        // create chapter button
        CustomButton chapter = new CustomButton("Chapter");
        getContentPane().add(chapter);
        chapter.setAlignmentX(JButton.CENTER_ALIGNMENT);
        chapter.setFontSize(50);
        chapter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                
            }
        });

        getContentPane().add(Box.createRigidArea(new Dimension(0, getContentPane().getHeight()/25)));

        // create exit button
        CustomButton exit = new CustomButton("Exit");
        getContentPane().add(exit);
        exit.setAlignmentX(JButton.CENTER_ALIGNMENT);
        exit.setFontSize(50);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                
            }
        });

        revalidate();
        repaint();
    }
    public void chapterMenu(){
        clearScreen();

        getContentPane().add(Box.createRigidArea(new Dimension(0, getContentPane().getHeight()/10)));

        CustomPanel container = new CustomPanel();
        getContentPane().add(container);
        container.setLayout(new BoxLayout(container, MAXIMIZED_BOTH));
        container.setPanelSize(0.5, 0.2);
        
        Map<String, Integer> data = new HashMap<String, Integer>();
        try{
            data = Default.loadData();
        }
        catch(FileNotFoundException e){
            System.out.println("No saved.txt"); 
            System.out.println(e);
            return; 
        }
        for(int i=0; i<Default.MAX_CHAPTER; i++){
            CustomButton chapter = new CustomButton(i+1);
            container.add(chapter);
            if(i<data.get(Default.CHAPTER)){
                chapter.setBackground(Color.WHITE);
            }
            else{
                chapter.setBackground(Color.GRAY);
            }
            chapter.setFontSize(50);
            chapter.setButtonSize(0.3, 1);
            if(i<Default.MAX_CHAPTER-1) container.add(Box.createRigidArea(new Dimension(0, getContentPane().getHeight()/10)));
        }
        getContentPane().add(Box.createRigidArea(new Dimension(0, getContentPane().getHeight()/10)));

        // create exit button
        CustomButton exit = new CustomButton("Exit");
        getContentPane().add(exit);
        exit.setFontSize(50);
        exit.setAlignmentX(JButton.CENTER_ALIGNMENT);

        revalidate();
        repaint();
    }
    public void clearScreen(){
        this.getContentPane().removeAll();
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }
}
