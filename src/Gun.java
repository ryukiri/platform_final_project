/**
 * Created by austin on 6/9/14.
 */
public class Gun extends Weapon {
    private int attackVal;

    public Gun(Room r, String s){
        super(r, "mainhand", 12, 5);
        setName(s);
    }

    public Gun(Room r, String s, int newValue, int newRange){
        super(r, "mainhand", newValue, newRange);
        setName(s);
    }

    public Gun(Room r, int atkVal){
        super(r, "mainhand", atkVal, atkVal+30, "Gun");
        attackVal = atkVal;
    }
}
