package asset.scripts;

import javax.swing.*;

public class Player extends Object implements attackable{

    private int hp=0;
    private ImageIcon icon = new ImageIcon(Default.DEFAULT_IMAGE_PATH, "Default Image Path");
    private Type.ObjectType type = Type.ObjectType.Player;

    public Player(int hp, String image_path, Type.ObjectType type){
        super(hp, image_path, type);
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
    @Override
    public void attack(Object obj) {
        if(obj.getType() != Type.ObjectType.Enemy) return;
        obj = (Enemy) obj;
    }
}
