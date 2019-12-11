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
import cn.edu.sdufe.sn20170667208.dao.UserDao;
import cn.edu.sdufe.sn20170667208.entity.User;
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
        User user=new User();
        UserDao userDao=new UserDao(this,"Shop.db",null,1);
//        MyDBHelper myDBHelper=new MyDBHelper(this,"Shop.db",null,1);
        userDao.select(user,loggingName);
        username.setText(user.getName());
        password.setText(user.getPassword());
//                Info_sex.setText(Info_cursor_sex);
        age.setText(user.getAge());
        phonenumber.setText(user.getTelphone());
        address.setText(user.getAddress());
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
        UserDao userDao=new UserDao(this,"Shop.db",null,1);
        User user=new User();
//        MyDBHelper myDBHelper=new MyDBHelper(this,"Shop.db",null,1);
        user.setName(username.getText().toString());
        user.setPassword(password.getText().toString());
        user.setAge(age.getText().toString());
        user.setAddress(address.getText().toString());
        user.setTelphone(phonenumber.getText().toString());
        user.setSex(SexName);
        int count=userDao.update(user);
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
