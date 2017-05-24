package com.cct.architecture_components.presentation.popularmovies;

import android.arch.lifecycle.Observer;

import com.cct.architecture_components.bussines.usecases.GetPopularMoviesUseCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.ArgumentCaptor;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

/**
 * Created by Carlos Carrasco Torres on 24/05/2017.
 */
@RunWith(JUnit4.class)
public class PopularMoviesViewModelTest {

    private GetPopularMoviesUseCase getPopularMoviesUseCase;
    private PopularMoviesViewModel popularMoviesViewModel;

    @Before
    public void setup() {
        getPopularMoviesUseCase = mock(GetPopularMoviesUseCase.class);
        popularMoviesViewModel = new PopularMoviesViewModel(getPopularMoviesUseCase);
    }

    //TODO
    @Test
    public void testCallRepo() {

    }

}