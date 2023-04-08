package com.uygulamalarim.foodrecipeapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uygulamalarim.foodrecipeapp.Model.RandomApiModel.RandomApiMain;
import com.uygulamalarim.foodrecipeapp.Model.SearchModel.SearchModelMain;
import com.uygulamalarim.foodrecipeapp.R;

import java.util.List;

public class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchRecyclerAdapter.ViewHolder>{
    List<SearchModelMain> searchedList;

    public SearchRecyclerAdapter(List<SearchModelMain> searchedList) {
        this.searchedList=searchedList;
    }


    @NonNull
    @Override
    public SearchRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category_recycler,parent,false);
        return new SearchRecyclerAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchRecyclerAdapter.ViewHolder holder, int position) {
        holder.searchName.setText(searchedList.get(position).getResults().get(position).getTitle());
        Glide.with(holder.itemView.getContext()).load(searchedList.get(position).getResults().get(position).getImage()).into(holder.searchImage);

    }

    @Override
    public int getItemCount() {
        return searchedList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView searchName;
        ImageView searchImage;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            searchName=itemView.findViewById(R.id.foodNameCategory);
            searchImage=itemView.findViewById(R.id.foodPicCategory);


        }
    }
}
