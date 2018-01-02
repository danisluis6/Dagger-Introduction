package tutorial.daggerrxtutorial.data.api;

/**
 * Created by lorence on 02/01/2018.
 *
 * @version master
 * @since 12/28/2017
 */

public class UserManager {

    private GithubApiService githubApiService;

    public UserManager(GithubApiService githubApiService) {
        this.githubApiService = githubApiService;
    }
}
