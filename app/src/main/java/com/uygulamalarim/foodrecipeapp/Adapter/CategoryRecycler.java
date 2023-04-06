package com.uygulamalarim.foodrecipeapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uygulamalarim.foodrecipeapp.Model.CategoryModel.CategoryDomain;
import com.uygulamalarim.foodrecipeapp.Model.CategoryRecyclerModel.CategoryRecyclerModel;
import com.uygulamalarim.foodrecipeapp.R;

import java.util.ArrayList;

public class CategoryRecycler extends RecyclerView.Adapter<CategoryRecycler.ViewHolder>{
    ArrayList<CategoryRecyclerModel> list;
    private OnItemClickListener listener;

    public CategoryRecycler(ArrayList<CategoryRecyclerModel> list, OnItemClickListener listener) {
        this.list=list;
        this.listener = listener;
    }

    @Override
    public CategoryRecycler.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category_recycler,parent,false);
        return new CategoryRecycler.ViewHolder(inflate, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRecycler.ViewHolder holder, int position) {
        holder.categoryName.setText(list.get(0).getResults().get(position).getTitle());
        Glide.with(holder.itemView.getContext()).load(list.get(0).getResults().get(position).getImage()).into(holder.categoryPic);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView categoryName;
        ImageView categoryPic;
        OnItemClickListener listener;

        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.foodNameCategory);
            categoryPic = itemView.findViewById(R.id.foodPicCategory);
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (listener != null) {
                int position = getAdapterPosition();
                listener.onItemClick(view, position);
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}

