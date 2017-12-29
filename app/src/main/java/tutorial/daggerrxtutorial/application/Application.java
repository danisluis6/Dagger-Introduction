package tutorial.daggerrxtutorial.application;

import android.content.Context;

import com.frogermcs.androiddevmetrics.AndroidDevMetrics;

import timber.log.Timber;
import tutorial.daggerrxtutorial.ApplicationModule;
import tutorial.daggerrxtutorial.BuildConfig;
import tutorial.daggerrxtutorial.ApplicationComponent;
import tutorial.daggerrxtutorial.DaggerApplicationComponent;

/**
 * Created by lorence on 28/12/2017.
 *
 * Requirements about demonstration application
 * - We create ApplicationModule.
 * +
 */

public class Application extends android.app.Application {

    private ApplicationComponent applicationComponent;

    public static Application get(Context context) {
        return (Application)context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            AndroidDevMetrics.initWith(this);
        }
        initAppComponent();
    }

    private void initAppComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getAppComponent() {
        return applicationComponent;
    }
}
