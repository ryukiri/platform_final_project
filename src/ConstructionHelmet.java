/**
 * Created by austin on 6/10/14.
 */
public class ConstructionHelmet extends Weapon {
    public ConstructionHelmet(Room r, String s){
        super(r, "Head", 0, 0);
        setName(s);
    }

    public ConstructionHelmet(Room r, String s, int newValue, int newRange){
        super(r, "Head", newValue, newRange);
        setName(s);
    }

    @Override
    public void equipEffect(Actor a){
        a.getStr().setValue(a.getStr().getValue()+25);
        a.getShield().setValue(a.getShield().getValue() + 9);
        a.getShield().setMaxValue(a.getShield().getMaxValue() + 9);
        a.getDefVal().setValue(a.getDefVal().getValue()+1);
        setRange(0);
    }

    @Override
    public void unequipEffect(Actor a){
        a.getStr().setValue(a.getStr().getValue()-25);
        a.getShield().setValue(a.getShield().getValue() - 9);
        a.getShield().setMaxValue(a.getShield().getMaxValue() - 9);
        a.getDefVal().setValue(a.getDefVal().getValue()-1);
        setRange(0);
    }
}
