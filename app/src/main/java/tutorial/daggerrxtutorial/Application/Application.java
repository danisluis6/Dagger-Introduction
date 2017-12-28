package tutorial.daggerrxtutorial.Application;

import android.content.Context;
import android.support.compat.BuildConfig;

import timber.log.Timber;

/**
 * Created by lorence on 28/12/2017.
 *
 */

public class Application extends android.app.Application {

    public Application get(Context context) {
        return (Application)context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
