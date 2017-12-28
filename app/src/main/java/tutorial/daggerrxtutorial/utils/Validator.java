package tutorial.daggerrxtutorial.utils;

import android.text.TextUtils;

import javax.inject.Inject;

/**
 * Created by lorence on 28/12/2017.
 *
 * @version master
 * @since 12/28/2017
 */

public class Validator {

    @Inject
    public Validator() {
    }

    public boolean validUsername(String username) {
        return !TextUtils.isEmpty(username);
    }
}
