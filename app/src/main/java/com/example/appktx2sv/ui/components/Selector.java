package com.example.appktx2sv.ui.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appktx2sv.R;
import com.example.appktx2sv.databinding.ComponentSelectorBinding;
import com.example.appktx2sv.interfaces.IGetItemText;
import com.example.appktx2sv.ui.adapter.AdapterSelector;

import java.util.List;

public class Selector<T> extends LinearLayout {
    private ComponentSelectorBinding binding;
    private AdapterSelector<T> adapterSelector;
    boolean isShowWarning = false;
    public Selector(Context context) {
        super(context);
        init(context, null);
    }

    public Selector(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public Selector(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }
    public Selector(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }
    private void init(Context context, AttributeSet attrs) {
        binding = ComponentSelectorBinding.inflate(LayoutInflater.from(context), this, true);

        if(attrs != null){
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.selector);
            String warning = a.getString(R.styleable.selector_selectorWarning);

            if(warning != null){
                setWarning(warning);
            }
            a.recycle();
        }

        setEvent();
    }
    private void setEvent() {

    }
    public void setShowWarning(boolean isShow){
        this.isShowWarning = isShow;
        if(this.isShowWarning){
            binding.warning.setVisibility(VISIBLE);
        }
        else{
            binding.warning.setVisibility(GONE);
        }
    }
    public boolean getShowWarning(){
        return  this.isShowWarning;
    }
    public void setWarning(String warning){
        binding.warning.setText(warning);
        setShowWarning(true);
    }
    public T getSelectedItem(){
        return (T) binding.spinner.getSelectedItem();
    }
    public void setData(@NonNull List<T> dataList,@NonNull IGetItemText iGetItemText){
        adapterSelector = new AdapterSelector<>(getContext(), dataList);
        adapterSelector.setEventShowText(iGetItemText);
        binding.spinner.setAdapter(adapterSelector);
    }
    public void setOnSelected(AdapterView.OnItemSelectedListener listener){
        binding.spinner.setOnItemSelectedListener(listener);
    }
}
