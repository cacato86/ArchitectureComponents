
package com.cct.architecture_components;

import android.support.annotation.NonNull;

public class Application extends android.app.Application {

    protected static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    public void initializeInjector() {
        applicationComponent = prepareAppComponent();
    }

    @NonNull
    protected ApplicationComponent prepareAppComponent() {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
