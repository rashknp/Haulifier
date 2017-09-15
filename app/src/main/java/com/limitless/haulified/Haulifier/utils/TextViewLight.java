package com.limitless.haulified.Haulifier.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.limitless.haulified.Haulifier.common.AppConstants;


/**
 * Created by apta on 5/24/2017.
 */

public class TextViewLight extends android.support.v7.widget.AppCompatTextView {

    public TextViewLight(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public TextViewLight(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextViewLight(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface mTypeFace = Typeface.createFromAsset(getContext().getAssets(), AppConstants.FONT_ROBOTO_LIGHT);
        setTypeface(mTypeFace);
    }
}
