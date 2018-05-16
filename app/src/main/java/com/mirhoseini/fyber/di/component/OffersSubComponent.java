package com.mirhoseini.fyber.di.component;

import com.mirhoseini.fyber.di.module.AppOffersModule;
import com.mirhoseini.fyber.di.scope.OffersScope;
import com.mirhoseini.fyber.view.fragment.OffersFragment;

import dagger.Subcomponent;

/**
 * Created by Mohsen on 30/09/2016.
 */
@OffersScope
@Subcomponent(modules = {
        AppOffersModule.class
})
public interface OffersSubComponent {

    void inject(OffersFragment fragment);

}
