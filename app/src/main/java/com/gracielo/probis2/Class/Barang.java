package com.gracielo.probis2.Class;

import android.os.Parcel;
import android.os.Parcelable;

public class Barang implements Parcelable {
    int id;
    String nama;
    String harga;
    String desc;
    String satuan;
    String linkGambar;
    String idKategori;

    public Barang(int id, String nama, String harga, String desc, String satuan, String linkGambar,String idKategori) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.desc = desc;
        this.satuan = satuan;
        this.linkGambar = linkGambar;
        this.idKategori=idKategori;
    }

    public String getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(String idKategori) {
        this.idKategori = idKategori;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public String getLinkGambar() {
        return linkGambar;
    }

    public void setLinkGambar(String linkGambar) {
        this.linkGambar = linkGambar;
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readInt();
        this.nama = source.readString();
        this.harga = source.readString();
        this.desc = source.readString();
        this.satuan = source.readString();
        this.linkGambar = source.readString();
        this.idKategori=source.readString();
    }

    public Barang() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nama);
        dest.writeString(this.harga);
        dest.writeString(this.desc);
        dest.writeString(this.satuan);
        dest.writeString(this.linkGambar);
        dest.writeString(this.idKategori);
    }


    protected Barang(Parcel in) {
        this.id = in.readInt();
        this.nama = in.readString();
        this.harga = in.readString();
        this.desc = in.readString();
        this.satuan = in.readString();
        this.linkGambar = in.readString();
        this.idKategori = in.readString();
    }

    public static final Creator<Barang> CREATOR = new Creator<Barang>() {
        @Override
        public Barang createFromParcel(Parcel source) {
            return new Barang(source);
        }

        @Override
        public Barang[] newArray(int size) {
            return new Barang[size];
        }
    };
}
