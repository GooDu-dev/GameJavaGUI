package asset.scripts;
import asset.nonScript.Type;
import asset.nonScript.attackable;
public class Enemy extends Object implements Default, attackable{
    public Enemy(int hp, String image_path, Type.ObjectType type){
        super(hp, image_path, type);
    }
    @Override
    public void attack(Object player) {
        if(player.getType() != Type.ObjectType.Player) return;
        player = (Player) player;
    }
}
