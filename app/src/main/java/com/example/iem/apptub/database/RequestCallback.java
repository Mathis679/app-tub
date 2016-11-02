package com.example.iem.apptub.database;

/**
 * Created by alexislp on 07/01/16.
 */

import java.util.List;

/**
 * A basic Callback interface to handle request result,
 * use by Domains and Databases with the Providers
 */
public interface RequestCallback {

    /**
     * <p>Result from an entities request</p>
     * <p>Always call {@link #onError(Throwable)} after onResult (if need to be called)
     * to allow core client to show data and ignore error if wanted to</p>
     *
     * @param entities a list of entities, can be empty
     */
    void onResult(List<? extends Object> entities);

    /**
     * <p>An error occur</p>
     * <p>Always call onError after {@link #onResult} (if need to be called)
     * to allow core client to show data and ignore error if wanted to</p>
     *
     * @param error
     */
    void onError(Throwable error);
}
