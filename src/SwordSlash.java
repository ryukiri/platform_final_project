/**
 * Created by austin on 6/17/14.
 */
public class SwordSlash extends Basic{
    private String name = "SwordSlash";

    public SwordSlash(Actor a){
        super(a);
        setName("SwordSlash");
    }

    public void critMsg(Actor target){
        getMainField().append("Critical hit on " + target.getName() + "\n");
    }

    public void hitMsg(Actor target, int damage){
        getMainField().append(getUser().getName() + " smacks " + target.getName() + " for " + damage + " points of damage." + "\n");
    }

    public void atkMsg(Actor target){
        getMainField().append(getUser().getName() + " attempts to smack " + target.getName() + "!" + "\n");
    }
}
