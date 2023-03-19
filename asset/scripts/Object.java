package asset.scripts;

import javax.swing.*;

public abstract class Object {
    private int hp;
    private ImageIcon icon;
    private Type.ObjectType type = Type.ObjectType.None;
    public Object(int hp, String image_path, Type.ObjectType type){
        this.hp = hp;
        this.icon = new ImageIcon(image_path);
        this.type = type;
    }
    public int getHp(){
        return this.hp;
    }
    public void setIcon(String image_path){
        icon = new ImageIcon(image_path);
    }
    public Type.ObjectType getType(){
        return type;
    }
}
