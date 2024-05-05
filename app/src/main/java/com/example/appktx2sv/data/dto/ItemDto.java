package com.example.appktx2sv.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    Integer id;
    String itemName;
    int quantity;
    int quantityLeft;
    Integer idResource;
}
