package tutorial.daggerrxtutorial.data.api;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import tutorial.daggerrxtutorial.data.api.response.UserResponse;
import tutorial.daggerrxtutorial.data.model.User;

/**
 * Created by lorence on 29/12/2017.
 *
 * @version master
 * @since 12/28/2017
 */

public class UserManager {

    /**
     * TOPIC => A
     *
     * In here, we need to get @GitHubApiService instead of using constructor. But, nature of this
     * Instead of put @GitHubApiService in here.
     * We will go to GithubApiModule. We had GithubApiService that provides in this class or inject from
     * other component or other modules.
     * So my task in here just call this module(object, class or interface).
     * => We use it as argument and return object that we need to expect.
     *
     * The big problem in here is how to activate module.(GithubApiModule)
     */

    private GithubApiService githubApiService;

    public UserManager(GithubApiService githubApiService) {
        this.githubApiService = githubApiService;
    }

    public Observable<User> getUser(String username) {
        return githubApiService.getUser(username)
                .map(new Func1<UserResponse, User>() {
                    @Override
                    public User call(UserResponse userResponse) {
                        User user = new User();
                        user.login = userResponse.login;
                        user.id = userResponse.id;
                        user.url = userResponse.url;
                        user.email = userResponse.email;
                        return user;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
