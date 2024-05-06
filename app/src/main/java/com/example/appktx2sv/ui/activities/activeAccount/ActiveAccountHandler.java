package com.example.appktx2sv.ui.activities.activeAccount;

import android.content.Intent;
import android.widget.Toast;

import com.example.appktx2sv.data.dto.UserCodeDto;
import com.example.appktx2sv.interfaces.IPDM;
import com.example.appktx2sv.net.RetrofitClient;
import com.example.appktx2sv.net.services.IActiveAccountService;
import com.example.appktx2sv.ui.activities.login.ActivityLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActiveAccountHandler implements IPDM.Handler {
    IActiveAccountService activeAccountService = RetrofitClient.GI().getRetrofit().create(IActiveAccountService.class);
    ActivityActiveAccount view;
    @Override
    public void setView(IPDM.View view) {
        this.view = (ActivityActiveAccount)view;
    }
    public ActiveAccountHandler(IPDM.View view){
        setView(view);
    }
    public void resendActiveCode(String email){
        activeAccountService.reSendActivationCode(email).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });
    }
    public void activeAccount(String email, String code){
        UserCodeDto userCodeDto = new UserCodeDto();
        userCodeDto.setEmail(email);
        userCodeDto.setActiveCode(code);
        activeAccountService.activeAccount(userCodeDto).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if(response.isSuccessful()){
                    view.finish();
                }
                else {
                    Toast.makeText(view, "Mã kích hoạt không đúng", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });
    }
}
