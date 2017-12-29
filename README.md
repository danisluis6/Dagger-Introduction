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

>> **Working with data**
   - We have User in this system. So we need to scope of using this User, and declare some methods involve  
   with User, API, ResponseObject to get from server.
   - Build structure folders.

>**BUILDING SCOPE USER**
   - Done
   
>**BUILDING SCOPE USER MODULE**
    - Think about provides these things for object outside.
    - Come back to CakeModule. We need to compare between CakeModule and UserModule. What is the difference or the same 
    things in here.
    
    @Module
    public class CakeModule {
    
        private MainView mView;
    
        public CakeModule(MainView view) {
            mView = view;
            Log.i("TAG", "CakeModule -> CakeModule(Constructor)");
        }
    
        @PerActivity
        @Provides
        CakeApiService provideApiService(Retrofit retrofit) {
            Log.i("TAG", "CakeModule -> CakeApiService");
            return retrofit.create(CakeApiService.class);
        }
    
        @PerActivity
        @Provides
        MainView provideView() {
            Log.i("TAG", "CakeModule -> MainView");
            return mView;
        }
    
    }
    
> Explain about CakeModule. We need provides Retrofit in here, But we use @Inject(Retrofit). We can't, Retrofit will get from
ApplicationComponent.

    @Module
    public class UserModule {
    
        private User user;
    
        public UserModule(User user) {
            this.user = user;
        }
    
        @Provides
        @UserScope
        User provideUser() {
            return user;
        }
    
        @Provides
        @UserScope
        RepositoriesManager provideRepositoriesManager(User user, GithubApiService githubApiService) {
            return new RepositoriesManager(user, githubApiService);
        }
    }
    
> Explain about UserModule. We still need to have ApiService.
   - Build User
   - Build UserModule
   
> We need to analyze how to get data from API with Dagger 2 + Rx Android
       
     - Case 1: CakePresenter
     
        - CakePresenter => getObject() => CakeApiService(CakeModule) => Retrofit(ApplicationModule)
        - Create Object and using Observable to get data from API
        - Observable<Object> => Thread is running => Observer(Object) 
        
     - Case 2: SplashScreenActivity
        - Create GitHubApisService as resource resource link URL to access Api
        - Create GitHubApiModule as resource to provides method utils to access database.
        - Create UserResponse(Object response to get data from Serve API)
        - After done provides these method to prepare get DATA from API
        
> We will analyze in here

   - Notice SplashScreenActivity, GithubApiService, UserManager
     -  
     - All object is created inside 
     
   - Using constructor for UserManager, GithubApiService(no constructor but this is a operation)
     - 
     - How to activate new module(GithubApiModule) and using this class (UserManager -> Make Constructor is initialize)
     
   - Explain: 
     - 
     - To activate new module(GithubApiService) before activating new module(UserModule)
     
   - Activate new module(GithubApiService)
     - 
     - Using ApplicationComponent (no method in here will run)
     
   - Activate new class(UserManager)
     -
     - There are two purpose: 
        - Use these methods inside class.
        - Harmonic class become @Inject
     - Activate (Module => new Object) => Initialize Object outside and provide some objects that involes with these
     method inside class
     
        
         public class UserManager {
             private GithubApiService githubApiService;
             public UserManager(GithubApiService githubApiService) {
                 this.githubApiService = githubApiService;
             }
         
             public Observable<User> getUser(String username) {
                 return githubApiService.getUser(username)
                         .map(new Func1<UserResponse, User>() {
                             @Override
                             public User call(UserResponse userResponse) {
                                 User user = new User();
                                 user.login = userResponse.login;
                                 user.id = userResponse.id;
                                 user.url = userResponse.url;
                                 user.email = userResponse.email;
                                 return user;
                             }
                         })
                         .subscribeOn(Schedulers.io())
                         .observeOn(AndroidSchedulers.mainThread());
             }
         }
         
   - GithubApiService to support for method getUser(that provided by UserManager)
   - Are able to use UserManager is null?
   - No => UserManager is call => Module => new Object() => Not null pointer
   - Actiavate(no need just harmonic argument to use or @Inject if you want(@Provides in GithubApiModule)
     
            @Provides
                @Singleton
                public UserManager provideUserManager(GithubApiService githubApiService) {
                    return new UserManager(githubApiService);
                }
            }
            
   - Done Initialize UserManager, GithubApiService, GithubApiModule, Activate GithubApiModule, UserScope, UserResponse
           
> We will get data from Api
>> https://github.com/JakeWharton/RxBinding REBINDING
