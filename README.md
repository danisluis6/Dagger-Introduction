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
         
 - We use @Inject(Constructor)
 
    