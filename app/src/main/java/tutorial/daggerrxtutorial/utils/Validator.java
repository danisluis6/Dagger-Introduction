package tutorial.daggerrxtutorial.utils;

import android.text.TextUtils;

/**
 * Created by lorence on 28/12/2017.
 *
 * @version master
 * @since 12/28/2017
 */

public class Validator {
    public Validator() {
    }

    public boolean validUsername(String username) {
        return !TextUtils.isEmpty(username);
    }
}
