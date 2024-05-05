package com.example.appktx2sv.ui.activities.home;

import android.widget.Toast;

import com.example.appktx2sv.AppKtx;
import com.example.appktx2sv.data.dto.UserDto;
import com.example.appktx2sv.interfaces.IPDM;
import com.example.appktx2sv.net.RetrofitClient;
import com.example.appktx2sv.net.services.IHomeApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeHandler implements IPDM.Handler {
    ActivityHome view;
    IHomeApiService service = RetrofitClient.GI().getRetrofit().create(IHomeApiService.class);
    @Override
    public void setView(IPDM.View  view) {
        this.view = (ActivityHome)view;
    }
    public void getMyInfo(){
        service.getMyInfo().enqueue(new Callback<UserDto>() {
            @Override
            public void onResponse(Call<UserDto> call, Response<UserDto> response) {
                if (response.isSuccessful()){
                    AppKtx.userDto = response.body();
                }
                else{
                    Toast.makeText(view, "Gọi api thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserDto> call, Throwable t) {
                Toast.makeText(view, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
