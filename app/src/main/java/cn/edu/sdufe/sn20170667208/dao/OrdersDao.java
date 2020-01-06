package cn.edu.sdufe.sn20170667208.dao;

import cn.edu.sdufe.sn20170667208.DButil.MyDBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cn.edu.sdufe.sn20170667208.entity.Orders;

import java.util.ArrayList;
import java.util.List;

public class OrdersDao {
    private Context context;
    public OrdersDao(Context context){

        this.context=context;
    }
    //插入到订单表中
    public void  insertIntoOrders(Orders orders){
        MyDBHelper myDbOpenHelper=new MyDBHelper(context, "Shop.db", null, 1);
        SQLiteDatabase sqLiteDatabase=myDbOpenHelper.getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try{
            ContentValues contentValues=new ContentValues();
            contentValues.put("title",orders.getTitle());
            contentValues.put("photo",orders.getPhoto());
            contentValues.put("price",String.valueOf(orders.getPrice()));
            sqLiteDatabase.insert("orders","",contentValues);
            contentValues.clear();
            sqLiteDatabase.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqLiteDatabase.endTransaction();
            sqLiteDatabase.close();
        }
    }


    //查询数据
    public List queryOrders(){
        MyDBHelper myDbOpenHelper=new MyDBHelper(context, "Shop.db", null, 1);
        SQLiteDatabase sqLiteDatabase1=myDbOpenHelper.getWritableDatabase();
        List orderList=new ArrayList();     //保存订单中所有的商品
        try{
            Cursor cursor=sqLiteDatabase1.query("orders",new String[]{"id","title","photo","price"},null,null,null,null,null);
            while (cursor.moveToNext()){
                int id=cursor.getInt(cursor.getColumnIndex("id"));
                String title=cursor.getString(cursor.getColumnIndex("title"));
                String photo=cursor.getString(cursor.getColumnIndex("photo"));
                double price=cursor.getDouble(cursor.getColumnIndex("price"));
                Orders orders=new Orders(photo,price,title);
                orderList.add(orders);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqLiteDatabase1.close();
        }
        return orderList;
    }

}
