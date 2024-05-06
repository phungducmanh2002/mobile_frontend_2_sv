package com.example.appktx2sv.net.services;

import com.example.appktx2sv.data.dto.BillDto;
import com.example.appktx2sv.data.dto.ElectricWaterDto;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IBillService {
    @POST("/api/v1/bill/create-electric-water")
    Call<BillDto> createElectricWaterBill(@Body ElectricWaterDto electricWaterDto);

    @GET("/api/v1/bill/my-bill-by-semester/{idSemester}/{idUser}")
    Call<List<BillDto>> getAllMyBillBySemesterId(@Path("idSemester") Integer idSemester, @Path("idUser") Integer idUser);

    @GET("/api/v1/bill/room-bill-by-semester/{idSemester}")
    Call<List<BillDto>> getAllRoomBillBySemesterId(@Path("idSemester") Integer idSemester);

    @GET("/api/v1/bill/electric-water-bill-by-semester/{idSemester}")
    Call<List<BillDto>> getAllElectricWaterBillBySemesterId(@Path("idSemester") Integer idSemester);

    @GET("/api/v1/bill/{idBill}")
    Call<BillDto> getBillDetail(@Path("idBill") Integer idBill);

    @PUT("/api/v1/bill/pay/{idBill}")
    Call<BillDto> payBill(@Path("idBill") Integer idBill);

    @GET("/api/v1/bill/chart-room-bill")
    Call<List<BillDto>> getChartRoomBill(@Query("fromTime")Date fromTime, @Query("toTime") Date toTime);
}
