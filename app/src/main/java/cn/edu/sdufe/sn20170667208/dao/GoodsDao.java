package cn.edu.sdufe.sn20170667208.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;
import cn.edu.sdufe.sn20170667208.DButil.MyDBHelper;
import cn.edu.sdufe.sn20170667208.entity.Goods;

import java.util.ArrayList;
import java.util.List;

public class GoodsDao {
    private Context context;

    public GoodsDao(Context context){

        this.context=context;
    }

    public void addGoods(){
        MyDBHelper myDBHelper=new MyDBHelper(context, "Shop.db", null, 1);
    SQLiteDatabase sqLiteDatabase=myDBHelper.getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try {
        ContentValues contentValues=new ContentValues();
        //插入数据
            contentValues.put("title","德运全脂牛奶 1L*10");
            contentValues.put("introduce","澳大利亚 进口牛奶 整箱装");
            contentValues.put("price","99");
            contentValues.put("photo","https://img11.360buyimg.com/n7/jfs/t3112/329/5142322648/303045/23f34e14/58649978N2ca539d6.jpg");
            contentValues.put("type","食品区");
            sqLiteDatabase.insert("goods","",contentValues);
            contentValues.clear();
            contentValues.put("title","精制猪肉铺 200g/袋");
            contentValues.put("introduce","休闲零食 靖江猪肉脯");
            contentValues.put("price","16.9");
            contentValues.put("photo","https://img12.360buyimg.com/n7/jfs/t2782/118/3406783312/150099/a3c976c6/578deaedNf62f3e74.jpg");
            contentValues.put("type","食品区");
            sqLiteDatabase.insert("goods","",contentValues);
            contentValues.clear();
            contentValues.put("title","百味草 年货牛肉干 100g/袋");
            contentValues.put("introduce","肉类零食 五香牛肉粒 肉制品");
            contentValues.put("price","14.8");
            contentValues.put("photo","https://img11.360buyimg.com/n7/jfs/t3298/119/241707660/330921/bd52264f/57ac3177Ne80e8da3.jpg");
            contentValues.put("type","食品区");
            sqLiteDatabase.insert("goods","",contentValues);
            contentValues.clear();
            contentValues.put("title","HMFMQHM垃圾桶");
            contentValues.put("introduce"," 生活日用品垃圾桶 圆形塑料垃圾桶 稻金款 大号");
            contentValues.put("price","8.4");
            contentValues.put("photo","https://img11.360buyimg.com/n7/jfs/t3298/119/241707660/330921/bd52264f/57ac3177Ne80e8da3.jpg");
            contentValues.put("type","日用品");
            sqLiteDatabase.insert("goods","",contentValues);
            contentValues.clear();
            contentValues.put("title","一口米 情侣漱口杯套装");
            contentValues.put("introduce","一口米 韩国时尚简约家居日用品百货北欧简约风情侣漱口杯套装 塑料旅行刷牙杯子 牙刷架牙具座");
            contentValues.put("price","28");
            contentValues.put("photo","https://img11.360buyimg.com/n7/jfs/t3298/119/241707660/330921/bd52264f/57ac3177Ne80e8da3.jpg");
            contentValues.put("type","日用品");
            sqLiteDatabase.insert("goods","",contentValues);
            contentValues.clear();

        sqLiteDatabase.setTransactionSuccessful();

    }catch (Exception e){

        e.printStackTrace();
    }finally {
        sqLiteDatabase.endTransaction();
        sqLiteDatabase.close();
    }

}
    /*
     根据商品名查商品
     */
    public List<Goods> queryByName(String name){
        List goodlist=new ArrayList();//定义一个集合存储商品对象
        Goods goods=null;
        MyDBHelper myDbOpenHelper=new MyDBHelper(context, "Shop.db", null, 1);
        SQLiteDatabase sqLiteDatabase=myDbOpenHelper.getWritableDatabase();
        try{
            Cursor cursor=sqLiteDatabase.query("goods",new String[]{"title","introduce","price","photo","type"},"title like ?",new String[]{"%"+name+"%"},null,null,null);
            while (cursor.moveToNext()){
                String title=cursor.getString(cursor.getColumnIndex("title"));
                String introduce=cursor.getString(cursor.getColumnIndex("introduce"));
                double price=cursor.getDouble(cursor.getColumnIndex("price"));
                String photo=cursor.getString(cursor.getColumnIndex("photo"));
                String type=cursor.getString(cursor.getColumnIndex("type"));
                goods=new Goods(title,introduce,price,photo,type);
                goodlist.add(goods);
            }
            cursor.close();
        }catch (Exception e){
            Toast.makeText(context,"查询错误",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }finally {

            sqLiteDatabase.close();
        }
        return goodlist;
    }
    /*
    根据商品类型查询
     */
    public List<Goods> queryByType(String typename){
        List goodlist=new ArrayList();//定义一个集合存储商品对象
        Goods goods=null;
        MyDBHelper myDbOpenHelper=new MyDBHelper(context, "Shop.db", null, 1);
        SQLiteDatabase sqLiteDatabase=myDbOpenHelper.getWritableDatabase();
        try{
            Cursor cursor=sqLiteDatabase.query("goods",new String[]{"title","introduce","price","photo","type"},"type=?",new String[]{typename},null,null,null);
            while (cursor.moveToNext()){
                String title=cursor.getString(cursor.getColumnIndex("title"));
                String introduce=cursor.getString(cursor.getColumnIndex("introduce"));
                double price=cursor.getDouble(cursor.getColumnIndex("price"));
                String photo=cursor.getString(cursor.getColumnIndex("photo"));
                String type=cursor.getString(cursor.getColumnIndex("type"));
                goods=new Goods(title,introduce,price,photo,type);
                goodlist.add(goods);
            }
            cursor.close();
        }catch (Exception e){
            Toast.makeText(context,"查询错误",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }finally {

            sqLiteDatabase.close();
        }
        return goodlist;
    }
    /*
    根据id查询商品
     */
    public Goods queryById(int id){
        Goods goods=null;
        MyDBHelper myDbOpenHelper=new MyDBHelper(context, "Shop.db", null, 1);
        SQLiteDatabase sqLiteDatabase=myDbOpenHelper.getWritableDatabase();
        try{
            Cursor cursor=sqLiteDatabase.query("goods",new String[]{"title","introduce","price","photo","type"},"goodsid=?",new String[]{String.valueOf(id)},null,null,null);
            while (cursor.moveToNext()){
                String title=cursor.getString(cursor.getColumnIndex("title"));
                String introduce=cursor.getString(cursor.getColumnIndex("introduce"));
                double price=cursor.getDouble(cursor.getColumnIndex("price"));
                String photo=cursor.getString(cursor.getColumnIndex("photo"));
                String type=cursor.getString(cursor.getColumnIndex("type"));
                goods=new Goods(title,introduce,price,photo,type);
            }
            cursor.close();
        }catch (Exception e){
            Toast.makeText(context,"查询错误",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }finally {

            sqLiteDatabase.close();
        }
        return goods;
    }
    public int delete(){
        int count=0;
        MyDBHelper myDbOpenHelper=new MyDBHelper(context, "Shop.db", null, 1);
        SQLiteDatabase sqLiteDatabase=myDbOpenHelper.getWritableDatabase();
        try {
            count=sqLiteDatabase.delete("goods",null,null);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqLiteDatabase.close();
        }
        return count;
    }
}
