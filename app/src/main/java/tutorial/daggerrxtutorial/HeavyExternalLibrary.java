package tutorial.daggerrxtutorial;

/**
 * Created by lorence on 28/12/2017.
 *
 * @version master
 * @since 12/28/2017
 */

public class HeavyExternalLibrary {
    private boolean initialized = false;

    public HeavyExternalLibrary() {
    }

    public void init() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        initialized = true;
    }

    public void callMethod() {
        if (!initialized) throw new RuntimeException("Call init() before you use this library");
    }
}
