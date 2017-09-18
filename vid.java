/**
 * Created by KnotScientific on 2017-07-06.
 */
public class vid {
    private static vid ourInstance = new vid();

    public static vid getInstance() {
        return ourInstance;
    }

    private vid() {
    }
}
