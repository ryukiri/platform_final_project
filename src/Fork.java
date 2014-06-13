/**
 * Created by austin on 6/9/14.
 */
public class Fork extends Weapon{
    private int attackVal;

    public Fork(Room r, String s){
        super(r, "mainhand", 25, 10);
        setName(s);
    }

    public Fork(Room r, String s, int newValue, int newRange){
        super(r, "mainhand", newValue, newRange);
        setName(s);
    }

    public Fork(Room r, int atkVal, int lv){
        super(r, "mainhand", atkVal, atkVal+0, "Saber", lv);
        attackVal = atkVal;
    }
}
