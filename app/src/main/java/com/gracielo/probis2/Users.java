package com.gracielo.probis2;

import android.os.Parcel;
import android.os.Parcelable;

public class Users implements Parcelable {
    int id;
    String nama;
    String alamat;
    String jk;
    String nomor;
    String email;
    String password;

    public Users(String nama, String alamat, String jk, String nomor, String email, String password) {
        this.nama = nama;
        this.alamat = alamat;
        this.jk = jk;
        this.nomor = nomor;
        this.email = email;
        this.password = password;
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

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nama);
        dest.writeString(this.alamat);
        dest.writeString(this.jk);
        dest.writeString(this.nomor);
        dest.writeString(this.email);
        dest.writeString(this.password);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readInt();
        this.nama = source.readString();
        this.alamat = source.readString();
        this.jk = source.readString();
        this.nomor = source.readString();
        this.email = source.readString();
        this.password = source.readString();
    }

    public Users() {
    }

    protected Users(Parcel in) {
        this.id = in.readInt();
        this.nama = in.readString();
        this.alamat = in.readString();
        this.jk = in.readString();
        this.nomor = in.readString();
        this.email = in.readString();
        this.password = in.readString();
    }

    public static final Parcelable.Creator<Users> CREATOR = new Parcelable.Creator<Users>() {
        @Override
        public Users createFromParcel(Parcel source) {
            return new Users(source);
        }

        @Override
        public Users[] newArray(int size) {
            return new Users[size];
        }
    };
}
