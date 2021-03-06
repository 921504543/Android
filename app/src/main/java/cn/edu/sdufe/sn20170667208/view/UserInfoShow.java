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
import cn.edu.sdufe.sn20170667208.dao.UserDao;
import cn.edu.sdufe.sn20170667208.entity.User;
import com.xuexiang.xui.XUI;

public class UserInfoShow extends AppCompatActivity {
    private Context context;
    private TextView usernameView;
    private TextView passwordView;
    private TextView sexView;
    private TextView ageView;
    private TextView phoneView;
    private TextView addressView;
    String loggingName;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        XUI.initTheme(this);//调整应用的基础主题
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_show);
        Intent intent1=getIntent();
        loggingName=intent1.getStringExtra("transform_username");
        System.out.println("ShowUserInfologgingName:" +intent1+"aaaa:"+intent1.getStringExtra("username"));
        usernameView=(TextView) findViewById(R.id.info_username);
        passwordView=(TextView) findViewById(R.id.info_password);
        sexView=(TextView) findViewById(R.id.info_sex);
        ageView=(TextView) findViewById(R.id.info_age);
        phoneView=(TextView) findViewById(R.id.info_phone_number);
        addressView=(TextView) findViewById(R.id.info_address);
        User user=new User();
        UserDao userDao=new UserDao(this,"Shop.db",null,1);
//        MyDBHelper myDBHelper=new MyDBHelper(this,"Shop.db",null,1);
        userDao.select(user,loggingName);
        usernameView.setText(user.getName());
        passwordView.setText(user.getPassword());
        sexView.setText(user.getSex());
        ageView.setText(user.getAge());
        phoneView.setText(user.getTelphone());
        addressView.setText(user.getAddress());
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
