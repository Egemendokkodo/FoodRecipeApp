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
import com.uygulamalarim.foodrecipeapp.R;

import java.util.List;

public class RandomAdapter extends RecyclerView.Adapter<RandomAdapter.ViewHolder>{
    List<RandomApiMain> categoryDomains;
    private OnClickListenerRecycler onClickListenerRecycler;


    public RandomAdapter(List<RandomApiMain> categoryDomains,OnClickListenerRecycler onClickListenerRecycler) {
        this.categoryDomains = categoryDomains;
        this.onClickListenerRecycler=onClickListenerRecycler;

    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_random_adapter,parent,false);
        return new ViewHolder(inflate,onClickListenerRecycler);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.randomName.setText(categoryDomains.get(position).getRecipes().get(position).getTitle());
        holder.randomByWho.setText(categoryDomains.get(position).getRecipes().get(position).getSourceName());
        Glide.with(holder.itemView.getContext()).load(categoryDomains.get(position).getRecipes().get(position).getImage()).into(holder.randomImage);



    }

    @Override
    public int getItemCount() {
        return categoryDomains.size();
    }


    public interface OnClickListenerRecycler{
        void onClickRecycler(int pos);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView randomName,randomByWho;
        ImageView randomImage;
        OnClickListenerRecycler onClickListenerRecycler;

        public ViewHolder(@NonNull View itemView,OnClickListenerRecycler onClickListenerRecycler) {
            super(itemView);
            randomName=itemView.findViewById(R.id.randomName);
            randomByWho=itemView.findViewById(R.id.randomByWho);
            randomImage=itemView.findViewById(R.id.randomImage);
            itemView.setOnClickListener((View.OnClickListener) this);
            this.onClickListenerRecycler=onClickListenerRecycler;

        }

        @Override
        public void onClick(View view) {
            onClickListenerRecycler.onClickRecycler(getAdapterPosition());
        }
    }
}
