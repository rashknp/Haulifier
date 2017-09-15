package com.limitless.haulified.Haulifier.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.limitless.haulified.Haulifier.common.AppConstants;


/**
 * Created by apta on 5/30/2017.
 */

public class EditTextRegular extends android.support.v7.widget.AppCompatEditText {

    public EditTextRegular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public EditTextRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditTextRegular(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface mTypeFace = Typeface.createFromAsset(getContext().getAssets(), AppConstants.FONT_ROBOTO_REGULAR);
        setTypeface(mTypeFace);
    }
}
