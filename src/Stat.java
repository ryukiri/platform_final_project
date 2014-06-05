/**
 * Contains background logic for stats.
 */
public class Stat {
    private String name;
    private String display;
    private int value;
    private int maxValue;
    private String type;

    public Stat(String newName, int firstValue){
        value = firstValue;
        maxValue = firstValue;
        type = "vessel";
        name = newName;
    }

    public Stat(String newName, int val, String typeSpec){
        if(typeSpec.equals("skill")){
            value = val;
            type = "skill";
            name = newName;
        }
    }

    public Stat(String newName, int val, int maxVal, String typeSpec){
        name = newName;
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
    }

    public void multiply(int x){
        if(value*x > maxValue){
            value = maxValue;
        }else
            value *= x;
    }

    public void permAdd(int x){
        value+=x;
        maxValue+=x;
    }

    public void permMultiply(int x){
        value*=x;
        maxValue*=x;
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
