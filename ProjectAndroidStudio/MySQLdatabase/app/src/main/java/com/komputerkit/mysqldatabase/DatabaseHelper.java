package com.komputerkit.mysqldatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Nama database dan versi
    public static final String DATABASE_NAME = "dbtoko.db";
    public static final int DATABASE_VERSION = 1;

    // Nama tabel dan kolom
    public static final String TABLE_NAME = "tblbarang";
    public static final String COL_ID = "ID_barang";
    public static final String COL_NAMA = "nama_barang";
    public static final String COL_STOK = "stok";
    public static final String COL_HARGA = "harga";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Membuat tabel saat database pertama kali dibuat
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAMA + " TEXT NOT NULL, " +
                COL_STOK + " INTEGER DEFAULT 0, " +
                COL_HARGA + " REAL DEFAULT 0.0);";
        db.execSQL(createTable);
    }

    // Menangani upgrade database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Menjalankan perintah SQL langsung (misal: INSERT manual) dengan penanganan error
    public boolean runSQL(String sql) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.execSQL(sql);
            return true;
        } catch (android.database.SQLException e) {
            return false;
        }
    }

    // Menambah data barang baru
    public boolean tambahBarang(String nama, int stok, double harga) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAMA, nama);
        contentValues.put(COL_STOK, stok);
        contentValues.put(COL_HARGA, harga);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    /**
     * Mengambil semua data barang dari tabel. Caller WAJIB menutup cursor setelah selesai.
     * Jangan tutup database di sini, biarkan Android yang mengelola lifecycle database.
     */
    public Cursor lihatSemuaBarang() {
        return this.getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    // Memperbarui data barang berdasarkan ID
    public boolean updateBarang(String id, String nama, int stok, double harga) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAMA, nama);
        contentValues.put(COL_STOK, stok);
        contentValues.put(COL_HARGA, harga);
        int result = db.update(TABLE_NAME, contentValues, COL_ID + " = ?", new String[]{id});
        return result > 0;
    }

    // Menghapus data barang berdasarkan ID
    public Integer hapusBarang(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COL_ID + " = ?", new String[]{id});
    }
}
