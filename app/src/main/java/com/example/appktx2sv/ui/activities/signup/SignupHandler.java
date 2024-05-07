package com.example.appktx2sv.ui.activities.signup;

import android.content.Intent;
import android.widget.Toast;

import com.example.appktx2sv.data.dto.UserDto;
import com.example.appktx2sv.interfaces.IPDM;
import com.example.appktx2sv.net.RetrofitClient;
import com.example.appktx2sv.net.services.ILoginService;
import com.example.appktx2sv.ui.activities.login.ActivityLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupHandler implements IPDM.Handler {
    ILoginService loginService = RetrofitClient.GI().getRetrofit().create(ILoginService.class);
    ActivitySignup view;
    @Override
    public void setView(IPDM.View view) {
        this.view = (ActivitySignup)view;
    }
    public SignupHandler(IPDM.View view){
        this.setView(view);
    }
    public void register(UserDto userDto){
        loginService.signup(userDto).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if(response.isSuccessful()){
                    Intent loginIntent = new Intent(view, ActivityLogin.class);
                    loginIntent.putExtra("email", userDto.getEmail());
                    loginIntent.putExtra("password", userDto.getPassword());
                    view.startActivity(loginIntent);
                    view.finish();
                }
                else{
                    Toast.makeText(view, "Unsuccess Sigup", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });
    }
}
