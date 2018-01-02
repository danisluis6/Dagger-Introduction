# Let do it as soon as possible.
> **Explain about Using UserManager**
 
     @Component(modules = {
             ApplicationModule.class,
             GithubApiModule.class
         }
     )
 
- We have GithubApiModule.class is registered we use GithubApiService(Interface) and UserManager.
Why we need to use UserManager. In UserManager, we create some methods to use or get object. Instead of using
- @Inject GithubApiModule.class
- We can use UserManager as argument to get it from SplashActivityModule. In SplashActivityModule we need to Initialize
Constructor to call UserManager or Using UserManager(@Inject) from GithubApiModule.
- GithubApiModule => UserManager(@Provides) => SplashActivityModule

        public class UserManager {
            private GithubApiService githubApiService;
            public UserManager(GithubApiService githubApiService) {
                this.githubApiService = githubApiService;
            }
        }
        
        @Module
        public class GithubApiModule {
            @Provides
            @Singleton
            public UserManager provideUserManager(GithubApiService githubApiService) {
                return new UserManager(githubApiService);
            }
        }
        
        @Module
        public class SplashActivityModule {
            @Provides
            @ActivityScope
            SplashActivityPresenter provideSplashActivityPresenter(Validator validator, UserManager userManager, HeavyLibraryWrapper heavyLibraryWrapper) {
                return new SplashActivityPresenter(splashActivity, validator, userManager, heavyLibraryWrapper);
            }
        }
        
- When we use UserManager(). We separate two parts:
    - Find the way to initialize Constructor before implementing(Above)
    - Definition method to call
        
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
                
> **Explain about Using SplashActivityPresenter**

  - Create Constructor(Instead of using @Inject, we can use argument in Constructor)
    - List all of @Inject that you want to get from Activity
    
            public class SplashActivityPresenter {
                public String username;
                private SplashActivity splashActivity;
                private Validator validator;
                private UserManager userManager;
                private HeavyLibraryWrapper heavyLibraryWrapper;
            
                public SplashActivityPresenter(SplashActivity splashActivity, Validator validator,
                                               UserManager userManager, HeavyLibraryWrapper heavyLibraryWrapper) {
                    this.splashActivity = splashActivity;
                    this.validator = validator;
                    this.userManager = userManager;
                    this.heavyLibraryWrapper = heavyLibraryWrapper;
            
                    //This calls should be delivered to ExternalLibrary right after it will be initialized
                    this.heavyLibraryWrapper.callMethod();
                    this.heavyLibraryWrapper.callMethod();
                    this.heavyLibraryWrapper.callMethod();
                    this.heavyLibraryWrapper.callMethod();
                }
            }
   
            @Module
            public class SplashActivityModule {
                @Provides
                @ActivityScope
                SplashActivityPresenter provideSplashActivityPresenter(Validator validator, UserManager userManager, HeavyLibraryWrapper heavyLibraryWrapper) {
                    return new SplashActivityPresenter(splashActivity, validator, userManager, heavyLibraryWrapper);
                }
            }  
            
  - After we will definite some methods in SplashActivityPresenter to execute your business.
            