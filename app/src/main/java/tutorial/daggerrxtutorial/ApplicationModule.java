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

    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    AnalyticsManager provideAnalyticsManager() {
        return new AnalyticsManager(application);
    }

    @Provides
    @Singleton
    Validator provideValidator() {
        return new Validator();
    }

    @Provides
    @Singleton
    HeavyExternalLibrary provideHeavyExternalLibrary() {
        HeavyExternalLibrary heavyExternalLibrary = new HeavyExternalLibrary();
        heavyExternalLibrary.init();
        return heavyExternalLibrary;
    }

    @Provides
    @Singleton
    HeavyLibraryWrapper provideLibraryWrapper() {
        return new HeavyLibraryWrapper();
    }

}
