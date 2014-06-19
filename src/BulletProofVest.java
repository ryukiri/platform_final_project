/**
 * Created by austin on 6/10/14.
 */
public class BulletProofVest extends Armor{
    private int defVal;

    public BulletProofVest(Room r, String s){
        super(r, "chest", 0);
        setName(s);
    }

    public BulletProofVest(Room r, String s, int newValue, int newRange){
        super(r, "chest", newValue);
        setName(s);
    }

    public BulletProofVest(Room r, int dVal, int level){
        super(r, "chest", dVal, 0, "Bullet Proof Vest", level);
        defVal = dVal;
    }
}