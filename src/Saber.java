/**
 * Created by austin on 6/9/14.
 */
public class Saber extends Weapon {
    private int attackVal;

    public Saber(Room r, String s){
        super(r, "mainhand", 25, 10);
        setName(s);
    }

    public Saber(Room r, String s, int newValue, int newRange){
        super(r, "mainhand", newValue, newRange);
        setName(s);
    }

    public Saber(Room r, int atkVal, int lv){
        super(r, "mainhand", atkVal, atkVal+10, "Saber", lv);
        attackVal = atkVal;
    }
}
