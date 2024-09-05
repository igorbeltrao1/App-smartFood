package com.phoebus.smartfood.data.remote;

import com.phoebus.smartfood.data.model.Food;

import retrofit2.Call;
import retrofit2.http.GET;
import java.util.List;

public interface ApiService {

    @GET("/our-foods")
    Call<List<Food>> getAllFoods();

    @GET("/burgers")
    Call<List<Food>> getBurgers();

    @GET("/drinks")
    Call<List<Food>> getDrinks();

    @GET("/best-foods")
    Call<List<Food>> getBestFoods();

}