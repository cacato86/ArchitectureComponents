
package com.cct.architecture_components.common.router;

import com.cct.architecture_components.PerActivityScope;

import dagger.Component;

@PerActivityScope
@Component(modules = RouterModule.class)
public interface RouterComponent {

    Router provideRouter();
}
