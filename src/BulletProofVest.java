/**
 * Created by austin on 6/10/14.
 */
public class BulletProofVest extends Armor{
    private int defVal;

    public BulletProofVest(Room r, String s){
        super(r, "Body", 0);
        setName(s);
    }

    public BulletProofVest(Room r, String s, int newValue, int newRange){
        super(r, "Body", newValue);
        setName(s);
    }

    public BulletProofVest(Room r, int dVal){
        super(r, "chest", dVal, dVal+0, "Bullet Proof Vest");
        defVal = dVal;
    }
}