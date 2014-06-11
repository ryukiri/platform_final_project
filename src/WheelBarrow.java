/**
 * Created by austin on 6/10/14.
 */
public class WheelBarrow extends Weapon {
    private int x;
    public WheelBarrow(Room r, String s){
        super(r, "floor", 0, 0);
        setName(s);
    }

    public WheelBarrow(Room r, String s, int newValue, int newRange){
        super(r, "floor", newValue, newRange);
        setName(s);
    }

    @Override
    public void equipEffect(Actor a){
        a.getStr().setValue(a.getStr().getValue()+1);
        a.getShield().setValue(a.getShield().getValue() + 20);
        a.getShield().setMaxValue(a.getShield().getMaxValue() + 20);
        a.getDefVal().setValue(a.getDefVal().getValue()+10);
        a.getSp().setValue(a.getSp().getValue()+25);
        x = a.getAtkVal().getValue();
        a.getAtkVal().setValue(a.getAtkVal().getValue()-x);
        a.getRes().setValue(a.getRes().getValue()+20);
        setRange(0);
    }

    @Override
    public void unequipEffect(Actor a){
        a.getStr().setValue(a.getStr().getValue()-1);
        a.getShield().setValue(a.getShield().getValue() - 20);
        a.getShield().setMaxValue(a.getShield().getMaxValue() - 20);
        a.getDefVal().setValue(a.getDefVal().getValue()-10);
        a.getSp().setValue(a.getSp().getValue()-25);
        a.getAtkVal().setValue(a.getAtkVal().getValue()+x);
        a.getRes().setValue(a.getRes().getValue()-20);
        setRange(0);
    }
}
