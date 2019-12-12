package com.example.bledos.model;

public class SignUpModel {

    private String nama;

    private String alamat;

    private String tgl_lahir;

    private String nohp;

    private int level;

    private String image;

    private String kategori_level;

    public SignUpModel(String nama, String alamat, String tgl_lahir, String nohp, int level, String image, String kategori_level) {
        this.nama = nama;
        this.alamat = alamat;
        this.tgl_lahir = tgl_lahir;
        this.nohp = nohp;
        this.level = level;
        this.image = image;
        this.kategori_level = kategori_level;
    }
}
