/**
 * Created by austin on 6/10/14.
 */
public class ConstructionHelmet extends Armor {
    private int defVal;

    /*
    public ConstructionHelmet(Room r, String s){
        super(r, "Head", 0, 0);
        setName(s);
    }

    public ConstructionHelmet(Room r, String s, int newValue, int newRange){
        super(r, "Head", newValue, newRange);
        setName(s);
    }*/

    public ConstructionHelmet(Room r, int dVal){
        super(r, "floor", dVal, 0, "Construction Helmet");
        defVal = dVal;
    }
}
