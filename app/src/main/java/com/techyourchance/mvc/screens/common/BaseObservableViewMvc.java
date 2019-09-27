package com.techyourchance.mvc.screens.common;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Stephen Gemin on 9/24/2019
 */
public abstract class BaseObservableViewMvc<ListenerType> extends BaseViewMvc
        implements ObservableViewMvc<ListenerType> {

    private Set<ListenerType> mListeners = new HashSet<>();

    @Override
    public final void registerListener(ListenerType listener) {
        mListeners.add(listener);
    }

    @Override
    public final void unregisterListener(ListenerType listener) {
        mListeners.remove(listener);
    }

    protected final Set<ListenerType> getListeners() {
        return Collections.unmodifiableSet(mListeners);
    }
}
