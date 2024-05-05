package com.example.appktx2sv.net.services;

import com.example.appktx2sv.data.dto.UserDto;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IHomeApiService {
    @GET("/api/v1/user/my-info")
    Call<UserDto> getMyInfo();
}
