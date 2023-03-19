package asset.scripts;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import asset.scripts.inteface.Type;
public class Enemy extends JLabel{
    
    private int hp;
    private ImageIcon icon = new ImageIcon(Default.DEFAULT_IMAGE_PATH, "Default Image Path");
    private Type.ObjectType type;

    public Enemy(int hp, String image_path, Type.ObjectType type){
        super("Enemy");
        this.hp = hp;
        this.icon = new ImageIcon(image_path);
        this.type = type;
    }
    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public Type.ObjectType getType() {
        return type;
    }
    public ImageIcon getIcon() {
        return icon;
    }
    public void attack(Player obj) {
        if(obj.getType() != Type.ObjectType.Player) return;
        obj = (Player) obj;
    }
}
