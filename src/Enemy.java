import java.util.ArrayList;

/**
 * Created by austin on 6/17/14.
 */
public class Enemy extends Actor {
    private static GameBoard gameBoard;
    private Room location;
    private String name;

    public Enemy() {

    }

    public Enemy(int atk, int def, int sp, Room a) {
        location = a;
        setAtkVal(new Stat("Attack Value", atk, atk + (int) (Math.random() * 8) + 2, "range", this));
        setDefVal(new Stat("Defense Value", def, "skill", this));
        setSp(new Stat("Speed", sp, "skill", this));
        setLocation(a);
        a.getContentEnemies().add(this);
    }

    public void setName(String name) {
        setActorName(name);
    }

    @Override
    public void initStats() {
        setLevel(new Stat("Level", gameBoard.getPlayer().getLevel().getValue(), "skill", this));
        setHealth(new Stat("Health", gameBoard.getPlayer().getHealth().getValue() + (int) Math.ceil(gameBoard.getPlayer().getHealth().getValue() * 1.25), this));
    }
}
