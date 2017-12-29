package tutorial.daggerrxtutorial.ui.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;
import tutorial.daggerrxtutorial.application.Application;
import tutorial.daggerrxtutorial.R;
import tutorial.daggerrxtutorial.ui.activity.module.SplashActivityModule;
import tutorial.daggerrxtutorial.ui.activity.presenter.SplashActivityPresenter;
import tutorial.daggerrxtutorial.utils.AnalyticsManager;

/**
 * Created by lorence on 28/12/2017.
 *
 * @version master
 * @since 12/28/2017
 *
 * @link(https://www.youtube.com/watch?v=vfjgQabgjOg&t=685s)
 */

public class SplashActivity extends BaseActivity {

    @BindView(R.id.edtUserGit)
    EditText edtUserGit;

    /**
     * In this case, we just attach Module, we can't use any method with this module. Because, we will get
     * Null Pointer Exception. It's a big problem in here.
     *
     * In this cake, we will run resolve Null Pointer Exception. Be sure, we will call DaggerSplashActivityComponent.
     * => We will definite SplashActivityComponent, SplashModuleComponent
     *
     * DONE, we will start with this SplashScreen
     *
     *
     */
    @Inject
    protected AnalyticsManager mAnalyticsManager;
    @Inject
    protected SplashActivityPresenter mPresenter;

    private Subscription textChangeSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        mAnalyticsManager.logScreenView(getClass().getName());

        edtUserGit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    textChangeSubscription = RxTextView.textChangeEvents(edtUserGit).subscribe(new Action1<TextViewTextChangeEvent>() {
                        @Override
                        public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
                            mPresenter.mUserName = textViewTextChangeEvent.text().toString();
                            Toast.makeText(SplashActivity.this, mPresenter.mUserName, Toast.LENGTH_LONG);
                        }
                    });
                }
                return false;
            }
        });
    }

    @Override
    protected void setupActivityComponent() {
        Application.get(this)
                .getAppComponent()
                .plus(new SplashActivityModule(this))
                .inject(this);

    }
}
