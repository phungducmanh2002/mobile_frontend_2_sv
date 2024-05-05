package com.example.appktx2sv.ui.activities.login;

import android.content.Intent;
import android.widget.Toast;

import com.example.appktx2sv.AppKtx;
import com.example.appktx2sv.data.dto.LoginResDto;
import com.example.appktx2sv.data.model.Account;
import com.example.appktx2sv.interfaces.IPDM;
import com.example.appktx2sv.net.RetrofitClient;
import com.example.appktx2sv.net.services.ILoginService;
import com.example.appktx2sv.ui.activities.home.ActivityHome;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginHandler implements IPDM.Handler {
    ActivityLogin view;
    ILoginService loginService = RetrofitClient.GI().getRetrofit().create(ILoginService.class);
    @Override
    public void setView(IPDM.View view) {
        this.view = (ActivityLogin)view;
    }
    public void login(String email, String password){
        Account account  = new Account();
        account.setEmail(email);
        account.setPassword(password);
        loginService.login(account).enqueue(new Callback<LoginResDto>() {
            @Override
            public void onResponse(Call<LoginResDto> call, Response<LoginResDto> response) {
                if(response.isSuccessful()){
                    String token = response.body().getToken();
                    // Gán token vào request retrofit
                    AppKtx.OnLoginSuccess(token);
                    /*Chuyển đến trang home*/
                    Intent homeIntent = new Intent(view, ActivityHome.class);
                    view.startActivity(homeIntent);
                    view.finish();
                }
                else {
                    Toast.makeText(view, "SERVER ERROR", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResDto> call, Throwable t) {
                Toast.makeText(view, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
