package com.limitless.haulified.Haulifier.common;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;

import com.limitless.haulified.Haulifier.Activity.BaseActivity;
import com.limitless.haulified.Haulifier.R;

import java.lang.ref.WeakReference;


/**
 * class to create the Custom Dialog
 **/
public class CustomDialog extends Dialog {
    //initializations
    boolean isCancellable = true;

    /**
     * Constructor
     *
     * @param context
     * @param view
     */

    private WeakReference<BaseActivity> BaseActivity;

    public CustomDialog(Context context, View view) {
        super(context, R.style.Dialog);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(view);
        this.BaseActivity= new WeakReference<BaseActivity>((BaseActivity) context);
    }

    /**
     * Constructor
     *
     * @param context
     * @param view
     * @param lpW
     * @param lpH
     */
    public CustomDialog(Context context, View view, int lpW, int lpH) {
        this(context, view, lpW, lpH, true);
    }

    /**
     * Constructor
     *
     * @param context
     * @param view
     * @param lpW
     * @param lpH
     * @param isCancellable
     */
    public CustomDialog(Context context, View view, int lpW, int lpH, boolean isCancellable) {
        super(context, R.style.Dialog);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(view, new LayoutParams(lpW, lpH));
        this.isCancellable = isCancellable;
    }

    public CustomDialog(Context context, View view, int lpW, int lpH, boolean isCancellable, int style) {
        super(context, R.style.Dialog);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(view, new LayoutParams(lpW, lpH));
        this.isCancellable = isCancellable;
    }

    @Override
    public void onBackPressed() {
        if (isCancellable)
            super.onBackPressed();
    }

    @Override
    public void setCanceledOnTouchOutside(boolean cancel) {
        super.setCanceledOnTouchOutside(cancel);
    }
}
