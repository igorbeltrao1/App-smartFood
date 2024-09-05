package com.phoebus.smartfood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.phoebus.smartfood.databinding.FoodItemBinding;
import com.phoebus.smartfood.data.model.Food;

import java.time.Instant;
import java.util.ArrayList;

public class FoodAdapter  extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private final ArrayList<Food> foodList;
    private final Context context;

    public FoodAdapter(ArrayList<Food> foodList, Context context) {
        this.foodList = foodList;
        this.context = context;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        FoodItemBinding listItem;
        listItem = FoodItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new FoodViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {

        Food food = foodList.get(position);


        Glide.with(context)
                .load(food.getImg())
                .into(holder.binding.imgFood);

        holder.binding.txtFoodName.setText(food.getName());
        holder.binding.txtFoodDescription.setText(food.getDsc());
        String formattedPrice = String.format("%.2f",food.getPrice());
        holder.binding.txtPrice.setText(formattedPrice);
        //holder.binding.txtPrice.setText(foodList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public static class FoodViewHolder extends RecyclerView.ViewHolder{

        FoodItemBinding binding;

        public FoodViewHolder(FoodItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
