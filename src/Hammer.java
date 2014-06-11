/**
 * Created by austin on 6/10/14.
 */
public class Hammer extends Weapon {
    public Hammer(Room r, String s){
        super(r, "mainhand", 25, 10);
        setName(s);
    }

    public Hammer(Room r, String s, int newValue, int newRange){
        super(r, "mainhand", newValue, newRange);
        setName(s);
    }

    @Override
    public void equipEffect(Actor a){
        a.getAtkVal().setValue(a.getAtkVal().getValue() + 20);
        a.getAtkVal().setMaxValue(a.getAtkVal().getValue() + 20);
        a.getStr().setValue(a.getStr().getValue() + 30);
        setRange(2);
    }

    @Override
    public void unequipEffect(Actor a){
        a.getAtkVal().setValue(a.getAtkVal().getValue() - 20);
        a.getAtkVal().setMaxValue(a.getAtkVal().getValue() - 20);
        a.getStr().setValue(a.getStr().getValue() + 30);
        setRange(0);
    }
}
