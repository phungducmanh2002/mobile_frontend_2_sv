package com.example.appktx2sv.ui.activities.room.detail.roomItemList;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appktx2sv.R;
import com.example.appktx2sv.databinding.ActivityRoomDetailItemManagerBinding;

public class ActivityRoomDetailItemManager extends AppCompatActivity {

    RoomDetailItemManagerHandler handler;
    ActivityRoomDetailItemManagerBinding binding;
    Integer idRoom = 1;
    boolean isShowItemAdded = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityRoomDetailItemManagerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getExtraIdRoom();
        this.handler =  new RoomDetailItemManagerHandler(this);
        setEvent();

    }
    private void getExtraIdRoom() {
        idRoom = getIntent().getIntExtra("idRoom", -1);
    }
    @Override
    protected void onResume() {
        super.onResume();
        handler.getAllItemAdded(idRoom);
    }
    private void setEvent() {


    }
}
