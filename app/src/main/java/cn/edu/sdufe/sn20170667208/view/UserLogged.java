package cn.edu.sdufe.sn20170667208.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import cn.edu.sdufe.sn20170667208.R;

public class UserLogged extends AppCompatActivity {
    String loggingName;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login_ed);
        Intent intent=getIntent();
        System.out.println("Logging Intent:"+intent);
        loggingName=intent.getStringExtra("transform_username");
        System.out.println("Logged is："+loggingName);
        TextView displayname=(TextView)findViewById(R.id.login_display_name);

        displayname.append(loggingName);
    }
    public void toUserInfoShow(View view){

        Intent intent=new Intent(this,UserInfoShow.class);
        intent.putExtra("transform_username",loggingName);
        startActivity(intent);
    }
    public void backIntoLogging(View view ){
        Intent intent =new Intent(this,UserNotLogin.class);
        intent.putExtra("transform_username",loggingName);
        startActivity(intent);
    }
}
