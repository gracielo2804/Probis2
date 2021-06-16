package com.gracielo.probis2.Pembeli;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gracielo.probis2.Class.Barang;
import com.gracielo.probis2.databinding.ItemBarangCheckoutBinding;
import com.gracielo.probis2.databinding.ItemBarangPenjualTocartBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ListBarangCheckoutAdapter extends RecyclerView.Adapter<ListBarangCheckoutAdapter.ViewHolder> {
    ArrayList<Barang> listBarang;
    public ListBarangCheckoutAdapter(ArrayList<Barang>listBarang) {
        this.listBarang=listBarang;
    }
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemBarangCheckoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ListBarangCheckoutAdapter.ViewHolder holder, int position) {
        Barang barang=listBarang.get(position);
        holder.binding.txtNamaBarang.setText(barang.getNama());
        holder.binding.txtNamaBarang2.setText(" /"+barang.getSatuan());
        holder.binding.txtnamatoko.setText("Wira");
    }

    @Override
    public int getItemCount() {
        return listBarang.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemBarangCheckoutBinding binding;
        public ViewHolder(ItemBarangCheckoutBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
