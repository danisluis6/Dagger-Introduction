package tutorial.daggerrxtutorial.data.api;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import tutorial.daggerrxtutorial.data.api.response.UserResponse;

/**
 * Created by lorence on 29/12/2017.
 *
 * @version master
 * @since 12/28/2017
 */

public interface GithubApiService {
    @GET("/users/{username}")
    Observable<UserResponse> getUser(
            @Path("username") String username
    );
}
