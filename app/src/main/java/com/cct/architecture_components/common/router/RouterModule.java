
package com.cct.architecture_components.common.router;

import android.app.Activity;

import com.cct.architecture_components.PerActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class RouterModule {

    private final Activity activity;

    public RouterModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivityScope
    Router provideRouter() {
        return new Router(activity);
    }


}
