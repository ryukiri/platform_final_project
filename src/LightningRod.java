/**
 * Created by austin on 6/11/14.
 */
public class LightningRod extends Armor {
    private int defVal;

    public LightningRod(Room r, String s){
        super(r, "backpack", 0);
        setName(s);
    }

    public LightningRod(Room r, String s, int newValue, int newRange){
        super(r, "backpack", newValue);
        setName(s);
    }

    public LightningRod(Room r, int dVal, int level){
        super(r, "backpack", dVal, 0, "Lightning Rod", level);
        defVal = dVal;
    }
}
