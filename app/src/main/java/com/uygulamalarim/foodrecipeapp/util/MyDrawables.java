package com.uygulamalarim.foodrecipeapp.util;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.uygulamalarim.foodrecipeapp.R;

public class MyDrawables {
    private Context context;

    public MyDrawables(Context context) {
        this.context = context;
    }

    public Drawable getCheapTrueDrawable() {
        Drawable cheap_true_drawable=ContextCompat.getDrawable(context, R.drawable.cheap_true);
        cheap_true_drawable.setBounds(0, 0, cheap_true_drawable.getIntrinsicWidth(), cheap_true_drawable.getIntrinsicHeight());
        return cheap_true_drawable;
    }

    public Drawable getGlutenFreeTrueDrawable() {
        Drawable gluten_free_drawable=ContextCompat.getDrawable(context, R.drawable.gluten_free_true);
        gluten_free_drawable.setBounds(0, 0, gluten_free_drawable.getIntrinsicWidth(), gluten_free_drawable.getIntrinsicHeight());
        return gluten_free_drawable;
    }

    public Drawable getVegetarianTrueDrawable() {
        Drawable vegetarian_drawable=ContextCompat.getDrawable(context, R.drawable.vegetarian_true);
        vegetarian_drawable.setBounds(0, 0, vegetarian_drawable.getIntrinsicWidth(), vegetarian_drawable.getIntrinsicHeight());
        return vegetarian_drawable;
    }

    public Drawable getVeganTrueDrawable() {
        Drawable vegan_drawable=ContextCompat.getDrawable(context, R.drawable.vegan_true);
        vegan_drawable.setBounds(0, 0, vegan_drawable.getIntrinsicWidth(), vegan_drawable.getIntrinsicHeight());
        return vegan_drawable;
    }
}


