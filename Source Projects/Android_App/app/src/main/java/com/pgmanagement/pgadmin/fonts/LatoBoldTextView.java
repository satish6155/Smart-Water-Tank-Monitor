package com.pgmanagement.pgadmin.fonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class LatoBoldTextView extends TextView {
    public LatoBoldTextView(Context context) {
        super(context);
        m14392a();
    }

    public LatoBoldTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m14392a();
    }

    public LatoBoldTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m14392a();
    }

    /* renamed from: a */
    private void m14392a() {
        setTypeface(Typeface.createFromAsset(getContext().getAssets(), "Lato-Bold.ttf"));
    }
}
