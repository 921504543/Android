package cn.edu.sdufe.sn20170667208.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import cn.edu.sdufe.sn20170667208.DButil.MyDBHelper;
import cn.edu.sdufe.sn20170667208.entity.User;

public class UserDao {
    private MyDBHelper myDbHelper;
    public UserDao(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        myDbHelper=new MyDBHelper(context, name, null, version);
    }
    public User select(User user ,String loggingName){
        SQLiteDatabase sqLiteDatabase=myDbHelper.getWritableDatabase();
        try{
            Cursor cursor=sqLiteDatabase.query("user",new String[]{"username","password","sex","age","phonenumber","address"},"username=?",new String[]{loggingName},null,null,null);

            while(cursor.moveToNext()){
                int index0=cursor.getColumnIndex("username");
                user.setName(cursor.getString(index0));
                int index=cursor.getColumnIndex("password");
                user.setPassword(cursor.getString(index));
                int index1=cursor.getColumnIndex("sex");
                user.setSex(cursor.getString(index1));
                int index2=cursor.getColumnIndex("age");
                user.setAge(cursor.getString(index2));
                int index3=cursor.getColumnIndex("phonenumber");
                user.setTelphone(cursor.getString(index3));
                int index4=cursor.getColumnIndex("address");
                user.setAddress(cursor.getString(index4));
            }
            cursor.close();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            sqLiteDatabase.close();
        }
        return user;
    }
    public int update(User user){
        int count=0;
        SQLiteDatabase sqLiteDatabase= myDbHelper.getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try{
            ContentValues contentValues=new ContentValues();
            contentValues.put("password",user.getPassword());
            contentValues.put("age",user.getAge());
            contentValues.put("phonenumber",user.getTelphone());
            contentValues.put("address",user.getAddress());
            contentValues.put("sex",user.getSex());
           count =sqLiteDatabase.update("user",contentValues,"username=?",new String[]{user.getName()});
            sqLiteDatabase.setTransactionSuccessful();
        }catch(Exception e){
        e.printStackTrace();
        }finally {
            sqLiteDatabase.endTransaction();
            sqLiteDatabase.close();
        }
        return count;
    }
    public int create(User user){
        int count =0;
        SQLiteDatabase sqLiteDatabase= myDbHelper.getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("username", user.getName());
            contentValues.put("password", user.getPassword());
            contentValues.put("age", user.getAge());
            contentValues.put("sex", user.getSex());
            contentValues.put("phonenumber", user.getTelphone());
            contentValues.put("address", user.getAddress());
            sqLiteDatabase.insert("user", "", contentValues);
            sqLiteDatabase.setTransactionSuccessful();
        }catch (Exception e){

        }finally {
            sqLiteDatabase.endTransaction();
            sqLiteDatabase.close();
        }
        return count;
    }
//    public void delete(){
//        SQLiteDatabase db=myDbHelper.getWritableDatabase();
////        db.delete("user", null, null);
//        Log.i("open", String.valueOf(db.isOpen()));
//        String path = db.getPath();
//        Log.i("path",path);
//        boolean readOnly = db.isReadOnly();
//        Log.i("isreadonly", String.valueOf(readOnly));
//        db.execSQL("delete  from user");
//        db.close();
//    }
}
