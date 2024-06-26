package com.example.appktx2sv.ui.activities.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appktx2sv.R;
import com.example.appktx2sv.data.dto.UserDto;
import com.example.appktx2sv.databinding.ActivityLoginBinding;
import com.example.appktx2sv.interfaces.IPDM;
import com.example.appktx2sv.ui.activities.signup.ActivitySignup;

public class ActivityLogin extends AppCompatActivity implements IPDM.View {
    ActivityLoginBinding binding;
    String email, password;
    CheckBox chkBox;
    LoginHandler handler  = new LoginHandler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getUserDtoExtra();
        handler.setView(this);
        setControl();
        setEvent();
    }

    @Override
    protected void onResume() {
        docGhiNho();
        super.onResume();
    }

    private void setControl() {
        chkBox = findViewById(R.id.chkLuuDangNhap);
    }

    private void getUserDtoExtra() {
        this.email = getIntent().getStringExtra("email");
        this.password = getIntent().getStringExtra("password");

        if(this.email != null && this.password != null){
            binding.email.setText(this.email);
            binding.password.setText(this.password);
        }
    }

    private void luuDangNhap(){
        SharedPreferences sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("taiKhoan", binding.email.getText().toString());
        editor.putString("matKhau", binding.password.getText().toString());
        editor.apply();
    }
    private void xoaGhiNho(){
        SharedPreferences sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    private void docGhiNho(){
        SharedPreferences sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        String tk = sharedPreferences.getString("taiKhoan", "");
        String mk = sharedPreferences.getString("matKhau", "");
        binding.email.setText(tk);
        binding.password.setText(mk);
    }
    private void setEvent() {

        binding.login.setOnClickListener(v -> {
                if(checkInput()){
                    String email = binding.email.getText();
                    String password = binding.password.getText();
                    handler.login(email, password);
                    if (chkBox.isChecked())
                        luuDangNhap();
                    else
                        xoaGhiNho();
                }
        });

        binding.signup.setOnClickListener(v -> {
            Intent signupIntent = new Intent(this, ActivitySignup.class);
            startActivity(signupIntent);
        });
    }
    boolean checkInput(){
        if(binding.email.getText().equals("")){
            binding.email.setWarning("Invalid email");
            binding.email.requestFocus();
            return false;
        }
        else{
            binding.email.setShowWarning(false);
        }
        if(binding.password.getText().equals("")){
            binding.password.setWarning("Invalid password");
            binding.password.requestFocus();
            return false;
        }
        else{
            binding.password.setShowWarning(false);
        }

        return true;
    }
}
