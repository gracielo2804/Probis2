package com.gracielo.probis2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gracielo.probis2.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.etNoTelpLogin.getText().toString().trim().equalsIgnoreCase("")) {
                    binding.etNoTelpLogin.setError("Field ini Harus diisi");
                    binding.etNoTelpLogin.requestFocus();
                }
                if (binding.etPassLogin.getText().toString().isEmpty()) {
                    binding.etPassLogin.setError("Field ini Harus diisi");
                    binding.etPassLogin.requestFocus();
                }
            }
        });
    }
}