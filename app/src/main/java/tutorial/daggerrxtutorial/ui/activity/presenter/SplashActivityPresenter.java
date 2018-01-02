package tutorial.daggerrxtutorial.ui.activity.presenter;

import android.util.Log;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;

import rx.functions.Action1;
import tutorial.daggerrxtutorial.HeavyLibraryWrapper;
import tutorial.daggerrxtutorial.data.api.UserManager;
import tutorial.daggerrxtutorial.data.model.User;
import tutorial.daggerrxtutorial.ui.activity.SplashActivity;
import tutorial.daggerrxtutorial.utilities.SimpleObserver;
import tutorial.daggerrxtutorial.utilities.Validator;

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
    private UserManager mUserManager;
    private HeavyLibraryWrapper mHeavyLibraryWrapper;

    public SplashActivityPresenter(SplashActivity splashActivity, Validator validator,
                                   UserManager userManager, HeavyLibraryWrapper heavyLibraryWrapper) {
        mSplashActivity = splashActivity;
        mValidator = validator;
        mUserManager = userManager;
        mHeavyLibraryWrapper = heavyLibraryWrapper;

        //This calls should be delivered to ExternalLibrary right after it will be initialized
        mHeavyLibraryWrapper.callMethod();
        mHeavyLibraryWrapper.callMethod();
        mHeavyLibraryWrapper.callMethod();
        mHeavyLibraryWrapper.callMethod();
    }

    public void onShowRepositoriesClick() {
        if (mValidator.validUsername(mUserName)) {
            mSplashActivity.showLoading(true);
            mUserManager.getUser(mUserName).subscribe(new SimpleObserver<User>() {
                @Override
                public void onNext(User user) {
                    mSplashActivity.showLoading(false);
                    mSplashActivity.showRepositoriesListForUser(user);
                }

                @Override
                public void onError(Throwable e) {
                    mSplashActivity.showLoading(false);
                    mSplashActivity.showValidationError();
                }
            });
        } else {
            mSplashActivity.showValidationError();
        }
    }
}
