package tutorial.daggerrxtutorial.data;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by lorence on 29/12/2017.
 *
 * @version master
 * @since 12/28/2017
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface UserScope {
}
