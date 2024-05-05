package com.example.appktx2sv.ui.activities.semester.manager;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appktx2sv.data.dto.SemesterDto;
import com.example.appktx2sv.databinding.ActivitySemesterManagerBinding;
import com.example.appktx2sv.ui.dialog.MyDialog;

public class ActivitySemesterManager extends AppCompatActivity {

    SemesterManagerHandler handler;
    ActivitySemesterManagerBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySemesterManagerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.handler =  new SemesterManagerHandler(this);
        setEvent();
    }
    @Override
    protected void onResume() {
        super.onResume();
        this.handler.getAllSemester();
    }
    private void setEvent() {
    }
}
