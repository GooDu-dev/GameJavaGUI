package asset.scripts;

import javax.swing.*;
import asset.scripts.inteface.*;

public class Player extends JLabel{
    private int hp=0;
    private ImageIcon icon = new ImageIcon(Default.DEFAULT_IMAGE_PATH, "Default Image Path");
    private Type.ObjectType type = Type.ObjectType.Player;

    public Player(int hp, String image_path, Type.ObjectType type){
        super("Player");
        this.hp = hp;
        this.icon = new ImageIcon(image_path);
        this.type = type;
    }
    public int getHp() {
        return hp;
    }
    public ImageIcon getIcon() {
        return icon;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    public Type.ObjectType getType() {
        return type;
    }
    public void attack(Enemy obj) {
        if(obj.getType() != Type.ObjectType.Enemy) return;
        obj = (Enemy) obj;
    }
}
