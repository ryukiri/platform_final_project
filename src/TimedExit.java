import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 15quachaustin on 6/18/2014.
 */
public class TimedExit {
    // Source: http://stackoverflow.com/questions/15747277/how-to-make-java-program-exit-after-a-couple-of-seconds
    Timer timer = new Timer();
    TimerTask exitApp = new TimerTask() {
        public void run() {
            System.exit(0);
        }
    };

    public TimedExit() {
        timer.schedule(exitApp, new Date(System.currentTimeMillis()+5*1000));
    }
}
