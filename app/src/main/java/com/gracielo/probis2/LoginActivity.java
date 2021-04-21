package com.gracielo.probis2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gracielo.probis2.databinding.ActivityMainBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);
    }
}