package asset.scripts;

import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class CustomPanel extends JPanel{
    public CustomPanel(){
        
    }
    public CustomPanel(LayoutManager layout){
        setLayout(layout);
    }
    public void setPanelSize(double width, double height){
        setMaximumSize(new Dimension((int)(getParent().getWidth()*width), (int)(getParent().getHeight()*height)));
        setSize(new Dimension((int)(getParent().getWidth()*width), (int)(getParent().getHeight()*height)));
        setMinimumSize(new Dimension((int)(getParent().getWidth()*width), (int)(getParent().getHeight()*height)));
    }
}
