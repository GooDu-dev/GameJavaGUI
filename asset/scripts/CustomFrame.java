package asset.scripts;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

public class CustomFrame extends JFrame{
    private static final TimeUnit SECONDS = null;
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
        setIconImage(Toolkit.getDefaultToolkit().getImage("asset/picture/logo/game.png"));
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
        title.setFont(new Font(Font.SERIF,Font.BOLD, 70));
        getContentPane().add(title);
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        getContentPane().add(Box.createRigidArea(new Dimension(0, getContentPane().getHeight()/10)));
        // create start button
        CustomButton start = new CustomButton("asset/picture/button/play-button.png");
        getContentPane().add(start);
        // start.setButtonSize(0.001, 0.0006);
        start.setOpaque(false);
        start.setContentAreaFilled(false);
        start.setBorderPainted(false);
        start.setAlignmentX(JButton.CENTER_ALIGNMENT);
        start.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                chapterMenu();
            }
        });
        getContentPane().add(Box.createRigidArea(new Dimension(0, getContentPane().getHeight()/20)));
        
        // create exit button
        CustomButton exit = new CustomButton("asset/picture/button/exit-button.png");
        getContentPane().add(exit);
        exit.setOpaque(false);
        exit.setContentAreaFilled(false);
        exit.setBorderPainted(false);
        exit.setAlignmentX(JButton.CENTER_ALIGNMENT);
        exit.setButtonSize(0.6, 1);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Default.savedData(Default.data);
                System.exit(0);
            }
        });
        revalidate();
        repaint();
    }
    public void chapterMenu(){
        clearScreen();
        getContentPane().add(Box.createRigidArea(new Dimension(0, getContentPane().getHeight()/8)));
        CustomLabel title = new CustomLabel(this.title);
        getContentPane().add(title);
        title.setFontSize(20);
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        getContentPane().add(Box.createRigidArea(new Dimension(0, getContentPane().getHeight()/15)));

        CustomPanel container = new CustomPanel();
        getContentPane().add(container);
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        container.setPanelSize(0.4, 0.3);
        for(int i=0; i<Default.MAX_CHAPTER; i++){
            System.out.println(i);
            int id = i;
            CustomButton chapter = new CustomButton(i+1);
            container.add(chapter);
            if(i<Default.data.get(Default.CHAPTER)){
                chapter.setBackground(Color.WHITE);
            }
            else{
                chapter.setBackground(Color.GRAY);
            }
            chapter.setFontSize(25);
            chapter.setButtonSize(0.45, 1);
            chapter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    episodeMenu(id+1);
                }
            });
            if(i<Default.MAX_CHAPTER-1) container.add(Box.createRigidArea(new Dimension(getContentPane().getHeight()/10, 0)));
        }

        getContentPane().add(Box.createRigidArea(new Dimension(0, getContentPane().getHeight()/10)));

        // create exit button
        CustomButton exit = new CustomButton("asset/picture/button/exit-button-2.png");
        getContentPane().add(exit);
        exit.setAlignmentX(JButton.CENTER_ALIGNMENT);
        exit.setOpaque(false);
        exit.setContentAreaFilled(false);
        exit.setBorderPainted(false);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenu();
            }
        });
        
        revalidate();
        repaint();
    }
    public void episodeMenu(int e){
        clearScreen();

        getContentPane().add(Box.createRigidArea(new Dimension(0, getContentPane().getHeight()/10)));

        CustomLabel title = new CustomLabel(Default.CHAPTER + " " + (Default.data.get(Default.CHAPTER)));
        getContentPane().add(title);
        title.setFontSize(20);
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        getContentPane().add(Box.createRigidArea(new Dimension(0, getContentPane().getHeight()/20)));

        CustomPanel group = new CustomPanel();
        getContentPane().add(group);
        group.setPanelSize(0.8, 0.5);
        group.setLayout(new GridLayout(2, 5));
        for(int i=0; i<Default.MAX_EPISODE; i++){
            CustomButton episode = new CustomButton(i+1);
            group.add(episode);
            episode.setFontSize(50);
            episode.setButtonSize(0.2, 1);
            if(i<Default.data.get(Default.EPISODE)){
                episode.setBackground(Color.WHITE);
            }
            else{
                episode.setBackground(Color.GRAY);
            }
            episode.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    App.wordBeforeStart();
                }
            });
        }

        getContentPane().add(Box.createRigidArea(new Dimension(0, getContentPane().getHeight()/10)));

        CustomButton exit = new CustomButton("asset/picture/button/exit-button.png");
        getContentPane().add(exit);
        exit.setOpaque(false);
        exit.setContentAreaFilled(false);
        exit.setBorderPainted(false);
        exit.setAlignmentX(JButton.CENTER_ALIGNMENT);
        exit.setButtonSize(0.6, 1);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chapterMenu();
            }
        });
    }
    public void clearScreen(){
        getContentPane().removeAll();
        getContentPane().revalidate();
        getContentPane().repaint();
    }
}