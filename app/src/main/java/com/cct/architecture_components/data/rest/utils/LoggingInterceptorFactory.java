
package com.cct.architecture_components.data.rest.utils;

import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * The type Logging interceptor factory.
 */
public class LoggingInterceptorFactory {

    /**
     * Gets logging interceptor.
     *
     * @return the logging interceptor
     */
    public Interceptor getLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }
}
