package com.techyourchance.mvc.common;

/**
 * Created by Stephen Gemin on 9/24/2019
 */
public interface ObservableViewMvc<ListenerType> extends ViewMvc {

    void registerListener(ListenerType listener);

    void unregisterListener(ListenerType listener);
}
