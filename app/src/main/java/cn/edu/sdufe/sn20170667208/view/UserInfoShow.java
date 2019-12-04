package cn.edu.sdufe.sn20170667208.view;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import cn.edu.sdufe.sn20170667208.DButil.MyDBHelper;
import cn.edu.sdufe.sn20170667208.R;
import com.xuexiang.xui.XUI;

public class UserInfoShow extends AppCompatActivity {
    private Context context;
    private TextView Info_username;
    private TextView Info_password;
    private TextView Info_sex;
    private TextView Info_age;
    private TextView Info_phonenumber;
    private TextView Info_address;
    String loggingName;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        XUI.initTheme(this);//调整应用的基础主题
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_show);
        Intent intent1=getIntent();
        loggingName=intent1.getStringExtra("transform_username");
        System.out.println("ShowUserInfologgingName:" +intent1+"aaaa:"+intent1.getStringExtra("username"));
        Info_username=(TextView) findViewById(R.id.info_username);
        Info_password=(TextView) findViewById(R.id.info_password);
        Info_sex=(TextView) findViewById(R.id.info_sex);
        Info_age=(TextView) findViewById(R.id.info_age);
        Info_phonenumber=(TextView) findViewById(R.id.info_phone_number);
        Info_address=(TextView) findViewById(R.id.info_address);
        MyDBHelper myDBHelper=new MyDBHelper(this,"Shop.db",null,1);
        SQLiteDatabase sqLiteDatabase=myDBHelper.getReadableDatabase();

//        Log.i("ShowUserInfologgingName",loggingName);
        try{

            Cursor cursor=sqLiteDatabase.query("user",new String[]{"username","password","sex","age","phonenumber","address"},"username=?",new String[]{loggingName},null,null,null);
            Log.i("cusor Count", String.valueOf(cursor.getCount()));
            while(cursor.moveToNext()){
                int index0=cursor.getColumnIndex("username");
                String Info_cursor_username=cursor.getString(index0);
                int index=cursor.getColumnIndex("password");
                String Info_cursor_password=cursor.getString(index);
                int index1=cursor.getColumnIndex("sex");
                String Info_cursor_sex=cursor.getString(index1);
                int index2=cursor.getColumnIndex("age");
                String Info_cursor_age=cursor.getString(index2);
                int index3=cursor.getColumnIndex("phonenumber");
                String Info_cursor_phonenumber=cursor.getString(index3);
                int index4=cursor.getColumnIndex("address");
                String Info_cursor_address=cursor.getString(index4);
                Info_username.setText(Info_cursor_username);
                Info_password.setText(Info_cursor_password);
                Info_sex.setText(Info_cursor_sex);
                Info_age.setText(Info_cursor_age);
                Info_phonenumber.setText(Info_cursor_phonenumber);
                Info_address.setText(Info_cursor_address);
            }
            cursor.close();
        }catch(Exception e){
        }finally{
            sqLiteDatabase.close();
        }
    }

    public void toUserInfoChange(View view){
        Intent intent=new Intent(this,UserInfoChange.class);
        intent.putExtra("transform_username",loggingName);
        startActivity(intent);
    }
    public void toUserLogged(View view){
        Intent intent =new Intent(this,UserLogged.class);
        intent.putExtra("transform_username",loggingName);
        startActivity(intent);
    }
}
