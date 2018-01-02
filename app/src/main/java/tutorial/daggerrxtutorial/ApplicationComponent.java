package tutorial.daggerrxtutorial;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import tutorial.daggerrxtutorial.data.api.GithubApiModule;
import tutorial.daggerrxtutorial.ui.activity.component.SplashActivityComponent;
import tutorial.daggerrxtutorial.ui.activity.module.SplashActivityModule;

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
}
