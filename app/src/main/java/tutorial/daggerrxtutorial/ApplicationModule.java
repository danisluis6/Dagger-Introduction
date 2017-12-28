package tutorial.daggerrxtutorial;

import android.app.Application;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import tutorial.daggerrxtutorial.utils.AnalyticsManager;
import tutorial.daggerrxtutorial.utils.Validator;

/**
 * Created by lorence on 28/12/2017.
 *
 * @version master
 * @since 12/28/2017
 */

@Module
public class ApplicationModule {

    @Inject AnalyticsManager mAnalyticsManager;
    @Inject Validator mValidator;
    @Inject HeavyExternalLibrary mHeavyExternalLibrary;
    @Inject HeavyLibraryWrapper mHeavyLibraryWrapper;

    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    AnalyticsManager provideAnalyticsManager() {
        mAnalyticsManager.initAnalyticsManager(application);
        return mAnalyticsManager;
    }

    @Provides
    @Singleton
    Validator provideValidator() {
        return mValidator;
    }

    @Provides
    @Singleton
    HeavyExternalLibrary provideHeavyExternalLibrary() {
        mHeavyExternalLibrary.init();
        return mHeavyExternalLibrary;
    }

    @Provides
    @Singleton
    HeavyLibraryWrapper provideLibraryWrapper() {
        return mHeavyLibraryWrapper;
    }

}
