package tutorial.daggerrxtutorial.ui.activity;

import android.os.Bundle;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
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
     */
    @Inject
    protected AnalyticsManager mAnalyticsManager;
    @Inject
    protected SplashActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        mAnalyticsManager.logScreenView(getClass().getName());
    }

    @Override
    protected void setupActivityComponent() {
        Application.get(this)
                .getAppComponent()
                .plus(new SplashActivityModule(this))
                .inject(this);

    }
}
