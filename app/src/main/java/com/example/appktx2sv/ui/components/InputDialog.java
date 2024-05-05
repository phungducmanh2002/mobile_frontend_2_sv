package com.example.appktx2sv.ui.components;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.example.appktx2sv.interfaces.IDialogComponent;

public class InputDialog extends Input implements IDialogComponent {

    public InputDialog(Context context) {
        super(context);
    }

    public InputDialog(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public InputDialog(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public InputDialog(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onChange(Object... objects) {
    }

    @Override
    public void onClick(Object... objects) {

    }

    @Override
    public boolean isValidate(Object... objects) {
        if(getText().equals("")){
            binding.input.requestFocus();
            this.setWarning("Invalid");
            return false;
        }
        else{
            this.setShowWarning(false);
        }
        return true;
    }
}