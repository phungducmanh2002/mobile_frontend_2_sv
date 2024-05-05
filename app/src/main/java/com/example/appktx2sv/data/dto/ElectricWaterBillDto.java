package com.example.appktx2sv.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElectricWaterBillDto extends BillDtoParent {
    Integer electricPrice;
    Integer waterPrice;
    Integer electricNumber;
    Integer waterNumber;
    Integer month;
    Integer year;
}
