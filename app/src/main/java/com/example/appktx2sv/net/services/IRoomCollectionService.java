package com.example.appktx2sv.net.services;

import com.example.appktx2sv.data.dto.RoomCollectionDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IRoomCollectionService {
    @POST("/api/v1/room-collection/create")
    Call<RoomCollectionDto> createRoomCollection(@Body RoomCollectionDto roomCollectionDto);

    @GET("/api/v1/room-collection")
    Call<List<RoomCollectionDto>> getAllRoomCollection();

    @GET("/api/v1/room-collection/{roomCollectionId}")
    Call<RoomCollectionDto> getDetailsRoomCollection(@Path("roomCollectionId") Integer roomCollectionId);
}
