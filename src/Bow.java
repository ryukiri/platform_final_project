/**
 * Created by austin on 6/9/14.
 */
public class Bow extends Weapon {
    private int attackVal;

    public Bow(Room r, String s){
        super(r, "mainhand", 12, 5);
        setName(s);
    }

    public Bow(Room r, String s, int newValue, int newRange){
        super(r, "mainhand", newValue, newRange);
        setName(s);
    }

    public Bow(Room r, int atkVal, int lv){
        super(r, "mainhand", atkVal, atkVal+21, "Saber", lv);
        attackVal = atkVal;
    }
}
