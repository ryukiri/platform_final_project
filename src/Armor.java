/**
 * Created by austin on 6/11/14.
 */
public class Armor extends Gear {
    private int defVal;
    private int range;
    private int tempVal;

    public Armor(Actor a, String newSlot, int dVal){
        super(a, newSlot);
        defVal = dVal;
        range = 0;
    }

    public Armor(Room a, String newSlot, int dVal){
        super(a, newSlot);
        defVal = dVal;
        range = 0;
    }

    public Armor(Room a, String newSlot, int dVal, int dRange, String name, int level){
        super(a, newSlot);
        range = dRange;
        String ss = "";
        getRanNum(dVal, level);

        if(dVal >=0 && dVal < 10){
            ss += "Titanium Plastic ";
            setName(name = ss + name);
        }else if(dVal >= 10 && dVal < 20){
            ss+="Liquid Fire ";
            setName(name = ss + name);
        }else if(dVal >= 20 && dVal < 30){
            ss += "Sharpened Tungsten ";
            setName(name = ss + name);
        }else if(dVal >= 30 && dVal < 40){
            ss += "Wooden Platinum Conductor ";
            setName(name = ss + name);
        }else if(dVal >= 40 && dVal < 50){
            ss += "Xenon Chromatized Lead ";
            setName(name = ss + name);
        }else if(dVal >= 50){
            ss+="Computerized mechanical aluminum ";
        }
    }

    private void getRanNum(int def, int lv){
        int y = (int)(Math.random()*5);
        int x = (def+(lv*y)+y)/2;

        defVal = x;
        System.out.println("y= " + y);
        System.out.println("defense value: " + defVal);
    }

    public int getDefVal(){
        return defVal;
    }

    public void setDefVal(int x){
        defVal = x;
    }

    @Override
    public void equipEffect(Actor a){
        tempVal = a.getDefVal().getValue();
        a.getDefVal().setValue(a.getDefVal().getValue() + defVal);
    }
    @Override
    public void unequipEffect(Actor a){
        a.getDefVal().setValue(a.getDefVal().getValue() - defVal);
    }

    public int process(Actor a, int x){
        //System.out.println((double) a.getStr().getValue()/20 + " strength modifier");
        return (int) (x * (1 + (double) a.getStr().getValue()/20 ));
    }
}
