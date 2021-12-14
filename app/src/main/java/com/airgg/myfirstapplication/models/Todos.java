package com.airgg.myfirstapplication.models;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Todos implements Serializable {
    Integer id;
    String judul, konten, tglstart, tglend;

    public static final String TABLE_NAME = "tbl_todo";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_JUDUL = "judul";
    public static final String COLUMN_KONTEN = "konten";
    public static final String COLUMN_TGLSTART = "tglstart";
    public static final String COLUMN_TGLEND = "tglend";

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_JUDUL + " VARCHAR(50),"
                    + COLUMN_KONTEN + " VARCHAR(255),"
                    + COLUMN_TGLSTART + " VARCHAR(16),"
                    + COLUMN_TGLEND + " VARCHAR(16)"
                    + ")";

    public Todos() {}

    public Todos(Integer id, String judul, String konten, String tglstart, String tglend) {
        this.id = id;
        this.judul = judul;
        this.konten = konten;
        this.tglstart = tglstart;
        this.tglend = tglend;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getKonten() {
        return konten;
    }

    public void setKonten(String konten) {
        this.konten = konten;
    }

    public String getTglstart() {
        return tglstart;
    }

    public void setTglstart(String tglstart) {
        this.tglstart = tglstart;
    }

    public String getTglend() {
        return tglend;
    }

    public void setTglend(String tglend) {
        this.tglend = tglend;
    }
}
