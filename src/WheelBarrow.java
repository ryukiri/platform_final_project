/**
 * Created by austin on 6/10/14.
 */
public class WheelBarrow extends Armor {
    private int x;
    private int defVal;

    public WheelBarrow(Room r, String s){
        super(r, "floor", 0);
        setName(s);
    }

    public WheelBarrow(Room r, String s, int newValue, int newRange){
        super(r, "floor", newValue);
        setName(s);
    }

    public WheelBarrow(Room r, int dVal){
        super(r, "floor", dVal, dVal+3, "Wheel Barrow");
        defVal = dVal;
    }
}
