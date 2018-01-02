package tutorial.daggerrxtutorial;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import tutorial.daggerrxtutorial.data.api.GithubApiModule;
import tutorial.daggerrxtutorial.ui.activity.splash.component.SplashActivityComponent;
import tutorial.daggerrxtutorial.ui.activity.splash.module.SplashActivityModule;
import tutorial.daggerrxtutorial.utils.AnalyticsManager;
import tutorial.daggerrxtutorial.utils.Validator;

/**
 * Created by lorence on 28/12/2017.
 *
 * @version master
 * @since 12/28/2017
 */

@Singleton
@Component(modules = {
        ApplicationModule.class,
        GithubApiModule.class
    }
)
public interface ApplicationComponent {

    SplashActivityComponent plus(SplashActivityModule module);

    Context exposeContext();

    Validator exposeValidator();

    AnalyticsManager exposeAnalyticsManager();

    HeavyLibraryWrapper exposeHeavyLibraryWrapper();

    HeavyExternalLibrary exposeHeavyExternalLibrary();
}
