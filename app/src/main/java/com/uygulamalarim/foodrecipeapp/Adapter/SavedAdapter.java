package com.uygulamalarim.foodrecipeapp.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uygulamalarim.foodrecipeapp.Model.FirebaseResponseModel.FirebaseModel;
import com.uygulamalarim.foodrecipeapp.R;

import java.util.List;

public class SavedAdapter extends RecyclerView.Adapter<SavedAdapter.ViewHolder> {

    List<FirebaseModel> savedList;

    public SavedAdapter(List<FirebaseModel> savedList) {
        this.savedList = savedList;
    }

    @NonNull
    @Override
    public SavedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_saved_adapter,parent,false);
        return new SavedAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.foodNameSaved.setText(savedList.get(position).name);
        Glide.with(holder.itemView.getContext()).load(savedList.get(position).url).into(holder.foodPicSaved);






    }

    @Override
    public int getItemCount() {
        return savedList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView foodNameSaved;
        ImageView foodPicSaved;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodPicSaved=itemView.findViewById(R.id.foodPicSaved);
            foodNameSaved=itemView.findViewById(R.id.foodNameSaved);

        }


    }
}
