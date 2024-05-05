package com.example.appktx2sv.ui.activities.bill.manager;

import android.content.Intent;
import android.widget.Toast;

import com.example.appktx2sv.data.dto.BillDto;
import com.example.appktx2sv.data.dto.ElectricWaterDto;
import com.example.appktx2sv.data.dto.SemesterDto;
import com.example.appktx2sv.data.dto.SemesterRoomNameDto;
import com.example.appktx2sv.interfaces.ICallBack;
import com.example.appktx2sv.net.RetrofitClient;
import com.example.appktx2sv.net.services.IBillService;
import com.example.appktx2sv.net.services.ISemesterService;
import com.example.appktx2sv.ui.activities.bill.detail.ActivityBillDetail;
import com.example.appktx2sv.utils.MapperUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BillManagerHandler {
    ActivityBillManager view;
    ISemesterService semesterService = RetrofitClient.GI().getRetrofit().create(ISemesterService.class);
    IBillService billService = RetrofitClient.GI().getRetrofit().create(IBillService.class);
    public BillManagerHandler(ActivityBillManager view){
        this.view = view;
    }
    /*
    * Get all semester gọi khi view resume
    * */
    public void getAllSemester(){
        semesterService.getAllSemester().enqueue(new Callback<List<SemesterDto>>() {
            @Override
            public void onResponse(Call<List<SemesterDto>> call, Response<List<SemesterDto>> response) {
                if(response.isSuccessful()){
                    BillManagerHandler.this.view.semesterDtoList = response.body();
                    view.binding.semesterSelector.setData(BillManagerHandler.this.view.semesterDtoList, (obj) -> {
                        SemesterDto semesterDto = (SemesterDto)obj;
                        return  semesterDto.getSemesterName();
                    });
                }
                else{
                    Toast.makeText(view, "SERVER ERROR", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<SemesterDto>> call, Throwable t) {

            }
        });
    }
    /*
    * Get all bill gọi khi chọn bill type là all
    * */
    public void getAllMyBill(Integer idSemester, Integer idUser){
        billService.getAllMyBillBySemesterId(idSemester, idUser).enqueue(new Callback<List<BillDto>>() {
            @Override
            public void onResponse(Call<List<BillDto>> call, Response<List<BillDto>> response) {
                setBillDatas(response.body());
            }

            @Override
            public void onFailure(Call<List<BillDto>> call, Throwable t) {

            }
        });
    }
    /*
     * Get all bill gọi khi chọn bill type là room
     * */
    public void setBillDatas(List<BillDto> bills){
        this.view.allBills = bills;
        setDisplayBills(bills);
    }
    public void setDisplayBills(List<BillDto> bills){
        view.displayBills = view.filterBillByStatus(bills);
        view.displayBills = view.filterBillBySearchText(view.displayBills);
        view.binding.myListView.setItemDataList(MapperUtils.ConvertListBillDto2ItemData(view,view.displayBills));
    }
    public void getBillDetail(Integer idBill){
        billService.getBillDetail(idBill).enqueue(new Callback<BillDto>() {
            @Override
            public void onResponse(Call<BillDto> call, Response<BillDto> response) {
                if(response.isSuccessful()){
                    Intent billDetailIntent =  new Intent(view, ActivityBillDetail.class);
                    billDetailIntent.putExtra("idBill", idBill);
                    view.startActivity(billDetailIntent);
                }
                else{
                    Toast.makeText(view, "Không tìm thấy bill", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<BillDto> call, Throwable t) {

            }
        });
    }
}
