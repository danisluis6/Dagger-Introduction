package tutorial.daggerrxtutorial;

import javax.inject.Singleton;

import dagger.Component;
import tutorial.daggerrxtutorial.ApplicationModule;

/**
 * Created by lorence on 28/12/2017.
 *
 * @version master
 * @since 12/28/2017
 */

@Singleton
@Component(modules = {
        ApplicationModule.class
    }
)
public interface ApplicationComponent {

}
