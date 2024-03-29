package com.techyourchance.mvc.screens.common.toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.techyourchance.mvc.R;
import com.techyourchance.mvc.screens.common.views.BaseViewMvc;

/**
 * Created by Stephen Gemin on 9/27/2019
 */
public class ToolbarViewMvc extends BaseViewMvc {

    public interface NavigateUpClickListener {
        void onNavigateUpClicked();
    }

    private final TextView mTxtTitle;
    private final ImageButton mBtnBack;
    private NavigateUpClickListener mNavigateUpClickListener;

    public ToolbarViewMvc(LayoutInflater inflater, ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.toolbar_layout, parent, false));
        mTxtTitle = findViewById(R.id.txt_toolbar_title);
        mBtnBack = findViewById(R.id.btn_back);
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNavigateUpClickListener.onNavigateUpClicked();
            }
        });
    }

    public void setTitle(String title) {
        mTxtTitle.setText(title);
    }

    public void enableUpButtonAndListen(NavigateUpClickListener navigateUpClickListener) {
        mNavigateUpClickListener = navigateUpClickListener;
        mBtnBack.setVisibility(View.VISIBLE);
    }
}
