package tutorial.daggerrxtutorial.data.api;

import dagger.Module;
import dagger.Provides;
import tutorial.daggerrxtutorial.data.UserScope;
import tutorial.daggerrxtutorial.data.model.User;

/**
 * Created by lorence on 29/12/2017.
 *
 * @version master
 * @since 12/28/2017
 */

@Module
public class UserModule {

    private User mUser;

    public UserModule(User user) {
        this.mUser = user;
    }

    @Provides
    @UserScope
    User provideUser() {
        return mUser;
    }
}
