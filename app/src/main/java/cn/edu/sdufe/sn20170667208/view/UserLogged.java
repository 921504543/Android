package cn.edu.sdufe.sn20170667208.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import cn.edu.sdufe.sn20170667208.R;
import com.xuexiang.xui.XUI;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

public class UserLogged extends AppCompatActivity {
    String loggingName;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        XUI.initTheme(this);//调整应用的基础主题
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login_ed);
        Intent intent=getIntent();
        System.out.println("Logging Intent:"+intent);
        loggingName=intent.getStringExtra("transform_username");
        System.out.println("Logged is："+loggingName);
        SuperTextView displayname=(SuperTextView)findViewById(R.id.login_display_name);
        displayname.setLeftString(loggingName);
    }
    public void toUserInfoShow(View view){

        Intent intent=new Intent(this,UserInfoShow.class);
        intent.putExtra("transform_username",loggingName);
        startActivity(intent);
    }
    public void toRecently(View view){
        Intent intent=new Intent(this,UserRecently.class);
        startActivity(intent);
    }
    public void backIntoLogging(View view ){
        Intent intent =new Intent(this,UserNotLogin.class);
        intent.putExtra("transform_username",loggingName);
        startActivity(intent);
    }
    public void toHome(View view){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void toCar(View view){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void toWeather(View view){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void toUser(View view){
        Intent intent=new Intent(this,UserNotLogin.class);
        startActivity(intent);
    }
}
