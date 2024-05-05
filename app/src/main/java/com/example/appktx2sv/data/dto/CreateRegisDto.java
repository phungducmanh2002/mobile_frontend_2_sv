package com.example.appktx2sv.data.dto;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateRegisDto {
    @SerializedName("idUser")
    Integer idUser;
    @SerializedName("idSemester")
    Integer idSemester;
    @SerializedName("idRoom")
    Integer idRoom;
}
