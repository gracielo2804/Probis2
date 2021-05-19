package com.gracielo.probis2.Pembeli;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.gracielo.probis2.R;
import com.gracielo.probis2.databinding.ActivityTransaksiBinding;

public class TransaksiActivity extends AppCompatActivity {

    Fragment fragment;
    ActivityTransaksiBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityTransaksiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        binding.bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case(R.id.item_cart):
//                        fragment=new AddFragment();
//                        fragment=AddFragment.newInstance();
//                        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
                        return true;
                    case (R.id.item_profile):
//                        fragment=ListFragment.newInstance();
//                        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
                        return true;
                }
                return false;
            }
        });

        if(savedInstanceState==null){
            fragment=HomeTransFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.layout_container,fragment).commit();
        }
    }
}