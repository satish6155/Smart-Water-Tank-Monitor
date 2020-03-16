package com.pgmanagement.pgadmin.fonts;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.TypefaceSpan;

/* renamed from: com.nucleus.finnone.mobile.mfi.fonts.a */
public class C2923a extends TypefaceSpan {
    /* renamed from: a */
    private final Typeface f12041a;

    public C2923a(String str, Typeface typeface) {
        super(str);
        this.f12041a = typeface;
    }

    /* renamed from: a */
    private static void m14394a(Paint paint, Typeface typeface) {
        Typeface typeface2 = paint.getTypeface();
        int style = (typeface2 == null ? 0 : typeface2.getStyle()) & (typeface.getStyle() ^ -1);
        if ((style & 1) != 0) {
            paint.setFakeBoldText(true);
        }
        if ((style & 2) != 0) {
            paint.setTextSkewX(-0.25f);
        }
        paint.setTypeface(typeface);
    }

    public void updateDrawState(TextPaint textPaint) {
        C2923a.m14394a(textPaint, this.f12041a);
    }

    public void updateMeasureState(TextPaint textPaint) {
        C2923a.m14394a(textPaint, this.f12041a);
    }
}
