/**
 * Contains background logic for stats.
 */
public class Stat {
    private String name;
    private String display;
    private int value;
    private int maxValue;
    private String type;
    private Actor owner;

    public Stat(String newName, int firstValue, Actor a){
        value = firstValue;
        maxValue = firstValue;
        type = "vessel";
        name = newName;
        owner = a;
    }

    public Stat(String newName, int val, String typeSpec, Actor a){
        if(typeSpec.equals("skill")){
            value = val;
            type = "skill";
            name = newName;
            owner = a;
        }
    }

    public Stat(String newName, int val, int maxVal, String typeSpec, Actor a){
        name = newName;
        owner = a;
        if(typeSpec.equals("range")){
            value = val;
            maxValue = maxVal;
            type = "range";
        }
        if(typeSpec.equals("xp")){
            value = 0;
            maxValue = maxVal;
            type = "xp";
        }
    }

    public String getName(){
        return name;
    }

    public void add(int x){
        if(value+x > maxValue){
            value = maxValue;
        }else
            value += x;
        owner.update();
    }

    public void subtract(int x){
        if(value-x < 0){
            value = 0;
        }else
            value -= x;
        owner.update();
    }

    public void multiply(int x){
        if(value*x > maxValue){
            value = maxValue;
        }else
            value *= x;
        owner.update();
    }

    public void permAdd(int x){
        if(value + x < 0){
            value = 0;
            maxValue = 0;
        }
        value+=x;
        maxValue+=x;
        owner.update();
    }

    public void permMultiply(int x){
        value*=x;
        maxValue*=x;
        owner.update();
    }
    public String getType(){
        return type;
    }

    public int getValue(){
        return value;
    }

    public int getMaxValue(){
        return maxValue;
    }
    
    public void setValue(int n){
        value = n;
        owner.update();
    }
    
    public void setMaxValue(int n){
        maxValue = n;
        owner.update();
    }
    
    public void permSet(int n){
        value = n;
        maxValue = n;
    }
    
    public void permSetUp(int n){
        value = n;
        maxValue = n;
        owner.update();
    }
    
    public void permMax(int n){
        maxValue = n;
    }

    public String getDisplay(){
        if(type.equals("vessel") || type.equals("xp"))
            return value + "/" + maxValue;
        if(type.equals("skill"))
            return value + "";
        if(type.equals("range"))
            return value + "-" + maxValue;
        return "unknown type";
    }

    public Boolean equals(String x){
        if(name.equals(x))
            return true;
        return false;
    }
}
