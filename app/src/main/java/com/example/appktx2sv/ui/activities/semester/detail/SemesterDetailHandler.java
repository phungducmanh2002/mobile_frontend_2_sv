package com.example.appktx2sv.ui.activities.semester.detail;

import android.widget.Toast;

import com.example.appktx2sv.data.dto.SemesterDto;
import com.example.appktx2sv.net.RetrofitClient;
import com.example.appktx2sv.net.services.ISemesterService;
import com.example.appktx2sv.utils.DateUtils;
import com.example.appktx2sv.utils.NumberUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SemesterDetailHandler {
    ActivitySemesterDetail view;
    ISemesterService semesterService = RetrofitClient.GI().getRetrofit().create(ISemesterService.class);
    public SemesterDetailHandler(ActivitySemesterDetail view){
        this.view = view;

    }
    public void getSemesterDetail(Integer idSemester){
        semesterService.getDetailsById(idSemester).enqueue(new Callback<SemesterDto>() {
            @Override
            public void onResponse(Call<SemesterDto> call, Response<SemesterDto> response) {
                if(response.isSuccessful()){
                    SemesterDto semesterDto = response.body();
                    view.binding.semesterName.setText(semesterDto.getSemesterName());
                    view.binding.roomPrice.setValue(NumberUtils.GetMoney(semesterDto.getRoomPrice()));
                    view.binding.electricPrice.setValue(NumberUtils.GetMoney(semesterDto.getElectricPrice()));
                    view.binding.waterPrice.setValue(NumberUtils.GetMoney(semesterDto.getWaterPrice()));
                    view.binding.startAt.setValue(DateUtils.Date2String(semesterDto.getStartAt()));
                    view.binding.endAt.setValue(DateUtils.Date2String(semesterDto.getEndAt()));
                }
                else {
                    Toast.makeText(view, "SERVER ERROR", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SemesterDto> call, Throwable t) {

            }
        });
    }
}
