package com.example.appktx2sv.ui.activities.activeAccount;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appktx2sv.databinding.ActivityActiveAccountBinding;
import com.example.appktx2sv.databinding.ActivityBillManagerBinding;
import com.example.appktx2sv.interfaces.IPDM;
import com.example.appktx2sv.ui.activities.bill.manager.BillManagerHandler;
import com.example.appktx2sv.ui.activities.login.ActivityLogin;

public class ActivityActiveAccount extends AppCompatActivity implements IPDM.View {
    ActivityActiveAccountBinding binding;
    ActiveAccountHandler handler;
    long distanceTimeSendCode = 10000l;
    boolean canResendCode = false;
    CountDownTimer countDownTimer;
    String email, password;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityActiveAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getEmailExtra();
        this.handler =  new ActiveAccountHandler(this);
        setEvent();
    }
    @Override
    protected void onResume() {
        super.onResume();
        resendCode();
    }
    private void getEmailExtra() {
        this.email = getIntent().getStringExtra("email");
        this.password = getIntent().getStringExtra("password");

        if(this.email == null || this.password == null){
            Toast.makeText(this, "KHONG TRUYEN DU DU LIEU", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    private void setEvent() {

        binding.active.setEnabled(false);

        binding.number1.setAfterTextChanged(object -> {
            if(checkInput()){
                binding.active.setEnabled(true);
                binding.number2.requestFocus();
            }
            else{
                binding.active.setEnabled(false);
            }
        });

        binding.number2.setAfterTextChanged((obj) -> {
            if(checkInput()){
                binding.active.setEnabled(true);
                binding.number3.requestFocus();
            }
            else{
                binding.active.setEnabled(false);
            }
        });

        binding.number3.setAfterTextChanged((obj) -> {
            if(checkInput()){
                binding.active.setEnabled(true);
                binding.number4.requestFocus();
            }
            else{
                binding.active.setEnabled(false);
            }
        });

        binding.number4.setAfterTextChanged((obj) -> {
            if(checkInput()){
                binding.active.setEnabled(true);
                binding.number5.requestFocus();
            }
            else{
                binding.active.setEnabled(false);
            }
        });

        binding.number5.setAfterTextChanged((obj) -> {
//            Toast.makeText(this, Boolean.toString(checkInput()), Toast.LENGTH_SHORT).show();
            if(checkInput()){
                binding.active.setEnabled(true);
                binding.active.requestFocus();
            }
            else{
                binding.active.setEnabled(false);
            }
        });

        binding.active.setOnClickListener(v -> {
            String code = getInputCode();
            this.handler.activeAccount(email, code);
        });

        binding.resendCode.setOnClickListener(v -> {
            if(canResendCode){
                resendCode();
            }
        });
    }
    private boolean checkInput(){
        if(binding.number1.getText().equals("")){
            binding.number1.requestForcus();
            return false;
        }
        if(binding.number2.getText().equals("")){
            binding.number2.requestForcus();
            return false;
        }
        if(binding.number3.getText().equals("")){
            binding.number3.requestForcus();
            return false;
        }
        if(binding.number4.getText().equals("")){
            binding.number4.requestForcus();
            return false;
        }
        if(binding.number5.getText().equals("")){
            binding.number5.requestForcus();
            return false;
        }
        return true;
    }
    private String getInputCode(){
        StringBuilder code = new StringBuilder();
        code.append(binding.number1.getText());
        code.append(binding.number2.getText());
        code.append(binding.number3.getText());
        code.append(binding.number4.getText());
        code.append(binding.number5.getText());
        return code.toString();
    }
    private void countDownLeftTimeResendCode(){
        canResendCode = false;
        binding.resendCode.setTextColor(Color.BLACK);
        countDownTimer = new CountDownTimer(distanceTimeSendCode, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                binding.resendCode.setText(String.format("Gửi lại mã (%ds)", (millisUntilFinished/1000)));
            }

            @Override
            public void onFinish() {
                binding.resendCode.setText("Gửi lại mã");
                binding.resendCode.setTextColor(Color.BLUE);
                canResendCode = true;
            }
        }.start();
    }
    private void resendCode() {
        handler.resendActiveCode(email);
        binding.number1.setText("");
        binding.number2.setText("");
        binding.number3.setText("");
        binding.number4.setText("");
        binding.number5.setText("");
        binding.active.setEnabled(false);

        binding.number1.requestForcus();
        countDownLeftTimeResendCode();
    }
}
