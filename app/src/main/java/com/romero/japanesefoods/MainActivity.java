package com.romero.japanesefoods;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerV;
    FoodAdapter adapter;
    ArrayList<Food> foods,favs;
    LinearLayoutManager lManager;
    Button principalB;
    Button favouritesB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        foods =new ArrayList<>();
        favs =new ArrayList<>();
        principalB=findViewById(R.id.foods);
        favouritesB=findViewById(R.id.favourites);

        recyclerV=findViewById(R.id.recycler);
        recyclerV.setHasFixedSize(true);

        lManager = new LinearLayoutManager(this);
        recyclerV.setLayoutManager(lManager);

        fillFoods();

        adapter =new FoodAdapter(foods,this);
        recyclerV.setAdapter(adapter);
    }

    public void allFoodsBtn(View v){
        adapter.setFalse();
        principalB.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        favouritesB.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        adapter = new FoodAdapter(foods,v.getContext());
        recyclerV.setAdapter(adapter);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    public void favouritesBtn(View v){
        adapter.setTrue();
        favouritesB.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        principalB.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        adapter = new FoodAdapter(favs,v.getContext());
        recyclerV.setAdapter(adapter);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    private void fillFoods(){

        String TAG = "Message";
        foods = new ArrayList<>();
        foods.add(new Food("Ramen",R.drawable.ramen,"Comida hecha de fideos"));
        foods.add(new Food("Katsudon",R.drawable.katsudon,"Comida hecha de empanizados"));
        foods.add(new Food("Okonomiyaki",R.drawable.okonomiyaki,"Comida hecha de fideos fritos"));
        foods.add(new Food("Onigiri",R.drawable.onigiri,"Comida hecha a base de arroz"));
    }

    public void addFavourite(Food food) {
        favs.add(food);
    }

    public void eraseFavourite(String name) {
        int counter=0;
        for (Food food : favs){
            if (food.getName()== name){
                break;
            }

            counter++;
        }

        favs.remove(counter);

        if (adapter.isOnFavS()){
            adapter = new FoodAdapter(favs,this);
            recyclerV.setAdapter(adapter);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        }
    }
}
