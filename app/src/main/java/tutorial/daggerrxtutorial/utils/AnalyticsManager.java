package tutorial.daggerrxtutorial.utils;

import android.app.Application;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by lorence on 28/12/2017.
 *
 * @version master
 * @since 12/28/2017
 */

public class AnalyticsManager {
    private Application app;

    @Inject
    public AnalyticsManager() {

    }

    public void initAnalyticsManager(Application app) {
        this.app = app;
    }

    void logScreenView(String screenName) {
        Timber.d("Logged screen name: " + screenName);
    }
}
