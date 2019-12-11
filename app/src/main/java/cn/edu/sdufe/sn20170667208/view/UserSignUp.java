package cn.edu.sdufe.sn20170667208.view;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import cn.edu.sdufe.sn20170667208.DButil.MyDBHelper;
import cn.edu.sdufe.sn20170667208.R;
import cn.edu.sdufe.sn20170667208.dao.UserDao;
import cn.edu.sdufe.sn20170667208.entity.User;
import com.xuexiang.xui.XUI;

public class UserSignUp extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private EditText password_confrim;
    private EditText age;
    private EditText phone_number;
    private EditText address;
    private RadioButton male;
    private RadioButton female;
    private RadioGroup sex_group;
    private String SexName;
    private TextView info;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        XUI.initTheme(this);//调整应用的基础主题
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_sign_up);
        username=(EditText) findViewById(R.id.registe_username);
        password=(EditText)findViewById(R.id.registe_password);
        password_confrim=(EditText)findViewById(R.id.registe_password_confirm);
        age=(EditText)findViewById(R.id.registe_age);
        phone_number=(EditText)findViewById(R.id.registe_phone_number);
        address=(EditText)findViewById(R.id.registe_adddress);
        male=(RadioButton)findViewById(R.id.registe_male);
        female=(RadioButton)findViewById(R.id.registe_female);
        sex_group=(RadioGroup)findViewById(R.id.registe_sex_group);
        sex_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group,  int checkedId) {
                if(male.getId()==checkedId){
                    SexName=male.getText().toString();
                }else if (female.getId()==checkedId){
                    SexName=female.getText().toString();
                }
            }
        });
        info=(TextView)findViewById(R.id.registe_info);
    }
    public void RegisterSubmit (View view){
//        MyDBHelper myDBHelper=new MyDBHelper(this,"Shop.db",null,1);
        UserDao userDao=new UserDao(this,"Shop.db",null,1);
        User user =new User();
        String username_add=username.getText().toString();
        if(password.getText().toString().equals(password_confrim.getText().toString())) {
            user.setName(username_add);
            user.setPassword(password.getText().toString());
            user.setAge(age.getText().toString());
            user.setTelphone(phone_number.getText().toString());
            user.setAddress(address.getText().toString());
            user.setSex(SexName);
            userDao.create(user);
            info.setText("恭喜您，注册成功！");
            Intent intent=new Intent(this,UserNotLogin.class);
            startActivity(intent);
        }else if(password.getText().toString()!=password_confrim.getText().toString()){
            info.setText("两次输入密码不一致！");
        }else if(password.getText().toString()==null||password_confrim.getText().toString()==null){
            info.setText("请输入注册信息！");
        }
    }
    public void returnToNotLogin(View view){
        Intent intent=new Intent(this,UserNotLogin.class);
        startActivity(intent);
    }

}
