package com.romero.japanesefoods;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sara on 25/4/2018.
 */

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private ArrayList<Food> foods;
    boolean isfav = false;
    private Context context;

    public FoodAdapter(ArrayList<Food> foods, Context context) {
        this.foods = foods;
        this.context = context;
    }

    @Override
    public FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cardview, parent, false);
        return (new FoodViewHolder(v));
    }

    @Override
    public void onBindViewHolder(final FoodViewHolder holder, final int position) {
        holder.name.setText(foods.get(position).getName());
        holder.image.setImageResource(foods.get(position).getImage());

        if (foods.get(position).isFav()) {
            holder.favBtn.setImageResource(R.drawable.likefull);
        } else {
            holder.favBtn.setImageResource(R.drawable.likeempty);
        }

        holder.favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!foods.get(position).isFav()) {
                    foods.get(position).setFav(true);
                    holder.favBtn.setImageResource(R.drawable.likefull);
                    ((MainActivity) context).addFavourite(foods.get(position));

                } else {
                    holder.favBtn.setImageResource(R.drawable.likeempty);
                    foods.get(position).setFav(false);
                    ((MainActivity) context).eraseFavourite(foods.get(position).getName());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder {
        CardView cardV;
        TextView name;
        ImageView image;
        ImageButton favBtn;

        public FoodViewHolder(View itemView) {
            super(itemView);
            cardV = itemView.findViewById(R.id.card_view);
            name = itemView.findViewById(R.id.name);
            image = itemView.findViewById(R.id.imgView);
            favBtn = itemView.findViewById(R.id.favBtn);
        }
    }

    public void setTrue() {
        isfav = true;
    }
    public void setFalse() {
        isfav = false;
    }
    public boolean isOnFavS() {
        return isfav;
    }
}
