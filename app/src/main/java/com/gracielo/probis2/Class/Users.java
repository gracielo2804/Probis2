package com.gracielo.probis2.Class;

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
    int role;

    public Users(String nama, String alamat, String jk, String nomor, String email, String password, int role) {
        this.nama = nama;
        this.alamat = alamat;
        this.jk = jk;
        this.nomor = nomor;
        this.email = email;
        this.password = password;
        this.role= role;
    }

    public int getRole() {
        return role;
    }
    public String getRoleString() {
        return String.valueOf(role);
    }

    public void setRole(int role) {
        this.role = role;
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

    public Users() {
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
        dest.writeInt(this.role);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readInt();
        this.nama = source.readString();
        this.alamat = source.readString();
        this.jk = source.readString();
        this.nomor = source.readString();
        this.email = source.readString();
        this.password = source.readString();
        this.role = source.readInt();
    }

    protected Users(Parcel in) {
        this.id = in.readInt();
        this.nama = in.readString();
        this.alamat = in.readString();
        this.jk = in.readString();
        this.nomor = in.readString();
        this.email = in.readString();
        this.password = in.readString();
        this.role = in.readInt();
    }

    public static final Creator<Users> CREATOR = new Creator<Users>() {
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
