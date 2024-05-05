package com.example.appktx2sv.data.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomCollectionDto {
    @SerializedName("id")
    Integer id;
    @SerializedName("roomCollectionName")
    String roomCollectionName;
    List<RoomDto> rooms;
}
