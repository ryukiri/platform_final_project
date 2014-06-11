/**
 * Created by austin on 6/10/14.
 */
public class BulletProofVest extends Weapon{
    public BulletProofVest(Room r, String s){
        super(r, "Body", 0, 0);
        setName(s);
    }

    public BulletProofVest(Room r, String s, int newValue, int newRange){
        super(r, "Body", newValue, newRange);
        setName(s);
    }

    @Override
    public void equipEffect(Actor a){
        a.getHealth().setMaxValue(a.getHealth().getValue()+100);
        a.getHealth().setValue(a.getHealth().getValue()+100);
        a.getStr().setValue(a.getStr().getValue()+75);
        a.getShield().setValue(a.getShield().getValue() + 130);
        a.getShield().setMaxValue(a.getShield().getMaxValue() + 130);
        a.getDefVal().setValue(a.getDefVal().getValue()+100);
        setRange(0);
    }

    @Override
    public void unequipEffect(Actor a){
        a.getHealth().setMaxValue(a.getHealth().getValue()-100);
        a.getHealth().setValue(a.getHealth().getValue()-100);
        a.getStr().setValue(a.getStr().getValue()-75);
        a.getShield().setValue(a.getShield().getValue() - 130);
        a.getShield().setMaxValue(a.getShield().getMaxValue() - 130);
        a.getDefVal().setValue(a.getDefVal().getValue()-100);
        setRange(0);
    }
}
