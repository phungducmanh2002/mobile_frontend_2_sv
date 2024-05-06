package com.example.appktx2sv.data.dto;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCodeDto {
    @SerializedName("userId")
    Integer userId;
    @SerializedName("activeCode")
    String activeCode;
    @SerializedName("email")
    String email;
}
