package com.uygulamalarim.foodrecipeapp.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uygulamalarim.foodrecipeapp.Model.RandomApiModel.RandomApiMain;
import com.uygulamalarim.foodrecipeapp.Model.SearchModel.SearchModelMain;
import com.uygulamalarim.foodrecipeapp.R;

import java.util.List;

public class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchRecyclerAdapter.ViewHolder>{
    List<SearchModelMain> searchedList;
    private onClickListenerRecyclerSearch onClickListenerRecyclerSearch;

    public SearchRecyclerAdapter(List<SearchModelMain> searchedList,onClickListenerRecyclerSearch onClickListenerRecyclerSearch) {
        this.searchedList=searchedList;
        this.onClickListenerRecyclerSearch=onClickListenerRecyclerSearch;
    }


    @NonNull
    @Override
    public SearchRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_search_recycler,parent,false);
        return new SearchRecyclerAdapter.ViewHolder(inflate,onClickListenerRecyclerSearch);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchRecyclerAdapter.ViewHolder holder, int position) {
        try{
            holder.searchName.setText(searchedList.get(position).getResults().get(position).getTitle());
            Glide.with(holder.itemView.getContext()).load(searchedList.get(position).getResults().get(position).getImage()).into(holder.searchImage);

        }catch (IndexOutOfBoundsException e){
            Log.d("ERRORMSG","ıNDEX OUT OF BOUNDS"+e);
        }

    }

    public interface onClickListenerRecyclerSearch{
        void onClickRecyclerSearch(int pos);
    }

    @Override
    public int getItemCount() {
        return searchedList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView searchName;
        ImageView searchImage;
        onClickListenerRecyclerSearch onClickListenerRecyclerSearch;


        public ViewHolder(@NonNull View itemView,onClickListenerRecyclerSearch onClickListenerRecyclerSearch) {
            super(itemView);
            searchName=itemView.findViewById(R.id.foodNameCategory);
            searchImage=itemView.findViewById(R.id.foodPicCategory);
            itemView.setOnClickListener((View.OnClickListener) this);
            this.onClickListenerRecyclerSearch=onClickListenerRecyclerSearch;

        }

        @Override
        public void onClick(View view) {
            onClickListenerRecyclerSearch.onClickRecyclerSearch(getAdapterPosition());
        }
    }

}
