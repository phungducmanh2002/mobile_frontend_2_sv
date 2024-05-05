package com.example.appktx2sv.ui.activities.room.detail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appktx2sv.databinding.ActivityRoomDetailBinding;
import com.example.appktx2sv.ui.activities.room.detail.roomItemList.ActivityRoomDetailItemManager;
import com.example.appktx2sv.utils.FileUtils;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.IOException;
import java.io.InputStream;

public class ActivityRoomDetail extends AppCompatActivity {

    RoomDetailHandler handler;
    ActivityRoomDetailBinding binding;
    Integer idRoom = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityRoomDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getExtraIdRoom();
        this.handler =  new RoomDetailHandler(this);
        setEvent();

    }
    private void getExtraIdRoom() {
        idRoom = getIntent().getIntExtra("idRoom", -1);
    }
    @Override
    protected void onResume() {
        super.onResume();
        this.handler.getRoomDetail(idRoom);
    }
    private void setEvent() {
        binding.itemManager.setOnClickListener(v -> {
            Intent intentRoomItemManager = new Intent(this, ActivityRoomDetailItemManager.class);
            intentRoomItemManager.putExtra("idRoom", idRoom);
            startActivity(intentRoomItemManager);
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data == null){
            return;
        }
        Uri uri = data.getData();
        if(uri == null){
            return;
        }
        try {
            InputStream iStream =   getContentResolver().openInputStream(uri);
            byte[] avatarData = FileUtils.GetBytes(iStream);
            handler.onSelectAvatarResponse(avatarData, idRoom);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
