package asset.scripts;
<<<<<<< HEAD
import asset.nonScript.Type;
import asset.nonScript.attackable;
=======
import asset.scripts.inteface.Type;
import asset.scripts.inteface.attackable;
>>>>>>> main
public class Enemy extends Object implements attackable{
    public Enemy(int hp, String image_path, Type.ObjectType type){
        super(hp, image_path, type);
    }
    @Override
    public void attack(Object obj) {
        if(obj.getType() != Type.ObjectType.Player) return;
        obj = (Player) obj;
    }
}
