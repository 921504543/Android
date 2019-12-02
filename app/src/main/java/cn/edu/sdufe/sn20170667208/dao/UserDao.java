package cn.edu.sdufe.sn20170667208.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import cn.edu.sdufe.sn20170667208.DButil.MyDBHelper;

public class UserDao {
    private MyDBHelper myDbHelper;
    public UserDao(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        myDbHelper=new MyDBHelper(context, name, null, version);
    }
    public void delete(){
        SQLiteDatabase db=myDbHelper.getWritableDatabase();
        db.delete("user", null, null);
        db.close();
    }
}
