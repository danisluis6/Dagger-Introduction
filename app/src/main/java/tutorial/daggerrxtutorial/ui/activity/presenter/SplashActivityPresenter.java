package tutorial.daggerrxtutorial.ui.activity.presenter;

import android.os.UserManager;

import tutorial.daggerrxtutorial.HeavyLibraryWrapper;
import tutorial.daggerrxtutorial.ui.activity.SplashActivity;
import tutorial.daggerrxtutorial.utils.Validator;

/**
 * Created by lorence on 29/12/2017.
 *
 * @version master
 * @since 12/28/2017
 */

public class SplashActivityPresenter {
    /**
     * Get from SplashActivityModule, ApplicationComponent
     * if we can't find. We will define(declare) in ApplicationComponent
     * @Inject protected SplashActivity mSplashActivity;
     */
    /**
     * We already have in ApplicationModule
     */

    /**
        @Inject protected Validator mValidator;
        @Inject protected UserManager mUserManager;
        @Inject protected HeavyLibraryWrapper mHeavyLibraryWrapper;
     */


    /**
     * We use parameters and pass argument. We no need to Inject in here.
     * @param mSplashActivity
     * @param mValidator
     * @param mUserManager
     * @param mHeavyLibraryWrapper
     *
     */
    public String mUserName;

    private SplashActivity mSplashActivity;
    private Validator mValidator;
    private HeavyLibraryWrapper mHeavyLibraryWrapper;

    public SplashActivityPresenter(SplashActivity splashActivity, Validator validator,
                                   HeavyLibraryWrapper heavyLibraryWrapper) {
        this.mSplashActivity = splashActivity;
        this.mValidator = validator;
        this.mHeavyLibraryWrapper = heavyLibraryWrapper;

        //This calls should be delivered to ExternalLibrary right after it will be initialized
        this.mHeavyLibraryWrapper.callMethod();
        this.mHeavyLibraryWrapper.callMethod();
        this.mHeavyLibraryWrapper.callMethod();
        this.mHeavyLibraryWrapper.callMethod();
    }
}
