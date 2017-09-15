package com.limitless.haulified.Haulifier.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.limitless.haulified.Haulifier.common.AppConstants;


/**
 * Created by apta on 5/30/2017.
 */

public class EditTextLight extends android.support.v7.widget.AppCompatEditText {

    public EditTextLight(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public EditTextLight(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditTextLight(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface mTypeFace = Typeface.createFromAsset(getContext().getAssets(), AppConstants.FONT_ROBOTO_LIGHT);
        setTypeface(mTypeFace);
    }
}
