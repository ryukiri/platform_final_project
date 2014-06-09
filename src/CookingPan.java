/**
 * Created by austin on 6/9/14.
 */
public class CookingPan extends Weapon{
    public CookingPan(Room r, String s){
        super(r, "mainhand", 0, 0);
        setName(s);
    }

    public CookingPan(Room r, String s, int newValue, int newRange){
        super(r, "mainhand", newValue, newRange);
        setName(s);
    }

    @Override
    public void equipEffect(Actor a){
        a.getStr().setValue(a.getStr().getValue()+55);
        a.getShield().setValue(a.getShield().getValue() + 30);
        a.getShield().setMaxValue(a.getShield().getMaxValue() + 30);
        setRange(0);
    }

    @Override
    public void unequipEffect(Actor a){
        a.getStr().setValue(a.getStr().getValue()-55);
        a.getShield().setValue(a.getShield().getValue() - 30);
        a.getShield().setMaxValue(a.getShield().getMaxValue() - 30);
        setRange(0);
    }
}
