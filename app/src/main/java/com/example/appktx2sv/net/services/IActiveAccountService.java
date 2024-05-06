package com.example.appktx2sv.net.services;

import com.example.appktx2sv.data.dto.UserCodeDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IActiveAccountService {
    @GET("/api/v1/user/send-activation-code2/{email}")
    Call<Object> reSendActivationCode(@Path("email") String email);

    @PUT("/api/v1/user/active2")
    Call<Object> activeAccount(@Body UserCodeDto userCodeDto);
}
