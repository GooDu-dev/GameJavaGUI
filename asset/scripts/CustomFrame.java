package asset.scripts;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
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
                App.wordBeforeStart();
            }
        });
        
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
                System.exit(0);
            }
        });
        revalidate();
        repaint();
    }

    public void clearScreen(){
        getContentPane().removeAll();
        getContentPane().revalidate();
        getContentPane().repaint();
    }
}