package com.example.appktx2sv.ui.activities.bill.detail;

import android.widget.Toast;

import com.example.appktx2sv.data.dto.BillDto;
import com.example.appktx2sv.net.RetrofitClient;
import com.example.appktx2sv.net.services.IBillService;
import com.example.appktx2sv.data.dto.BillDto;
import com.example.appktx2sv.net.RetrofitClient;
import com.example.appktx2sv.net.services.IBillService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BillDetailHandler {
    ActivityBillDetail view;
    IBillService billService = RetrofitClient.GI().getRetrofit().create(IBillService.class);
    public BillDetailHandler(ActivityBillDetail view){
        this.view = view;
    }
    public void getBillDetail(Integer idBill){
        billService.getBillDetail(idBill).enqueue(new Callback<BillDto>() {
            @Override
            public void onResponse(Call<BillDto> call, Response<BillDto> response) {
                if(response.isSuccessful()){
                    view.currentBillDto = response.body();
                    view.setBillUI(response.body());
                }
                else{
                    Toast.makeText(view, "SERVER ERROR", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BillDto> call, Throwable t) {
                Toast.makeText(view, t.getCause().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void payBill(Integer idBill){
        billService.payBill(idBill).enqueue(new Callback<BillDto>() {
            @Override
            public void onResponse(Call<BillDto> call, Response<BillDto> response) {
                if(response.isSuccessful()){
                    view.onResume();
                }
                else {
                    Toast.makeText(view, "SERVER ERROR", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BillDto> call, Throwable t) {

            }
        });
    }
}
