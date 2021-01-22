package com.example.rensposi.Database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "db_supermarket";
    public static final String TABLE_ADMIN = "tb_user";
    public static final String COL_EMAIL = "username";
    public static final String COL_PASSWORD = "password";
    public static final String COL_NAME = "name";
    public static final String TABLE_PEGAWAI = "tb_user";
    public static final String COL_IDPEGAWAI = "username";
    public static final String COL_PASS = "password";
    public static final String COL_NAMA = "name";
    public static final String TABLE_STOK = "tb_book";
    public static final String COL_CEK = "id_book";
    public static final String COL_ID = "id";
    public static final String COL_BARANG = "barang";
    public static final String COL_TANGGAL = "tanggal";
    public static final String COL_STOK = "stok";
    public static final String TABLE_hasil = "tb_hasil";
    public static final String COL_TOTAL_BARANG = "total_Barang";

    private SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("PRAGMA foreign_keys=ON");
        db.execSQL("create table " + TABLE_ADMIN + " (" + COL_EMAIL + " TEXT PRIMARY KEY, " + COL_PASSWORD +
                " TEXT, " + COL_NAME + " TEXT)");
        db.execSQL("create table " + TABLE_PEGAWAI + " (" + COL_IDPEGAWAI + " TEXT PRIMARY KEY, " + COL_PASS +
                " TEXT, " + COL_NAME + " TEXT)");
        db.execSQL("create table " + TABLE_STOK + " (" + COL_CEK + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_ID + " TEXT, " + COL_BARANG + " TEXT" + ", " + COL_TANGGAL + " TEXT, " + COL_STOK);
        db.execSQL("create table " + TABLE_hasil + " (" + COL_EMAIL + " TEXT, " + COL_CEK + " INTEGER, TEXT,  " + COL_TOTAL_BARANG +
                " TEXT, FOREIGN KEY(" + COL_EMAIL + ") REFERENCES " + TABLE_ADMIN
                + ", FOREIGN KEY(" + COL_CEK + ") REFERENCES " + TABLE_STOK + ")");
        db.execSQL("insert into " + TABLE_ADMIN + " values ('rayhanirambey@gmail.com','hani','Raihani Rambe');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMIN);
        onCreate(db);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public boolean Register(String username, String password, String name) throws SQLException {

        @SuppressLint("Recycle") Cursor mCursor = db.rawQuery("INSERT INTO " + TABLE_ADMIN + "(" + COL_EMAIL + ", " + COL_PASSWORD + ", " + COL_NAME + ") VALUES (?,?,?)", new String[]{username, password, name});
        if (mCursor != null) {
            return mCursor.getCount() > 0;
        }
        return false;
    }

    public boolean Login(String username, String password) throws SQLException {
        @SuppressLint("Recycle") Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_ADMIN + " WHERE " + COL_EMAIL + "=? AND " + COL_PASSWORD + "=?", new String[]{username, password});
        if (mCursor != null) {
            return mCursor.getCount() > 0;
        }
        return false;
    }

}
