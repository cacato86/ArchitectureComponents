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

package com.cct.architecture_components.presentation.popularmovies;

import android.arch.lifecycle.ViewModelProvider;

import com.cct.architecture_components.ViewModuleScope;
import com.cct.architecture_components.bussines.usecases.GetPopularMoviesUseCase;
import com.cct.architecture_components.bussines.viewmodel.FactoryViewModel;

import dagger.Module;
import dagger.Provides;

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
