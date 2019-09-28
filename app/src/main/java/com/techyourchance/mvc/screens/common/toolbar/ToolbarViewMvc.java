package com.techyourchance.mvc.screens.common.toolbar;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.techyourchance.mvc.R;
import com.techyourchance.mvc.screens.common.views.BaseViewMvc;

/**
 * Created by Stephen Gemin on 9/27/2019
 */
public class ToolbarViewMvc extends BaseViewMvc {

    private final TextView mTxtTitle;

    public ToolbarViewMvc(LayoutInflater inflater, ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.toolbar_layout, parent, false));
        mTxtTitle = findViewById(R.id.txt_toolbar_title);
    }

    public void setTitle(String title) {
        mTxtTitle.setText(title);
    }
}
