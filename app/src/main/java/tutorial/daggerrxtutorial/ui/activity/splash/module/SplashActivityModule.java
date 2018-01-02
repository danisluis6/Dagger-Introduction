package tutorial.daggerrxtutorial.ui.activity.splash.module;

import dagger.Module;
import dagger.Provides;
import tutorial.daggerrxtutorial.HeavyLibraryWrapper;
import tutorial.daggerrxtutorial.data.api.UserManager;
import tutorial.daggerrxtutorial.ui.activity.ActivityScope;
import tutorial.daggerrxtutorial.ui.activity.splash.SplashActivity;
import tutorial.daggerrxtutorial.ui.activity.splash.presenter.SplashActivityPresenter;
import tutorial.daggerrxtutorial.utils.Validator;

/**
 * Created by lorence on 28/12/2017.
 *
 * @version master
 * @since 12/28/2017
 */

@Module
public class SplashActivityModule {

    private SplashActivity splashActivity;

    public SplashActivityModule(SplashActivity splashActivity) {
        this.splashActivity = splashActivity;
    }

    @Provides
    @ActivityScope
    SplashActivity provideSplashActivity() {
        return splashActivity;
    }

//    @Provides
//    @ActivityScope
//    SplashActivityPresenter provideSplashActivityPresenter(Validator validator, HeavyLibraryWrapper heavyLibraryWrapper) {
//        return new SplashActivityPresenter(splashActivity, validator, heavyLibraryWrapper);
//    }

    @Provides
    @ActivityScope
    SplashActivityPresenter provideSplashActivityPresenter(Validator validator, UserManager userManager, HeavyLibraryWrapper heavyLibraryWrapper) {
        return new SplashActivityPresenter(splashActivity, validator, userManager, heavyLibraryWrapper);
    }

    /** Another way, if you don't want to push parameters. You can use @Inject in here
     * Push parameters in here if you want to use it parameters outside
     * For example: You want to use Validator or HeavyLibraryWrapper.
     *
     *
     * @Inject Validator mValidator;
     * @Inject HeavyLibraryWrapper  mLibraryWrapper;
     * Inside module new Object() or get it from other Module or Component (Using @Provides)
     * +> Go to GithubApiModule
     */
}
