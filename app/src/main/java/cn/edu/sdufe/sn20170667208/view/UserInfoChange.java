package cn.edu.sdufe.sn20170667208.view;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import cn.edu.sdufe.sn20170667208.DButil.MyDBHelper;
import cn.edu.sdufe.sn20170667208.R;
import com.xuexiang.xui.XUI;

public class UserInfoChange extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private EditText age;
    private EditText phonenumber;
    private EditText address;
    private RadioButton male;
    private RadioButton female;
    private RadioGroup sex_group;
    private String SexName;
    String loggingName;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        XUI.initTheme(this);//调整应用的基础主题
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_change);
        Intent intent=getIntent();
        loggingName=intent.getStringExtra("transform_username");
        username=(EditText) findViewById(R.id.change_info_username);
        password=(EditText) findViewById(R.id.change_info_password);
        age=(EditText) findViewById(R.id.change_info_age);
        phonenumber=(EditText) findViewById(R.id.change_info_phonenumber);
        address=(EditText) findViewById(R.id.change_info_address);
        male=(RadioButton) findViewById(R.id.change_info_male);
        female=(RadioButton) findViewById(R.id.change_info_female);
        sex_group=(RadioGroup)findViewById(R.id.change_info_sex_group);
        MyDBHelper myDBHelper=new MyDBHelper(this,"Shop.db",null,1);
        SQLiteDatabase sqLiteDatabase=myDBHelper.getReadableDatabase();
        try{

            Cursor cursor=sqLiteDatabase.query("user",new String[]{"username","password","sex","age","phonenumber","address"},"username=?",new String[]{loggingName},null,null,null);

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
                username.setText(Info_cursor_username);
                password.setText(Info_cursor_password);
//                Info_sex.setText(Info_cursor_sex);
                age.setText(Info_cursor_age);
                phonenumber.setText(Info_cursor_phonenumber);
                address.setText(Info_cursor_address);
            }
            cursor.close();
        }catch(Exception e){
        }finally{
            sqLiteDatabase.close();
        }
        sex_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(male.getId()==checkedId){
                    SexName=male.getText().toString();
                }else if(female.getId()==checkedId){
                    SexName=female.getText().toString();
                }
            }
        });
    }
    public void changUserInfo(View view){
        TextView output=(TextView) findViewById(R.id.change_info_output);
        MyDBHelper myDBHelper=new MyDBHelper(this,"Shop.db",null,1);
        SQLiteDatabase sqLiteDatabase=myDBHelper.getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try{
            ContentValues contentValues=new ContentValues();
            contentValues.put("password",password.getText().toString());
            contentValues.put("age",age.getText().toString());
            contentValues.put("phonenumber",phonenumber.getText().toString());
            contentValues.put("address",address.getText().toString());
            contentValues.put("sex",SexName);
            sqLiteDatabase.update("user",contentValues,"username=?",new String[]{username.getText().toString()});
            sqLiteDatabase.setTransactionSuccessful();
        }catch(Exception e){

        }finally {
            sqLiteDatabase.endTransaction();
            sqLiteDatabase.close();
        }
        output.setText("修改信息成功！");

        Intent intent=new Intent(this,UserInfoShow.class);
        intent.putExtra("transform_username",loggingName);
        startActivity(intent);
    }
    public void toUserInfoShow(View view){
        Intent intent=new Intent(this,UserInfoShow.class);
        intent.putExtra("transform_username",loggingName);
        startActivity(intent);
    }
}
