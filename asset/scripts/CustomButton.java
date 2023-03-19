package asset.scripts;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CustomButton extends JButton {
    public CustomButton(){}
    public CustomButton(String path){
        super(new ImageIcon(path));
    }
    public CustomButton(int number){
        super(String.valueOf(number));
    }
    public void setFontSize(int size){
        setFont(new Font(getFont().getName(), getFont().getStyle(), getParent().getWidth()/size));
    }
    public void setButtonSize(double width, double height){
        setMaximumSize(new Dimension((int)(getParent().getWidth()*width), (int)(getParent().getHeight()*height)));
        setSize(new Dimension((int)(getParent().getWidth()*width), (int)(getParent().getHeight()*height)));
        setMinimumSize(new Dimension((int)(getParent().getWidth()*width), (int)(getParent().getHeight()*height)));
    }
}
