package cn.edu.sdufe.sn20170667208.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cn.edu.sdufe.sn20170667208.DButil.MyDBHelper;
import cn.edu.sdufe.sn20170667208.entity.Recently;

import java.util.ArrayList;
import java.util.List;

public class RecentlyDao {
    private Context context;
    public RecentlyDao(Context context){
        this.context=context;
    }
    //添加足迹
    public void  inertIntoRecently(Recently recently){
        MyDBHelper myDbOpenHelper=new MyDBHelper(context, "Shop.db", null, 1);
        SQLiteDatabase sqLiteDatabase=myDbOpenHelper.getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try{
            ContentValues contentValues=new ContentValues();
            contentValues.put("title",recently.getTitle());
            contentValues.put("photo",recently.getPhoto());
            contentValues.put("price",String.valueOf(recently.getPrice()));
            sqLiteDatabase.insert("recently","",contentValues);
            contentValues.clear();
            sqLiteDatabase.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqLiteDatabase.endTransaction();
            sqLiteDatabase.close();
        }
    }
    //查看足迹
    public List queryOrders(){
        MyDBHelper myDbOpenHelper=new MyDBHelper(context, "Shop.db", null, 1);
        SQLiteDatabase sqLiteDatabase1=myDbOpenHelper.getWritableDatabase();
        List recentlyList=new ArrayList();//保存订单中所有的商品
        try{
            Cursor cursor=sqLiteDatabase1.query("recently",new String[]{"id","title","photo","price"},null,null,null,null,null);
            while (cursor.moveToNext()){
                int id=cursor.getInt(cursor.getColumnIndex("id"));
                String title=cursor.getString(cursor.getColumnIndex("title"));
                String photo=cursor.getString(cursor.getColumnIndex("photo"));
                double price=cursor.getDouble(cursor.getColumnIndex("price"));
                Recently recently=new Recently(photo,price,title);
                recentlyList.add(recently);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqLiteDatabase1.close();
        }
        return recentlyList;
    }
    public int delete(){
        int count=0;
        MyDBHelper myDbOpenHelper=new MyDBHelper(context, "Shop.db", null, 1);
        SQLiteDatabase sqLiteDatabase=myDbOpenHelper.getWritableDatabase();
        try {
            count=sqLiteDatabase.delete("recently",null,null);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqLiteDatabase.close();
        }
        return count;
    }

}
