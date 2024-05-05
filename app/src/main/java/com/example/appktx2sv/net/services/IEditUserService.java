package com.example.appktx2sv.net.services;

import com.example.appktx2sv.data.dto.UserDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;

public interface IEditUserService {
    @PUT("/api/v1/user/edit")
    Call<UserDto> editUser(@Body UserDto userDto);
}
