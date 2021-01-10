package com.sivamalabrothers.sanakirja;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class VeriTabani extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "sozluk";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLO_KELIME = "kelimeler";
    private static final String ROW_ID = "id";
    private static final String ROW_KELIME = "kelime";
    private static final String ROW_KELIME_ANLAM = "kelimeAnlam";
    private static final String ROW_KELIME_CUMLE= "kelimeCumle";


    public VeriTabani(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLO_KELIME + "("
                + ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ROW_KELIME +  " TEXT NOT NULL, "
                + ROW_KELIME_ANLAM + " TEXT NOT NULL, "
                + ROW_KELIME_CUMLE + " TEXT NOT NULL)");
    }





    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public void VeriEkle(String kelime,String kelimeAnlam , String kelimeCumle){
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues cv = new ContentValues();
            cv.put(ROW_KELIME, kelime);
            cv.put(ROW_KELIME_ANLAM, kelimeAnlam);
            cv.put(ROW_KELIME_CUMLE, kelimeCumle);


            db.insert(TABLO_KELIME, null,cv);
        }catch (Exception e){
        }
        db.close();
    }

    public List<String> VeriListele(){
        List<String> veriler = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            String[] stunlar = {
                    ROW_ID,
                    ROW_KELIME,
                    ROW_KELIME_ANLAM,
                    ROW_KELIME_CUMLE


            };
            Cursor cursor = db.query(TABLO_KELIME, stunlar,
                    null,null,null,null,null);
            while (cursor.moveToNext()){

                veriler.add(String.valueOf(cursor.getInt(0)));
                veriler.add(cursor.getString(1));
                veriler.add(cursor.getString(2));
                veriler.add(cursor.getString(3));

            }
        }catch (Exception e){
        }
        db.close();
        return veriler;
    }

    public List<Kelime> kelimeGetir(){
        List<Kelime> veriler = new ArrayList<Kelime>();
        Kelime kelime;
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            String[] stunlar = {
                    ROW_ID,
                    ROW_KELIME,
                    ROW_KELIME_ANLAM,
                    ROW_KELIME_CUMLE


            };
            Cursor cursor = db.query(TABLO_KELIME, stunlar,
                    null,null,null,null,null);
            while (cursor.moveToNext()){
                kelime = new Kelime();
                kelime.setId(String.valueOf(cursor.getInt(0)));
                kelime.setKelime(cursor.getString(1));
                kelime.setAnlam(cursor.getString(2));
                kelime.setCumle(cursor.getString(3));
                veriler.add(kelime);

            }
        }catch (Exception e){
        }
        db.close();
        return veriler;
    }

    public List<String> veriGetir(int id){
        List<String> veriler = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            String[] stunlar = {
                    ROW_ID,
                    ROW_KELIME,
                    ROW_KELIME_ANLAM,
                    ROW_KELIME_CUMLE
                     };


            String where = ROW_ID + " = '" + id+"'" ;
            Cursor cursor = db.query(TABLO_KELIME, stunlar,where,null,null,null,null);
            while (cursor.moveToNext()){
                veriler.add(String.valueOf(cursor.getInt(0)));
                veriler.add(cursor.getString(1));
                veriler.add(cursor.getString(2));
                veriler.add(cursor.getString(3));

            }
        }catch (Exception e){
        }
        db.close();
        return veriler;
    }

    public List<String> veriGetir(String kelime){
        List<String> veriler = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            String[] stunlar = {
                    ROW_ID,
                    ROW_KELIME,
                    ROW_KELIME_ANLAM,
                    ROW_KELIME_CUMLE
            };


            String where = ROW_KELIME + " = '" +kelime+"'" ;
            Cursor cursor = db.query(TABLO_KELIME, stunlar,where,null,null,null,null);
            while (cursor.moveToNext()){
                veriler.add(String.valueOf(cursor.getInt(0)));
                veriler.add(cursor.getString(1));
                veriler.add(cursor.getString(2));
                veriler.add(cursor.getString(3));

            }
        }catch (Exception e){
        }
        db.close();
        return veriler;
    }


    public void VeriSil(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            // id ye göre verimizi siliyoruz
            String where = ROW_ID + " = '" + id +"'" ;
            db.delete(TABLO_KELIME,where,null);
        }catch (Exception e){
        }
        db.close();
    }

    public void VeriSil(String kelime){
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            // kelimeye göre verimizi siliyoruz
            String where = ROW_KELIME + " = '" + kelime +"'" ;
            db.delete(TABLO_KELIME,where,null);
        }catch (Exception e){
        }
        db.close();
    }

    public void VeriDuzenle(String kelime,String kelimeAnlam , String kelimeCumle){
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues cv = new ContentValues();
            cv.put(ROW_KELIME, kelime);
            cv.put(ROW_KELIME_ANLAM, kelimeAnlam);
            cv.put(ROW_KELIME_CUMLE, kelimeCumle);
            String where = ROW_KELIME +" = '"+ kelime + "'";
            db.update(TABLO_KELIME,cv,where,null);
        }catch (Exception e){
        }
        db.close();
    }

    public void VeriDuzenle(String id, String kelime,String kelimeAnlam , String kelimeCumle){
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues cv = new ContentValues();
            cv.put(ROW_KELIME, kelime);
            cv.put(ROW_KELIME_ANLAM, kelimeAnlam);
            cv.put(ROW_KELIME_CUMLE, kelimeCumle);
            String where = ROW_ID +" = '"+ id + "'";
            db.update(TABLO_KELIME,cv,where,null);
        }catch (Exception e){
        }
        db.close();
    }

}