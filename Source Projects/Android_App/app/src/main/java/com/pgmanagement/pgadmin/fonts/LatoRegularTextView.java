package com.pgmanagement.pgadmin.fonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class LatoRegularTextView extends TextView {
    public LatoRegularTextView(Context context) {
        super(context);
        m14393a();
    }

    public LatoRegularTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m14393a();
    }

    public LatoRegularTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m14393a();
    }

    /* renamed from: a */
    private void m14393a() {
        setTypeface(Typeface.createFromAsset(getContext().getAssets(), "Lato-Regular.ttf"));
    }
}
