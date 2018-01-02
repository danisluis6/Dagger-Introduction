package tutorial.daggerrxtutorial.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;
import tutorial.daggerrxtutorial.R;
import tutorial.daggerrxtutorial.application.Application;
import tutorial.daggerrxtutorial.data.model.User;
import tutorial.daggerrxtutorial.ui.activity.module.SplashActivityModule;
import tutorial.daggerrxtutorial.ui.activity.presenter.SplashActivityPresenter;
import tutorial.daggerrxtutorial.utilities.AnalyticsManager;
import tutorial.daggerrxtutorial.utilities.Helper;

/**
 * Created by lorence on 28/12/2017.
 *
 * @version master
 * @link(https://www.youtube.com/watch?v=vfjgQabgjOg&t=685s)
 * @since 12/28/2017
 */

public class SplashActivity extends BaseActivity {

    @BindView(R.id.edtUserGit)
    EditText edtUserGit;

    /**
     * In this case, we just attach Module, we can't use any method with this module. Because, we will get
     * Null Pointer Exception. It's a big problem in here.
     * <p>
     * In this cake, we will run resolve Null Pointer Exception. Be sure, we will call DaggerSplashActivityComponent.
     * => We will definite SplashActivityComponent, SplashModuleComponent
     * <p>
     * DONE, we will start with this SplashScreen
     */
    @Inject
    protected AnalyticsManager mAnalyticsManager;
    @Inject
    protected SplashActivityPresenter mPresenter;
    @Inject
    protected Context mContext;

    private Subscription textChangeSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        mAnalyticsManager.logScreenView(getClass().getName());
        edtUserGit.setText(Helper.Constant.EMPTY_STRING);

        edtUserGit.addTextChangedListener(
                new TextWatcher() {
                    @Override public void onTextChanged(CharSequence s, int start, int before, int count) { }
                    @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                    @Override
                    public void afterTextChanged(final Editable s) {
                        final long DELAY = 500; // milliseconds
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                textChangeSubscription = RxTextView.textChangeEvents(edtUserGit).subscribe(new Action1<TextViewTextChangeEvent>() {
                                    @Override
                                    public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
                                        mPresenter.mUserName = textViewTextChangeEvent.text().toString();
                                    }
                                });
                                mPresenter.onShowRepositoriesClick();
                            }
                        }, DELAY);
                    }
                }
        );
    }

    @Override
    protected void setupActivityComponent() {
        Application.get(this)
                .getAppComponent()
                .plus(new SplashActivityModule(this))
                .inject(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        textChangeSubscription.unsubscribe();
    }

    public void showRepositoriesListForUser(User user) {
//        Application.get(this).createUserComponent(user);
//        startActivity(new Intent(this, RepositoriesListActivity.class));
    }

    public void showValidationError() {
        Helper.showToast(mContext, "User not found. Please try again");
        edtUserGit.setText(Helper.Constant.EMPTY_STRING);
        edtUserGit.requestFocus();
    }

    public void showLoading(boolean loading) {
        if (loading) {
            showDialog("Loading user...");
        } else {
            hideDialog();
        }
    }
}
