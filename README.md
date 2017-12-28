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
  - 