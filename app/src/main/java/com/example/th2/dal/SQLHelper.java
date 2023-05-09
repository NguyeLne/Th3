package com.example.th2.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.th2.model.Item;

import java.util.ArrayList;
import java.util.List;

public class SQLHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "CongViec.db";
    private static int DATABASE_VERSION = 1;

    public SQLHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE tasks("+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "name TEXT,  date TEXT, nganhhoc TEXT, active TEXT, hocphi DOUBLE)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public long addItem(Item i){
        ContentValues values = new ContentValues();
        values.put("name", i.getName());
        values.put("date", i.getDate());
        values.put("nganhhoc",i.getNganh());
        values.put("active", i.getActive());
        values.put("hocphi", i.getHocphi());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("tasks", null, values);
    }
    public List<Item> getAll(){
        List list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String order = "date DESC";
        Cursor rs = st.query("tasks", null, null, null, null, null, order);
        while(rs != null && rs.moveToNext()){
            int id = rs.getInt(0);
            String name = rs.getString(1);
            String date = rs.getString(2);
            String nganhhoc = rs.getString(3);
            String active = rs.getString(4);
            Double hocphi =Double.parseDouble(rs.getString(5));

            list.add(new Item(id,name, date, nganhhoc, active, hocphi));

        }
        return list;
    }
//    public List<Item> getByDate(String date){
//        List<Item> list = new ArrayList<>();
//        String whereClause = "date like ?";
//        String[] whereArgs ={date};
//        SQLiteDatabase st = getReadableDatabase();
//        Cursor rs = st.query("tasks", null, whereClause, whereArgs, null, null, null);
//        while(rs != null && rs.moveToNext()){
//            int id = rs.getInt(0);
//            String name = rs.getString(1);
//            String desc = rs.getString(2);
//            String date1 = rs.getString(3);
//            String tinhtrang = rs.getString(4);
//            String congtac = rs.getString(5);
//            String congtacStr="";
//            if(congtac.equals("true")){
//                congtacStr = "lam chung";
//            }else{
//                congtacStr = "1 minh";
//            }
//            list.add(new Item(id,name, desc, date1, tinhtrang, congtacStr));
//
//        }
//        return list;
//    }
    public int update(Item i){
        ContentValues values = new ContentValues();
        values.put("name", i.getName());
        values.put("date", i.getDate());
        values.put("nganhhoc",i.getNganh());
        values.put("active", i.getActive());
        values.put("hocphi", i.getHocphi());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String whereClause = "ID = ?";
        String[] whereArgs ={String.valueOf(i.getId())};
        System.out.println("Update Item" + String.valueOf(i.getId()));
        return sqLiteDatabase.update("tasks", values, whereClause, whereArgs);
    }
    //delete
    public int delete(int id){
        String whereClause = "ID = ?";
        String[] whereArgs = {String.valueOf(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        System.out.println("Delete Item" + String.valueOf(id));
        return sqLiteDatabase.delete("tasks", whereClause, whereArgs);
    }
//    public List<Item> searchByDateFromTo(String from, String to){
//        List<Item> list = new ArrayList<>();
//        String whereClause = "date between ? AND ?";
//        String[] whereArgs ={from.trim(), to.trim()};
//        SQLiteDatabase st = getReadableDatabase();
//        Cursor rs = st.query("tasks", null, whereClause, whereArgs, null, null, null);
//        while(rs != null && rs.moveToNext()){
//            int id = rs.getInt(0);
//            String name = rs.getString(1);
//            String desc = rs.getString(2);
//            String date1 = rs.getString(3);
//            String tinhtrang = rs.getString(4);
//            String congtac = rs.getString(5);
//            String congtacStr="";
//            if(congtac.equals("true")){
//                congtacStr = "lam chung";
//            }else{
//                congtacStr = "1 minh";
//            }
//            list.add(new Item(id,name, desc, date1, tinhtrang, congtacStr));
//
//        }
//        return list;
//    }
//    public List<Item> searchByTinhTrang(String tt){
//
//
//        List<Item> list = new ArrayList<>();
//        String whereClause = "tinhtrang like ?";
//        String[] whereArgs ={tt};
//        SQLiteDatabase st = getReadableDatabase();
//        Cursor rs = st.query("tasks", null, whereClause, whereArgs, null, null, null);
//        while(rs != null && rs.moveToNext()){
//            int id = rs.getInt(0);
//            String name = rs.getString(1);
//            String desc = rs.getString(2);
//            String date1 = rs.getString(3);
//            String tinhtrang = rs.getString(4);
//            String congtac = rs.getString(5);
//            String congtacStr="";
//            if(congtac.equals("true")){
//                congtacStr = "lam chung";
//            }else{
//                congtacStr = "1 minh";
//            }
//            list.add(new Item(id,name, desc, date1, tinhtrang, congtacStr));
//
//        }
//        return list;
//    }
//    public List<Item> searchByCongTac(String ct){
//        if(ct.equals("1 minh")){
//            ct="false";
//        }else
//            ct="true";
//        List<Item> list = new ArrayList<>();
//        String whereClause = "congtac = ?";
//        String[] whereArgs ={ct};
//        SQLiteDatabase st = getReadableDatabase();
//        Cursor rs = st.query("tasks", null, whereClause, whereArgs, null, null, null);
//        while(rs != null && rs.moveToNext()){
//            int id = rs.getInt(0);
//            String name = rs.getString(1);
//            String desc = rs.getString(2);
//            String date1 = rs.getString(3);
//            String tinhtrang = rs.getString(4);
//            String congtac = rs.getString(5);
//            String congtacStr="";
//            if(congtac.equals("true")){
//                congtacStr = "lam chung";
//            }else{
//                congtacStr = "1 minh";
//            }
//            list.add(new Item(id,name, desc, date1, tinhtrang, congtacStr));
//
//        }
//        return list;
//    }
//    public List<Item> searchByName(String key){
//        List<Item> list = new ArrayList<>();
//        String whereClause = "name like ?";
//        String[] whereArgs ={"%" + key + "%"};
//        SQLiteDatabase st = getReadableDatabase();
//        Cursor rs = st.query("tasks", null, whereClause, whereArgs, null, null, null);
//        while(rs != null && rs.moveToNext()){
//            int id = rs.getInt(0);
//            String name = rs.getString(1);
//            String desc = rs.getString(2);
//            String date1 = rs.getString(3);
//            String tinhtrang = rs.getString(4);
//            String congtac = rs.getString(5);
//            String congtacStr="";
//            if(congtac.equals("true")){
//                congtacStr = "lam chung";
//            }else{
//                congtacStr = "1 minh";
//            }
//            list.add(new Item(id,name, desc, date1, tinhtrang, congtacStr));
//
//        }
//        return list;
//    }
}
