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

package com.cct.mvi_example.presentation.popularmovies;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;

import com.cct.mvi_example.ViewModuleScope;
import com.cct.mvi_example.bussines.usecases.GetPopularMoviesUseCase;
import com.cct.mvi_example.bussines.viewmodel.FactoryViewModel;
import com.cct.mvi_example.data.Repository;
import com.cct.mvi_example.data.RepositoryImpl;
import com.cct.mvi_example.data.rest.ApiClient;
import com.cct.mvi_example.data.rest.utils.LoggingInterceptorFactory;
import com.cct.mvi_example.data.rest.utils.OkHttpClientFactory;
import com.cct.mvi_example.data.rest.utils.QueryInterceptorFactory;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class PopularMoviesModule {

    @Provides
    @ViewModuleScope
    PopularMoviesViewModel providePopularMoviesModule(GetPopularMoviesUseCase getPopularMoviesUseCase) {
        return new PopularMoviesViewModel(getPopularMoviesUseCase);
    }

    @Provides
    @ViewModuleScope
    ViewModelProvider.Factory provideViewModelFactory(PopularMoviesViewModel popularMoviesViewModel) {
        return new FactoryViewModel(popularMoviesViewModel);
    }

}
