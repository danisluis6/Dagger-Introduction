# Let do it as soon as possible.
> **Level 1: Initialize Application, ApplicationModule, ApplicationComponent**
 - @Component ApplicationComponent
 - @Provide ApplicationModule
 
> **Level 2: We need to notice.**
 - We always create new object Validator and HeavyExternalLibrary
 
 
    @Provides 
         @Singleton
         Validator provideValidator() {
             return new Validator();
         }
         @Provides
         @Singleton
         HeavyExternalLibrary provideHeavyExternalLibrary() {
             HeavyExternalLibrary heavyExternalLibrary = new HeavyExternalLibrary();
             heavyExternalLibrary.init();
             return heavyExternalLibrary;
         }
       
     >> template_1    
         
 - We use @Inject(Constructor)
 
        @Module
        public class ApplicationModule {
            @Inject AnalyticsManager mAnalyticsManager;
            @Inject Validator mValidator;
            @Inject HeavyExternalLibrary mHeavyExternalLibrary;
            @Inject HeavyLibraryWrapper mHeavyLibraryWrapper;
        
            private Application application;
        
            public ApplicationModule(Application application) {
                this.application = application;
            }
        
            @Provides
            @Singleton
            Application provideApplication() {
                return application;
            }
        
            @Provides
            @Singleton
            AnalyticsManager provideAnalyticsManager() {
                mAnalyticsManager.initAnalyticsManager(application);
                return mAnalyticsManager;
            }
        
            @Provides
            @Singleton
            Validator provideValidator() {
                return mValidator;
            }
        
            @Provides
            @Singleton
            HeavyExternalLibrary provideHeavyExternalLibrary() {
                mHeavyExternalLibrary.init();
                return mHeavyExternalLibrary;
            }
        
            @Provides
            @Singleton
            HeavyLibraryWrapper provideLibraryWrapper() {
                return mHeavyLibraryWrapper;
            }
        }
        >> template_2
         
> **Level 3: We need to go or approach Build Activity and apply Rx(Dagger already applied).**
  - Create folder ui and contain MainActivity.java
  - Create BaseActivity.java
  
> **Level 4: With SplashScreen.java**
  - Create SplashActivityModule.java
  - Create SplashActivityComponent.java
  - Dagger 
  
>> We have two ways to express DaggerComponent in child activities
  - Code 1:
  
        DaggerCakeComponent.builder()
            .applicationComponent(getApplicationComponent())
                .cakeModule(new CakeModule(this))
                      .build().inject(this); // inject(this) => inject CakeModule into MainActivity

  - Code 2:
    
        Application.get(this)
            .getAppComponent()
                .plus(new SplashActivityModule(this))
                        .inject(this);
                        
>> What is the difference between Code 1 and Code 2?
   - Firstly, we will explore ApplicationComponent
   
           @Singleton
           @Component(modules = ApplicationModule.class)
           public interface ApplicationComponent {
                Retrofit exposeRetrofit();
                Context exposeContext();
           }    
    
           @Singleton
           @Component(modules = {
                   ApplicationModule.class
               }
           )
           public interface ApplicationComponent {
           
               SplashActivityComponent plus(SplashActivityModule module);
           }     
           
   - Secondly, we will explore CakeComponent and SplashActivityComponent
   
            @PerActivity
            @Component(modules = CakeModule.class, dependencies = ApplicationComponent.class)
            public interface CakeComponent {
            
                void inject(MainActivity activity);
            
            }
            
            @ActivityScope
            @Subcomponent(
                    modules = SplashActivityModule.class
            )
            public interface SplashActivityComponent {
                SplashActivity inject(SplashActivity splashActivity);
            }
            
>> Resolve null when @Inject
   - In RxModule instead of: new Object() or Instance.   

>> WE ARE STANDING IN SPLASH SCREEN
   - In splash screen: We will @Inject(Object <- @Provides)
   
    public class MainActivity extends BaseActivity implements MainView {
    
        @Bind(R.id.rcvCakes)
        public RecyclerView rcvCakes;
    
        @Inject CakePresenter mPresenter;
        @Inject Context mContext;
    
        private CakeAdapter mCakeAdapter;
    
        @Override
        protected int getContentView() {
            return layout.activity_main;
        }
   - @Inject Context(Come from ApplicationComponent)
   
**We also move @Inject to @Presenter** 
   
> **CakeApplication**
   - @Inject protected CakeApiService mApiService;
   - @Inject protected CakeMapper mCakeMapper;
   - @Inject protected Storage mStorage;
      
   - Explain about NullPointerException  
   => DaggerCakeComponent(Run) => MainActivity(run firstly onCreate) => All @Inject (run) 
   => CakePresenter(Constructor is initialized) => Done
   
   - Explain about @Inject Constructor => @Inject all provides from Module 
    
> **GithubclientApplication**
   - @Inject inside SplashScreen or SplashScreenPresenter.
   
> **Step by step**
   - Try to @Inject(AnalyticsManager)
    
> **WE ARE STANDING IN SPLASHACTIVITYPRESENTER**

   - @Inject protected SplashActivity splashActivity;
   - We have two solutions in here:
    
* Solution 1: We will create Constructor of this class and put annotation @Inject
    @Inject
    public SplashActivityComponent() {}
    
* Solution 2: We will provide Object(SplashActivityComponent) from Module. And we just 
call it from Module.
   - When we provide, we wont' have parameter. Otherwise, we will have parameters. So we 
   will create functions and get parameters from Modules.
   - Implement solution 2
   
   