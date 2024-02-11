package com.visz.tv;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.visz.tv.databinding.ChannelItemBinding;

public class ChannelItem extends LinearLayout {
    public ChannelItemBinding binding;

    public ChannelItem(Context context) {
        super(context);
        init();
    }

    public ChannelItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ChannelItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        binding = ChannelItemBinding.inflate(LayoutInflater.from(getContext()), this, true);
    }
}