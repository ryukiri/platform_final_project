/**
 * Created by austin on 6/9/14.
 */
public class Falcon extends Sword {
    public Falcon(Room r, String s){
        super(r, "mainhand", 25, 10);
        setName(s);
    }

    public Falcon(Room r, String s, int newValue, int newRange){
        super(r, "mainhand", newValue, newRange);
        setName(s);
    }

    @Override
    public void equipEffect(Actor a){
        a.getCritDamage().setValue(20);
        System.out.println(a.getCritDamage().getValue() + " Critical Damage");
    }

    @Override
    public void unequipEffect(Actor a){
        a.getCritDamage().setValue(1);
        System.out.println(a.getCritDamage().getValue() + " Critical Damage");
    }
}
