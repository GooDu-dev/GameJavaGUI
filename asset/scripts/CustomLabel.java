package asset.scripts;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;

public class CustomLabel extends JLabel{
    public CustomLabel(){
        
    }
    public CustomLabel(String text){
        super(text);
    }
    public CustomLabel(String text, int pos){
        super(text, pos);
    }
    public void setFontSize(int size){
        this.setFont(new Font(getFont().getName(), getFont().getStyle(), (int)(getParent().getWidth()/size)));
    }
    public void setLabelSize(double width, double height){
        setMaximumSize(new Dimension((int)(getParent().getWidth()*width), (int)(getParent().getHeight()*height)));
        setSize(new Dimension((int)(getParent().getWidth()*width), (int)(getParent().getHeight()*height)));
        setMinimumSize(new Dimension((int)(getParent().getWidth()*width), (int)(getParent().getHeight()*height)));
    }

}
