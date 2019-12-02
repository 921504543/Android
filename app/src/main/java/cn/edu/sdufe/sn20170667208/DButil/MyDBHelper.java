package cn.edu.sdufe.sn20170667208.DButil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Parcelable;
import android.util.Log;

public class MyDBHelper extends SQLiteOpenHelper {
//    final String CREATE_TABLE_USER_SQL="create table user (_id integer primary key autoincrement,password text)";
    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, version);
    }




    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        sqLiteDatabase.execSQL(CREATE_TABLE_USER_SQL);
        sqLiteDatabase.execSQL("create table user(id integer primary key autoincrement,username text,password text,age text,sex text,phonenumber text,address text)");
        //创建商品列表
        sqLiteDatabase.execSQL("create table goods(goodsid integer primary key autoincrement,title text,introduce text,price text,photo text,type text)");
        //创建购物车表
        sqLiteDatabase.execSQL("create table shoppingcar(id integer primary key autoincrement,title text,photo text,number text,price text)");
        //创建订单表
        sqLiteDatabase.execSQL("create table orders(id integer primary key autoincrement,title text,photo text,price text)");
        //创建足迹表
        sqLiteDatabase.execSQL("create table recently(id integer primary key autoincrement,title text,photo text,price text)");

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.i("测试数据库更新","version update----"+oldVersion+"------>"+newVersion);

    }
}
