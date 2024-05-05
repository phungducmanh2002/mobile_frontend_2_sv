package com.example.appktx2sv.ui.activities.semester.detail.semesterRoomList;

import android.content.Intent;
import android.widget.Toast;

import com.example.appktx2sv.AppKtx;
import com.example.appktx2sv.R;
import com.example.appktx2sv.data.dto.CreateRegisDto;
import com.example.appktx2sv.data.dto.RoomDto;
import com.example.appktx2sv.data.dto.SemesterRoomDto;
import com.example.appktx2sv.data.model.itemCommon.ItemAction;
import com.example.appktx2sv.data.model.itemCommon.ItemDataManyAction;
import com.example.appktx2sv.net.RetrofitClient;
import com.example.appktx2sv.net.services.IRegisService;
import com.example.appktx2sv.net.services.IRoomCollectionService;
import com.example.appktx2sv.net.services.ISemesterService;
import com.example.appktx2sv.ui.activities.room.detail.ActivityRoomDetail;
import com.example.appktx2sv.ui.item.ItemManyAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SemesterDetailRoomManagerHandler {
    ActivitySemesterDetailRoomManager view;
    ISemesterService semesterService = RetrofitClient.GI().getRetrofit().create(ISemesterService.class);
    IRegisService regisService = RetrofitClient.GI().getRetrofit().create(IRegisService.class);
    IRoomCollectionService romCollectionService = RetrofitClient.GI().getRetrofit().create(IRoomCollectionService.class);
    public SemesterDetailRoomManagerHandler(ActivitySemesterDetailRoomManager view){
        this.view = view;
    }
    public void getAllRoomAdded(Integer idSemester){
        semesterService.getAllRoomAdded(idSemester).enqueue(new Callback<List<RoomDto>>() {
            @Override
            public void onResponse(Call<List<RoomDto>> call, Response<List<RoomDto>> response) {
                if(response.isSuccessful()){
                    view.binding.myListView.resetView();
                    for (RoomDto roomDto: response.body()) {

                        ItemManyAction itemManyAction = new ItemManyAction(view);
                        itemManyAction.setItemDataManyAction(RoomAdded2ItemDataManyAction(roomDto, idSemester));

                        view.binding.myListView.addChildren(itemManyAction);
                    }
                }
                else{
                    Toast.makeText(view, "SERVER ERROR", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<RoomDto>> call, Throwable t) {

            }
        });
    }
    private ItemDataManyAction RoomAdded2ItemDataManyAction(RoomDto roomDto, Integer idSemester){
        List<ItemAction> itemActionList = new ArrayList<>();
        if(roomDto.getSlot() > roomDto.getSlotUse()){
            itemActionList.add(CreateItemActionRegister(idSemester, roomDto.getId()));
        }
        itemActionList.add(CreateItemActionDetail(idSemester, roomDto.getId()));

        Map<String, String> attrs = new HashMap<>();
        attrs.put("Gender: ", roomDto.getRoomGender() == 0 ? "Male" : "Female");
        attrs.put("Status: ", roomDto.getRoomStatus() == 0 ? "Active" : "Disable");
        attrs.put("Slot: ", roomDto.getSlot().toString());
        attrs.put("Slot use: ", roomDto.getSlotUse().toString());

        ItemDataManyAction itemDataManyAction = new ItemDataManyAction();
        itemDataManyAction.setTitle(roomDto.getRoomName());

        itemDataManyAction.setItemActionList(itemActionList);
        itemDataManyAction.setFields(attrs);

        return  itemDataManyAction;
    }
    public ItemAction CreateItemActionRegister(Integer idSemester, Integer idRoom){
        return ItemAction.builder()
                .background(view.getDrawable(R.drawable.bg_rounded_green))
                .text("REGISTER")
                .textColor(view.getColor(R.color.white))
                .storedData(idRoom)
                .callBack(objects -> {
                    Integer idUser = AppKtx.userDto.getId();
                    CreateRegisDto createRegisDto = new CreateRegisDto();
                    createRegisDto.setIdUser(idUser);
                    createRegisDto.setIdSemester(idSemester);
                    createRegisDto.setIdRoom(idRoom);

                    regisService.createRegis(createRegisDto).enqueue(new Callback<Object>() {
                        @Override
                        public void onResponse(Call<Object> call, Response<Object> response) {
                            if(response.isSuccessful()){
                                view.onResume();
                            }
                            else{
                                Toast.makeText(view, "Không thể đăng kí phòng nữa", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Object> call, Throwable t) {

                        }
                    });

                })
                .build();
    }
    public ItemAction CreateItemActionDetail(Integer idSemester, Integer idRoom){
        return ItemAction.builder()
                .background(view.getDrawable(R.drawable.bg_rounded_blue))
                .text("Detail")
                .textColor(view.getColor(R.color.white))
                .storedData(idRoom)
                .callBack(objects -> {
                    Intent itentRoomDetail = new Intent(view, ActivityRoomDetail.class);
                    itentRoomDetail.putExtra("idRoom", idRoom);
                    view.startActivity(itentRoomDetail);
//                    Toast.makeText(view, "REMOVE: " + idRoom.toString() + "______" + idItem.toString(), Toast.LENGTH_SHORT).show();
                })
                .build();
    }

}
