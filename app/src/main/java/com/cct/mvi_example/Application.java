/*
 * Copyright (c) 2016 AXA Group Solutions.
 *
 * Licensed under the AXA Group Solutions License (the "License");
 * you may not use this file except in compliance with the License.
 * A copy of the License can be found in the LICENSE.TXT file distributed
 * together with this file.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cct.mvi_example;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
/**
 * Application base class.
 * BuddyBuild SDK is injected from their builder.
 */
public class Application extends android.app.Application {

    /**
     * The application Component.
     */
    protected static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    /**
     * Initialize injector
     */
    public void initializeInjector() {
        applicationComponent = prepareAppComponent();
    }

    @NonNull
    protected ApplicationComponent prepareAppComponent() {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
    }

    /**
     * Gets application component.
     *
     * @return the application component
     */
    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    @VisibleForTesting
    public void setComponent(ApplicationComponent applicationComponent) {
        this.applicationComponent = applicationComponent;
    }
}
