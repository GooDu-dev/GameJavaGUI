package asset.scripts;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

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
        setTitle(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(width, height);
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
        CustomButton start = new CustomButton("asset/picture/button/play-button.png");
        getContentPane().add(start);
        start.setSize(100, 60);
        start.setAlignmentX(JButton.CENTER_ALIGNMENT);
        start.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                chapterMenu();
            }
        });

        getContentPane().add(Box.createRigidArea(new Dimension(0, getContentPane().getHeight()/25)));

        // create endless button
        CustomButton endless = new CustomButton("Endless");
        getContentPane().add(endless);
        endless.setAlignmentX(JButton.CENTER_ALIGNMENT);
        endless.setFontSize(50);
        endless.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        getContentPane().add(Box.createRigidArea(new Dimension(0, getContentPane().getHeight()/25)));

        // create exit button
        CustomButton exit = new CustomButton("Exit");
        getContentPane().add(exit);
        exit.setAlignmentX(JButton.CENTER_ALIGNMENT);
        exit.setFontSize(50);
        // exit.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         int choice = JOptionPane.showConfirmDialog(exit, "Are you sure?");
        //         switch(choice){
        //             case JOptionPane.YES_OPTION -> {
        //                 Default.savedData(Default.data);
        //                 System.exit(0);
        //             }
        //         }
        //     }
        // });

        revalidate();
        repaint();
    }
    public void chapterMenu(){
        clearScreen();

        getContentPane().add(Box.createRigidArea(new Dimension(0, getContentPane().getHeight()/5)));

        CustomLabel title = new CustomLabel(this.title);
        getContentPane().add(title);
        title.setFontSize(20);
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        getContentPane().add(Box.createRigidArea(new Dimension(0, getContentPane().getHeight()/10)));

        CustomPanel container = new CustomPanel();
        getContentPane().add(container);
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        container.setPanelSize(0.5, 0.2);
        for(int i=0; i<Default.MAX_CHAPTER; i++){
            int id = i;
            CustomButton chapter = new CustomButton(i+1);
            container.add(chapter);
            if(i<Default.data.get(Default.CHAPTER)){
                chapter.setBackground(Color.WHITE);
            }
            else{
                chapter.setBackground(Color.GRAY);
            }
            chapter.setFontSize(50);
            chapter.setButtonSize(0.3, 1);
            // chapter.addActionListener(new ActionListener() {
            //     @Override
            //     public void actionPerformed(ActionEvent e) {
            //         episodeMenu(id+1);
            //     }
            // });
            if(i<Default.MAX_CHAPTER-1) container.add(Box.createRigidArea(new Dimension(getContentPane().getHeight()/10, 0)));
        }

        getContentPane().add(Box.createRigidArea(new Dimension(0, getContentPane().getHeight()/10)));

        // create exit button
        CustomButton exit = new CustomButton("Exit");
        getContentPane().add(exit);
        exit.setFontSize(50);
        exit.setAlignmentX(JButton.CENTER_ALIGNMENT);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenu();
            }
        });
        
        revalidate();
        repaint();
    }
    // public void episodeMenu(int e){
    //     clearScreen();

    //     getContentPane().add(Box.createRigidArea(new Dimension(0, getContentPane().getHeight()/10)));

    //     CustomLabel title = new CustomLabel(Default.CHAPTER + " " + (Default.data.get(Default.CHAPTER)));
    //     getContentPane().add(title);
    //     title.setFontSize(20);
    //     title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
    //     getContentPane().add(Box.createRigidArea(new Dimension(0, getContentPane().getHeight()/20)));

    //     CustomPanel group = new CustomPanel();
    //     getContentPane().add(group);
    //     group.setPanelSize(0.8, 0.5);
    //     group.setLayout(new GridLayout(2, 5));
    //     for(int i=0; i<Default.MAX_EPISODE; i++){
    //         CustomButton episode = new CustomButton(i+1);
    //         group.add(episode);
    //         episode.setFontSize(50);
    //         episode.setButtonSize(0.2, 1);
    //         if(i<Default.data.get(Default.EPISODE)){
    //             episode.setBackground(Color.WHITE);
    //         }
    //         else{
    //             episode.setBackground(Color.GRAY);
    //         }
    //         switch(e){
    //             case 1 -> {
    //                 // set episode in chapter 1 icon
    //             }
    //             case 2 -> {
    //                 // set episode in chapter 2 icon
    //             }
    //             case 3 -> {
    //                 // set episode in chapter 3 icon
    //             }
    //         }
    //     }

    //     getContentPane().add(Box.createRigidArea(new Dimension(0, getContentPane().getHeight()/10)));

    //     CustomButton back = new CustomButton("Back");
    //     getContentPane().add(back);
    //     back.setFontSize(50);
    //     back.setAlignmentX(JButton.CENTER_ALIGNMENT);
    //     back.addActionListener(new ActionListener() {
    //         @Override
    //         public void actionPerformed(ActionEvent e) {
    //             chapterMenu();
    //         }
    //     });
    // }
    public void clearScreen(){
        getContentPane().removeAll();
        getContentPane().revalidate();
        getContentPane().repaint();
    }
}