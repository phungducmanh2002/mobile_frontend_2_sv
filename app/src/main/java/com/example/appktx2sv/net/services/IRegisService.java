package com.example.appktx2sv.net.services;

import com.example.appktx2sv.data.dto.CreateRegisDto;
import com.example.appktx2sv.data.dto.UserDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface IRegisService {
    @POST("/api/v1/regis/create")
    Call<Object> createRegis(@Body CreateRegisDto createRegisDto);
}
