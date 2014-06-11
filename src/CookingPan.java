/**
 * Created by austin on 6/9/14.
 */
public class CookingPan extends Armor{
    private int defVal;

    public CookingPan(Room r, String s){
        super(r, "mainhand", 0);
        setName(s);
    }

    public CookingPan(Room r, String s, int newValue, int newRange){
        super(r, "mainhand", newValue);
        setName(s);
    }

    public CookingPan(Room r, int dVal){
        super(r, "mainhand", dVal, 0, "Cooking Pan");
        defVal = dVal;
    }
}
