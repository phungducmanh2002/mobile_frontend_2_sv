package com.example.appktx2sv.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomBillDto extends  BillDtoParent {
    Integer roomPrice;
}
