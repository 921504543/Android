package cn.edu.sdufe.sn20170667208.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import cn.edu.sdufe.sn20170667208.R;

public class UserLogged extends AppCompatActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login_ed);
        Intent intent=getIntent();
        String loggingName=intent.getStringExtra("transform_username");
        TextView displayname=(TextView)findViewById(R.id.login_display_name);
        displayname.append(loggingName);
    }
    public void toUserInfoShow(View view){
        Intent intent=new Intent(this,UserInfoShow.class);
        startActivity(intent);
    }
    public void backIntoLogging(View view ){
        Intent intent =new Intent(this,UserNotLogin.class);
    }
}
