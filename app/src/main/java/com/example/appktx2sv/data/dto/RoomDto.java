package com.example.appktx2sv.data.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomDto {
    @SerializedName("id")
    Integer id;
    @SerializedName("idRoomCollection")
    Integer idRoomCollection;
    @SerializedName("roomName")
    String roomName;
    @SerializedName("roomGender")
    Integer roomGender;
    @SerializedName("roomStatus")
    Integer roomStatus;
    @SerializedName("roomAcreage")
    Float roomAcreage;
    @SerializedName("slot")
    Integer slot;
    @SerializedName("slotUse")
    Integer slotUse;
    RoomCollectionDto roomCollection;
    List<ResourceDto> resources;
}
