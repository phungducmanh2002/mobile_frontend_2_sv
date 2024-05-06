package com.example.appktx2sv.ui.activities.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appktx2sv.data.dto.UserDto;
import com.example.appktx2sv.databinding.ActivityLoginBinding;
import com.example.appktx2sv.interfaces.IPDM;
import com.example.appktx2sv.ui.activities.signup.ActivitySignup;

public class ActivityLogin extends AppCompatActivity implements IPDM.View {
    ActivityLoginBinding binding;
    String email, password;
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
        setEvent();
    }
    private void getUserDtoExtra() {
        this.email = getIntent().getStringExtra("email");
        this.password = getIntent().getStringExtra("password");

        if(this.email != null && this.password != null){
            binding.email.setText(this.email);
            binding.password.setText(this.password);
        }
    }
    private void setEvent() {
        binding.login.setOnClickListener(v -> {
                if(checkInput()){
                    String email = binding.email.getText();
                    String password = binding.password.getText();
                    handler.login(email, password);
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
