package com.example.appktx2sv.ui.activities.semester.detail;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appktx2sv.databinding.ActivitySemesterDetailBinding;
import com.example.appktx2sv.ui.activities.semester.detail.semesterRoomList.ActivitySemesterDetailRoomManager;

public class ActivitySemesterDetail extends AppCompatActivity {
    SemesterDetailHandler handler;
    ActivitySemesterDetailBinding binding;
    Integer idSemester = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySemesterDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getExtraIdRoom();
        this.handler =  new SemesterDetailHandler(this);
        setEvent();

    }
    private void getExtraIdRoom() {
        idSemester = getIntent().getIntExtra("idSemester", -1);
    }
    @Override
    protected void onResume() {
        super.onResume();
        this.handler.getSemesterDetail(idSemester);
    }
    private void setEvent() {
        binding.roomManager.setOnClickListener(v -> {
            Intent intentSemeterDetailRoomManager = new Intent(this, ActivitySemesterDetailRoomManager.class);
            intentSemeterDetailRoomManager.putExtra("idSemester", idSemester);
            startActivity(intentSemeterDetailRoomManager);
        });
    }
}
