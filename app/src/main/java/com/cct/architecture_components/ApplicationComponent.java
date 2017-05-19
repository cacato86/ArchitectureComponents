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

package com.cct.architecture_components;

import android.content.Context;

import com.cct.architecture_components.data.Repository;
import com.cct.architecture_components.data.rest.utils.LoggingInterceptorFactory;
import com.cct.architecture_components.data.rest.utils.OkHttpClientFactory;
import com.cct.architecture_components.data.rest.utils.QueryInterceptorFactory;
import com.cct.architecture_components.presentation.popularmovies.PopularMoviesComponent;
import com.cct.architecture_components.presentation.popularmovies.PopularMoviesModule;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import io.reactivex.Scheduler;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Context context();

    LoggingInterceptorFactory loginInterceptor();

    QueryInterceptorFactory queryInterceptor();

    OkHttpClientFactory okHttp();

    Repository repository();

    @Named("subscriber")
    Scheduler subscriber();

    @Named("observer")
    Scheduler observer();

    PopularMoviesComponent newPopularMoviesComponent(PopularMoviesModule aplicationModule);
}
