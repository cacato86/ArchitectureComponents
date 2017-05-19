package com.cct.architecture_components.presentation.search;

import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cct.architecture_components.R;

/**
 * Created by Carlos Carrasco Torres on 19/05/2017.
 */

public class SearchActivity extends LifecycleActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout_activity);
    }
}
