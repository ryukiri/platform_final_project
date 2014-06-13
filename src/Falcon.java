/**
 * Created by austin on 6/9/14.
 */
public class Falcon extends Weapon {
    private int attackVal;

    public Falcon(Room r, String s){
        super(r, "mainhand", 25, 10);
        setName(s);
    }

    public Falcon(Room r, String s, int newValue, int newRange){
        super(r, "mainhand", newValue, newRange);
        setName(s);
    }

    public Falcon(Room r, int atkVal, int lv){
        super(r, "mainhand", atkVal, atkVal+9, "Falcon", lv);
        attackVal = atkVal;
    }
}
