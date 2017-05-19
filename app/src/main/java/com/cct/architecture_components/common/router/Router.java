package com.cct.architecture_components.common.router;

import android.app.Activity;
import android.content.Intent;

import com.cct.architecture_components.presentation.search.SearchActivity;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

/**
 * Created by Carlos Carrasco Torres on 19/05/2017.
 */

public class Router {
    private final WeakReference<Activity> activity;

    @Inject
    public Router(Activity activity) {
        this.activity = new WeakReference<>(activity);
    }

    public void routeToSearchActivity(){
        Intent intent = new Intent(activity.get(), SearchActivity.class);
        activity.get().startActivity(intent);
    }
}
