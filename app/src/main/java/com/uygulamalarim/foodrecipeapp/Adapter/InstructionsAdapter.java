package com.uygulamalarim.foodrecipeapp.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uygulamalarim.foodrecipeapp.Model.RandomApiModel.RandomApiMain;
import com.uygulamalarim.foodrecipeapp.Model.RecipeModel.RecipeModelMain;
import com.uygulamalarim.foodrecipeapp.Model.RecipeModel.Step;
import com.uygulamalarim.foodrecipeapp.R;

import java.util.List;

public class InstructionsAdapter extends RecyclerView.Adapter<InstructionsAdapter.ViewHolder>{

    List<RecipeModelMain> instructionList;

    public InstructionsAdapter(List<RecipeModelMain> instructionList) {
        this.instructionList = instructionList;
    }

    @NonNull
    @Override
    public InstructionsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_instructions,parent,false);
        return new InstructionsAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionsAdapter.ViewHolder holder, int position) {
        Step step = instructionList.get(0).getResults().get(0).getAnalyzedInstructions().get(0).getSteps().get(position);
        holder.stepNumber.setText("Number "+String.valueOf(step.getNumber()));
        holder.stepInfo.setText(step.getStep());

    }



    @Override
    public int getItemCount() {
        return instructionList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView stepNumber,stepInfo;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            stepNumber=itemView.findViewById(R.id.numberStep);
            stepInfo=itemView.findViewById(R.id.stepInfo);

        }


    }
}
