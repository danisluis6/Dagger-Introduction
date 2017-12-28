package tutorial.daggerrxtutorial.ui.activity.module;

import dagger.Module;
import tutorial.daggerrxtutorial.ui.activity.SplashActivity;

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
}
