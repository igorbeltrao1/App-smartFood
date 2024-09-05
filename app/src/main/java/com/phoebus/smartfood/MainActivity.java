package com.phoebus.smartfood;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import com.phoebus.smartfood.adapter.FoodAdapter;
import com.phoebus.smartfood.data.remote.ApiService;
import com.phoebus.smartfood.data.remote.RetrofitClient;
import com.phoebus.smartfood.databinding.ActivityMainBinding;
import com.phoebus.smartfood.data.model.Food;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FoodAdapter foodAdapter;
    private final ArrayList<Food> foodList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configura o RecyclerView
        RecyclerView recyclerViewFood = binding.RecyclerViewFood;
        recyclerViewFood.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewFood.setHasFixedSize(true);

        // Inicializa o Adapter com a lista de comidas
        foodAdapter = new FoodAdapter(foodList, this);
        recyclerViewFood.setAdapter(foodAdapter);

        // Chama a API para buscar os dados
        fetchFoodData();


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void fetchFoodData() {
        ApiService apiService = RetrofitClient.getClient("https://free-food-menus-api.onrender.com/").create(ApiService.class);
        Call<List<Food>> call = apiService.getDrinks();

        call.enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Atualiza a lista de comidas e notifica o Adapter
                    foodList.clear();
                    foodList.addAll(response.body());
                    foodAdapter.notifyDataSetChanged();
                } else {

                    Toast.makeText(MainActivity.this, "Erro ao obter dados", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {

                Log.e("MainActivity", "Erro: " + t.getMessage());
                Toast.makeText(MainActivity.this, "Falha na requisição", Toast.LENGTH_SHORT).show();
            }
        });
    }
}