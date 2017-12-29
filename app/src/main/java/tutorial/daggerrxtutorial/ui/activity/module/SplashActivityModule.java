package tutorial.daggerrxtutorial.ui.activity.module;

import android.os.UserManager;

import dagger.Module;
import dagger.Provides;
import tutorial.daggerrxtutorial.HeavyLibraryWrapper;
import tutorial.daggerrxtutorial.ui.activity.ActivityScope;
import tutorial.daggerrxtutorial.ui.activity.SplashActivity;
import tutorial.daggerrxtutorial.ui.activity.presenter.SplashActivityPresenter;
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

    @Provides
    @ActivityScope
    SplashActivityPresenter provideSplashActivityPresenter(Validator validator, HeavyLibraryWrapper heavyLibraryWrapper) {
        return new SplashActivityPresenter(splashActivity, validator, heavyLibraryWrapper);
    }
}
