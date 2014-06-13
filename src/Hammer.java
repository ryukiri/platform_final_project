/**
 * Created by austin on 6/10/14.
 */
public class Hammer extends Weapon {
    private int attackVal;
    public Hammer(Room r, String s){
        super(r, "mainhand", 25, 10);
        setName(s);
    }

    public Hammer(Room r, String s, int newValue, int newRange){
        super(r, "mainhand", newValue, newRange);
        setName(s);
    }

    public Hammer(Room r, int atkVal, int lv){
        super(r, "mainhand", atkVal, atkVal+4, "Hammer", lv);
        attackVal = atkVal;
    }
}
