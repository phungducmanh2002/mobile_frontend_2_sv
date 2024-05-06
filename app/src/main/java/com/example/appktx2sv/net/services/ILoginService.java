package com.example.appktx2sv.net.services;


import com.example.appktx2sv.data.dto.LoginResDto;
import com.example.appktx2sv.data.dto.UserDto;
import com.example.appktx2sv.data.model.Account;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ILoginService {
    @POST("/api/v1/user/login")
    Call<LoginResDto> login(@Body Account account);

    @POST("/api/v1/user/signup")
    Call<Object> signup(@Body UserDto userDto);
}
