/**
 * Created by austin on 6/9/14.
 */
public class Saber extends Sword {
    public Saber(Room r, String s){
        super(r, "mainhand", 25, 10);
        setName(s);
    }

    public Saber(Room r, String s, int newValue, int newRange){
        super(r, "mainhand", newValue, newRange);
        setName(s);
    }

    @Override
    public void equipEffect(Actor a){
        a.getHealth().setMaxValue(a.getHealth().getValue()+40);
        a.getHealth().add(40);
        a.getStr().add(10);
    }

    @Override
    public void unequipEffect(Actor a){
        a.getHealth().setMaxValue(a.getHealth().getValue()-40);
        a.getHealth().subtract(40);
        a.getStr().subtract(10);
    }
}
