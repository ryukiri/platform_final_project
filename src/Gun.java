/**
 * Created by austin on 6/9/14.
 */
public class Gun extends Weapon {
    public Gun(Room r, String s){
        super(r, "mainhand", 12, 5);
        setName(s);
    }

    public Gun(Room r, String s, int newValue, int newRange){
        super(r, "mainhand", newValue, newRange);
        setName(s);
    }

    @Override
    public void equipEffect(Actor a){
        a.getHealth().subtract(10);
        a.getAtkVal().setValue(a.getAtkVal().getValue()+3);
        a.getAtkVal().setMaxValue(a.getAtkVal().getMaxValue() + process(a, getAttackVal()) + process(a, getRange()));
        a.getStr().setValue(a.getStr().getValue()-3);
        a.getFin().setValue(a.getFin().getValue()+5);
        setRange(20);
    }

    @Override
    public void unequipEffect(Actor a){
        a.getHealth().add(10);
        a.getAtkVal().setValue(a.getAtkVal().getValue()-3);
        a.getAtkVal().setMaxValue(a.getAtkVal().getMaxValue() + process(a, getAttackVal()) - process(a, getRange()));
        a.getStr().setValue(a.getStr().getValue()+3);
        a.getFin().setValue(a.getFin().getValue()-5);
        setRange(0);
    }
}
