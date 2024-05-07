package com.example.appktx2sv.ui.activities.signup;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appktx2sv.data.dto.UserDto;
import com.example.appktx2sv.databinding.ActivitySemesterManagerBinding;
import com.example.appktx2sv.databinding.ActivitySignupBinding;
import com.example.appktx2sv.interfaces.IPDM;
import com.example.appktx2sv.ui.activities.semester.manager.SemesterManagerHandler;

public class ActivitySignup extends AppCompatActivity implements IPDM.View {
    SignupHandler handler;
    ActivitySignupBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.handler =  new SignupHandler(this);
        setEvent();
    }
    public void setEvent(){
        binding.btnRegistation.setOnClickListener(v -> {
            if(checkInput()){
                UserDto userDto = new UserDto();
                userDto.setFirstName(binding.inputFirstName.getText());
                userDto.setLastName(binding.inputLastName.getText());
                userDto.setEmail(binding.inputEmail.getText());
                userDto.setPassword(binding.inputPassword.getText());

                Toast.makeText(ActivitySignup.this, userDto.toString(), Toast.LENGTH_SHORT).show();
                handler.register(userDto);
            }
        });
    }
    public boolean checkInput(){
        if(binding.inputFirstName.getText().equals("")){
            binding.inputFirstName.setWarning("Vui lòng không bỏ trống trường này");
            binding.inputFirstName.requestForcus();
            return false;
        }
        else{
            binding.inputFirstName.setShowWarning(false);
        }

        if(binding.inputLastName.getText().equals("")){
            binding.inputLastName.setWarning("Vui lòng không bỏ trống trường này");
            binding.inputLastName.requestForcus();
            return false;
        }
        else{
            binding.inputLastName.setShowWarning(false);
        }

        if(binding.inputEmail.getText().equals("")){
            binding.inputEmail.setWarning("Vui lòng không bỏ trống trường này");
            binding.inputEmail.requestForcus();
            return false;
        }
        else{
            binding.inputEmail.setShowWarning(false);
        }


        if(binding.inputPassword.getText().equals("")){
            binding.inputPassword.setWarning("Vui lòng không bỏ trống trường này");
            binding.inputPassword.requestForcus();
            return false;
        }
        else{
            binding.inputPassword.setShowWarning(false);
        }

        if(!binding.inputRepassword.getText().equals(binding.inputPassword.getText())){
            binding.inputRepassword.setWarning("Mật khẩu không khớp");
            binding.inputRepassword.requestForcus();
            return false;
        }
        else{
            binding.inputRepassword.setShowWarning(false);
        }

        return  true;
    }
}
