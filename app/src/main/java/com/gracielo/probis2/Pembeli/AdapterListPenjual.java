package com.gracielo.probis2.Pembeli;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gracielo.probis2.Class.Users;
import com.gracielo.probis2.Penjual.ListBarangAdapter;
import com.gracielo.probis2.databinding.ItemBarangLayoutBinding;
import com.gracielo.probis2.databinding.ItemCardPenjualBinding;

import java.util.ArrayList;

public class AdapterListPenjual extends RecyclerView.Adapter<AdapterListPenjual.ViewHolder> {

    ArrayList<Users>listUser=new ArrayList<>();

    public AdapterListPenjual(ArrayList<Users> listUser) {
        this.listUser = listUser;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemCardPenjualBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Users user = listUser.get(position);
        holder.binding.txtNamaPenjual.setText(user.getNama());
        holder.binding.txtNomorPenjual.setText(user.getNomor());
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemCardPenjualBinding binding;
        public ViewHolder(ItemCardPenjualBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
