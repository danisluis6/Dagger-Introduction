package tutorial.daggerrxtutorial.utilities;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by lorence on 28/12/2017.
 *
 * @version master
 * @since 12/28/2017
 */

public class AnalyticsManager {

    private Application app;

    public AnalyticsManager(Application app) {
        this.app = app;
    }

    public void logScreenView(String screenName) {
        Timber.d("Logged screen name: " + screenName);
    }
}
