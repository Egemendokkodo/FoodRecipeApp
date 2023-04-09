package com.uygulamalarim.foodrecipeapp.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.uygulamalarim.foodrecipeapp.Model.SearchModel.SearchModelMain;
import com.uygulamalarim.foodrecipeapp.R;

import java.util.List;

public class GridAdapter extends RecyclerView.Adapter<com.uygulamalarim.foodrecipeapp.Adapter.GridAdapter.GridAdapterViewHolder> {
    Context context;
    List<SearchModelMain> gridList;

    public GridAdapter(Context context, List<SearchModelMain> gridList) {
        this.context = context;
        this.gridList = gridList;
    }

    @NonNull
    @Override
    public GridAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GridAdapterViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.viewholder_grid_recycler,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull com.uygulamalarim.foodrecipeapp.Adapter.GridAdapter.GridAdapterViewHolder holder, int position) {
        holder.hiddenTextGrid.setText(gridList.get(position).getResults().get(position).getTitle());
        Glide.with(context).load(gridList.get(position).getResults().get(position).getImage()).into(holder.gridPic);
    }


    @Override
    public int getItemCount() {
        return gridList.size();
    }


    class GridAdapterViewHolder extends RecyclerView.ViewHolder{
        RoundedImageView gridPic;
        TextView hiddenTextGrid;
        public GridAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            gridPic=itemView.findViewById(R.id.gridPic);
            hiddenTextGrid=itemView.findViewById(R.id.hiddenTextGrid);
        }

    }
}
