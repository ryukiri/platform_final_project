/**
 * Created by austin on 6/9/14.
 */
public class Bow extends Weapon {
    public Bow(Room r, String s){
        super(r, "mainhand", 12, 5);
        setName(s);
    }

    public Bow(Room r, String s, int newValue, int newRange){
        super(r, "mainhand", newValue, newRange);
        setName(s);
    }

    @Override
    public void equipEffect(Actor a){
        a.getHealth().subtract(20);
        a.getHealth().setMaxValue(a.getHealth().getValue());
        a.getAtkVal().setValue(a.getAtkVal().getValue()+2);
        a.getAtkVal().setMaxValue(a.getAtkVal().getMaxValue() + process(a, getAttackVal()) + process(a, getRange()));
        a.getStr().setValue(a.getStr().getValue()-3);
        a.getFin().setValue(a.getFin().getValue()+8);
        a.getAgil().setValue(a.getAgil().getValue()+12);
        setRange(15);
    }

    @Override
    public void unequipEffect(Actor a){
        a.getHealth().add(20);
        a.getHealth().setMaxValue(a.getHealth().getValue());
        a.getAtkVal().setValue(a.getAtkVal().getValue()-2);
        a.getAtkVal().setMaxValue(a.getAtkVal().getMaxValue() + process(a, getAttackVal()) - process(a, getRange()));
        a.getStr().setValue(a.getStr().getValue()+3);
        a.getFin().setValue(a.getFin().getValue()-8);
        a.getAgil().setValue(a.getAgil().getValue()-12);
        setRange(0);
    }
}
