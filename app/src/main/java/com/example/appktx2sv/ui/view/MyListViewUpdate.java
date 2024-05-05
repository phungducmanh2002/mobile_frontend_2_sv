package com.example.appktx2sv.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

public class MyListViewUpdate extends MyListView {
    public MyListViewUpdate(Context context) {
        super(context);
    }
    public MyListViewUpdate(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public MyListViewUpdate(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public MyListViewUpdate(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void reRender() {

    }
    public void resetView(){
        binding.getRoot().removeAllViews();
    }
    public void addChildren(View view){
        if(view.getParent() != null){
            ((ViewGroup)view.getParent()).removeView(view);
        }
        binding.getRoot().addView(view);
    }
    public void addChildren(View... view){
        addChildren(view);
    }
}
