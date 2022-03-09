package com.example.salesrep.utils

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatRadioButton

class SRPRadioButton(context : Context, attributeSet: AttributeSet) : AppCompatRadioButton (context, attributeSet) {

    init {
        applyFont()
    }
    private fun applyFont()
    {
        val typeface : Typeface = Typeface.createFromAsset(context.assets, "Poppins-Bold.ttf")
        setTypeface(typeface)
    }



}