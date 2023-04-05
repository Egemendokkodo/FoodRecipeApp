package com.uygulamalarim.foodrecipeapp.Fragments;

import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.uygulamalarim.foodrecipeapp.R;

public class MyBottomSheetFragment extends BottomSheetDialogFragment {
    String list;
    BottomSheetDialog dialog;
    BottomSheetBehavior<View> bottomSheetBehavior;
    View rootView;


    TextView food_name;

    public MyBottomSheetFragment(String list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        dialog= (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.bottom_sheet_layout,container,false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        food_name=view.findViewById(R.id.food_name);
        food_name.setText(list);

        bottomSheetBehavior=BottomSheetBehavior.from((View)view.getParent());
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
        CoordinatorLayout layout=dialog.findViewById(R.id.bottomSheetLayout);
        assert layout !=null;
        layout.setMinimumHeight(Resources.getSystem().getDisplayMetrics().heightPixels);
    }
}
