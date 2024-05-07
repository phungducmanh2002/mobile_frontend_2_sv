package com.example.appktx2sv.ui.activities.room.detail.roomItemList;

import android.content.Intent;
import android.widget.Toast;

import com.example.appktx2sv.R;
import com.example.appktx2sv.data.dto.ItemAddedDto;
import com.example.appktx2sv.data.dto.ItemDto;
import com.example.appktx2sv.data.model.itemCommon.ItemAction;
import com.example.appktx2sv.data.model.itemCommon.ItemDataManyAction;
import com.example.appktx2sv.data.model.itemCommon.ItemResource;
import com.example.appktx2sv.net.RetrofitClient;
import com.example.appktx2sv.net.services.IRoomCollectionService;
import com.example.appktx2sv.net.services.IRoomService;
import com.example.appktx2sv.ui.activities.roomItem.detail.ActivityRoomItemDetail;
import com.example.appktx2sv.ui.dialog.MyDialog;
import com.example.appktx2sv.ui.item.ItemManyAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomDetailItemManagerHandler {
    ActivityRoomDetailItemManager view;
    IRoomService roomService = RetrofitClient.GI().getRetrofit().create(IRoomService.class);
    IRoomCollectionService romCollectionService = RetrofitClient.GI().getRetrofit().create(IRoomCollectionService.class);
    public RoomDetailItemManagerHandler(ActivityRoomDetailItemManager view){
        this.view = view;
    }
    public void getAllItemAdded(Integer idRoom){
        roomService.getAllItemAdded(idRoom).enqueue(new Callback<List<ItemAddedDto>>() {
            @Override
            public void onResponse(Call<List<ItemAddedDto>> call, Response<List<ItemAddedDto>> response) {
                if(response.isSuccessful()){
                    view.binding.myListView.resetView();
                    for (ItemAddedDto itemAddedDto: response.body()) {

                        ItemManyAction itemManyAction = new ItemManyAction(view);
                        itemManyAction.setItemDataManyAction(ItemAdded2ItemDataManyAction(itemAddedDto, idRoom));

                        view.binding.myListView.addChildren(itemManyAction);

                    }
                }
                else{
                    Toast.makeText(view, "SERVER ERROR", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ItemAddedDto>> call, Throwable t) {

            }
        });
    }
    public void getAllItemNoAdded(Integer idRoom){
        roomService.getAllItemNotAdded(idRoom).enqueue(new Callback<List<ItemDto>>() {
            @Override
            public void onResponse(Call<List<ItemDto>> call, Response<List<ItemDto>> response) {
                if(response.isSuccessful()){
                    view.binding.myListView.resetView();
                    for (ItemDto itemAddedDto: response.body()) {

                        ItemManyAction itemManyAction = new ItemManyAction(view);
                        itemManyAction.setItemDataManyAction(ItemNotAdded2ItemDataManyAction(itemAddedDto, idRoom));

                        view.binding.myListView.addChildren(itemManyAction);
                    }
                }
                else{
                    Toast.makeText(view, "SERVER ERROR", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ItemDto>> call, Throwable t) {

            }
        });
    }
    private ItemDataManyAction ItemAdded2ItemDataManyAction(ItemAddedDto itemAddedDto, Integer idRoom){
        List<ItemAction> itemActionList = new ArrayList<>();
        itemActionList.add(CreateItemActionDetail(itemAddedDto.getId(), idRoom));

        Map<String, String> attrs = new HashMap<>();
        attrs.put("Total: ", Integer.toString(itemAddedDto.getQuantity()));
        attrs.put("Quantity: ", Integer.toString(itemAddedDto.getQuantityInRoom()));
        attrs.put("Quantity left: ", Integer.toString(itemAddedDto.getQuantityLeft()));

        ItemDataManyAction itemDataManyAction = new ItemDataManyAction();
        itemDataManyAction.setTitle(itemAddedDto.getItemName());

        if(itemAddedDto.getIdResource() != null){
            itemDataManyAction.setItemResource(
                    ItemResource.GetItemResourceResServer(itemAddedDto.getIdResource())
            );
        }
        else{
            itemDataManyAction.setItemResource(ItemResource.builder()
                    .isLocal(true)
                    .idResource(R.drawable.ic_fan)
                    .build());
        }
        itemDataManyAction.setItemActionList(itemActionList);
        itemDataManyAction.setFields(attrs);
//        itemDataManyAction.setBackgroundDrawable(view.getDrawable(R.drawable.bg_rounded_orange));

        return  itemDataManyAction;
    }
    private ItemDataManyAction ItemNotAdded2ItemDataManyAction(ItemDto itemDto, Integer idRoom){
        List<ItemAction> itemActionList = new ArrayList<>();
        itemActionList.add(CreateItemActionDetail(itemDto.getId(), idRoom));

        Map<String, String> attrs = new HashMap<>();
        attrs.put("Total: ", Integer.toString(itemDto.getQuantity()));
        attrs.put("Quantity left: ", Integer.toString(itemDto.getQuantityLeft()));

        ItemDataManyAction itemDataManyAction = new ItemDataManyAction();
        itemDataManyAction.setTitle(itemDto.getItemName());

        if(itemDto.getIdResource() != null){
            itemDataManyAction.setItemResource(
                    ItemResource.GetItemResourceResServer(itemDto.getIdResource())
            );
        }
        else{
            itemDataManyAction.setItemResource(ItemResource.builder()
                    .isLocal(true)
                    .idResource(R.drawable.ic_fan)
                    .build());
        }
        itemDataManyAction.setItemActionList(itemActionList);
        itemDataManyAction.setFields(attrs);

        return  itemDataManyAction;
    }
    public ItemAction CreateItemActionDetail(Integer idItem, Integer idRoom){
        return ItemAction.builder()
                .background(view.getDrawable(R.drawable.bg_rounded_blue))
                .text("Detail")
                .textColor(view.getColor(R.color.white))
                .storedData(idItem)
                .callBack(objects -> {
                    Intent intentItemDetail = new Intent(view, ActivityRoomItemDetail.class);
                    intentItemDetail.putExtra("idItem", idItem);
                    view.startActivity(intentItemDetail);
//                    Toast.makeText(view, "REMOVE: " + idRoom.toString() + "______" + idItem.toString(), Toast.LENGTH_SHORT).show();
                })
                .build();
    }

}
