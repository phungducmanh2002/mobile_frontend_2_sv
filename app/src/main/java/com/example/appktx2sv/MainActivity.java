package com.example.appktx2sv;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appktx2sv.data.dto.BillDto;
import com.example.appktx2sv.databinding.ActivityMainBinding;
import com.example.appktx2sv.net.RetrofitClient;
import com.example.appktx2sv.net.services.IBillService;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    IBillService billService = RetrofitClient.GI().getRetrofit().create(IBillService.class);

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.sendApi.setOnClickListener(v -> {

            Date fromDate = binding.pickFromDate.getDate();
            Date toDate = binding.pickToDate.getDate();

            billService.getChartRoomBill(fromDate, toDate).enqueue(new Callback<List<BillDto>>() {
                @Override
                public void onResponse(Call<List<BillDto>> call, Response<List<BillDto>> response) {
                    Log.d("HKBNKMUHJBNK", response.body().toString());
                }

                @Override
                public void onFailure(Call<List<BillDto>> call, Throwable t) {

                }
            });

        });

    }
}