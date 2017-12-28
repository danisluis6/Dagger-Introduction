package tutorial.daggerrxtutorial.ui.activity.component;

import dagger.Subcomponent;
import tutorial.daggerrxtutorial.ui.activity.ActivityScope;
import tutorial.daggerrxtutorial.ui.activity.SplashActivity;
import tutorial.daggerrxtutorial.ui.activity.module.SplashActivityModule;

/**
 * Created by lorence on 28/12/2017.
 *
 * @version master
 * @since 12/28/2017
 */
@ActivityScope
@Subcomponent(
        modules = SplashActivityModule.class
)
public interface SplashActivityComponent {
    SplashActivity inject(SplashActivity splashActivity);
}
