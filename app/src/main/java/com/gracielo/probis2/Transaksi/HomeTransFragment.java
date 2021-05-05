package com.gracielo.probis2.Transaksi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gracielo.probis2.R;
import com.gracielo.probis2.databinding.FragmentHomeTransBinding;

public class HomeTransFragment extends Fragment {

    FragmentHomeTransBinding binding;

    public HomeTransFragment() {

    }

    public static HomeTransFragment newInstance() {
        HomeTransFragment fragment = new HomeTransFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentHomeTransBinding.inflate(inflater,container,false);
        View view=binding.getRoot();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding=null;
    }
}