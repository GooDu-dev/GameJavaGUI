package asset.scripts;

import java.awt.Font;
import javax.swing.JLabel;

public class CustomLabel extends JLabel{
    public CustomLabel(String text){
        super(text);
    }
    public void setFontSize(int size){
        this.setFont(new Font(getFont().getName(), getFont().getStyle(), (int)(getParent().getWidth()/size)));
    }
}
