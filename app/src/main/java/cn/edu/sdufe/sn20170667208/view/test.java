package cn.edu.sdufe.sn20170667208.view;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import cn.edu.sdufe.sn20170667208.DButil.MyDBHelper;
import cn.edu.sdufe.sn20170667208.R;
import cn.edu.sdufe.sn20170667208.dao.UserDao;

public class test extends AppCompatActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
    }
//    public void test(View view){
//        String loginName_db;
//        String loginPassword_db;
//        MyDBHelper myDBHelper=new MyDBHelper(this,"Shop.db",null,1);
//        SQLiteDatabase database=myDBHelper.getReadableDatabase();
//        try{
//           // Cursor cursor=database.query("goods",new String[]{"goodsid","title"},null,null,null,null,null);
//            Cursor cursor=database.rawQuery("select * from goods where goodsid=?",new String[]{"1"});
////            while(cursor.moveToNext()){
////                int index0=cursor.getColumnIndex("username");
////                loginName_db=cursor.getString(index0);
////                int index=cursor.getColumnIndex("password");
////                loginPassword_db=cursor.getString(index);
////                Log.i("User","--------->username:"+loginName_db+"---password:"+loginPassword_db);
////            }
//            Log.i("count", String.valueOf(cursor.getCount()));
//            cursor.close();
//        }catch(Exception e){
//        }finally{
//            database.close();
//        }
//    }
    public void test(View view ){
        UserDao userDao=new UserDao(this,"Shop.db",null,1);
        userDao.delete();

    }
}
