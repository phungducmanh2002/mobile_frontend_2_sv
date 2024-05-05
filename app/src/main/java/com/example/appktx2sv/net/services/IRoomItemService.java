package com.example.appktx2sv.net.services;

import com.example.appktx2sv.data.dto.ItemDto;
import com.example.appktx2sv.data.dto.RoomDto;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface IRoomItemService {
    @GET("/api/v1/item")
    Call<List<ItemDto>> getAllItem();

    @GET("/api/v1/item/{idItem}")
    Call<ItemDto> getItemById(@Path("idItem") Integer idItem);

    @POST("/api/v1/item/create")
    Call<RoomDto> createItem(@Body ItemDto itemDto);

    @Multipart
    @POST("/api/v1/item/update-image/{idItem}")
    Call<Object> updateImage(@Part MultipartBody.Part file, @Path("idItem") Integer idItem);

    @PUT("/api/v1/item/add/{idItem}/{quantity}")
    Call<ItemDto> addQuantity(@Path("idItem") Integer idItem, @Path("quantity") Integer quantity);
}
