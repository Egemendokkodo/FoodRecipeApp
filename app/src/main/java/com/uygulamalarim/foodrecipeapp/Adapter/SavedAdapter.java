package com.uygulamalarim.foodrecipeapp.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uygulamalarim.foodrecipeapp.Model.FirebaseResponseModel.FirebaseModel;
import com.uygulamalarim.foodrecipeapp.R;
import com.uygulamalarim.foodrecipeapp.util.FirebaseAuth;
import com.uygulamalarim.foodrecipeapp.util.UserData;

import java.util.List;

public class SavedAdapter extends RecyclerView.Adapter<SavedAdapter.ViewHolder>  {

    List<FirebaseModel> savedList;
    private SavedOnclickRecycler savedOnclickRecycler;

    public SavedAdapter(List<FirebaseModel> savedList,SavedOnclickRecycler savedOnclickRecycler) {
        this.savedList = savedList;
        this.savedOnclickRecycler=savedOnclickRecycler;
    }


    @NonNull
    @Override
    public SavedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_saved_adapter,parent,false);
        return new SavedAdapter.ViewHolder(inflate,savedOnclickRecycler);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.foodNameSaved.setText(savedList.get(position).name);
        Glide.with(holder.itemView.getContext()).load(savedList.get(position).url).into(holder.foodPicSaved);

        holder.deleteSaved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String recipeName = savedList.get(position).name;
                new FirebaseAuth().deleteRecipe(UserData.getInstance().getUsername().toString(), recipeName);
                savedList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, savedList.size());
                Toast.makeText(holder.itemView.getContext(), "Successfully deleted.", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return savedList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        SavedOnclickRecycler savedOnclickRecycler;
        TextView foodNameSaved;
        ImageView foodPicSaved;
        ImageButton deleteSaved;

        public ViewHolder(@NonNull View itemView, SavedOnclickRecycler savedOnclickRecycler) {
            super(itemView);
            foodPicSaved=itemView.findViewById(R.id.foodPicSaved);
            foodNameSaved=itemView.findViewById(R.id.foodNameSaved);
            deleteSaved=itemView.findViewById(R.id.deleteSaved);
            itemView.setOnClickListener((View.OnClickListener) this);
            this.savedOnclickRecycler=savedOnclickRecycler;

        }




        @Override
        public void onClick(View view) {
            savedOnclickRecycler.onClickSaved(getAdapterPosition());
        }
    }
    public interface SavedOnclickRecycler{
        void onClickSaved(int pos);
    }
}
