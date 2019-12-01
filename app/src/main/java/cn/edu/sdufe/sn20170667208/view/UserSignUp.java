package cn.edu.sdufe.sn20170667208.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import cn.edu.sdufe.sn20170667208.R;

public class UserSignUp extends AppCompatActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_sign_up);
    }
    public void RegisterSubmit (View view){
        Intent intent=new Intent(this,UserNotLogin.class);
        startActivity(intent);
    }
    public void returnToNotLogin(View view){
        Intent intent=new Intent(this,UserNotLogin.class);
        startActivity(intent);
    }

}
